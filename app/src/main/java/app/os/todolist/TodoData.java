package app.os.todolist;

import android.util.JsonReader;
import android.util.JsonWriter;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoData {
    public static final String TAG = "TodoData";
    String title = "Title";
    String message = "Message";
    Calendar calendar;
    boolean isDoneTask;

    public TodoData() {
        this.calendar = Calendar.getInstance();
    }

    public TodoData(String title) {
        this.title = title;
        this.calendar = Calendar.getInstance();
    }

    public TodoData(String title, String message, Calendar calendar) {
        this.title = title;
        this.message = message;
        this.calendar = calendar;
    }

    public void writeJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("title").value(title);
        writer.name("message").value(message);
        writer.name("year").value(calendar.get(Calendar.YEAR));
        writer.name("month").value(calendar.get(Calendar.MONTH));
        writer.name("day").value(calendar.get(Calendar.DAY_OF_MONTH));
        writer.name("hour").value(calendar.get(Calendar.HOUR));
        writer.name("minute").value(calendar.get(Calendar.MINUTE));
        writer.endObject();
    }

    public TodoData readJson(JsonReader reader) throws IOException {
        TodoData tmp = new TodoData();
        reader.beginObject();
        reader.nextName();
        tmp.setTitle(reader.nextString());
        reader.nextName();
        tmp.setMessage(reader.nextString());
        int year, month, day;
        reader.nextName();
        year = reader.nextInt();
        reader.nextName();
        month = reader.nextInt();
        reader.nextName();
        day = reader.nextInt();
        tmp.setDate(year, month, day);
        int hour, minute;
        reader.nextName();
        hour = reader.nextInt();
        reader.nextName();
        minute = reader.nextInt();
        tmp.setTime(hour, minute);
        reader.endObject();
        return tmp;
    }

    public void setDate(int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    public void setTime(int hour, int minute) {
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
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

    public String getDate() {
        return calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getDisplayDate() {
        return calendar.get(Calendar.YEAR) + "/"
                + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

    }

    public String getTime() {
        return calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
    }

    public String getDisplayTime() {
        return String.format("%02d", calendar.get(Calendar.HOUR)) +
                ":" + String.format("%02d", calendar.get(Calendar.MINUTE));
    }

    public boolean isDoneTask() {
        return isDoneTask;
    }

    public void setDoneTask(boolean doneTask) {
        isDoneTask = doneTask;
    }

    @Override
    public String toString() {
        return title + ":" + message;
    }
}
