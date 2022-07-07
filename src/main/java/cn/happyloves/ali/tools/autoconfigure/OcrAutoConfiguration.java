package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.condiotion.OcrCondition;
import cn.happyloves.ali.tools.properties.OcrProperties;
import com.aliyun.ocr_api20210707.Client;
import com.aliyun.teaopenapi.models.Config;
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
@Configuration
@EnableConfigurationProperties(OcrProperties.class)
@Conditional(OcrCondition.class)
@ConditionalOnProperty(prefix = "ali-tools.sms", value = "true", matchIfMissing = true)
public class OcrAutoConfiguration {

    private OcrProperties ocrProperties;

    @Autowired
    public void setOcrProperties(OcrProperties ocrProperties) {
        this.ocrProperties = ocrProperties;
    }

    @Bean
    public Client ocrClient() throws Exception {
        return new Client(new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(ocrProperties.getAccessKeyId())
                // 您的 AccessKey Secret
                .setAccessKeySecret(ocrProperties.getAccessKeySecret())
                // 访问的域名
                .setEndpoint(ocrProperties.getEndpoint()));
    }
}
