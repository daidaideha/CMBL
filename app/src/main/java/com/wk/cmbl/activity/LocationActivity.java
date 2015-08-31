package com.wk.cmbl.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.witalk.widget.CMBLTools;
import com.wk.cmbl.R;
import com.wk.cmbl.adapter.TextAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.model.TextUnit;
import com.wk.cmbl.widget.LocationManager;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class LocationActivity extends BaseActivity implements LocationManager.ReceiveLocationListener {
    @BindView(id = R.id.listview)
    private ListView mListView;
    private TextView curLocation;

    private TextAdatper adatper;
    private LocationManager locationManager;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.view_listview);
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
        curLocation = (TextView) header.findViewById(R.id.tv_cur_location);
        mListView.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.view_location_footer, null);
        mListView.addFooterView(footer);
        mListView.setBackgroundColor(getResources().getColor(R.color.color_bg_grey));

        List<TextUnit> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TextUnit model = new TextUnit();
            model.setId(R.id.tv_location);
            model.setContext("北京");
            list.add(model);
        }
        adatper = new TextAdatper(mListView, list, R.layout.adapter_location);
        mListView.setAdapter(adatper);
        mListView.setDividerHeight(0);

        curLocation.setText("正在定位中……");
    }

    @Override
    public void onReceiveLocation() {
        CMBLTools.toastMsg(locationManager.getCity(), this);
        if (!CMBLTools.isValueEmpty(locationManager.getCity()))
            curLocation.setText(locationManager.getCity());
        KJLoger.log(this.getPackageName(), locationManager.getCity());
    }
}
