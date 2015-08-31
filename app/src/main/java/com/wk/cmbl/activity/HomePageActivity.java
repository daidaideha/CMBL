package com.wk.cmbl.activity;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.witalk.widget.CMBLTools;
import com.witalk.widget.cycleviewpager.ADInfo;
import com.witalk.widget.cycleviewpager.CycleViewPager;
import com.wk.cmbl.R;
import com.wk.cmbl.activity.addcar.AddCarActivity;
import com.wk.cmbl.widget.LocationManager;
import com.wk.cmbl.widget.ViewFactory;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/6 0006.
 */
public class HomePageActivity extends KJActivity implements LocationManager.ReceiveLocationListener {
    /***
     * 侧边栏菜单布局控件
     */
    @BindView(id = R.id.menu)
    private SlidingMenu slidingMenu;
    /***
     * 头部个人中心按钮
     */
    @BindView(id = R.id.rl_header_person, click = true)
    private RelativeLayout rkPerson;
    /***
     * 头部定位按钮
     */
    @BindView(id = R.id.tv_location, click = true)
    private TextView tvLocation;
    private CycleViewPager cycleViewPager;
    @BindView(id = R.id.tv_message, click = true)
    private TextView tvMessage;
    @BindView(id = R.id.maintain, click = true)
    private RelativeLayout rlMaintain;

    private String[] imageUrls = {
            "http://d.hiphotos.baidu.com/image/w%3D400/sign=8d6ddf17bb389b5038ffe152b534e5f1/6d81800a19d8bc3e0a89c3c3808ba61ea8d34532.jpg",
            "http://h.hiphotos.baidu.com/image/w%3D400/sign=91e3c526fffaaf5184e380bfbc5594ed/314e251f95cad1c8f87e91277d3e6709c83d51ec.jpg",
            "http://b.hiphotos.baidu.com/image/w%3D400/sign=3cb9e5f779899e51788e3b1472a7d990/f9198618367adab443ae2d4989d4b31c8701e4b9.jpg",
            "http://a.hiphotos.baidu.com/image/w%3D400/sign=b55764e9e51190ef01fb93dffe1a9df7/838ba61ea8d3fd1f7156163e324e251f95ca5f52.jpg",
            "http://d.hiphotos.baidu.com/image/w%3D400/sign=2887480cfadcd100cd9cf921428b47be/d833c895d143ad4bc253a4c180025aafa40f06b5.jpg",
            "http://h.hiphotos.baidu.com/image/w%3D400/sign=16b3bfd575094b36db921aed93cc7c00/5d6034a85edf8db1ec0d3d6e0a23dd54564e749c.jpg"};
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    /***
     * 第二次按退出的时间
     */
    private long time = 0;
    private LocationManager locationManager;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_homepage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// Translucent navigation bar
//			window.setFlags(
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.cycleviewapger);
    }

    @Override
    public void initData() {
        super.initData();
        locationManager = new LocationManager(this);
        locationManager.initLocation(false);
        locationManager.setReceiveLocationListener(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initBodyer();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_header_person:
                slidingMenu.toggle();
                break;
            case R.id.tv_location:
                showActivity(this, LocationActivity.class);
                break;
            case R.id.tv_message:
                showActivity(this, MessageActivity.class);
                break;
            case R.id.maintain:
                showActivity(this, AddCarActivity.class);
                break;
        }
    }

    protected void initBodyer() {
        initSlidingMenu();
        initViewPager();
    }

    private void initSlidingMenu() {
        View menu = getLayoutInflater().inflate(R.layout.layout_home_menu, null);
        slidingMenu.setMenu(menu);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setMode(SlidingMenu.LEFT);
    }

    private void initViewPager() {
        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));
        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);
        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, null);
        //设置轮播
        cycleViewPager.setWheel(true);
        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(5000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            int LENG_EXIT = 1500;
            if (System.currentTimeMillis() - time > LENG_EXIT) {
                Toast.makeText(this, getResources().getString(R.string.toast_double_exit),
                        Toast.LENGTH_LONG).show();
                time = System.currentTimeMillis();
            } else {
                KJActivityStack.create().AppExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onReceiveLocation() {
        CMBLTools.toastMsg(locationManager.getCity(), this);
        if (!CMBLTools.isValueEmpty(locationManager.getCity()))
            tvLocation.setText(locationManager.getCity());
        KJLoger.log(this.getPackageName(), locationManager.getCity());
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.onDestroy();
    }
}
