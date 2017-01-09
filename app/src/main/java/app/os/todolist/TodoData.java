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

    public TodoData(String title, String message, Date date) {
        this.title = title;
        this.message = message;
        this.date = date;
    }
}
