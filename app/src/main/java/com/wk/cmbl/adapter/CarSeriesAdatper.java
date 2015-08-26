package com.wk.cmbl.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wk.cmbl.R;
import com.wk.cmbl.model.CarSeriesUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/15 0015.
 */
public class CarSeriesAdatper extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CarSeriesUnit> listData;

    public CarSeriesAdatper(Activity context) {
        this.context = context;
        this.inflater = context.getLayoutInflater();
        listData = new ArrayList<>();
    }

    public void clearList() {
        listData.clear();
    }

    public void addList(List<CarSeriesUnit> listData) {
        this.listData.addAll(listData);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		String nickName_abc = listData.get(position).getType();
        convertView = inflater.inflate(R.layout.adapter_car_series, null);

        View line = convertView.findViewById(R.id.line);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_car_type);
        TextView tv_catalog = (TextView) convertView.findViewById(R.id.tv_catalog);
        TextView tv_code = (TextView) convertView.findViewById(R.id.tv_code);

        tv_name.setText(listData.get(position).getName());
        tv_catalog.setText(nickName_abc);
        tv_code.setText(listData.get(position).getCode() + "");
		if (position == 0) {
			tv_catalog.setVisibility(View.VISIBLE);
			line.setVisibility(View.GONE);
			tv_catalog.setText(nickName_abc);
		} else {
			String catalog = listData.get(position - 1).getType();
			if (nickName_abc.equals(catalog)) {
				tv_catalog.setVisibility(View.GONE);
				line.setVisibility(View.VISIBLE);
			} else {
				tv_catalog.setVisibility(View.VISIBLE);
				line.setVisibility(View.GONE);
				tv_catalog.setText(nickName_abc);
			}
		}
        return convertView;
    }

}
