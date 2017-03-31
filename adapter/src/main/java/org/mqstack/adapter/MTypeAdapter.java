package org.mqstack.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mq on 2017/3/28.
 */

public class MTypeAdapter extends RecyclerView.Adapter {

    private TypePool pool;

    private List<?> items;

    private LayoutInflater inflater;

    public MTypeAdapter(TypePool pool, List<?> items) {
        this.pool = pool;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }
        MViewBinder binder = pool.getBinderByIndex(viewType);
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = items.get(position);
        MViewBinder binder = getBinderByItem(item);
        binder.onBindViewHolder(holder, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = items.get(position);
        return pool.findIndex(obj.getClass());
    }

    private MViewBinder getBinderByItem(Object item) {
        return pool.getBinderByClass(item.getClass());
    }
}
