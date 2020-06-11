package cn.happyloves.ali.tools.oss;

import cn.happyloves.ali.tools.oss.properties.OssProperties;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public class OssUtils {
    /**
     * 操作OSS存储空间
     */
    public static class OssBucket {
        private static final String SUCCESS = "success";
        private static final String FAILED = "failed";

        /**
         * 创建新的存储空间
         *
         * @param ossClient  OSS客户端
         * @param bucketName bucketName
         * @return 返回值
         */
        public static String createBucket(OSSClient ossClient, String bucketName) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            // 设置存储空间的权限为公共读，默认是私有。
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
            createBucketRequest.setStorageClass(StorageClass.IA);
            try {
                ossClient.createBucket(createBucketRequest);
                return SUCCESS;
            } catch (OSSException e) {
                log.error("error:{}", e.getMessage());
                return FAILED;
            }
        }

        /**
         * 列举存储空间
         *
         * @param ossClient    OSS客户端
         * @param bucketPrefix 前缀
         * @return 存储空间集合
         */
        public static List<Bucket> listBucket(OSSClient ossClient, String bucketPrefix) {
            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            // 列举指定前缀的存储空间。
            if (StringUtils.isNotBlank(bucketPrefix)) {
                listBucketsRequest.setPrefix(bucketPrefix);
            }
            try {
                return ossClient.listBuckets(listBucketsRequest).getBucketList();
            } catch (OSSException e) {
                log.error("error:{}", e.getMessage());
                return new ArrayList<>();
            }
        }

        /**
         * 获取存储空间信息
         *
         * @param ossClient  OSS客户端
         * @param bucketName 存储空间名称
         * @return 存储空间信息
         */
        public static Map<String, Object> getBucketInfo(OSSClient ossClient, String bucketName) {
            //判断空间是否存在
            boolean exists = ossClient.doesBucketExist(bucketName);
            if (!exists) {
                return new HashMap<>(1);
            }
            // 存储空间的信息包括地域（Region或Location）、创建日期（CreationDate）、拥有者（Owner）、权限（Grants）等。
            Map<String, Object> result = new HashMap<>(4);
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            // 获取地域
            result.put("location", info.getBucket().getLocation());
            // 获取创建日期
            result.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:ss").format(info.getBucket().getCreationDate()));
            // 获取拥有者信息
            result.put("owner", info.getBucket().getOwner());
            // 获取权限信息
            AccessControlList acl = ossClient.getBucketAcl(bucketName);
            result.put("authority", acl.toString());
            return result;
        }

        /**
         * 删除存储空间
         *
         * @param ossClient  OSS客户端
         * @param bucketName 存储空间名称
         * @return 是否删除成功
         */
        public static boolean deleteBucket(OSSClient ossClient, String bucketName) {
            //判断空间是否存在
            boolean exists = ossClient.doesBucketExist(bucketName);
            if (!exists) {
                return false;
            }
            // 删除存储空间
            ossClient.deleteBucket(bucketName);
            return true;
        }
    }

    /**
     * 操作OSS文件
     */
    public static class OssUpload {
        /**
         * listFiles 列举文件
         * 列举文件。 prefix，则列举存储空间下所有的文件。反之prefix，则列举包含指定前缀的文件。
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param prefix        前缀
         * @return 列举文件集合
         */
        public static List<String> listFiles(OSSClient ossClient, OssProperties ossProperties, String prefix) {
            ObjectListing objectListing = ossClient.listObjects(ossProperties.getBucketName(), prefix);
            List<OSSObjectSummary> ossObjectSummaries = objectListing.getObjectSummaries();
            List<String> keys = new ArrayList<>();
            for (OSSObjectSummary ossObjectSummary : ossObjectSummaries) {
                log.info("key name:{}", ossObjectSummary.getKey());
                keys.add(ossObjectSummary.getKey());
            }
            return keys;
        }

        /**
         * DeleteSpecifiedPrefixFile 删除指定前缀文件
         * 列举所有包含指定前缀的文件并删除
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param prefix        前缀
         * @return 返回值
         */
        public static List<String> deleteSpecifiedPrefixFile(OSSClient ossClient, OssProperties ossProperties, String prefix) {
            String bucketName = ossProperties.getBucketName();
            String nextMarker = null;
            List<String> keys = new ArrayList<>();
            ObjectListing objectListing = null;
            do {
                ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName)
                        .withPrefix(prefix)
                        .withMarker(nextMarker);
                objectListing = ossClient.listObjects(listObjectsRequest);
                if (objectListing.getObjectSummaries().size() > 0) {
                    for (OSSObjectSummary ossObjectSummary : objectListing.getObjectSummaries()) {
                        log.info("key name:{}", ossObjectSummary.getKey());
                        keys.add(ossObjectSummary.getKey());
                    }
                    List<String> files = new ArrayList<>(keys.size());
                    keys.forEach(item -> files.add(ossProperties.getHome().concat("/").concat(item)));
                    DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(files);
                    ossClient.deleteObjects(deleteObjectsRequest);
                }
                nextMarker = objectListing.getNextMarker();
            } while (objectListing.isTruncated());
            return keys;
        }

        /**
         * batchDeleteFile 批量删除文件
         * 删除文件。key等同于ObjectName，表示删除OSS文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param keys          key等同于ObjectName
         * @return 返回值
         */
        public static List<String> batchDeleteFile(OSSClient ossClient, OssProperties ossProperties, List<String> keys) {
            List<String> files = new ArrayList<>(keys.size());
            keys.forEach(item -> files.add(ossProperties.getHome().concat("/").concat(item)));
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossProperties.getBucketName()).withKeys(files));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            return deletedObjects;
        }

        /**
         * deleteFile 单个文件删除
         * 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param objectName    文件夹名称
         */
        public static void deleteFile(OSSClient ossClient, OssProperties ossProperties, String objectName) {
            ossClient.deleteObject(ossProperties.getBucketName(), ossProperties.getHome().concat("/").concat(objectName));
        }


        /**
         * 检查文件是否存在
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @return 返回值
         */
        public static boolean checkFileWhetherExist(OSSClient ossClient, OssProperties ossProperties, String fileName) {
            return ossClient.doesObjectExist(ossProperties.getBucketName(), ossProperties.getHome().concat("/").concat(fileName));
        }

        /**
         * filePath上传
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @param filePath      文件路径
         * @return 返回值
         * @throws Exception 异常
         */
        public static String uploadByFilePath(OSSClient ossClient, OssProperties ossProperties, String fileName, String filePath) throws Exception {
            return upload(ossClient, ossProperties, fileName, new FileInputStream(filePath));
        }

        /**
         * url上传
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @param url           url
         * @return 返回值
         * @throws Exception 异常
         */
        public static String uploadByUrl(OSSClient ossClient, OssProperties ossProperties, String fileName, String url) throws Exception {
            return upload(ossClient, ossProperties, fileName, new URL(url).openStream());
        }

        /**
         * 上传临时文件（设置过期时间不能访问，文件还是存在）
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @param inputStream   文件输入流
         * @param expireTime    超时时间
         * @return 返回值
         * @throws Exception 异常
         */
        public static String upload(OSSClient ossClient, OssProperties ossProperties, String fileName, InputStream inputStream, long expireTime) throws Exception {
            String home = ossProperties.getHome();
            String cdn = ossProperties.getCdn();
            String bucketName = ossProperties.getBucketName();
            String endpoint = ossProperties.getEndpoint();
            fileName = home.concat("/").concat(fileName);
            boolean whetherIsExist = checkFileWhetherExist(ossClient, ossProperties, fileName);
            if (whetherIsExist) {
                throw new RuntimeException("the file is exist!!!");
            }
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectAcl(CannedAccessControlList.PublicReadWrite);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
            Date expire = new Date(System.currentTimeMillis() + expireTime * 1000);
            ossClient.putObject(request);
            inputStream.close();
            String url = ossClient.generatePresignedUrl(bucketName, fileName, expire).toString();
            log.info("url:{}", url);
            if (StringUtils.isNotBlank(cdn)) {
                return ossClient.generatePresignedUrl(bucketName, fileName, expire).toString().replaceFirst(bucketName.concat(".").concat(endpoint), ossProperties.getCdn());
            } else {
                return url;
            }
        }


        /**
         * 默认上传
         *
         * @param ossClient     OSS客户端
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @param inputStream   文件输入流
         * @return 返回值
         * @throws Exception 异常
         */
        public static String upload(OSSClient ossClient, OssProperties ossProperties, String fileName, InputStream inputStream) throws Exception {
            String home = ossProperties.getHome();
            String bucketName = ossProperties.getBucketName();
            boolean whetherIsExist = checkFileWhetherExist(ossClient, ossProperties, fileName);
            if (whetherIsExist) {
                throw new RuntimeException("the file is exist!!!");
            }
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectAcl(CannedAccessControlList.PublicReadWrite);
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, home.concat("/").concat(fileName), inputStream, metadata);
            inputStream.close();
            String url = generateUrl(ossProperties, fileName);
            log.info("url:{}", url);
            return url;
        }

        /**
         * 生成地址(如果cdn为空则返回oss域名)
         * oss:https://bucketNmae.oss-cn-beijing.aliyuncs.com/upload/xxx.xxx
         * cdn:https://xxx.xxx.com/upload/xxx.xxx
         *
         * @param ossProperties OSS配置属性
         * @param fileName      文件名称
         * @return 返回值
         */
        private static String generateUrl(OssProperties ossProperties, String fileName) {
            String cdn = ossProperties.getCdn();
            String home = ossProperties.getHome();
            if (StringUtils.isNotBlank(cdn)) {
                return String.format("%s://%s/%s/%s", ossProperties.getHTTPProtocol(), cdn, home, fileName);
            } else {
                return String.format("%s://%s.%s/%s/%s", ossProperties.getHTTPProtocol(), ossProperties.getBucketName(), ossProperties.getEndpoint(), home, fileName);
            }
        }

    }
}
