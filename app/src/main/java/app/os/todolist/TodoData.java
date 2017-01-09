package app.os.todolist;

import java.util.Date;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoData {
    String title="Title";
    String message="Message";
    Date date;

    public TodoData() {
    }
    public TodoData(String title){
        this.title =title;
    }

    public TodoData(String title, String message, Date date) {
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
