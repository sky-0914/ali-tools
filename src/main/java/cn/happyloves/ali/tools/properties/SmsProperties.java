package cn.happyloves.ali.tools.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Component
@ConfigurationProperties(prefix = "ali-tools.sms")
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsProperties extends BaseProperties {
    /**
     * 是否启动
     */
    private boolean enable = false;

    private String regionId = "cn-hangzhou";
    /**
     * 短信签名名称。请在控制台签名管理页面签名名称一列查看。必须是已添加、并通过审核的短信签名。
     */
    private String singleName;
    /**
     * 短信模板ID。请在控制台模板管理页面模板CODE一列查看。必须是已添加、并通过审核的短信签名；且发送国际/港澳台消息时，请使用国际/港澳台短信模版。
     */
    private String templateCode;

}
