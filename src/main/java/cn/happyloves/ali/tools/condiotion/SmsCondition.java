package cn.happyloves.ali.tools.condiotion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 校验类
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public class SmsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        log.info("执行 SmsCondition...");
        String accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.sms.accessKeyId");
        if (StringUtils.isBlank(accessKeyId)) {
            accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.sms.access-key-id");
        }
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.sms.accessKeySecret");
        if (StringUtils.isBlank(accessKeySecret)) {
            accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.sms.access-key-secret");
        }
        if (StringUtils.isBlank(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools.sms configuration: ali-tools.sms.accessKeyId OR ali-tools.sms.access-key-id");
        } else if (StringUtils.isBlank(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools.sms configuration: ali-tools.sms.accessKeySecret OR ali-tools.sms.access-key-secret");
        } else {
            return true;
        }
    }
}
