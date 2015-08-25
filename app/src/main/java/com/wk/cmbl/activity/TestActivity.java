package com.wk.cmbl.activity;

import android.view.View;
import android.widget.ImageView;

import com.wk.cmbl.R;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.widget.MyScrollView;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class TestActivity extends BaseActivity {
    @BindView(id = R.id.scrollView)
    private MyScrollView myScrollView;
//    @BindView(id = R.id.iv_back)
    private ImageView ivBack;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.layout_test);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderBarHide(true);
    }

    @Override
    protected void initBodyer() {
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    setHeaderBarHide(true);
                    ivBack.setVisibility(View.VISIBLE);
                } else {
                    setHeaderBarHide(false);
                    ivBack.setVisibility(View.GONE);
                }
//                CMBLTools.toastMsg("Y: " + scrollY, TestActivity.this);
            }
        });
    }
}
