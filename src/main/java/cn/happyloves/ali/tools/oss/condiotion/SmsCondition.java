package cn.happyloves.ali.tools.oss.condiotion;

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
public class SmsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.sms.accessKeyId");
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.sms.accessKeySecret");
        if (StringUtils.isEmpty(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools.sms configuration:ali-tools.sms.accessKeyId");
        } else if (StringUtils.isEmpty(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools.sms configuration:ali-tools.sms.accessKeySecret");
        } else {
            return true;
        }
    }
}
