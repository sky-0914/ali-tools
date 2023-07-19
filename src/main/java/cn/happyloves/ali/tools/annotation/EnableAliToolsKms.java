package cn.happyloves.ali.tools.annotation;

import cn.happyloves.ali.tools.autoconfigure.KmsAutoConfiguration;
import cn.happyloves.ali.tools.condiotion.KmsCondition;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zc
 * @date 2023/7/19 13:40
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({KmsCondition.class, KmsAutoConfiguration.class})
public @interface EnableAliToolsKms {
}
