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
public class OssCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String endpoint = conditionContext.getEnvironment().getProperty("ali-tools.oss.endpoint");
        String accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.oss.accessKeyId");
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.oss.accessKeySecret");
        String bucketName = conditionContext.getEnvironment().getProperty("ali-tools.oss.bucketName");
        if (StringUtils.isEmpty(endpoint)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration:ali-tools.oss.endpoint");
        } else if (StringUtils.isEmpty(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration:ali-tools.oss.accessKeyId");
        } else if (StringUtils.isEmpty(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration:ali-tools.oss.accessKeySecret");
        } else if (StringUtils.isEmpty(bucketName)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration:ali-tools.oss.bucketName");
        } else {
            return true;
        }
    }
}
