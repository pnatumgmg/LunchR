package com.starappmorning.LunchR;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ResultDinnerActivity extends AppCompatActivity {
    MyOpenHelper myOpenHelper = null;
    String dinner_result;

    String toastMessage = "何も登録されていません";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_dinner);
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
        Cursor c = db.rawQuery("select * from myDinnertb", null);
        //myDinnertbの要素数を(int)countに代入
        int count = c.getCount();
        if (count != 0) {
            //countの中からランダムな数を取得
            int r = rand.nextInt(count);

            //ランダムな数に該当する部分にカーソルを移動
            c.moveToPosition(r);
            //カーソル位置のカラム(列)dinner_menuをdinner_resultに代入
            dinner_result = c.getString(1);

            //dinner_resultをlayoutに出力
            setText(dinner_result);
        }else{
            toastMake(toastMessage,0,+500);
        }
    }

    //画面に文字をセット
    private void setText(String dinner_menu){
        TextView text = findViewById(R.id.DinnerMenu);
        text.setText(dinner_menu);
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