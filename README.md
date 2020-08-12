# ali-tools
ali-tools目前支持阿里云OSS文件上传、删除文件、批量删除、查看文件列表

后续支持SMS服务，敬请期待。。。

使用教程：

第一步：添加依赖
```pom
<dependency>
    <groupId>cn.happyloves</groupId>
    <artifactId>ali-tools</artifactId>
    <version>${ali.tools.version}</version>
</dependency>
```
第二步：配置属性
```yaml
ali-tools:
  oss:
    accessKeyId: ${accessKeyId}
    accessKeySecret: ${accessKeySecret}
    endpoint: oss-cn-shanghai.aliyuncs.com
    bucketName: hopefunbak
#    HTTPProtocol: https #默认为http
#    home: 默认为:upload
# 示例：
#oss (https://bucketNmae.oss-cn-beijing.aliyuncs.com/upload/xxx.xxx)
#cdn (https://xxx.xxx.com/upload/xxx.xxx)
#    cdn: 不传则为oss域名，反之为cdn
```
第三步：添加注解
```java
@EnableAliToolsOss
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```
第四步：如何使用
```java
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private OSSClient ossClient;
    @Autowired
    private OssProperties ossProperties;

    @Test
    void contextLoads() throws Exception {
        File file = new File("C:\\Users\\ZC\\OneDrive\\圖片\\001.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);

        String upload = OssUtils.OssUpload.upload(ossClient, ossProperties, "test2.png", fileInputStream);
        System.out.printf(upload);
    }

}
```
