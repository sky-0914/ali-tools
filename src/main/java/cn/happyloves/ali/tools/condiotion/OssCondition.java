package cn.happyloves.ali.tools.condiotion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 校验类
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public class OssCondition implements AliToolsCondition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        log.info("执行 OssCondition...");
        aliMatches(conditionContext);
        String endpoint = conditionContext.getEnvironment().getProperty("ali-tools.oss.endpoint");

        String bucketName = conditionContext.getEnvironment().getProperty("ali-tools.oss.bucketName");
        if (StringUtils.isBlank(bucketName)) {
            bucketName = conditionContext.getEnvironment().getProperty("ali-tools.oss.bucket-name");
        }
        if (StringUtils.isBlank(endpoint)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: ali-tools.oss.endpoint");
        } else if (StringUtils.isBlank(bucketName)) {
            throw new RuntimeException("Lack of ali-tools.oss configuration: ali-tools.oss.bucketName OR ali-tools.oss.bucket-name");
        } else {
            return true;
        }
    }
}
