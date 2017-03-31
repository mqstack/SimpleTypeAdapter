package org.mqstack.simplealbum;

import org.mqstack.adapter.MTypeAdapter;
import org.mqstack.adapter.TypePool;

import java.util.List;

/**
 * Created by mq on 2017/3/29.
 */

public class AlbumAdapter extends MTypeAdapter {

    public AlbumAdapter(TypePool pool, List<?> items) {
        super(pool, items);
    }
}
