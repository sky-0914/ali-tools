package cn.happyloves.ali.tools.annotation;

import cn.happyloves.ali.tools.autoconfigure.OssAutoConfiguration;
import cn.happyloves.ali.tools.condiotion.OssCondition;
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
