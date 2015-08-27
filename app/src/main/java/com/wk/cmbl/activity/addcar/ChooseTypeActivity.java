package com.wk.cmbl.activity.addcar;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.TextAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.base.CMBLApplication;
import com.wk.cmbl.model.TextUnit;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/26 0026.
 */
public class ChooseTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
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
        setHeaderTitle(getString(R.string.header_choose_type));
    }

    @Override
    protected void initBodyer() {
        List<TextUnit> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TextUnit model = new TextUnit();
            model.setId(R.id.tv_location);
            model.setContext("2014款 舒适型 " + i);
            list.add(model);
        }
        adatper = new TextAdatper(mListView, list, R.layout.adapter_location);
        mListView.setAdapter(adatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PreferenceHelper.write(this, "ChooseCar", "carType", ((TextUnit) adatper.getItem(position)).getContext());
        finish();
        CMBLApplication.getInstance().finishChooseCar();
    }
}
