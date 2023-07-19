package cn.happyloves.ali.tools;

import cn.happyloves.ali.tools.bean.KMSClient;
import com.aliyun.dkms.gcs.sdk.models.DecryptRequest;
import com.aliyun.dkms.gcs.sdk.models.DecryptResponse;
import com.aliyun.dkms.gcs.sdk.models.EncryptRequest;
import com.aliyun.dkms.gcs.sdk.models.EncryptResponse;
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

}
