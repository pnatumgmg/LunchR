package com.starappmorning.LunchR;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListLunchActivity extends AppCompatActivity {

    ListView myListView;

    private MyOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_lunch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //リストビュー
        myListView = findViewById(R.id.listView_lunch);

        //db参照用
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();

        //select
        Cursor cur = db.rawQuery("select * from myLunchtb",null);

        //adapterの準備
        //表示するカルム名
        String[] from = {"lunch_shop","lunch_menu","lunch_price","_id"};

        //バインドするViewリソース
        int[] to = {R.id.custom_store,R.id.custom_menu,R.id.custom_price,R.id._id};

        //adapterの生成
        final ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_custom_lunch,cur,from,to,0);

        //バインドして表示
        myListView.setAdapter(adapter);

        //タップしたら更新　flgを反転させる---------------------------------------------------
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //_idを取得
                String s1 = ((TextView)view.findViewById(R.id._id)).getText().toString();

                //参照、更新へ
                Intent intent = new Intent(getApplication(),EditLunchActivity.class);

                //モード指定　_idを渡す
                intent.putExtra("KBN",s1);

                //行く
                startActivity(intent);
            }
        });
    }

    public void add_lunch(View view) {
        Intent intent = new Intent(getApplication(), EditLunchActivity.class);

        //モード指定　からは更新
        intent.putExtra("KBN","");

        //行く
        startActivity(intent);
    }

    //リターン時
    @Override
    protected void onRestart(){
        super.onRestart();
        reload();
    }

    public void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    //start_backを押した時の処理
    public void start_back(View view) {
        //前の画面に戻る
        finish();
    }
}