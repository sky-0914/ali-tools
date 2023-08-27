package cn.happyloves.ali.tools.model.request;

import com.aliyun.dkms.gcs.sdk.models.EncryptRequest;
import lombok.Data;

/**
 * @author zc
 * @date 2023/8/12 22:16
 */
@Data
public class KMSEncryptRequest {
    public String keyId;
    public byte[] plaintext;
    public String algorithm;
    public byte[] aad;
    public byte[] iv;
    public String paddingMode;

    public EncryptRequest getRequest() {
        final EncryptRequest request = new EncryptRequest();
        request.setKeyId(keyId);
        request.setPlaintext(plaintext);
        request.setAlgorithm(algorithm);
        request.setAad(aad);
        request.setIv(iv);
        request.setPaddingMode(paddingMode);
        return request;
    }
}
