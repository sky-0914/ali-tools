package cn.happyloves.ali.tools.oss.annotation;

import cn.happyloves.ali.tools.oss.autoconfigure.OssAutoConfiguration;
import cn.happyloves.ali.tools.oss.condiotion.OssCondition;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用注解：@EnableAliToolsOss
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OssCondition.class, OssAutoConfiguration.class})
public @interface EnableAliToolsOss {
}
