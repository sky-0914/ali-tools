package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.condiotion.OssCondition;
import cn.happyloves.ali.tools.properties.AliToolsProperties;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


/**
 * oss 自动配置
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AliToolsProperties.class)
@Conditional(OssCondition.class)
@ConditionalOnProperty(prefix = "ali-tools", name = {"enabled"}, matchIfMissing = true)
public class OssAutoConfiguration {
    private AliToolsProperties properties;

    @Autowired
    public void setAliToolsProperties(AliToolsProperties properties) {
        this.properties = properties;
    }

    /**
     * create OSS client
     */
    @Bean
    public OSSClient ossClient() {
        log.info("初始化 OSS Client...");
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();
        ClientConfiguration config = properties.getOss().getClientConfig();
        String endpoint = properties.getOss().getEndpoint();
        DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        if (config == null) {
            config = new ClientConfiguration();
        }
        return new OSSClient(endpoint, defaultCredentialProvider, config);
    }

}
