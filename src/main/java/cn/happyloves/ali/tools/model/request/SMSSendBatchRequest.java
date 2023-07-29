package cn.happyloves.ali.tools.model.request;

import cn.happyloves.ali.tools.SmsUtils;
import lombok.Data;

import java.util.List;

/**
 * @author zc
 * @date 2023/7/29 21:39
 */
@Data
public class SMSSendBatchRequest {
    private List<SMSBatchJsonRequest> jsonList;
    /**
     * 模板Code
     */
    private String templateCode;
}
