package cn.happyloves.ali.tools.properties;

import cn.happyloves.ali.tools.properties.sub.KmsProperties;
import cn.happyloves.ali.tools.properties.sub.OcrProperties;
import cn.happyloves.ali.tools.properties.sub.OssProperties;
import cn.happyloves.ali.tools.properties.sub.SmsProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zc
 * @date 2022/8/10 20:37
 */
@Component
@ConfigurationProperties(prefix = "ali-tools")
@Data
public class AliToolsProperties {
    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    private OcrProperties ocr;
    private OssProperties oss;
    private SmsProperties sms;
    private KmsProperties kms;

}
