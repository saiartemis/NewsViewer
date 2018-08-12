package com.example.zhangxian.newsviewer.Service;


import com.example.zhangxian.newsviewer.Entity.NewItem;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻业务类
 *
 * @user zhangxian
 * @DATE 2018/8/11
 */
public class NewsService {

    public static List<NewItem> getAllNewsInfo(InputStream is) {
        List<NewItem> list = new ArrayList<NewItem>();
        NewItem newItem = null;
        SAXReader saxReader = new SAXReader();
        try {
            //通过解析器解析xml文件
            Document document = saxReader.read(is);
            List<Element> elementList = document.selectNodes("/channel/item");
            //如果传入的是string类型的则使用 doc = DocumentHelper.parseText(string);
            for (int i = 0; i < elementList.size(); i++) {
                newItem = new NewItem();
                Element noteElement = elementList.get(i);
                if (noteElement.selectSingleNode("title") != null) {
                    String title = noteElement.selectSingleNode("title").getText().trim();
                    newItem.setTitle(title);
                }
                if (noteElement.selectSingleNode("description") != null) {
                    String desc = noteElement.selectSingleNode("description").getText().trim();
                    newItem.setDesc(desc);
                }
                if (noteElement.selectSingleNode("image") != null) {
                    String image = noteElement.selectSingleNode("image").getText().trim();
                    newItem.setImage(image);
                }
                if (noteElement.selectSingleNode("type") != null) {
                    String type = noteElement.selectSingleNode("type").getText().trim();
                    newItem.setType(type);
                }
                if (noteElement.selectSingleNode("comment") != null) {
                    String comment = noteElement.selectSingleNode("comment").getText().trim();
                    newItem.setCommentCount(Integer.parseInt(comment));
                }
                list.add(newItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }

}
