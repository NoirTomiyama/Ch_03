package com.tomiyama.noir.ch_03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Chapter3_1 extends AppCompatActivity {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3_1);

        // レイアウトの紐付け
        linearLayout = findViewById(R.id.container);

        // テキストビューの生成
        TextView textView = new TextView(this);
        textView.setText("これはテストです");
        textView.setTextSize(16.0f); //単位はsp
        textView.setTextColor(Color.rgb(0,0,0));

        // コンポーネントのサイズ指定
        textView.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));

        // レイアウトへのコンポーネントの追加
        linearLayout.addView(textView);

        // 画像の読み込み
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sample);

        // イメージビューの生成
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        linearLayout.addView(imageView);

    }
}
