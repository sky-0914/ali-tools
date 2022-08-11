package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.bean.SMSClient;
import cn.happyloves.ali.tools.condiotion.SmsCondition;
import cn.happyloves.ali.tools.properties.AliToolsProperties;
import com.aliyuncs.profile.DefaultProfile;
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
@Conditional(SmsCondition.class)
@ConditionalOnProperty(prefix = "ali-tools", name = {"enabled"}, matchIfMissing = true)
public class SmsAutoConfiguration {
    private AliToolsProperties properties;

    @Autowired
    public void setAliToolsProperties(AliToolsProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SMSClient smsClient() {
        log.info("初始化 SMS Client...");
        DefaultProfile profile = DefaultProfile.getProfile(properties.getSms().getRegionId(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        return new SMSClient(profile);
    }

}
