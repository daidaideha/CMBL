package com.wk.cmbl.activity.addcar;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wk.cmbl.R;
import com.wk.cmbl.adapter.TextAdatper;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.model.TextUnit;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class ChoosePlateActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(id = R.id.context)
    private GridView mGridView;
    @BindView(id = R.id.drawer)
    private DrawerLayout drawer;
    @BindView(id = R.id.right_drawer)
    private GridView LetterGV;

    private TextAdatper adatper;
    private TextAdatper letterAdatper;
    private String plate = "";

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.actvity_choose_plate);
        setResult(0);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.header_choose_plate));
    }

    @Override
    protected void initBodyer() {

        initGridView();
        initLetter();
    }

    private void initGridView() {
        List<TextUnit> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            TextUnit model = new TextUnit();
            model.setId(R.id.tv_plate);
            model.setContext("浙（浙江）");
            list.add(model);
        }
        adatper = new TextAdatper(mGridView, list, R.layout.adapter_choose_plate);
        mGridView.setAdapter(adatper);
        mGridView.setOnItemClickListener(this);
    }

    private void initLetter() {
        List<TextUnit> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            TextUnit model = new TextUnit();
            model.setId(R.id.tv_plate);
            model.setContext((char)(65 + i) + "");
            list.add(model);
        }
        letterAdatper = new TextAdatper(LetterGV, list, R.layout.adapter_choose_plate);
        LetterGV.setAdapter(letterAdatper);
        LetterGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                plate += ((TextUnit) letterAdatper.getItem(position)).getContext();
                Intent intent = new Intent();
                intent.putExtra("plate", plate);
                setResult(1, intent);
                ChoosePlateActivity.this.finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        drawer.openDrawer(Gravity.RIGHT);
        plate = ((TextUnit) adatper.getItem(position)).getContext().substring(0, 1);
    }
}
