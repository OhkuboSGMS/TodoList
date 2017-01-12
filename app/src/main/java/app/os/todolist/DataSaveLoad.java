package app.os.todolist;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ookubo on 2017/01/12.
 */
public interface DataSaveLoad<T> {
    /**
     * 関数内でclose及びflushを行うこと
     * @param out
     */
    void save(OutputStream out);

    /**
     * 関数内でcloseを行うこと
     * @param input
     */
    T[] load(InputStream input);
}
