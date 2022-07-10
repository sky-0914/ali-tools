package cn.happyloves.ali.tools.properties;

import com.aliyun.oss.ClientConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
public class OssProperties extends BaseProperties {
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
