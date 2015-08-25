package com.wk.cmbl.activity;

import android.view.View;
import android.widget.ListView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.TextAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.model.TextModel;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class LocationActivity extends BaseActivity {
    @BindView(id = R.id.listview)
    private ListView mListView;

    private TextAdatper adatper;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.view_listview);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.header_location));
    }

    @Override
    protected void initBodyer() {
        View header = getLayoutInflater().inflate(R.layout.view_location_header, null);
        mListView.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.view_location_footer, null);
        mListView.addFooterView(footer);
        mListView.setBackgroundColor(getResources().getColor(R.color.color_bg_grey));

        List<TextModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TextModel model = new TextModel();
            model.setId(R.id.tv_location);
            model.setContext("北京");
            list.add(model);
        }
        adatper = new TextAdatper(mListView, list, R.layout.adapter_location);
        mListView.setAdapter(adatper);
        mListView.setDividerHeight(0);

    }
}
