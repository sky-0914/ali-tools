package cn.happyloves.ali.tools.properties;

import lombok.Data;

/**
 * @author zc
 * @date 2020/11/26 18:38
 */
@Data
public abstract class BaseProperties {
    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;
}
