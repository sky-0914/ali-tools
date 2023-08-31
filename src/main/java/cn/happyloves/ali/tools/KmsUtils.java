package cn.happyloves.ali.tools;

import cn.happyloves.ali.tools.bean.KMSClient;
import cn.happyloves.ali.tools.model.request.KMSDecryptRequest;
import cn.happyloves.ali.tools.model.request.KMSEncryptRequest;
import cn.happyloves.ali.tools.model.request.KMSSignRequest;
import cn.happyloves.ali.tools.model.request.KMSVerifyRequest;
import com.aliyun.dkms.gcs.sdk.models.DecryptResponse;
import com.aliyun.dkms.gcs.sdk.models.EncryptResponse;
import com.aliyun.dkms.gcs.sdk.models.SignResponse;
import com.aliyun.dkms.gcs.sdk.models.VerifyResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zc
 * @date 2023/7/19 13:57
 */
@Slf4j
public final class KmsUtils {

    /**
     * 加密
     *
     * @param client  kms哭护短
     * @param request 请求参数
     * @return 加密返回值
     * @throws Exception 异常信息
     */
    public static EncryptResponse encrypt(KMSClient client, KMSEncryptRequest request) throws Exception {
        EncryptResponse response = client.encrypt(request.getRequest());
//        //加密数据。
//        byte[] cipherData = response.getCiphertextBlob();
//        //Cipher初始向量，用于解密数据。
//        byte[] iv = response.getIv();
//        //请求ID。
//        String requestId = response.getRequestId();
        return response;
    }

    /**
     * 解密
     *
     * @param client  kms哭护短
     * @param request 请求参数
     * @return 加密返回值
     * @throws Exception 异常信息
     */
    public static DecryptResponse decrypt(KMSClient client, KMSDecryptRequest request) throws Exception {

        DecryptResponse response = client.decrypt(request.getRequest());
        //原始数据。
        byte[] originData = response.getPlaintext();
        //请求ID。
        String requestId = response.getRequestId();
        return response;
    }

    /**
     * 加签
     *
     * @param client  kms哭护短
     * @param request 请求参数
     * @return 加密返回值
     * @throws Exception 异常信息
     */
    public static SignResponse sign(KMSClient client, KMSSignRequest request) throws Exception {
        SignResponse response = client.sign(request.getRequest());
        //签名值。
        byte[] signature = response.getSignature();
        //请求ID。
        String requestId = response.getRequestId();
        return response;
    }

    /**
     * 验签
     *
     * @param client  kms哭护短
     * @param request 请求参数
     * @return 加密返回值
     * @throws Exception 异常信息
     */
    public static VerifyResponse verify(KMSClient client, KMSVerifyRequest request) throws Exception {

        VerifyResponse response = client.verify(request.getRequest());
        //验签结果。
        boolean valid = response.getValue();
        //请求ID。
        String requestId = response.getRequestId();
        return response;
    }
}
