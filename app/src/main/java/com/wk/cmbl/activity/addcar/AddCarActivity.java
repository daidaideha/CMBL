package com.wk.cmbl.activity.addcar;

import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.witalk.widget.CMBLTools;
import com.wk.cmbl.R;
import com.wk.cmbl.base.BaseActivity;
import com.wk.cmbl.widget.DialogDate;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.PreferenceHelper;

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

    private DialogDate dialog;

    private final int REQUEST_CAR = 1;
    private final int REQUEST_DATE = 2;
    private final int REQUEST_LETTER = 3;
    private final int REQUEST_CODE = 1;

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.activity_add_car);
    }

    private void initDialog() {
        dialog = new DialogDate(this, R.style.dialog);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.dialogWindowAnim);  //添加动画
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth(); //设置宽度
        window.setAttributes(lp);
        dialog.setBtnSubmit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDate.setText(dialog.getDate());
                dialog.dismiss();
            }
        });
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
        initDialog();
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
                CMBLTools.IntentToOtherForResult(this, ChooseBrandActivity.class, null, REQUEST_CAR);
                break;
            case R.id.rl_date:
                dialog.show();
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
            String choose = PreferenceHelper.readString(this, "ChooseCar", "carSeries") + " "
                    + PreferenceHelper.readString(this, "ChooseCar", "carType");
            edtChoose.setText(choose);
        } else if (requestCode == REQUEST_DATE && resultCode == REQUEST_CODE) {

        } else if (requestCode == REQUEST_LETTER && resultCode == REQUEST_CODE) {
            String plate = data.getStringExtra("plate");
            tvNumber.setText(plate);
        }
    }
}
