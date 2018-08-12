package com.example.zhangxian.newsviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangxian.newsviewer.Entity.NewItem;
import com.example.zhangxian.newsviewer.Service.NewsService;
import com.example.zhangxian.newsviewer.View.SmartImage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int LOAD_SUCCESS = 1;
    private static final int LOAD_FAIL = 2;
    private static final int SHOW_NEWS = 0;
    private LinearLayout ll;
    private ListView lv;
    List<NewItem> lists;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ll.setVisibility(View.INVISIBLE);
            switch (msg.what) {
                case LOAD_SUCCESS:

                    break;
                case SHOW_NEWS:
                    Toast.makeText(MainActivity.this, "加载数据成功", Toast.LENGTH_SHORT).show();
                    lv.setAdapter(new MyNewsAdpter());
                    break;
                case LOAD_FAIL:
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
        getXML();
        //1、初始化控件
        //2、获取xml
        //3、解析xml
        //4、根据xml加载数据
    }

    /**
     * 加载初始化控件
     */
    private void initView() {
        ll = findViewById(R.id.ll);
        lv = findViewById(R.id.lv);
    }

    /**
     * 获取XML信息
     *
     * @return xml
     */
    private String getXML() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    URL url = new URL("http://192.168.0.106:8080/Test/news.xml");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = conn.getInputStream();
                        lists = NewsService.getAllNewsInfo(is);

                        Message.obtain();
                        Message msg = new Message();
                        msg.what = SHOW_NEWS;
                        handler.sendMessage(msg);

                    } else {
                        Message msg = new Message();
                        msg.what = LOAD_FAIL;
                        msg.obj = "加载失败。";
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = LOAD_FAIL;
                    msg.obj = "加载失败。";
                    handler.sendMessage(msg);
                }
            }
        }.start();
        return null;
    }

    private class MyNewsAdpter extends BaseAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public NewItem getItem(int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //inflat布局打气筒，把xml转为view
            NewItem newItem = getItem(i);
            View newView = View.inflate(MainActivity.this, R.layout.news_layout, null);
            SmartImage iv = newView.findViewById(R.id.iv_img);
          //  iv.setImageBitmap();
            iv.setImageUrlAndShow(newItem.getImage());

            TextView title_tv = newView.findViewById(R.id.tv_title);
            TextView tv_desc = newView.findViewById(R.id.tv_desc);
            TextView tv_type = newView.findViewById(R.id.tv_type);

            title_tv.setText(newItem.getTitle());
            tv_desc.setText(newItem.getDesc());
            String type = newItem.getType();
            if(type.equals("1"))
            {
                tv_type.setText("评论："+newItem.getCommentCount());
                tv_type.setBackgroundColor(Color.TRANSPARENT);
                tv_type.setTextColor(Color.BLACK);
            }
            else if(type.equals("2"))
            {
                tv_type.setText("专题");
                tv_type.setBackgroundColor(Color.RED);
                tv_type.setTextColor(Color.WHITE);
            }
            else if(type.equals("3"))
            {
                tv_type.setText("直播");
                tv_type.setBackgroundColor(Color.BLUE);
                tv_type.setTextColor(Color.WHITE);
            }
            return newView;
        }



    }
}
