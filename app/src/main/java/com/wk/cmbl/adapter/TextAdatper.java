package com.wk.cmbl.adapter;

import android.widget.AbsListView;

import com.wk.cmbl.model.TextUnit;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2015/7/31 0031.
 */
public class TextAdatper extends KJAdapter {

    public TextAdatper(AbsListView view, Collection<TextUnit> mDatas, int layout) {
        super(view, mDatas, layout);
    }

    @Override
    public void convert(AdapterHolder helper, Object item, boolean isScrolling) {
        helper.setText(((TextUnit)item).getId(), ((TextUnit)item).getContext());
    }
}
