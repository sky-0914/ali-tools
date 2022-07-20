package cn.happyloves.ali.tools.bean;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.teaopenapi.models.Config;

/**
 * @author zc
 * @date 2022/7/17 00:03
 */
public class ORCClient extends Client {
    public ORCClient(Config config) throws Exception {
        super(config);
    }
}
