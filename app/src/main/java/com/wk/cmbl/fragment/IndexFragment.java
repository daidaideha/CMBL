package com.wk.cmbl.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.witalk.widget.CMBLTools;
import com.witalk.widget.HViewPager;
import com.witalk.widget.PullToRefreshView;
import com.witalk.widget.cycleviewpager.ADInfo;
import com.witalk.widget.cycleviewpager.CycleViewPager;
import com.wk.cmbl.R;
import com.wk.cmbl.activity.addcar.AddCarActivity;
import com.wk.cmbl.base.CMBLApplication;
import com.wk.cmbl.model.MyCarUnit;
import com.wk.cmbl.widget.ViewFactory;

import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/28 0028.
 */
public class IndexFragment extends Fragment implements ViewPager.OnPageChangeListener, AddCarFragment.OnAddCarClickListener {
    /***
     * 头部定位按钮
     */
    private PullToRefreshView mPullToRefreshView;
    private RelativeLayout rlViewPager;
    private HViewPager mViewPager;
    private LinearLayout llIndicator;
    private CycleViewPager cycleViewPager;
    private RelativeLayout rlNotebook;

    /***
     * Fragment管理器
     */
    private FragmentManager fragmentManager;
    private List<Fragment> list;
    private String[] imageUrls = {
            "http://a.hiphotos.baidu.com/image/w%3D400/sign=b55764e9e51190ef01fb93dffe1a9df7/838ba61ea8d3fd1f7156163e324e251f95ca5f52.jpg",
            "http://d.hiphotos.baidu.com/image/w%3D400/sign=2887480cfadcd100cd9cf921428b47be/d833c895d143ad4bc253a4c180025aafa40f06b5.jpg",
            "http://h.hiphotos.baidu.com/image/w%3D400/sign=16b3bfd575094b36db921aed93cc7c00/5d6034a85edf8db1ec0d3d6e0a23dd54564e749c.jpg"};
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ImageView> vpIndicator = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private int height;
    private int cycleSpace = 7;// 底部圆点间距
    private FragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Rect outRect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        height = outRect.bottom - outRect.top;
        initData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        fragmentManager = getActivity().getSupportFragmentManager();
        list = new ArrayList<>();
        for (MyCarUnit unit: CMBLApplication.getInstance().getListMyCar()) {
            MyCarFragment fragment = new MyCarFragment();
            fragment.setUnit(unit);
            list.add(fragment);
        }
        AddCarFragment fragment4 = new AddCarFragment();
        fragment4.setOnAddCarClickListener(this);
        list.add(fragment4);
        adapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Fragment fragment = list.get(position);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.remove(fragment);
                if (fragment instanceof MyCarFragment) {
                    MyCarUnit unit = ((MyCarFragment) fragment).getUnit();
                    fragment = new MyCarFragment();
                    ((MyCarFragment) fragment).setUnit(unit);
                }
                else if (fragment instanceof AddCarFragment) {
                    fragment = new AddCarFragment();
                    ((AddCarFragment)fragment).setOnAddCarClickListener(IndexFragment.this);
                }
                ft.add(container.getId(), fragment, fragment.getTag());
                ft.commit();
                return fragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        initPullToRefreshView(view);
        initViewPager(view);
        initCycleViewPager();
        return view;
    }

    private void initPullToRefreshView(View v) {
        mPullToRefreshView = (PullToRefreshView) v.findViewById(R.id.pulltorefresh);
        rlNotebook = (RelativeLayout) v.findViewById(R.id.rl_notebook);

        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
    }

    private void initViewPager(View v) {
        rlViewPager = (RelativeLayout) v.findViewById(R.id.layout_viewpager);
        mViewPager = (HViewPager) v.findViewById(R.id.viewpager);
        llIndicator = (LinearLayout) v.findViewById(R.id.layout_indicator);
        initIndicator();
        int topHeight = (int) getResources().getDimension(R.dimen.dimen_homepage_top_height);
        rlViewPager.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height - topHeight - getStatusBarHeight()));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initIndicator() {
        vpIndicator.clear();
        llIndicator.removeAllViews();
        for (int i = 0; i < list.size(); i ++) {
            ImageView view = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(cycleSpace, 0, 0, 0);
            if (i == 0) {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            view.setLayoutParams(layoutParams);
            vpIndicator.add(view);
            llIndicator.addView(view);
        }
        setIndicator(0);
        mViewPager.setCurrentItem(0);
    }

    /**
     * 设置指示器
     *
     * @param selectedPosition
     *            默认指示器位置
     */
    private void setIndicator(int selectedPosition) {
        for (int i = 0; i < vpIndicator.size(); i++) {
            vpIndicator.get(i).setBackgroundResource(R.drawable.dot);
        }
        if (vpIndicator.size() > selectedPosition)
            vpIndicator.get(selectedPosition).setBackgroundResource(R.drawable.dot_current);
    }

    private void initCycleViewPager() {
        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager()
                .findFragmentById(R.id.cycleviewapger);
        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));
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

    /***
     * 获取状态来高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStop() {
        super.onStop();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        for (Fragment fragment: list) {
//            ft.remove(fragment);
//        }
//        ft.commit();
    }

    @Override
    public void onClick(View v) {
        CMBLTools.IntentToOtherForResult(getActivity(), AddCarActivity.class, null, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            MyCarUnit unit = new MyCarUnit();
            unit.setChoose(data.getStringExtra("choose"));
            unit.setPlate(data.getStringExtra("plate"));
//            KJLoger.log(getActivity().getPackageName(), "----onActivityResult-----choose: " +
//            data.getStringExtra("choose") + " plate: " + data.getStringExtra("plate"));
            MyCarFragment fragment = new MyCarFragment();
            fragment.setUnit(unit);
            list.add(0, fragment);
            adapter.notifyDataSetChanged();
            initIndicator();
        }
    }
}
