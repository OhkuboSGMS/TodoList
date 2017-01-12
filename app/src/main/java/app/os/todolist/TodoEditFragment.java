package app.os.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by ookubo on 2017/01/09.
 */
public class TodoEditFragment extends Fragment implements Serializable {
    public static final String TAG ="TodoEditFragment";
    public static final String TRANSITION_FRAGMENT_KEY ="009";
    Button editButton;
    TodoData resetData;
    TextView timeEditText;
    TextView dateEditText;
    EditText titleEditText;
    EditText messageEditText;
    TodoAdapter adapter;
    public TodoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_todo, null);
        editButton = (Button) rootView.findViewById(R.id.done_button);
        timeEditText =(TextView)rootView.findViewById(R.id.time_fragment);
        dateEditText =(TextView)rootView.findViewById(R.id.date_fragment);
        titleEditText =(EditText)rootView.findViewById(R.id.title_editText);
        messageEditText =(EditText)rootView.findViewById(R.id.message_editText);

        return rootView;
    }

    /**
     * OnCreateViewではEditTextを変更することができなかった
     */
    @Override
    public void onResume() {
        super.onResume();
        final TodoData changeData;
        this.clear(resetData);
        //既存のデータからの開き
        if(resetData!=null){
            changeData =resetData;
        }
        //新規作成
        else {
            changeData = new TodoData();
        }
        //編集を終了してリスト画面に戻る
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //既存のアイテムを編集した場合
                if (resetData != null) {
                    update(resetData);
                }
                //新しくアイテムを作成した場合
                else {
                    update(changeData);
                    adapter.add(changeData);
                }
                Activity activity =getActivity();
                if(activity instanceof OnCommunicateFragments){;
                    ((OnCommunicateFragments) activity).onUpdateAdapter(adapter,TAG,null);
                }
                //リスト画面に戻る
                getFragmentManager().popBackStack();
            }
        });
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment fragment =new DatePickerDialogFragment();
                fragment.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateEditText.setText(i+"/"+i1+"/"+i2);
                        changeData.setDate(i,i1,i2);
                    }
                });
                fragment.show(getFragmentManager(),null);
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialogFragment fragment =new TimePickerDialogFragment();
                fragment.setListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        timeEditText.setText(i+":"+i1);
                        changeData.setTime(i,i1);
                    }
                });
                fragment.show(getFragmentManager(),null);
            }
        });

    }

    /**
     * 編集画面のViewをきれいにする
     * *Warning* Viewが生成された後に呼び出すこと
     */
    public void clear(TodoData data){
        //新規タスクの場合はデフォルトの値に
        if(data==null){
            TodoData tmp =new TodoData(getString(R.string.edit_todo_default_title),
                                        getString(R.string.edit_todo_default_message),
                                        Calendar.getInstance());
            updateView(tmp);
        }
        //既存のタスクの編集の場合はその値に
        else{
           updateView(data);
        }
    }

    /**
     *　Viewを更新する
     * @param data
     */
    private void updateView(TodoData data){
        titleEditText.setText(data.getTitle());
        messageEditText.setText(data.getMessage());
        dateEditText.setText(data.getDisplayDate());
        timeEditText.setText(data.getDisplayTime());
    }

    /**
     * Date,TimeはFragmentなので更新したときにデータが更新される
     */
    private void update(TodoData data){
        data.setTitle(titleEditText.getText().toString());
        data.setMessage(messageEditText.getText().toString());
    }

    public void setResetData(TodoData resetData) {
        this.resetData = resetData;
    }

    public void setAdapter(TodoAdapter adapter) {
        this.adapter = adapter;
    }
}
