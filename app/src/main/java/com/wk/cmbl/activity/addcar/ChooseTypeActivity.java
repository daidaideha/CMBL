package com.wk.cmbl.activity.addcar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.CarTypeAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.model.CarTypeUnit;
import com.wk.cmbl.widget.MySideBar;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class ChooseTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(id = R.id.listview)
    private ListView mListView;
    @BindView(id = R.id.sideBar)
    private MySideBar sideBar;

    private List<CarTypeUnit> listData;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.activity_choose_type);
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
    protected void initBodyer() {;
        CarTypeAdatper adatper = new CarTypeAdatper(this);
        adatper.clearList();
        adatper.addList(listData);
        sideBar.setListView(mListView);
        sideBar.setAdapter(adatper);

        mListView.setAdapter(adatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        listData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CarTypeUnit unit = new CarTypeUnit();
            if (i < 5) {
                unit.setPinyin("a");
            } else if (i < 10 && i >= 5) {
                unit.setPinyin("b");
            } else if (i < 15 && i >= 10) {
                unit.setPinyin("c");
            } else if (i < 25 && i >= 15) {
                unit.setPinyin("d");
            } else if (i < 40 && i >= 25) {
                unit.setPinyin("f");
            } else if (i < 50 && i >= 40) {
                unit.setPinyin("x");
            }
            unit.setName(unit.getPinyin() + i);
            unit.setCode(i);
            listData.add(unit);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
