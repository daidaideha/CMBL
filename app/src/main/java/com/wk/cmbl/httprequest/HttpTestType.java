package com.wk.cmbl.httprequest;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wk.cmbl.base.FinalValue;
import com.wk.cmbl.http.HttpBodyBase;
import com.wk.cmbl.http.HttpThread;
import com.wk.cmbl.http.IHttpClinetListener;

import org.kymjs.kjframe.utils.KJLoger;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class HttpTestType extends HttpBodyBase {
    private HttpThread mHttpThread;
    private String strSuccesMsg = null;
    private String strErrorMsg = null;
    private IHttpClinetListener mIHttpClinetListener;

    public HttpTestType(Application context, int id) {
        super(context);
        mHttpThread = new HttpThread(context);
        setAction("login");
        setUrl("http://app.api.autohome.com.cn/autov4.8.5/cars/seriesprice-pm2-b" + id + "-t2.json");
    }

    @Override
    public void packget() {
    }

    public void setIHttpClinetListener(IHttpClinetListener mIHttpClinetListener) {
        this.mIHttpClinetListener = mIHttpClinetListener;
    }

    @Override
    public void doRequest() {
        KJLoger.log(FinalValue.Http_Tag, "request the http for action -----> " + this.getAction());
        mHttpThread.doRequestGet(this, mHandler);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            if (mIHttpClinetListener != null)
                mIHttpClinetListener.httpSuccessListener(data);
            KJLoger.log(FinalValue.Http_Tag, "http for action " + HttpTestType.this.getAction() + " backData ----->" + data);
//            String action = bundle.getString("action");
//            try {
//                JSONObject jsonObject = new JSONObject(data);
//                int code = jsonObject.getInt("code");
//                String message = jsonObject.getString("msg");
//                if (code == FinalValue.HTTP_SUCCESS) {
//                    strSuccesMsg = jsonObject.getString("data");
//                    KJLoger.log(FinalValue.Http_Tag, "http for action " + HttpTest.this.getAction() + " backMsg ----->" + strSuccesMsg);
//                    if (mIHttpClinetListener != null)
//                        mIHttpClinetListener.httpSuccessListener(strSuccesMsg);
//                } else {
//                    strErrorMsg = message;
//                    KJLoger.log(FinalValue.Http_Tag, "http for action " + HttpTest.this.getAction() + " errMsg ----->" + strErrorMsg);
//                    if (mIHttpClinetListener != null)
//                        mIHttpClinetListener.httpErrListener(strErrorMsg);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            return false;
        }
    });
}
