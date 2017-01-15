package app.os.todolist;

/**
 * Created by ookubo on 2017/01/10.
 * Fragment間の通信をActivityを介して行うためのインターフェイス
 *
 */
public interface OnCommunicateFragments {
    /**
     * Fragment間のAdapterを更新する
     * *AdapterとはListに入っているアイテムのデータを管理するクラスのこと
     * これを更新しないとリストのデータは更新されない
     * @param adapter TodoListのアダプター
     * @param tag Fragmentを判別するためのタグ
     * @param passData あいてのFragmentに渡すデータ例えば、既存のデータを編集するときなどに
     *                 渡しておくと、編集先で既存のデータをもとにエディタ内のデータを
     *                 変更できる
     */
    void onUpdateAdapter(TodoAdapter adapter, String tag, TodoData passData);
}
