package cn.happyloves.ali.tools.condiotion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;

/**
 * @author zc
 * @date 2022/8/11 17:27
 */
public interface AliToolsCondition extends Condition {

    default void aliMatches(ConditionContext conditionContext) {
        String accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.accessKeyId");
        if (StringUtils.isBlank(accessKeyId)) {
            accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.access-key-id");
        }
        if (StringUtils.isBlank(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools configuration: ali-tools.accessKeyId OR ali-tools.access-key-id");
        }
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.accessKeySecret");
        if (StringUtils.isBlank(accessKeySecret)) {
            accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.access-key-secret");
        }
        if (StringUtils.isBlank(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools configuration: ali-tools.accessKeySecret OR ali-tools.access-key-secret");
        }
    }
}
