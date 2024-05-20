package site.rainbowx.demo;

/**
 * @author yan32
 * createTime: 2024/04/30 10:57
 * description: 文章构成
 */
public class Diary {
    int id;
    String title, content, write_date;

    public Diary(int id, String title, String content, String write_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.write_date = write_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }
}
