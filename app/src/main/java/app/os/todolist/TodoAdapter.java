package app.os.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoAdapter extends ArrayAdapter<TodoData> {
    LayoutInflater inflater;
    public TodoAdapter(Context context, int resource) {
        super(context, resource);
        inflater =LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView =inflater.inflate(R.layout.item_todo,null);
        }
        TodoData todoData =getItem(position);
        TextView todoTitle =(TextView)convertView.findViewById(R.id.item_todoTitle);
        //表示を設定
        todoTitle.setText(todoData.title);
        return convertView;
    }
}
