package cn.happyloves.ali.tools;

import cn.happyloves.ali.tools.bean.SMSClient;
import cn.happyloves.ali.tools.properties.AliToolsProperties;
import cn.happyloves.ali.tools.properties.sub.SmsProperties;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZC
 * @date 2020/6/8-22:05
 */
@Slf4j
public final class SmsUtils {

    private static final String CODE = "OK";
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SYS_VERSION = "2017-05-25";
    private static final String REGION_ID = "RegionId";

    enum SysAction {
        /**
         * 发送短信
         */
        SendSms,
        /**
         * 批量发送短信
         */
        SendBatchSms
    }

    /**
     * 发送短信
     *
     * @param smsClient      客户端
     * @param smsProperties  SMS配置属性
     * @param sendSmsRequest 请求参数
     * @return 是否发送成功
     */
    public static boolean sendSms(SMSClient smsClient, SmsProperties smsProperties, SendSmsRequest sendSmsRequest) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SysAction.SendSms.name());
        request.putQueryParameter(REGION_ID, smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", sendSmsRequest.getPhone());
        request.putQueryParameter("TemplateParam", sendSmsRequest.getTemplateParam());
        String signName = StringUtils.isNotBlank(sendSmsRequest.getSign()) ? sendSmsRequest.getSign() : smsProperties.getSingleName();
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

    /**
     * TODO 调用SendBatchSms接口批量发送短信。未测试
     *
     * @param smsClient           客户端
     * @param smsProperties       SMS配置属性
     * @param sendBatchSmsRequest 请求参数
     * @return 是否发送成功
     */
    public static boolean sendBatchSms(IAcsClient smsClient, SmsProperties smsProperties, SendBatchSmsRequest sendBatchSmsRequest) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SysAction.SendBatchSms.name());
        request.putQueryParameter(REGION_ID, smsProperties.getRegionId());
        final List<BatchSmsJson> jsonList = sendBatchSmsRequest.getJsonList();
        String phoneNumberJson = JSONUtil.toJsonStr(jsonList.stream().map(BatchSmsJson::getPhone).collect(Collectors.toList()));
        String signNameJson = JSONUtil.toJsonStr(jsonList.stream().map(BatchSmsJson::getSign).collect(Collectors.toList()));
        String templateParamJson = JSONUtil.toJsonStr(jsonList.stream().map(BatchSmsJson::getTemplateParam).collect(Collectors.toList()));
        final List<String> smsUpExtendCodeJsonList = jsonList.stream().map(BatchSmsJson::getUpExtendCode).collect(Collectors.toList());
        if (ArrayUtil.isNotEmpty(smsUpExtendCodeJsonList)) {
            //上行短信扩展码，JSON数组格式。无特殊需要此字段的用户请忽略此字段
            request.putQueryParameter("SmsUpExtendCodeJson", JSONUtil.toJsonStr(smsUpExtendCodeJsonList));
        }
        //说明 验证码类型短信，建议使用接口SendSms单独发送。
        request.putQueryParameter("PhoneNumberJson", phoneNumberJson);
        //说明 如果JSON中需要带换行符，请参照标准的JSON协议处理；且模板变量值的个数必须与手机号码、签名的个数相同、内容一一对应，表示向指定手机号码中发对应签名的短信，且短信模板中的变量参数替换为对应的值。
        request.putQueryParameter("SignNameJson", signNameJson);
        //说明 如果JSON中需要带换行符，请参照标准的JSON协议处理；且模板变量值的个数必须与手机号码、签名的个数相同、内容一一对应，表示向指定手机号码中发对应签名的短信，且短信模板中的变量参数替换为对应的值。
        request.putQueryParameter("TemplateParamJson", templateParamJson);
        //短信模板CODE。请在控制台国内消息或国际/港澳台消息页面中的模板管理页签下模板CODE一列查看。
        request.putQueryParameter("TemplateCode", sendBatchSmsRequest.getTemplateCode());
        try {
            CommonResponse response = smsClient.getCommonResponse(request);
            log.debug("Ali SMS Response Data: [{}]", response.getData());
            JsonObject asJsonObject = JsonParser.parseString(response.getData()).getAsJsonObject();
            return CODE.equals(asJsonObject.get("Code").toString());
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

    @Data
    public static class BatchSmsJson {
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

    @Data
    public static class SendBatchSmsRequest {
        private List<BatchSmsJson> jsonList;
        /**
         * 模板Code
         */
        private String templateCode;

    }

}
