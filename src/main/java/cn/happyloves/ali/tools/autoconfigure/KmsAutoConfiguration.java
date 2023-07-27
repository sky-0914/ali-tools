package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.bean.KMSClient;
import cn.happyloves.ali.tools.condiotion.KmsCondition;
import cn.happyloves.ali.tools.properties.AliToolsProperties;
import cn.happyloves.ali.tools.properties.sub.KmsProperties;
import com.aliyun.dkms.gcs.openapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zc
 * @date 2023/7/19 13:41
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AliToolsProperties.class)
@Conditional(KmsCondition.class)
@ConditionalOnProperty(prefix = "ali-tools", name = {"enabled"}, matchIfMissing = true)
public class KmsAutoConfiguration {

    private AliToolsProperties properties;

    @Autowired
    public void setAliToolsProperties(AliToolsProperties properties) {
        this.properties = properties;
    }

    @Bean
    public KMSClient kmsClient() throws Exception {
        log.info("初始化 KMS Client...");
        final KmsProperties kms = properties.getKms();
        Config config = new Config()
                .setProtocol(kms.getProtocol())
                .setEndpoint(kms.getEndpoint());
        // 设置CA证书文件路径，还支持设置CA证书内容，请根据需要选择。
        config.setCaFilePath(kms.getCaCertPath());
        // 设置CA证书内容。
        //.setCa(caCert)
        //设置应用身份凭证文件路径，还支持设置应用身份凭证内容，请根据需要选择。
        config.setClientKeyFile(kms.getClientKeyFilePath());
        //设置应用身份凭证内容。
        //.setClientKeyContent(clientKey)
        config.setPassword(kms.getClientKeyPass());
        return new KMSClient(config);
    }
}
