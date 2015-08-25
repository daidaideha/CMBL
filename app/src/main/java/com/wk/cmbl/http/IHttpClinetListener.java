package com.wk.cmbl.http;

/**
 * Http请求返回接口
 * Created by Administrator on 2015/8/20 0020.
 */
public interface IHttpClinetListener {
    /***
     * 错误信息返回监听
     * @param errMsg
     */
    void httpErrListener(String errMsg);

    /***
     * 成功请求返回监听
     * @param successMsg
     */
    void httpSuccessListener(String successMsg);
}
