package cn.happyloves.ali.tools.condiotion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zc
 * @date 2022/7/7 21:14
 */
@Slf4j
public class OcrCondition implements AliToolsCondition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        log.info("执行 OcrCondition...");
        aliMatches(conditionContext);
        return true;
    }
}
