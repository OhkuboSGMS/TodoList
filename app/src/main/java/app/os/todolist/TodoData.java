package app.os.todolist;

import java.util.Calendar;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoData {
    String title="Title";
    String message="Message";
    Calendar calendar;

    public TodoData() {
        this.calendar =Calendar.getInstance();
    }
    public TodoData(String title){
        this.title =title;
        this.calendar =Calendar.getInstance();
    }

    public TodoData(String title, String message, Calendar calendar) {
        this.title = title;
        this.message = message;
        this.calendar = calendar;
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

    public void setDate(int year,int month,int dayOfMonth){
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
    }
    public void setTime(int hour,int minute){
        calendar.set(Calendar.HOUR,hour);
        calendar.set(Calendar.MINUTE,minute);
    }


}
