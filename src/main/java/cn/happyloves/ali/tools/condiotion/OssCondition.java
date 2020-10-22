package cn.happyloves.ali.tools.condiotion;

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
        if (StringUtils.isBlank(accessKeyId)) {
            accessKeyId = conditionContext.getEnvironment().getProperty("ali-tools.oss.access-key-id");
        }
        String accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.oss.accessKeySecret");
        if (StringUtils.isBlank(accessKeySecret)) {
            accessKeySecret = conditionContext.getEnvironment().getProperty("ali-tools.oss.access-key-secret");
        }
        String bucketName = conditionContext.getEnvironment().getProperty("ali-tools.oss.bucketName");
        if (StringUtils.isBlank(bucketName)) {
            bucketName = conditionContext.getEnvironment().getProperty("ali-tools.oss.bucket-name");
        }
        if (StringUtils.isBlank(endpoint)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: ali-tools.oss.endpoint");
        } else if (StringUtils.isBlank(accessKeyId)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: li-tools.oss.accessKeyId OR ali-tools.oss.access-key-id");
        } else if (StringUtils.isBlank(accessKeySecret)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: ali-tools.oss.accessKeySecret OR ali-tools.oss.access-key-secret");
        } else if (StringUtils.isBlank(bucketName)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: ali-tools.oss.bucketName OR ali-tools.oss.bucket-name");
        } else {
            return true;
        }
    }
}
