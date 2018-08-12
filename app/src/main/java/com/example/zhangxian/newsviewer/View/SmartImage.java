package com.example.zhangxian.newsviewer.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @user zhangxian
 * @DATE 2018/8/12
 */

public class SmartImage extends ImageView {


    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    setImageBitmap((Bitmap) msg.obj);
                    break;
                    default:
                        break;
            }
        }
    };

    public SmartImage(Context context) {
        super(context);
    }

    public SmartImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrlAndShow(final String path) {

        try {
            new Thread() {

                @Override
                public void run() {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        InputStream is = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        Message.obtain();
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
