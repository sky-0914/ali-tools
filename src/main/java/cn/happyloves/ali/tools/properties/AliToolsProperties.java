package cn.happyloves.ali.tools.properties;

import com.aliyun.oss.ClientConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zc
 * @date 2022/8/10 20:37
 */
@Component
@ConfigurationProperties(prefix = "ali-tools")
@Data
public class AliToolsProperties {
    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    private OcrProperties ocr;
    private OssProperties oss;
    private SmsProperties sms;
    private KmsProperties kms;

    @Data
    public final static class OcrProperties {
        /**
         * 访问的域名
         */
        private String endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
    }

    @Data
    public final static class OssProperties {
        /**
         * endpoint
         */
        private String endpoint;
        /**
         * bucketName
         */
        private String bucketName;
        /**
         * home（默认路径:upload）
         */
        private String home = "upload";
        /**
         * http协议（默认https）
         */
        private String httpProtocol = "https";
        /**
         * cdn
         */
        private String cdn = "";

        /**
         * OSSClient配置<br>
         * 配置列表通过<a href=
         * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.673.OqIgwj">https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.673.OqIgwj</a>查看
         */
        private ClientConfiguration clientConfig;
    }

    @Data
    public final static class SmsProperties {
        private String regionId = "cn-hangzhou";
        /**
         * 短信签名名称。请在控制台签名管理页面签名名称一列查看。必须是已添加、并通过审核的短信签名。
         */
        private String singleName;
        /**
         * 短信模板ID。请在控制台模板管理页面模板CODE一列查看。必须是已添加、并通过审核的短信签名；且发送国际/港澳台消息时，请使用国际/港澳台短信模版。
         */
        private String templateCode;

    }

    @Data
    public final static class KmsProperties {
        // 连接协议请设置为"https"。KMS实例服务仅允许通过HTTPS协议访问。
        private String protocol = "https";
        // 设置endpoint为<your KMS Instance Id>.cryptoservice.kms.aliyuncs.com。
        private String endpoint;

        // Client Key。
        private String clientKeyFilePath;
        //String clientKey = "<your client key>";

        // Client Key口令。
        private String clientKeyPass;

        // KMS实例的CA证书，可通过文件路径或直接设置内容。
        private String caCertPath;
        //String caCert = "<The DKMS instance CA certificates content>";
    }
}
