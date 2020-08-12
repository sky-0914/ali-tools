package cn.happyloves.ali.tools.oss;

import cn.happyloves.ali.tools.oss.properties.SmsProperties;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public class SmsUtils {
    private static final String CODE = "OK";

    /**
     * 发送短信
     *
     * @param smsClient      客户端
     * @param smsProperties  SMS配置属性
     * @param sendSmsRequest 请求参数
     * @return 是否发送成功
     */
    public static boolean sendSms(IAcsClient smsClient, SmsProperties smsProperties, SendSmsRequest sendSmsRequest) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", sendSmsRequest.getPhoneNumber());
        request.putQueryParameter("TemplateParam", sendSmsRequest.getTemplateParam());
        String signName = StringUtils.isNotBlank(sendSmsRequest.getSignName()) ? sendSmsRequest.getSignName() : smsProperties.getSingleName();
        String templateCode = StringUtils.isNotBlank(sendSmsRequest.getTemplateCode()) ? sendSmsRequest.getTemplateCode() : smsProperties.getTemplateCode();
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        try {
            CommonResponse response = smsClient.getCommonResponse(request);
            log.debug("Ali SMS Response Data: [{}]", response.getData());
            JsonObject asJsonObject = JsonParser.parseString(response.getData()).getAsJsonObject();
            return CODE.equals(asJsonObject.get("Code").toString());
        } catch (ServerException e) {
            log.error("ServerException ErrCode:[{}], ErrMsg:[{}]", e.getErrCode(), e.getErrMsg());
        } catch (ClientException e) {
            log.error("ClientException ErrCode:[{}], ErrMsg:[{}]", e.getErrCode(), e.getErrMsg());
        }
        return false;
    }

    @Data
    public static class SendSmsRequest {
        /**
         * 手机号码
         */
        private String phoneNumber;
        /**
         * 签名
         */
        private String signName;
        /**
         * 模板Code
         */
        private String templateCode;
        /**
         * 模板参数，JSON格式
         */
        private String templateParam;
    }
}
