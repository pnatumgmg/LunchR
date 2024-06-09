package com.starappmorning.LunchR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //result_lunchボタンを押した時の処理
    public void result_lunch(View view) {
        Intent intent = new Intent(getApplication(), ResultLunchActivity.class);
        //ResultLunchActivityに画面推移
        startActivity(intent);
    }


    //result_dinnerボタンを押した時の処理
    public void result_dinner(View view) {
        Intent intent = new Intent(getApplication(), ResultDinnerActivity.class);
        //ResultDinnerActivityに画面推移
        startActivity(intent);
    }

    //add_editボタンを押した時の処理
    public void add_edit(View view) {
        Intent intent = new Intent(getApplication(), ChoiseActivity.class);
        //ChoiseActivityに画面推移
        startActivity(intent);
    }
}