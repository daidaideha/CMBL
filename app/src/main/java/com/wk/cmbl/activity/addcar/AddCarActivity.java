package com.wk.cmbl.activity.addcar;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.witalk.widget.CMBLTools;
import com.wk.cmbl.R;
import com.wk.cmbl.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class AddCarActivity extends BaseActivity {
    @BindView(id = R.id.rl_choose_car, click = true)
    private RelativeLayout rlChoose;
    @BindView(id = R.id.rl_date, click = true)
    private RelativeLayout rlDate;
    @BindView(id = R.id.btn_save, click = true)
    private Button btnSave;
    @BindView(id = R.id.edt_choose_car)
    private EditText edtChoose;
    @BindView(id = R.id.edt_date)
    private EditText edtDate;
    @BindView(id = R.id.edt_distance)
    private EditText edtDistance;
    @BindView(id = R.id.edt_number)
    private EditText edtNumber;
    @BindView(id = R.id.tv_number, click = true)
    private TextView tvNumber;

    private final int REQUEST_CAR = 1;
    private final int REQUEST_DATE = 2;
    private final int REQUEST_LETTER = 3;
    private final int REQUEST_CODE = 1;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.activity_add_car);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.header_add_car));
    }

    @Override
    protected void initBodyer() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_choose_car:

                break;
            case R.id.rl_date:

                break;
            case R.id.btn_save:

                break;
            case R.id.tv_number:
                CMBLTools.IntentToOtherForResult(this, ChoosePlateActivity.class, null, REQUEST_LETTER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAR && resultCode == REQUEST_CODE) {

        } else if (requestCode == REQUEST_DATE && resultCode == REQUEST_CODE) {

        } else if (requestCode == REQUEST_LETTER && resultCode == REQUEST_CODE) {
            String plate = data.getStringExtra("plate");
            tvNumber.setText(plate);
        }
    }
}
