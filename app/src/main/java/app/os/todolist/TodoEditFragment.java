package app.os.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoEditFragment extends Fragment {
    Button editButton;
    TodoData todoData;
    TodoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_todo, null);
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
        editButton = (Button) rootView.findViewById(R.id.done_button);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Log.w("TAG", "onBackStackChanged:change " );
                        //既存のアイテムを編集した場合
                        if (todoData != null) {
                            todoData.setTitle("Task");
                        }
                        //新しくアイテムを作成した場合
                        else {
                            adapter.add(new TodoData("Task:"));
                        }

                getFragmentManager().popBackStack();
                ((FloatingActionButton) getActivity().findViewById(R.id.fab)).show();
            }
        });
    }

    public void setTodoData(TodoData todoData) {
        this.todoData = todoData;
    }

    public void setAdapter(TodoAdapter adapter) {
        this.adapter = adapter;
    }
}
