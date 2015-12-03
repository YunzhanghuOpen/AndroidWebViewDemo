package com.yunzhanghu.library;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by max on 15/11/30.
 * 截获Ajax请求时返回给商户App的数据模型
 */
public class RequestParams {
    /**
     * 错误码
     * <p/>
     * code=0 表示操作成功
     * code=1 表示操作失败，请记录
     * code=2 表示失败次数过多
     */
    public String code;
    /**
     * 错误码对应的说明信息
     */
    public String msg;
    /**
     * 商户APP需要获得的JSON数据
     */
    public String jsonStr;

    @Override
    public String toString() {
        return "RequestParams{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + jsonStr +
                '}';
    }
}
