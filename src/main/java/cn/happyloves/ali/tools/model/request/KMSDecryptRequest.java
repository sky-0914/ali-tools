package cn.happyloves.ali.tools.model.request;

import com.aliyun.dkms.gcs.sdk.models.DecryptRequest;
import lombok.Data;

/**
 * @author zc
 * @date 2023/8/12 22:16
 */
@Data
public class KMSDecryptRequest {
    public byte[] ciphertextBlob;
    public String keyId;
    public String algorithm;
    public byte[] aad;
    public byte[] iv;
    public String paddingMode;

    public DecryptRequest getRequest() {
        final DecryptRequest request = new DecryptRequest();
        request.setCiphertextBlob(this.ciphertextBlob);
        request.setKeyId(keyId);
        request.setAlgorithm(algorithm);
        request.setAad(aad);
        request.setIv(iv);
        request.setPaddingMode(paddingMode);
        return request;
    }
}
