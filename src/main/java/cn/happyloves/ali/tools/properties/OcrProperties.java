package cn.happyloves.ali.tools.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zc
 * @date 2022/7/7 21:12
 */
@Component
@ConfigurationProperties(prefix = "ali-tools.ocr")
@Data
@EqualsAndHashCode(callSuper = false)
public class OcrProperties extends BaseProperties {
    /**
     * 访问的域名
     */
    private String endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
}
