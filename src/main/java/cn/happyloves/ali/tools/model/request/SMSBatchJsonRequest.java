package cn.happyloves.ali.tools.model.request;

import lombok.Data;

/**
 * @author zc
 * @date 2023/7/29 21:38
 */
@Data
public class SMSBatchJsonRequest {
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 签名
     */
    private String sign;
    /**
     * 模板参数，JSON格式
     */
    private String templateParam;
    /**
     * 上行短信扩展码,可不传
     */
    private String upExtendCode;
}
