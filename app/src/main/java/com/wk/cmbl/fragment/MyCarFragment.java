package com.wk.cmbl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wk.cmbl.R;
import com.wk.cmbl.model.MyCarUnit;

/**
 * Created by Administrator on 2015/8/28 0028.
 */
public class MyCarFragment extends Fragment {
    private TextView tvNumber;
    private TextView tvCar;
    private MyCarUnit unit;

    public MyCarUnit getUnit() {
        return unit;
    }

    public void setUnit(MyCarUnit unit) {
        this.unit = unit;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_car, container, false);
        tvNumber = (TextView) mView.findViewById(R.id.tv_number);
        tvCar = (TextView) mView.findViewById(R.id.tv_car);

        tvNumber.setText(unit.getPlate());
        tvCar.setText(unit.getChoose());
        return mView;
    }


}
