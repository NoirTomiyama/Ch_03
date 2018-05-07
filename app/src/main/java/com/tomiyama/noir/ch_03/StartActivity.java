package com.tomiyama.noir.ch_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void intent(View view){

        int id = view.getId();

        switch (id){
            case R.id.one:
                intent = new Intent(this,Chapter3_1.class);
                startActivity(intent);
                break;
            case R.id.two:
                intent = new Intent(this,Chapter3_2.class);
                startActivity(intent);
                break;
        }

    }


}
