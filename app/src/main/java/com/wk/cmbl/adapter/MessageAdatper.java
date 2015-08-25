package com.wk.cmbl.adapter;

import android.widget.AbsListView;

import com.wk.cmbl.R;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2015/7/31 0031.
 */
public class MessageAdatper extends KJAdapter {

    public MessageAdatper(AbsListView view, Collection mDatas, KJBitmap kjBitmap) {
        super(view, mDatas, R.layout.adapter_message);
    }

    @Override
    public void convert(AdapterHolder helper, Object item, boolean isScrolling) {
//        helper.setText(R.id.tv_title, item.toString());
//        helper.setText(R.id.tv_date, item.toString());
//        helper.setText(R.id.tv_context, item.toString());
    }
}
