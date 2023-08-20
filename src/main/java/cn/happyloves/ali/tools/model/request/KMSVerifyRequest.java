package cn.happyloves.ali.tools.model.request;

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
}
