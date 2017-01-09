package app.os.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TodoActivity extends AppCompatActivity {
    String title = "Task:";
    TodoEditFragment mTodoEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        mTodoEditFragment = new TodoEditFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        final TodoAdapter adapter = ((TodoActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.todoListFragment)).getAdapter();
        mTodoEditFragment.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //新たなTodoを追加
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TodoData todoData =new TodoData(title + adapter.getCount());
//                adapter.add(todoData);
//                mTodoEditFragment.setTodoData(todoData);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, mTodoEditFragment);
                transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, R.string.addTodo, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
