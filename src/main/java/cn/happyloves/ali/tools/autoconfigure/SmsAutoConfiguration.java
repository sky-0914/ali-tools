package cn.happyloves.ali.tools.autoconfigure;

import cn.happyloves.ali.tools.condiotion.SmsCondition;
import cn.happyloves.ali.tools.properties.SmsProperties;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
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
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@Conditional(SmsCondition.class)
@ConditionalOnProperty(prefix = "ali-tools.sms", value = "true", matchIfMissing = true)
public class SmsAutoConfiguration {
    private final SmsProperties smsProperties;

    @Autowired
    public SmsAutoConfiguration(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    /**
     * create IAcsClient client
     */
    @Bean
    public IAcsClient smsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getRegionId(), smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }

}
