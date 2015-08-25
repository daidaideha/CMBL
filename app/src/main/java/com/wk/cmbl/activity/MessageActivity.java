package com.wk.cmbl.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.MessageAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.witalk.widget.PullToRefreshView;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class MessageActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener, AdapterView.OnItemClickListener {
    @BindView(id = R.id.listview)
    private ListView mListView;
    @BindView(id = R.id.pulltorefresh)
    private PullToRefreshView mPullToRefreshView;

    private MessageAdatper adatper;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.view_refresh_list);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.header_message));
    }

    @Override
    protected void initBodyer() {
        mPullToRefreshView.setEnablePullTorefresh(false);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        mListView.setDividerHeight(Math.round(getResources().getDimension(R.dimen.message_list_divider_height)));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            list.add("position " + i);
        adatper = new MessageAdatper(mListView, list, null);
        mListView.setAdapter(adatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showActivity(this, WebViewActivity.class);
    }
}
