package com.starappmorning.LunchR;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ResultLunchActivity extends AppCompatActivity {

    MyOpenHelper myOpenHelper = null;
    String lunch_shop_result;
    String lunch_menu_result;
    String lunch_price_result;
    String toastMessage = "何も登録されていません";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_lunch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Random rand = new Random();
        //DB呼び出し
        myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //現在のmyDinnertbの要素数を取得
        Cursor c = db.rawQuery("select * from myLunchtb", null);
        //myDinnertbの要素数を(int)countに代入
        int count = c.getCount();
        if (count != 0) {
            //countの中からランダムな数を取得
            int r = rand.nextInt(count);

            //ランダムな数に該当する部分にカーソルを移動
            c.moveToPosition(r);
            //カーソル位置のカラム(列)dinner_menuをdinner_resultに代入
            lunch_shop_result = c.getString(1);
            lunch_menu_result = c.getString(2);
            lunch_price_result = c.getString(3);

            //dinner_resultをlayoutに出力
            setText(lunch_shop_result, lunch_menu_result, lunch_price_result);
        }else {
            toastMake(toastMessage,0,+500);
        }
    }

    private void setText(String lunch_shop,String lunch_menu,String lunch_price){
        TextView text1 = findViewById(R.id.LunchStore);
        text1.setText(lunch_shop);
        TextView text2 = findViewById(R.id.LunchMenu);
        text2.setText(lunch_menu);
        TextView text3 = findViewById(R.id.LunchPrice);
        text3.setText(lunch_price);
    }

    //動作確認用ToastMake
    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    //start_backを押した時の処理
    public void start_back(View view) {
        //前の画面に戻る
        finish();
    }
}