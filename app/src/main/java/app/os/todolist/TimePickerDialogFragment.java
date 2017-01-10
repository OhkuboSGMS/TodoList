package app.os.todolist;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by ookubo on 2017/01/10.
 */
public class TimePickerDialogFragment extends DialogFragment {
    public static final String LISTENER_KEY ="00ol";
    TimePickerDialog.OnTimeSetListener listener;

    public TimePickerDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar =Calendar.getInstance();
        int hour =calendar.get(Calendar.HOUR_OF_DAY);
        int minute =calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(getActivity(),listener,
                hour,minute,true);

        return timePickerDialog;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }
}
