package cn.happyloves.ali.tools.annotation;

import java.lang.annotation.*;

/**
 * @author zc
 * @date 2023/8/5 21:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAliToolsKms
@EnableAliToolsOcr
@EnableAliToolsOss
@EnableAliToolsSms
public @interface EnableAliToolsAll {
}
