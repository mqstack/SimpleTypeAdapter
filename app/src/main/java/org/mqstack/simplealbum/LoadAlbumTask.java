package org.mqstack.simplealbum;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mq on 2017/3/29.
 */

public class LoadAlbumTask {

    @NonNull
    public List<Object> loadAlbums(@NonNull Context context) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/OpenCamera";
        return addHeader(context, MediaUtil.getAllMediaInfo(context, path));
    }

    @NonNull
    private List<Object> addHeader(@NonNull Context context, @NonNull List<AlbumItem> albumItems) {
        List<Object> result = new ArrayList<>();
        Collections.sort(albumItems, new Comparator<AlbumItem>() {
            @Override
            public int compare(AlbumItem o1, AlbumItem o2) {
                return (int) (o2.time - o1.time);
            }
        });
        long lastDay = 0;
        for (AlbumItem item : albumItems) {
            if (item.day != lastDay) {
                result.add(new AlbumTitle(MediaUtil.getAlbumDateString(context, item.time * 1000)));
            }
            result.add(item);
            lastDay = item.day;
        }
        return result;
    }
}
