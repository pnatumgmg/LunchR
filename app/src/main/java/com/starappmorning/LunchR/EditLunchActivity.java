package com.starappmorning.LunchR;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditLunchActivity extends AppCompatActivity {

    private  MyOpenHelper helper;

    String kbn = "";

    String toastMessage = "登録しました。戻るを押してください";
    String toastMessage2 = "登録するものがありません";

    String toastMessage3 = "削除しました。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_lunch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //DBを作成
        helper = new MyOpenHelper(getApplicationContext());

        //データを受け取る
        Intent intent = getIntent();
        String KBN = intent.getStringExtra("KBN");

        Button button = findViewById(R.id.save_lunch);
        View view = findViewById(R.id.main);

        assert KBN != null;
        if(!KBN.isEmpty()){
            //参照
            kbn = KBN;

            //ボタンのテキストを更新
            button.setText("更新");

            //背景色を変更
            view.setBackgroundColor(Color.YELLOW);

            //既存データ参照
            readDate(KBN);


        }else{
            //新規
            kbn = "登録";

            //ボタンのテキストを登録
            button.setText("新規");

            //背景の色を変更
            view.setBackgroundColor(Color.CYAN);

            //新規登録の際は削除ボタンを非表示
            Button del = findViewById(R.id.delete_lunch);
            del.setVisibility(View.GONE);

        }
    }

    /**
     *データを参照する
     * @param read
     */
    public void readDate(String read){
        SQLiteDatabase db = helper.getWritableDatabase();

        EditText txt1 = findViewById(R.id.edit_lunch_store);
        EditText txt2 = findViewById(R.id.edit_lunch_menu);
        EditText txt3 = findViewById(R.id.edit_lunch_price);

        Cursor cursor = db.query(
                "myLunchtb",
                new String[]{"lunch_shop","lunch_menu","lunch_price"},
                "_ID = ?",
                new String[]{read},
                null,null,null
        );
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            txt1.setText(cursor.getString(0));
            txt2.setText(cursor.getString(1));
            txt3.setText(cursor.getString(2));
        }

        cursor.close();

    }

    //    save_dinnerボタンを押した時の処理
    public void save_lunch(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        EditText txt1 = findViewById(R.id.edit_lunch_store);
        EditText txt2 = findViewById(R.id.edit_lunch_menu);
        EditText txt3 = findViewById(R.id.edit_lunch_price);

        String store = txt1.getText().toString();
        String menu = txt2.getText().toString();
        String price = txt3.getText().toString();

        ContentValues values = new ContentValues();
        values.put("lunch_shop", store);
        values.put("lunch_menu", menu);
        values.put("lunch_price", price);

//        toastMake(String.valueOf(values),0,+500);
        //ボタンが登録の場合
        if(kbn== "登録"){
            if(menu.length() != 0){
                //新規登録
                db.insert("myLunchtb", null, values);
                //トーストを表示
                toastMake(toastMessage, 0, +400);
            }else{
                toastMake(toastMessage2, 0, +400);
            }
            //ボタンが更新の場合
        }else {
            if(menu.length() != 0){
                //更新
                UPData(kbn);
                //トーストを表示
                toastMake(toastMessage, 0, +400);
            }else{
                //トーストを表示
                toastMake(toastMessage2, 0, +400);
            }

        }

    }

    /**
     * データの更新
     * @param read
     */
    public void UPData(String read){
        SQLiteDatabase db = helper.getReadableDatabase();

        EditText txt1 = findViewById(R.id.edit_lunch_store);
        EditText txt2 = findViewById(R.id.edit_lunch_menu);
        EditText txt3 = findViewById(R.id.edit_lunch_price);

        String store = txt1.getText().toString();
        String menu = txt2.getText().toString();
        String price = txt3.getText().toString();

        ContentValues upvalue = new ContentValues();
        upvalue.put("lunch_shop", store);
        upvalue.put("lunch_menu", menu);
        upvalue.put("lunch_price", price);

        db.update("myLunchtb",upvalue,"_id=?",new String[]{read});

    }

    //delete_dinnerを押した時の処理
    public void delete_lunch(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        //削除
        db.delete("myLunchtb","_id=" + kbn,null);
        //再表示
        toastMake(toastMessage3,0,500);
        //画面を戻す
        finish();
    }

    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    //start_backを押した時の処理
    public void start_back(View view) {
        //mainActivityに戻る
        finish();
    }
}