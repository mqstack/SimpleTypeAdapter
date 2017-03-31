package org.mqstack.simplealbum;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by mq on 2017/3/29.
 */

public class MediaUtil {

    public static List<AlbumItem> getAllMediaInfo(Context context, String path) {
        List<AlbumItem> fileInfos = new ArrayList<>();

        String[] imageProjection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};
        Cursor imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imageProjection, MediaStore.Images.Media.DATA + "  like '" + path + "%'", null, null);
        while (imageCursor.moveToNext()) {
            AlbumItem mediaInfo = new AlbumItem();
            mediaInfo.fileName = imageCursor
                    .getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            mediaInfo.filePath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            mediaInfo.time = imageCursor.getLong(imageCursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
            mediaInfo.day = Long.valueOf(formatYYYYMMDD(mediaInfo.time * 1000l));
            mediaInfo.id = imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.Media._ID));
            fileInfos.add(mediaInfo);
        }
        imageCursor.close();
        return fileInfos;
    }

    private static String formatYYYYMMDD(long time) {
        Date date = new Date(time);
        String pattern = "yyyyMMdd";
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        return df.format(date);
    }

    static String getAlbumDateString(Context mContext, long mTime) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date(mTime));
        c2.setTime(new Date());
        int mCurrnetDay = c1.get(Calendar.DAY_OF_YEAR);
        int mSystemDay = c2.get(Calendar.DAY_OF_YEAR);
        int compareNum = mSystemDay - mCurrnetDay;
        if (compareNum == 0) {
            return mContext.getString(R.string.album_today) + " ";
        } else if (compareNum == 1) {
            return mContext.getString(R.string.album_yesterday) + " ";
        }
        return formatyyyy_MM_dd(mTime);
    }

    private static String formatyyyy_MM_dd(long time) {
        Date date = new Date(time);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        return df.format(date);
    }

}
