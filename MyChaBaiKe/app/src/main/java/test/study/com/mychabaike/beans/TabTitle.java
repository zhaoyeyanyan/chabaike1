package test.study.com.mychabaike.beans;

/**
 * create in 2016/6/23 9:29 by xinquan(version 1.0)
 * function:
 */
public class TabTitle {
    private String title;
    private int title_id;

    public TabTitle() {
    }

    public TabTitle(String title, int title_id) {
        this.title = title;
        this.title_id = title_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }
}
