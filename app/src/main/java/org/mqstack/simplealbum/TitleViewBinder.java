package org.mqstack.simplealbum;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mqstack.adapter.MViewBinder;

/**
 * Created by mq on 2017/3/30.
 */

public class TitleViewBinder extends MViewBinder<AlbumTitle, TitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.title_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AlbumTitle item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        public void setData(@NonNull AlbumTitle item) {
            tvTitle.setText(item.time);
        }
    }
}
