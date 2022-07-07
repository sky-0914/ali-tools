package cn.happyloves.ali.tools.condiotion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zc
 * @date 2022/7/7 21:14
 */
public class OcrCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.ocr.accessKeyId");
        if (StringUtils.isBlank(accessKeyId)) {
            accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.ocr.access-key-id");
        }
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.ocr.accessKeySecret");
        if (StringUtils.isBlank(accessKeySecret)) {
            accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.ocr.access-key-secret");
        }
        if (StringUtils.isBlank(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools.ocr configuration: ali-tools.ocr.accessKeyId OR ali-tools.ocr.access-key-id");
        } else if (StringUtils.isBlank(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools.ocr configuration: ali-tools.ocr.accessKeySecret OR ali-tools.ocr.access-key-secret");
        } else {
            return true;
        }
    }
}
