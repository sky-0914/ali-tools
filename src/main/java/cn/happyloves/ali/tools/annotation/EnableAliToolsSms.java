package cn.happyloves.ali.tools.annotation;

import cn.happyloves.ali.tools.condiotion.SmsCondition;
import cn.happyloves.ali.tools.autoconfigure.SmsAutoConfiguration;
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
