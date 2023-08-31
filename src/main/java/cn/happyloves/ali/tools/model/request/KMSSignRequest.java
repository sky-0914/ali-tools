package cn.happyloves.ali.tools.model.request;

import com.aliyun.dkms.gcs.sdk.models.SignRequest;
import lombok.Data;

/**
 * @author zc
 * @date 2023/8/12 22:16
 */
@Data
public class KMSSignRequest {
    public String keyId;
    public String algorithm;
    public byte[] message;
    public String messageType;

    public SignRequest getRequest() {
        final SignRequest request = new SignRequest();
        request.setKeyId(keyId);
        request.setAlgorithm(algorithm);
        request.setMessage(message);
        request.setMessageType(messageType);
        return request;
    }
}
