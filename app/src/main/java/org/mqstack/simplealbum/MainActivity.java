package org.mqstack.simplealbum;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.mqstack.adapter.TypePool;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rlAlbum;
    TypePool pool;

    List<Object> data;
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlAlbum = (RecyclerView) findViewById(R.id.rlAlbum);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        final GridLayoutManager.SpanSizeLookup lookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object obj = data.get(position);
                return obj instanceof AlbumTitle ? 4 : 1;
            }
        };
        manager.setSpanSizeLookup(lookup);
        rlAlbum.setLayoutManager(manager);
        final int albumSpace = getResources().getDimensionPixelSize(R.dimen.album_span);
        rlAlbum.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
//                int span = lookup.getSpanSize(position);
//                if (span == 1) {
                outRect.left = albumSpace;
//                    if (position % 4 == 0) {
                outRect.right = albumSpace;
//                    }
//                }
            }
        });
        pool = new TypePool();
        pool.bindClass(AlbumTitle.class, new TitleViewBinder());
        pool.bindClass(AlbumItem.class, new ItemViewBinder());
        checkStoragePermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadData();
                } else {
                    Toast.makeText(this, R.string.no_permission_tip, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//            } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
//            }
//            return;
        } else {
            loadData();
        }
    }


    public void loadData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = new LoadAlbumTask().loadAlbums(MainActivity.this);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rlAlbum.setAdapter(new AlbumAdapter(pool, data));
                    }
                });
            }
        }).start();
    }
}
