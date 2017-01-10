package app.os.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TodoActivity extends AppCompatActivity implements OnCommunicateFragments {
    public static final String TAG ="TodoActivity";
    String title = "Task:";
    TodoEditFragment mTodoEditFragment;
    TodoActivityFragment mTodoActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        mTodoEditFragment = new TodoEditFragment();
        mTodoActivityFragment =new TodoActivityFragment();
        Bundle bundle =new Bundle();
        bundle.putSerializable(TodoActivityFragment.TRANSITION_FRAGMENT_KEY,mTodoEditFragment);
        mTodoActivityFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                add(R.id.frame,mTodoActivityFragment,TodoActivityFragment.TAG).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        final TodoAdapter adapter = mTodoActivityFragment.getAdapter();


    }

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

    @Override
    public void onUpdateAdapter(TodoAdapter adapter,String tag) {
        Log.d(TAG, "onUpdateAdapter:"+adapter.getCount());
        if(TodoEditFragment.TAG.equals(tag)) {
            Log.d(TAG, "onUpdateAdapter: updateFragment");
            mTodoActivityFragment.setAdapter(adapter);
        }else if(TodoActivityFragment.TAG.equals(tag)){
            Log.d(TAG, "onUpdateAdapter: update Edit Fragment");
                mTodoEditFragment.setAdapter(adapter);
            }
        }
}
