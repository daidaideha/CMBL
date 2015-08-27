package com.wk.cmbl.activity.addcar;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.witalk.widget.CMBLTools;
import com.wk.cmbl.R;
import com.wk.cmbl.adapter.CarBrandAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.base.CMBLApplication;
import com.wk.cmbl.model.CarBrandUnit;
import com.wk.cmbl.widget.MySideBar;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class ChooseBrandActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(id = R.id.listview)
    private ListView mListView;
    @BindView(id = R.id.sideBar)
    private MySideBar sideBar;

    private List<CarBrandUnit> listData;

    @Override
    protected void onResume() {
        super.onResume();
        CMBLApplication.getInstance().removeChooseCar(this);
        PreferenceHelper.remove(this, "ChooseCar", "carBrand");
    }

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
        setHeaderTitle(getString(R.string.header_choose_brand));
        setOnHeaderLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });
    }

    @Override
    protected void initBodyer() {
        CarBrandAdatper adatper = new CarBrandAdatper(this);
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
            CarBrandUnit unit = new CarBrandUnit();
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
        PreferenceHelper.write(this, "ChooseCar", "carBrand", listData.get(position).getName());
        setResult(1);
        CMBLApplication.getInstance().addChooseCar(this);
        showActivity(this, ChooseSeriesActivity.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            setResult(0);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
