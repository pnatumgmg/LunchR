package com.starappmorning.LunchR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChoiseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //start_backを押した時の処理
    public void start_back(View view) {
        //前の画面に戻る
        finish();
    }

    //choise_nextを押した時の処理
    public void choise_next(View view) {
        //dinner_lunch_spのindexを取得
        Spinner spinner = findViewById(R.id.dinner_lunch_sp);
        int index = spinner.getSelectedItemPosition();
        //dinner_lunch_sp(プルダウン)で選択した項目に画面推移
        switch (index){
            case 0:
                Intent intent_lunch = new Intent(ChoiseActivity.this, ListLunchActivity.class);
                startActivity(intent_lunch);
                break;
            case 1:
                Intent intent_dinner = new Intent(ChoiseActivity.this, ListDinnerActivity.class);
                startActivity(intent_dinner);
                break;
        }
    }
}