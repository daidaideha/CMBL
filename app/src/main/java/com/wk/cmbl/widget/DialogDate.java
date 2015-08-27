package com.wk.cmbl.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.wk.cmbl.R;
import com.wk.cmbl.widget.wheelview.NumericWheelAdapter;
import com.wk.cmbl.widget.wheelview.WheelView;

/**
 * Created by Administrator on 2015/8/27 0027.
 */
public class DialogDate extends Dialog {
    private WheelView wheelYear;
    private WheelView wheelMonth;
    private Button btnCancel;
    private Button btnSubmit;
    private int START_YEAR = 2000;

    public DialogDate(Context context) {
        super(context);
        initWidget();
    }

    public DialogDate(Context context, int theme) {
        super(context, theme);
        initWidget();
    }

    private void initWidget() {
        setContentView(R.layout.dialog_choose_date);
        wheelYear = (WheelView) findViewById(R.id.wheel_year);
        wheelMonth = (WheelView) findViewById(R.id.wheel_month);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        int itemHeight = (int)getContext().getResources().getDimension(R.dimen.wheel_item_height);
        int textSize = (int)getContext().getResources().getDimension(R.dimen.wheel_item_text);
        wheelYear.setCyclic(true);
        wheelMonth.setCyclic(true);
        wheelYear.setVisibleItems(5);
        wheelMonth.setVisibleItems(5);
        wheelYear.setItemHeight(itemHeight);
        wheelMonth.setItemHeight(itemHeight);
        wheelYear.setOffSet((itemHeight) / 2);
        wheelMonth.setOffSet((itemHeight) / 2);
        wheelYear.setTextSize(textSize);
        wheelMonth.setTextSize(textSize);
        wheelYear.setAdapter(new NumericWheelAdapter(START_YEAR, START_YEAR + 100));
        wheelMonth.setAdapter(new NumericWheelAdapter(1, 12));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDate.this.dismiss();
            }
        });
    }

    public void setBtnSubmit(View.OnClickListener onClickListener) {
        if (onClickListener != null)
            btnSubmit.setOnClickListener(onClickListener);
    }

    public String getDate() {
        String year = (wheelYear.getCurrentItem() + START_YEAR) + "-" + add0(wheelMonth.getCurrentItem() + 1);
        return year;
    }

    private String add0(int size) {
        if (size < 10) {
            return "0" + size;
        }
        return size + "";
    }
}
