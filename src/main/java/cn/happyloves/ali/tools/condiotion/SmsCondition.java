package cn.happyloves.ali.tools.condiotion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 校验类
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public class SmsCondition implements AliToolsCondition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        log.info("执行 SmsCondition...");
        aliMatches(conditionContext);
        return true;
    }
}
