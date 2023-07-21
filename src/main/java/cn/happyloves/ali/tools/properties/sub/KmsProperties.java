package cn.happyloves.ali.tools.properties.sub;

import lombok.Data;

/**
 * @author zc
 * @date 2023/7/20 21:38
 */
@Data
public final class KmsProperties {
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
