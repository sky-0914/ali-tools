package cn.happyloves.ali.tools.model.request;

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
}
