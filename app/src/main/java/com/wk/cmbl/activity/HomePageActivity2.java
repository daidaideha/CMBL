package com.wk.cmbl.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.witalk.widget.CMBLTools;
import com.witalk.widget.HViewPager;
import com.witalk.widget.PullToRefreshView;
import com.witalk.widget.cycleviewpager.ADInfo;
import com.witalk.widget.cycleviewpager.CycleViewPager;
import com.wk.cmbl.R;
import com.wk.cmbl.activity.addcar.AddCarActivity;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.fragment.AddCarFragment;
import com.wk.cmbl.fragment.IndexFragment;
import com.wk.cmbl.fragment.MyCarFragment;
import com.wk.cmbl.fragment.MyFragment;
import com.wk.cmbl.fragment.OrderFragment;
import com.wk.cmbl.widget.LocationManager;
import com.wk.cmbl.widget.ViewFactory;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.utils.KJLoger;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/28 0028.
 */
public class HomePageActivity2 extends KJActivity implements LocationManager.ReceiveLocationListener, View.OnFocusChangeListener {
    @BindView(id = R.id.tv_title)
    private TextView tvTitle;
    @BindView(id = R.id.tv_location, click = true)
    private TextView tvLocation;
    @BindView(id = R.id.rl_header_message, click = true)
    private RelativeLayout rlMessage;
    @BindView(id = R.id.layout_bodyer)
    private RelativeLayout rlBodyer;
    @BindView(id = R.id.rl_homepage_index, click = true)
    private RelativeLayout rlIndex;
    @BindView(id = R.id.rl_homepage_order, click = true)
    private RelativeLayout rlOrder;
    @BindView(id = R.id.rl_homepage_my, click = true)
    private RelativeLayout rlMy;

    private IndexFragment indexFragment;
    private OrderFragment orderFragment;
    private MyFragment myFragment;

    private LocationManager locationManager;
    /***
     * Fragment管理器
     */
    private FragmentManager fragmentManager;
    /***
     * 第二次按退出的时间
     */
    private long time = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_homepage2);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        rlIndex.setOnFocusChangeListener(this);
        rlOrder.setOnFocusChangeListener(this);
        rlMy.setOnFocusChangeListener(this);
        rlIndex.performClick();
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.onDestroy();
    }

    @Override
    public void initData() {
        super.initData();
        locationManager = new LocationManager(this);
        locationManager.initLocation(false);
        locationManager.setReceiveLocationListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_location)
                showActivity(HomePageActivity2.this, LocationActivity.class);
        else if (v.getId() == R.id.rl_header_message)
                showActivity(HomePageActivity2.this, MessageActivity.class);
        else {
            v.requestFocus();
            seTitle(v.getId());
            setTabSelection(v.getId());
        }
    }

    /***
     * 底部菜单选中后操作
     * @author Pro.Linyl
     * @CreateTime 2015.07.13
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @param id 控件id
     */
    private void setTabSelection(int id) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
//        rlBodyer.removeAllViews();
        showFragment(id, transaction);
//        switch (id) {
//            case R.id.rl_homepage_index:// 首页
//                if (indexFragment == null)
//                    indexFragment = new IndexFragment();
//                transaction.replace(R.id.layout_bodyer, indexFragment);
//                break;
//            case R.id.rl_homepage_order:// 订单
//                if (orderFragment == null)
//                    orderFragment = new OrderFragment();
//                transaction.replace(R.id.layout_bodyer, orderFragment);
//                break;
//            case R.id.rl_homepage_my:// 我的
//                if (myFragment == null)
//                    myFragment = new MyFragment();
//                transaction.replace(R.id.layout_bodyer, myFragment);
//                break;
//            default:
//                break;
//        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (indexFragment != null)
            transaction.hide(indexFragment);
        if (orderFragment != null)
            transaction.hide(orderFragment);
        if (myFragment != null)
            transaction.hide(myFragment);
    }

    private void showFragment(int id, FragmentTransaction transaction) {
        switch (id) {
            case R.id.rl_homepage_index:// 首页
                if (indexFragment == null) {
                    indexFragment = new IndexFragment();
                    transaction.add(R.id.layout_bodyer, indexFragment);
                } else {
                    transaction.show(indexFragment);
                }
                break;
            case R.id.rl_homepage_order:// 订单
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.layout_bodyer, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
                break;
            case R.id.rl_homepage_my:// 我的
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.layout_bodyer, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
    }

    /***
     * 标题改变操作
     * @author Pro.Linyl
     * @CreateTime 2015.07.13
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @param id 控件id
     */
    private void seTitle(int id) {
        // TODO Auto-generated method stub
        String title = "";
        switch (id) {
            case R.id.rl_homepage_index:
                tvLocation.setVisibility(View.VISIBLE);
                rlMessage.setVisibility(View.VISIBLE);
                title = getString(R.string.app_name);
                break;
            case R.id.rl_homepage_order:
                tvLocation.setVisibility(View.GONE);
                rlMessage.setVisibility(View.GONE);
                title = getString(R.string.tv_homepage_order);
                break;
            case R.id.rl_homepage_my:
                tvLocation.setVisibility(View.GONE);
                rlMessage.setVisibility(View.GONE);
                title = getString(R.string.tv_homepage_my);
                break;
            default:
                break;
        }
        tvTitle.setText(title);
    }

    @Override
    public void onReceiveLocation() {
        CMBLTools.toastMsg(locationManager.getCity(), this);
        if (!CMBLTools.isValueEmpty(locationManager.getCity()))
            tvLocation.setText(locationManager.getCity());
        KJLoger.log(this.getPackageName(), locationManager.getCity());
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
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            v.performClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (indexFragment != null)
            indexFragment.onActivityResult(requestCode, resultCode, data);
    }
}
