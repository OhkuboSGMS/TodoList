package app.os.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoAdapter extends ArrayAdapter<TodoData> implements Serializable {
    LayoutInflater inflater;
    public TodoAdapter(Context context, int resource) {
        super(context, resource);
        inflater =LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_todo, null);
        }

        if(position %2==0){
            convertView.setBackgroundColor(getContext().getResources()
                    .getColor(android.support.design.R.color.abc_hint_foreground_material_dark));
//                    .getColor(R.color.colorPrimary));
        }
        final TodoData todoData = getItem(position);
        TextView todoTitle = (TextView) convertView.findViewById(R.id.item_todoTitle);
        final TextView doneTask = (TextView) convertView.findViewById(R.id.item_done);
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
