package cn.happyloves.ali.tools.condiotion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zc
 * @date 2023/7/19 13:45
 */
@Slf4j
public class KmsCondition implements AliToolsCondition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("执行 KmsCondition...");
        aliMatches(context);
        return true;
    }
}
