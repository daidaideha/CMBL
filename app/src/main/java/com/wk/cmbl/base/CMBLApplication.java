package com.wk.cmbl.base;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.wk.cmbl.model.MyCarUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/13 0013.
 */
public class CMBLApplication extends Application {
    /***
     * 公用application
     */
    public static CMBLApplication application;
    /***
     * token有效令牌
     */
    private String token = "";
    private List<Activity> listChooseCar;
    private List<MyCarUnit> listMyCar;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        application = this;
        listChooseCar = new ArrayList<>();
        listMyCar = new ArrayList<>();
    }

    /***
     * 获取公用的application
     * @author Pro.Linyl
     * @CreateTime 2015年7月13日
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @return 公用的application
     */
    public static CMBLApplication getInstance() {
        return application;
    }

    /***
     * 获取Token有效令牌
     * @author Pro.Linyl
     * @CreateTime 2015年7月13日
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @return Token令牌
     */
    public String getToken() {
        return token;
    }

    /***
     * 设置Token有效令牌
     * @author Pro.Linyl
     * @CreateTime 2015年7月13日
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @param token Token有效令牌
     */
    public void setToken(String token) {
        this.token = token;
    }

    public void removeChooseCar(Activity activity) {
        if (listChooseCar.contains(activity))
            listChooseCar.remove(activity);
    }

    public void addChooseCar(Activity activity) {
        listChooseCar.add(activity);
    }

    public void finishChooseCar() {
        for (Activity activity : listChooseCar)
            activity.finish();
    }

    public List<MyCarUnit> getListMyCar() {
        return listMyCar;
    }

    public void addMyCar(MyCarUnit unit) {
        listMyCar.add(0, unit);
    }
}
