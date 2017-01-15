package app.os.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by ookubo on 2017/01/09.
 * TodoListのアイテムを管理するAdapter
 */
public class TodoAdapter extends ArrayAdapter<TodoData> implements Serializable {
    LayoutInflater inflater;

    public TodoAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
    }

    /**
     * getView で表示されるアイテムのビューを編集できる
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_todo, null);
        }
        //リストの背景色を交互に色を変更する　色模様というらしい
        if (position % 2 == 0) {
            convertView.setBackgroundColor(getContext().getResources()
                    .getColor(android.support.design.R.color.abc_hint_foreground_material_dark));
//                    .getColor(R.color.colorPrimary));
        }
        final TodoData todoData = getItem(position);
        TextView todoTitle = (TextView) convertView.findViewById(R.id.item_todoTitle);
        final TextView doneTask = (TextView) convertView.findViewById(R.id.item_done);
        //完了がクリックされたときの動作を設定。このデータを消す
        doneTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(todoData);
            }
        });
        //表示を設定
        todoTitle.setText(todoData.title);
        return convertView;
    }
}
