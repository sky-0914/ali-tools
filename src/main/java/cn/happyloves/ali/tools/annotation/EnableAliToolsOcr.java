package cn.happyloves.ali.tools.annotation;

import cn.happyloves.ali.tools.autoconfigure.OcrAutoConfiguration;
import cn.happyloves.ali.tools.condiotion.OcrCondition;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zc
 * @date 2022/7/7 21:30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OcrCondition.class, OcrAutoConfiguration.class})
public @interface EnableAliToolsOcr {
}
