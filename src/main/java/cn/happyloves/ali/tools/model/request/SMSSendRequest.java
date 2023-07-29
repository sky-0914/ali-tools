package cn.happyloves.ali.tools.model.request;

import lombok.Data;

/**
 * @author zc
 * @date 2023/7/29 21:37
 */
@Data
public class SMSSendRequest {
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 签名
     */
    private String sign;
    /**
     * 模板Code
     */
    private String templateCode;
    /**
     * 模板参数，JSON格式
     */
    private String templateParam;
}
