package com.wk.cmbl.activity.addcar;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.CarSeriesAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.base.CMBLApplication;
import com.wk.cmbl.model.CarSeriesUnit;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/26 0026.
 */
public class ChooseSeriesActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(id = R.id.listview)
    private ListView mListView;

    private CarSeriesAdatper adatper;

    private List<CarSeriesUnit> listData;

    @Override
    protected void onResume() {
        super.onResume();
        CMBLApplication.getInstance().removeChooseCar(this);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.view_listview);
        listData = new ArrayList<>();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.header_choose_series));
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
        for (int i = 0; i < 10; i++) {
            CarSeriesUnit model = new CarSeriesUnit();
            model.setCode(i);
            model.setName("奥迪A" + i);
            model.setType("一汽奥迪");
            listData.add(model);
        }
        for (int i = 0; i < 10; i++) {
            CarSeriesUnit model = new CarSeriesUnit();
            model.setCode(i);
            model.setName("大众" + i);
            model.setType("一汽大众");
            listData.add(model);
        }
        adatper = new CarSeriesAdatper(this);
        adatper.clearList();
        adatper.addList(listData);
        mListView.setAdapter(adatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CMBLApplication.getInstance().addChooseCar(this);
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
