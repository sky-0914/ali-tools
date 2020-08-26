package cn.happyloves.ali.tools.oss.properties;

import com.aliyun.oss.ClientConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Component
@ConfigurationProperties(prefix = "ali-tools.oss")
@Data
public class OssProperties {

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

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
    private String HTTPProtocol = "https";
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
