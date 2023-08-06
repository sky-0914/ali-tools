package cn.happyloves.ali.tools;

import cn.happyloves.ali.tools.bean.ORCClient;
import cn.happyloves.ali.tools.model.request.ORCRecognizeAdvancedRequest;
import cn.happyloves.ali.tools.model.request.ORCRecognizeHandwritingRequest;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.ocr_api20210707.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zc
 * @date 2021/7/7 23:00
 */
@Slf4j
public final class OcrUtils {

    /**
     * 身份证
     *
     * @param img     图片二进制数据的base64编码/图片url
     * @param appcode 应用Code
     * @param isFront 是否是正面
     * @return 返回值
     */
    public static String idCard(String img, String appcode, boolean isFront) {
        String host = "http://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        Map<String, String> headers = new HashMap<>(4);
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        JSONObject bodyJson = JSONUtil.createObj()
                .putOpt("image", img)
                .putOpt("configure", JSONUtil.createObj()
                        .putOpt("side", isFront ? "face" : "back")
                        .putOpt("ignore_exif", "false"));
        //链式构建请求
        final String body = HttpRequest.post(host + path)
                .headerMap(headers, true)
                .body(bodyJson.toString())
                .timeout(20000)
                .execute().body();
        if (JSONUtil.isTypeJSON(body)) {
            JSONObject jsonObject = JSONUtil.parseObj(body);
            final Boolean success = jsonObject.getBool("success");
            if (!success) {
                log.error(body);
            }
        } else {
            log.error(body);
        }
        return body;
    }

    /**
     * 通用文字识别
     */
    public static class Text {
        /**
         * 全文识别高精版
         * url:https://next.api.aliyun.com/api/ocr-api/2021-07-07/RecognizeAdvanced?spm=api-workbench.API%20Document.0.0.112b4dc3iXL46R&sdkStyle=dara&tab=DOC&lang=JAVA
         *
         * @param ocrClient  Client:OCR客户端
         * @param orcRequest RecognizeAdvancedRequest:参数
         */
        public static String recognizeAdvanced(ORCClient ocrClient, ORCRecognizeAdvancedRequest orcRequest) {
            final RecognizeAdvancedRequest request = new RecognizeAdvancedRequest();
            request.setNeedRotate(orcRequest.isNeedRotate());
            request.setNeedSortPage(orcRequest.isNeedSortPage());
            request.setNoStamp(orcRequest.isNoStamp());
            request.setOutputCharInfo(orcRequest.isOutputCharInfo());
            request.setOutputFigure(orcRequest.isOutputFigure());
            request.setOutputTable(orcRequest.isOutputTable());
            request.setParagraph(orcRequest.isParagraph());
            request.setRow(orcRequest.isRow());
            if (StringUtils.isNotBlank(orcRequest.getUrl())) {
                request.setUrl(orcRequest.getUrl());
            }
            if (orcRequest.getBody() != null) {
                request.setBody(orcRequest.getBody());
            }
            try {
                RecognizeAdvancedResponse response = ocrClient.recognizeAdvancedWithOptions(request, new RuntimeOptions());
                log.debug("ORC recognizeAdvanced Response Data: [{}]", JSONUtil.toJsonStr(response));
                if ("200".equals(response.body.code)) {
                    return response.body.data;
                }
            } catch (TeaException e) {
                // 如有需要，请打印 error
                log.error("ORC recognizeAdvanced ClientException ErrCode:[{}], ErrMsg:[{}]", e.getCode(), e.getMessage());
            } catch (Exception e) {
                TeaException error = new TeaException(e.getMessage(), e);
                log.error("ORC recognizeAdvanced ClientException ErrCode:[{}], ErrMsg:[{}]", error.getCode(), error.getMessage());
            }
            return StringUtils.EMPTY;
        }

        /**
         * 通用手写体识别
         * url:https://next.api.aliyun.com/api/ocr-api/2021-07-07/RecognizeHandwriting?sdkStyle=dara
         *
         * @param ocrClient  Client:OCR客户端
         * @param orcRequest RecognizeHandwritingRequest:参数
         */
        public static String recognizeHandwriting(ORCClient ocrClient, ORCRecognizeHandwritingRequest orcRequest) {
            final RecognizeHandwritingRequest request = new RecognizeHandwritingRequest();
            orcRequest.setNeedRotate(orcRequest.isNeedRotate());
            orcRequest.setNeedSortPage(orcRequest.isNeedSortPage());
            orcRequest.setOutputCharInfo(orcRequest.isOutputCharInfo());
            orcRequest.setOutputTable(orcRequest.isOutputTable());
            if (StringUtils.isNotBlank(orcRequest.getUrl())) {
                request.setUrl(orcRequest.getUrl());
            }
            if (orcRequest.getBody() != null) {
                request.setBody(orcRequest.getBody());
            }

            try {
                // 复制代码运行请自行打印 API 的返回值
                RecognizeHandwritingResponse response = ocrClient.recognizeHandwritingWithOptions(request, new RuntimeOptions());
                log.debug("ORC recognizeHandwriting Response Data: [{}]", JSONUtil.toJsonStr(response));
                if ("200".equals(response.body.code)) {
                    return response.body.data;
                }
            } catch (TeaException e) {
                // 如有需要，请打印 error
                log.error("ORC recognizeHandwriting ClientException ErrCode:[{}], ErrMsg:[{}]", e.getCode(), e.getMessage());
            } catch (Exception e) {
                TeaException error = new TeaException(e.getMessage(), e);
                log.error("ORC recognizeHandwriting ClientException ErrCode:[{}], ErrMsg:[{}]", error.getCode(), error.getMessage());
            }
            return StringUtils.EMPTY;
        }
    }

    /**
     * 个人证照识别
     */
    public static class PersonalCertificate {

        /**
         * 识别身份证
         *
         * @param ocrClient Client:OCR客户端
         * @param request   RecognizeIdcardRequest:参数
         */
        public static RecognizeIdcardResponse idCard(ORCClient ocrClient, RecognizeIdcardRequest request) {
            RuntimeOptions runtime = new RuntimeOptions();
            RecognizeIdcardResponse response = null;
            try {
                // 复制代码运行请自行打印 API 的返回值
                response = ocrClient.recognizeIdcardWithOptions(request, runtime);
                log.info(JSONUtil.toJsonStr(response));
            } catch (TeaException error) {
                log.error(error.message);
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            } catch (Exception _error) {
                TeaException error = new TeaException(_error.getMessage(), _error);
                log.error(error.message);
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            }
            return response;
        }


    }
}
