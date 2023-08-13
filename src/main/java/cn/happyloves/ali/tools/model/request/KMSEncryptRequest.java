package cn.happyloves.ali.tools.model.request;

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
}
