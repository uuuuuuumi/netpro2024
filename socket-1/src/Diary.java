import java.io.Serializable;

public class Diary implements Serializable {

    String date;
    String content;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String present) {
        this.content = present;
    }

}
