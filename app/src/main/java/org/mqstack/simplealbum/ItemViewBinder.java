package org.mqstack.simplealbum;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.mqstack.adapter.MViewBinder;


/**
 * Created by mq on 2017/3/30.
 */

public class ItemViewBinder extends MViewBinder<AlbumItem, ItemViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ItemViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewBinder.ViewHolder holder, @NonNull AlbumItem item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }

        public void setData(final AlbumItem item) {
            Glide.with(itemView.getContext())
                    .load(Uri.withAppendedPath(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(item.id)))
                    .dontAnimate()
                    .placeholder(R.color.transparent)
                    .centerCrop()
                    .into(ivImage);

        }
    }
}
