package cn.happyloves.ali.tools.model.reponse;

import lombok.Data;

/**
 * @author zc
 * @date 2023/7/27 19:59
 */
@Data
public class KMSEncryptResponse {
    /**
     * 加密数据。
     */
    private byte[] message;
    /**
     * Cipher初始向量，用于解密数据。
     */
    private byte[] iv;
    /**
     * 请求ID。
     */
    private String requestId;
}
