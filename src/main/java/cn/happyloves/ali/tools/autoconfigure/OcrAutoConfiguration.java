package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.bean.ORCClient;
import cn.happyloves.ali.tools.condiotion.OcrCondition;
import cn.happyloves.ali.tools.properties.AliToolsProperties;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zc
 * @date 2022/7/7 21:12
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AliToolsProperties.class)
@Conditional(OcrCondition.class)
@ConditionalOnProperty(prefix = "ali-tools", name = {"enabled"}, matchIfMissing = true)
public class OcrAutoConfiguration {

    private AliToolsProperties properties;

    @Autowired
    public void setAliToolsProperties(AliToolsProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ORCClient ocrClient() throws Exception {
        log.info("初始化 OCR Client...");
        return new ORCClient(new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(properties.getAccessKeyId())
                // 您的 AccessKey Secret
                .setAccessKeySecret(properties.getAccessKeySecret())
                // 访问的域名
                .setEndpoint(properties.getOcr().getEndpoint()));
    }
}
