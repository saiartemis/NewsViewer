package com.example.zhangxian.newsviewer;

import com.example.zhangxian.newsviewer.Entity.NewItem;
import com.example.zhangxian.newsviewer.Service.NewsService;

import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    //@Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getXMLTest()
    {
        try
        {
            URL url = new URL("http://192.168.0.105:8080/Test/news.xml");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            List<NewItem> list =NewsService.getAllNewsInfo(is);
            System.out.println(list.get(0).getTitle()+";"+list.get(1).getTitle());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}