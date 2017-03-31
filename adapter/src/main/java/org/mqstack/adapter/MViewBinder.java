package org.mqstack.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by mq on 2017/3/28.
 */

public abstract class MViewBinder<T, V extends RecyclerView.ViewHolder> {

    @NonNull
    protected abstract V onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull V holder, @NonNull T item);
}
