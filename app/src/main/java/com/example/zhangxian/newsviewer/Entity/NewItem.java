package com.example.zhangxian.newsviewer.Entity;

/**
 * 新闻实体类
 * @user zhangxian
 * @DATE 2018/8/11
 */
public class NewItem {

    private String title;

    private String desc;

    private String image;

    private String type;

    private int commentCount;


    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "NewItem{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }
}
