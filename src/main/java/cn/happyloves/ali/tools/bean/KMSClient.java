package cn.happyloves.ali.tools.bean;


import com.aliyun.dkms.gcs.openapi.models.Config;
import com.aliyun.dkms.gcs.sdk.Client;

/**
 * @author zc
 * @date 2023/7/19 13:48
 */
public class KMSClient extends Client {
    public KMSClient(Config config) throws Exception {
        super(config);
    }
}
