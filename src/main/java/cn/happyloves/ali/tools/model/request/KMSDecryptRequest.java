package cn.happyloves.ali.tools.model.request;

/**
 * @author zc
 * @date 2023/8/12 22:16
 */
public class KMSDecryptRequest {
    public byte[] ciphertextBlob;
    public String keyId;
    public String algorithm;
    public byte[] aad;
    public byte[] iv;
    public String paddingMode;
}
