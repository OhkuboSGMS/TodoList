package app.os.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Fragmentを起動　->todoリストを読み込む
 * 終了:todoリストを保存
 */
public class TodoActivityFragment extends Fragment {
    private ListView mTodoListView;
    private TodoAdapter adapter;

    public TodoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);
        mTodoListView =(ListView)rootView.findViewById(R.id.todoListView);
        adapter =new TodoAdapter(getContext(), R.layout.item_todo);
        mTodoListView.setAdapter(adapter);
        return rootView;
    }

    public TodoAdapter getAdapter() {
        return adapter;
    }
}
