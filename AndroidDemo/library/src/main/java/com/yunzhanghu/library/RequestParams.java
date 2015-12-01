package com.yunzhanghu.library;

import java.util.HashMap;

/**
 * Created by max on 15/11/30.
 * 截获Ajax请求时返回给商户App的数据模型
 */
public class RequestParams {
    /**
     * 错误码
     */
    public String code;
    /**
     * 错误码对应的说明信息
     */
    public String msg;
    /**
     * 商户APP需要获得的数据
     */
    public HashMap<String, String> data;

    @Override
    public String toString() {
        return "RequestParams{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
