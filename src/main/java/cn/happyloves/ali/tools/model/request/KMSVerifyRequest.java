package cn.happyloves.ali.tools.model.request;

import com.aliyun.dkms.gcs.sdk.models.VerifyRequest;
import lombok.Data;

/**
 * @author zc
 * @date 2023/8/12 22:17
 */
@Data
public class KMSVerifyRequest {
    public String keyId;
    public byte[] signature;
    public String algorithm;
    public byte[] message;
    public String messageType;

    public VerifyRequest getRequest() {
        final VerifyRequest request = new VerifyRequest();
        request.setKeyId(keyId);
        request.setSignature(signature);
        request.setAlgorithm(algorithm);
        request.setMessage(message);
        request.setMessageType(messageType);
        return request;
    }
}
