package org.mqstack.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mq on 2017/3/28.
 */

public class TypePool {

    private final List<Class<?>> contents;
    private final List<MViewBinder> binders;

    public TypePool() {
        this.contents = new ArrayList<>();
        this.binders = new ArrayList<>();
    }

    public void bindClass(@NonNull Class<?> clazz, @NonNull MViewBinder binder) {
        if (contents.contains(clazz)) {
            int index = contents.indexOf(clazz);
            binders.set(index, binder);
        } else {
            contents.add(clazz);
            binders.add(binder);
        }
    }

    public int findIndex(Class<?> clazz) {
        if (contents.contains(clazz)) {
            return contents.indexOf(clazz);
        } else {
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).isAssignableFrom(clazz)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public MViewBinder getBinderByIndex(int index) {
        return binders.get(index);
    }

    public MViewBinder getBinderByClass(Class<?> clazz) {
        return getBinderByIndex(findIndex(clazz));
    }
}
