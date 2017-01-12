package app.os.todolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;

/**
 * Fragmentを起動　->todoリストを読み込む
 * 終了:todoリストを保存
 */
public class TodoActivityFragment extends Fragment implements Serializable ,AdapterView.OnItemClickListener{
    public static final String TAG ="TodoActivityFragment";
    private Fragment transFragment;
    private ListView mTodoListView;
    private FloatingActionButton fab;
    private TodoAdapter adapter;
    public static final String TRANSITION_FRAGMENT_KEY ="008";
    public static final String ADAPER_KEY ="ARRAY_ADAPTER";

    public TodoActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter =new TodoAdapter(getContext(),R.layout.item_todo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: OncreateView");
        View rootView = inflater.inflate(R.layout.fragment_todo, container,false);
        mTodoListView =(ListView)rootView.findViewById(R.id.todoListView);
        mTodoListView.setAdapter(adapter);
        fab = (FloatingActionButton)rootView.findViewById(R.id.fab);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //既存のリストアイテムを選択したときに
        mTodoListView.setOnItemClickListener(this);
        //新たなTodoを追加
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Activity activity =getActivity();
                if(activity instanceof OnCommunicateFragments){
                    Log.d(TAG, "onClick: update");
                    ((OnCommunicateFragments) activity).onUpdateAdapter(adapter,TAG,null);
                }
//                Log.d(TAG, "onClick: ItemCount:"+adapter.getCount());

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                transaction.replace(R.id.frame, transFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, R.string.addTodo, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "onItemClick: index"+i);
        TodoData clickedData =adapter.getItem(i);

        //既存のアイテムを編集
        Activity activity =getActivity();
        if(activity instanceof OnCommunicateFragments){
            ((OnCommunicateFragments) activity).onUpdateAdapter(adapter,TAG,clickedData);
        }
//        Log.d(TAG, "onClick: ItemCount:"+adapter.getCount());
        getFragmentManager().beginTransaction().
                setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right).
                replace(R.id.frame, transFragment).
                addToBackStack(null).
                commit();



    }

    public TodoAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TodoAdapter adapter) {
        this.adapter = adapter;
//        Log.d(TAG, "setAdapter:"+adapter.getCount());
        if(mTodoListView!=null){
            mTodoListView.setAdapter(adapter);
        }
//        Log.d(TAG, "setAdapter:"+adapter.getCount());
    }

    public void setTransFragment(Fragment transFragment) {
        this.transFragment = transFragment;
    }
}
