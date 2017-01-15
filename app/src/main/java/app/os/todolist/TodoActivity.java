package app.os.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Activity
 */
public class TodoActivity extends AppCompatActivity implements OnCommunicateFragments, DataSaveLoad {
    public static final String TAG = "TodoActivity";
    //アイテムの情報を編集するフラグメント
    TodoEditFragment mTodoEditFragment;
    //アイテムをリストで表示するフラグメント
    TodoActivityFragment mTodoActivityFragment;
    //データ保存するファイル名(とりあえず固定している)
    private String fileName = "todo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //各フラグメントを作成
        mTodoEditFragment = new TodoEditFragment();
        mTodoActivityFragment = new TodoActivityFragment();
        mTodoActivityFragment.setTransFragment(mTodoEditFragment);
        //アクティビティにリストフラグメントを追加
        getSupportFragmentManager().beginTransaction().
                add(R.id.frame, mTodoActivityFragment, TodoActivityFragment.TAG).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    /**
     * 右上の︙の表示部分の処理
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * OnStart の後に呼ばれる
     * fileNameのファイルが存在すればデータを読み込む
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            TodoAdapter adapter = new TodoAdapter(this, R.layout.item_todo);
            TodoData data[] = load(openFileInput(fileName));
            if (data != null) {
                for (int i = 0; i < data.length; i++) adapter.add(data[i]);
                mTodoActivityFragment.setAdapter(adapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json形式のデータを読み出す
     * @param input
     * @return
     */
    @Override
    public TodoData[] load(InputStream input) {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(input));
            TodoData m = new TodoData();
            reader.beginObject();
            reader.nextName();
            int count = reader.nextInt();

            TodoData tmp[] = new TodoData[count];
            reader.nextName();
            reader.beginArray();
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = m.readJson(reader);
            }
            reader.endArray();
            reader.endObject();
            return tmp;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "load: File Not Found");
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * アプリが停止するときに呼び出される
     * リストのデータをセーブする
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            save(openFileOutput(fileName, MODE_PRIVATE));
            debug(openFileInput(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json形式で保存(セーブ)する
     * @param out
     */
    @Override
    public void save(OutputStream out) {
        try {
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));
            TodoAdapter adapter = mTodoActivityFragment.getAdapter();
            //write Count
            writer.beginObject().name("count").value(adapter.getCount());
            writer.name("array");
            writer.beginArray();
            for (int i = 0; i < adapter.getCount(); i++) {
                TodoData data = adapter.getItem(i);
                data.writeJson(writer);
            }
            writer.endArray();
            writer.endObject();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存したデータがどうなっているか確認する
     * @param input
     */
    public void debug(InputStream input) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                Log.d(TAG, "debug:" + tmp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateAdapter(TodoAdapter adapter, String tag, TodoData passData) {
        //EditFragment ->ActivityFragment　に移動(編集画面からリストに戻る)
        //リストを更新する
        if (TodoEditFragment.TAG.equals(tag)) {
            Log.d(TAG, "onUpdateAdapter: updateFragment");
            mTodoActivityFragment.setAdapter(adapter);
        }
        //ActivityFragment -> EditFragment に移動(リストから編集画面に移動)
        //エディタにデータを渡す
        //渡すデータが既存のデータであればpassdataがnullではない
        else if (TodoActivityFragment.TAG.equals(tag)) {
            Log.d(TAG, "onUpdateAdapter: update Edit Fragment:");
            mTodoEditFragment.setAdapter(adapter);
            mTodoEditFragment.setResetData(passData);
        }
    }
}
