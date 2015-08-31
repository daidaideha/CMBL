package com.wk.cmbl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.witalk.widget.CMBLTools;
import com.wk.cmbl.R;
import com.wk.cmbl.activity.addcar.AddCarActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2015/8/28 0028.
 */
public class AddCarFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout rlAddCar;
    private OnAddCarClickListener onAddCarClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_addcar, container, false);
        if(mView != null){
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            rlAddCar = (RelativeLayout) mView.findViewById(R.id.layout_addcar);
            rlAddCar.setOnClickListener(this);
            return mView;
        }
        return mView;
    }


    @Override
    public void onClick(View v) {
        if (onAddCarClickListener != null)
            onAddCarClickListener.onClick(v);
    }

    public interface OnAddCarClickListener {
        void onClick(View v);
    }

    public void setOnAddCarClickListener(OnAddCarClickListener onAddCarClickListener) {
        this.onAddCarClickListener = onAddCarClickListener;
    }
}
