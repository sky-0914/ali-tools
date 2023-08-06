package cn.happyloves.ali.tools.model.request;

import lombok.Data;

import java.io.InputStream;

/**
 * @author zc
 * @date 2023/8/5 21:42
 */
@Data
public class ORCRecognizeAdvancedRequest {
    /**
     * 本字段和body字段二选一，不可同时透传或同时为空。
     */
    private String url;
    /**
     * 本字段和URL字段二选一，不可同时透传或同时为空。
     * 图片二进制文件，最大10MB。
     * 使用HTTP方式调用，把图片二进制文件放到HTTP body中上传即可。
     * 使用SDK的方式调用，把图片放到SDK的body中即可。
     */
    private InputStream body;
    /**
     * 是否输出单字识别结果，默认不需要。
     */
    private boolean outputCharInfo;
    /**
     * 是否需要自动旋转功能，默认不需要。
     */
    private boolean needRotate;
    /**
     * 是否输出表格识别结果，包含单元格信息，默认不需要。
     */
    private boolean outputTable;
    /**
     * 是否按顺序输出文字块，默认为false。
     */
    private boolean needSortPage;
    /**
     * 是否需要图案检测功能，默认不需要。
     */
    private boolean outputFigure;
    /**
     * 是否需要去除印章功能，默认不需要。
     */
    private boolean noStamp;
    /**
     * 是否需要分段功能，默认不需要。
     */
    private boolean paragraph;
    /**
     * 是否需要成行返回功能，默认不需要。
     */
    private boolean row;
}
