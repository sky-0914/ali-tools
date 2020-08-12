package cn.happyloves.ali.tools.oss.annotation;

import cn.happyloves.ali.tools.oss.autoconfigure.SmsAutoConfiguration;
import cn.happyloves.ali.tools.oss.condiotion.SmsCondition;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用注解：@EnableAliToolsOss
 *
 * @author ZC
 * @date 2020/8/12-16:45
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SmsCondition.class, SmsAutoConfiguration.class})
public @interface EnableAliToolsSms {
}
