package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.condiotion.OssCondition;
import cn.happyloves.ali.tools.properties.OssProperties;
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
@EnableConfigurationProperties(OssProperties.class)
@Conditional(OssCondition.class)
@ConditionalOnProperty(prefix = "ali-tools.oss", name = "enable", havingValue = "true")
public class OssAutoConfiguration {
    private final OssProperties ossProperties;

    @Autowired
    public OssAutoConfiguration(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     * create OSS client
     */
    @Bean
    public OSSClient ossClient() {
        log.info("初始化 OSS Client...");
        ClientConfiguration config = ossProperties.getClientConfig();
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        if (config == null) {
            config = new ClientConfiguration();
        }
        return new OSSClient(endpoint, defaultCredentialProvider, config);
    }

}
