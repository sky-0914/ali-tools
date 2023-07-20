package cn.happyloves.ali.tools;

import cn.happyloves.ali.tools.bean.KMSClient;
import com.aliyun.dkms.gcs.sdk.models.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zc
 * @date 2023/7/19 13:57
 */
@Slf4j
public class KmsUtils {

    public void encrypt(KMSClient client) throws Exception {
        EncryptRequest encryptRequest = new EncryptRequest();
//        encryptRequest.setKeyId(cipherKeyId);
//        encryptRequest.setPlaintext(originData);

        EncryptResponse encryptResponse = client.encrypt(encryptRequest);
        //加密数据。
        byte[] cipherData = encryptResponse.getCiphertextBlob();
        //Cipher初始向量，用于解密数据。
        byte[] iv = encryptResponse.getIv();
        //请求ID。
        String requestId = encryptResponse.getRequestId();
    }

    public void decrypt(KMSClient client) throws Exception {
        DecryptRequest decryptRequest = new DecryptRequest();
//        decryptRequest.setKeyId(cipherKeyId);
//        decryptRequest.setCiphertextBlob(cipherData);
//        decryptRequest.setIv(iv);

        DecryptResponse decryptResponse = client.decrypt(decryptRequest);
        //原始数据。
        byte[] originData = decryptResponse.getPlaintext();
        //请求ID。
        String requestId = decryptResponse.getRequestId();
    }

    public void sign(KMSClient client) throws Exception {
        //密钥的ID或别名（Alias）。
        String signerKeyId = "<the signer key id>";
        //待签名数据。
        byte[] message = null;

        SignRequest signRequest = new SignRequest();
        signRequest.setKeyId(signerKeyId);
        signRequest.setMessage(message);

        SignResponse signResponse = client.sign(signRequest);
        //签名值。
        byte[] signature = signResponse.getSignature();
        //请求ID。
        String requestId = signResponse.getRequestId();
    }

    public void verify(KMSClient client) throws Exception {
        //密钥的ID或别名（Alias）。
        String signerKeyId = "<the signer key id>";
        //待验证签名的数据。
        byte[] message = null;

        VerifyRequest verifyRequest = new VerifyRequest();
        verifyRequest.setKeyId(signerKeyId);
        verifyRequest.setMessage(message);
        verifyRequest.setSignature(new byte[]{});

        VerifyResponse verifyResponse = client.verify(verifyRequest);
        //验签结果。
        boolean valid = verifyResponse.getValue();
        //请求ID。
        String requestId = verifyResponse.getRequestId();
    }
}
