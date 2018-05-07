package com.tomiyama.noir.ch_03;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Chapter3_2 extends AppCompatActivity implements View.OnClickListener{

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_MESSAGE = "0";
    private final static String TAG_YESNO   = "1";
    private final static String TAG_TEXT    = "2";
    private final static String TAG_IMAGE   = "3";

    LinearLayout Layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3_2);

        Layout = findViewById(R.id.container);

        // ボタンの生成
        Layout.addView(makeButton("メッセージダイアログの表示",TAG_MESSAGE));
        Layout.addView(makeButton("Yes/Noダイアログの表示",TAG_YESNO));
        Layout.addView(makeButton("テキスト入力ダイアログの表示",TAG_TEXT));
        Layout.addView(makeButton(res2bmp(this,R.drawable.sample),TAG_IMAGE));

    }

    // ボタンの生成
    private Button makeButton(String text, String tag){
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        return button;
    }

    // イメージボタンの生成
    private ImageButton makeButton(Bitmap bmp,String tag){
        ImageButton imageButton = new ImageButton(this);
        imageButton.setTag(tag);
        imageButton.setOnClickListener(this);
        imageButton.setImageBitmap(bmp);
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        return imageButton;
    }

    // リソース→ビットマップ
    public Bitmap res2bmp(Context context,int resID){
        return BitmapFactory.decodeResource(
                context.getResources(),resID);
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();

        if(TAG_MESSAGE.equals(tag)){
            MessageDialog.show(this,"メッセージダイアログ","ボタンを押した");
            Log.d("TAG:",tag);
        }else if(TAG_YESNO.equals(tag)){
            YesNoDialog.show(this,"Yes/Noダイアログ","Yes/Noを選択");
            Log.d("TAG:",tag);

        }else if(TAG_TEXT.equals(tag)){
            TextDialog.show(this,"テキスト入力ダイアログ","テキストを入力");
            Log.d("TAG:",tag);

        }else if(TAG_IMAGE.equals(tag)){
            MessageDialog.show(this,"","イメージボタンを押した");
            Log.d("TAG:",tag);

        }

    }

    // メッセージダイアログの定義
    public static class MessageDialog extends DialogFragment{
        // ダイアログの表示
        public static void show(Activity activity,String title,String text){
            MessageDialog f = new MessageDialog();
            Bundle args = new Bundle();
            args.putString("title",title);
            args.putString("text",text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(),"messageDialog");
        }

        // ダイアログの生成
        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));
            ad.setPositiveButton("OK",null);
            return ad.create();
        }
    }

    // Yes/Noダイアログの定義
    public static class YesNoDialog extends DialogFragment{
        // ダイアログの表示
        public static void show(Activity activity,String title,String text){
            YesNoDialog f = new YesNoDialog();
            Bundle args = new Bundle();
            args.putString("title",title);
            args.putString("text",text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(),"yesNoDialog");
        }

        // Yes,Noダイアログの生成
        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            // リスナーの生成
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
                // ダイアログボタン押下時に呼ばれる
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            MessageDialog.show(getActivity(),"","Yes");
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            MessageDialog.show(getActivity(),"","No");
                            break;
                    }
                }
            };
            // Yes,Noダイアログの生成
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));
            ad.setPositiveButton("Yes",listener);
            ad.setNegativeButton("No",listener);
            return ad.create();

        }
    }

    // テキスト入力ダイアログの定義
    public static class TextDialog extends DialogFragment{
        private EditText editText;

        // ダイアログの表示
        public static void show(Activity activity,String title,String text){
            TextDialog f = new TextDialog();
            Bundle args = new Bundle();
            args.putString("title",title);
            args.putString("text",text);
            f.setArguments(args);
            f.show(activity.getFragmentManager(),"textDialog");
        }

        // テキスト入力ダイアログの生成
        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            // リスナーの生成
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                // ダイアログボタン押下時に呼ばれる
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MessageDialog.show(getActivity(),"",editText.getText().toString());
                }
            };

            // エディットテキスト
            editText = new EditText(getActivity());

            // テキスト入力ダイアログの生成
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));
            ad.setView(editText);
            ad.setPositiveButton("OK",listener);

            // Fragmentの状態復帰
            if(bundle != null) editText.setText(bundle.getString("editText",""));

            return ad.create();
        }

        // Fragmentの状態保存

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putString("editText",editText.getText().toString());
        }
    }


}

