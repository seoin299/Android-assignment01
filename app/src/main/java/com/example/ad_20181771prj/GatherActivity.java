package com.example.ad_20181771prj;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.os.Build.ID;
import static com.example.ad_20181771prj.DBHelper.SQL_LOAD;
import static com.example.ad_20181771prj.DBHelper.SQL_SELECT;
import static com.example.ad_20181771prj.DBHelper.TABLE_NAME;


public class GatherActivity extends AppCompatActivity {

   //DB설정
    ListView listView;
    EditText list;
    SimpleAdapter sAdapter;
    int iSelectedItem = -1;
    ArrayList<HashMap<String, String>> listData = new ArrayList<>();
    int iSelectedId=-1;
/*    int id = 0;*/

    //사용자 이름과 버전 정보를 이용해서 구현해보면 될 것같다!
    DBHelper dbHelper;
    SQLiteDatabase db;

   //슬라이딩
    boolean isPageOpen = false;
    float downX;
    float upX;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPage01;
    Button button1;



/*    void loadTable(){
        db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_LOAD, null);

        while(cursor.moveToNext()) {
            id= cursor.getInt(0);
            HashMap<String,String> item = new HashMap<>();
            item.put("id",cursor.getString(0));
            item.put("title",cursor.getString(1));
            item.put("rating",cursor.getString(2));
            item.put("review",cursor.getString(2));
            listData.add(item);
        }
        sAdapter.notifyDataSetChanged();
    }*/

   //삭제하는 기능 리스트뷰
    public void delClick(View v) {
        if (iSelectedItem < 0) {
            Toast.makeText(getApplicationContext(), "항목을 선택해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        db=dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "  + TABLE_NAME + " WHERE " + "ID" + "=iSelectedId");
       // static final String SQL_SELECT_ID = "SELECT ID FROM " + TABLE_NAME + " WHERE " + COL_TITLE + "=?";
        //db.rawQuery("DELETE FROM FRIEND WHERE id = "+ iSelectedId +";");

  /*      db.delete(TABLE_NAME,"id="+iSelectedId,null);*/
     /* db.execSQL("DELETE FROM FRIEND WHERE( '"ID="','"+iSelectedId+"');");*/
/*        db.execSQL("INSERT INTO FRIEND VALUES( '"+ id +"', '"+title+"' , '" + rating + "', '" + review + "');");
     */
       /* db.rawQuery("delete  from FRIEND where id",null);*/
        db.close();

              /*  c = db.rawQuery("DELETE * from FRIEND WHERE",null);*/

        listData.remove(iSelectedItem);
        sAdapter.notifyDataSetChanged();
        // boolean isComplete = deleteColumn(iSelectedId);

        //Log.d("no", "result : " + isComplete);
        iSelectedItem = -1; //선택된 것이 삭제되었기 때문에 삭제된 후에 선택된 다른 항목이 없게함
        iSelectedId = -1;

    }

   /* public boolean deleteColumn(int id)
    {
        return db.delete(TABLE_NAME, "ID="+id,null) > 0;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       sAdapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_activated_2,
               new String[]{"title","rating&review"},
               new int[]{android.R.id.text1,android.R.id.text2});
        listView = findViewById(R.id.listView);
        listView.setAdapter(sAdapter);

        button1 = findViewById(R.id.button1);

        // 슬라이딩으로 보여질 레이아웃 객체 참조
        slidingPage01 = findViewById(R.id.slidingPage01);

        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        translateLeftAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isPageOpen = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        //리뷰 액티비티에서 값을 받아서 리스트뷰에 값을 넣어주는 거임.
        Intent it = getIntent();
        //String result = it.getStringExtra("result");
        dbHelper = new DBHelper(getApplicationContext());
        db=dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from FRIEND",null);
/*        db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_LOAD, null);*/

        String reviewrating,title,idStr;
        while (c.moveToNext()){
            idStr=c.getString(0);
            HashMap<String,String> item = new HashMap<>();
            item.put("id",idStr);
            title="영화제목: "+c.getString(1);
            item.put("title",title);

            reviewrating="평점: "+c.getString(2) + "\n"+"리뷰내용: " +c.getString(3);
            item.put("rating&review",reviewrating);
            //item.put("rating",c.getString(2));
            //item.put("review",c.getString(3));


           // Log.d("no",Integer.toString(id)+"/"+title+"/"+rating+"/"+review);

            listData.add(item);
        }


           sAdapter.notifyDataSetChanged();

        //리스트뷰 항목을 선택할 수 있는 기능
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iSelectedItem = position;
                HashMap<String, String> item = (HashMap<String, String>) listData.get(position);
                iSelectedId = Integer.parseInt(item.get("id"));
            }
        });


/*        Button btnDel = findViewById(R.id.delete);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iSelectedItem < 0) {
                    Toast.makeText(getApplicationContext(), "항목을 선택해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                db=dbHelper.getWritableDatabase();
       *//*         db.delete(TABLE_NAME,"id="+iSelectedId,null);*//*
                db.execSQL("DELETE FROM FRIEND WHERE id = "+ iSelectedId +";");
              *//*  c = db.rawQuery("DELETE * from FRIEND WHERE",null);*//*

                listData.remove(iSelectedItem);
                sAdapter.notifyDataSetChanged();
                // boolean isComplete = deleteColumn(iSelectedId);

                //Log.d("no", "result : " + isComplete);
                iSelectedItem = -1; //선택된 것이 삭제되었기 때문에 삭제된 후에 선택된 다른 항목이 없게함
                iSelectedId = -1;
                dbHelper.close();
            }
        });*/

        //영화목록 액티비티로 다시 돌아감.
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "영화 예매하러 가시겠습니까?", Snackbar.LENGTH_LONG)
                        .setAction("예매하러 가기", new View.OnClickListener() {  //스낵바가뜨면 보여주고 반응하게해주는거임 기본값이 null인데 그걸 이벤트처리메소드로 바꿔줌.온클릭으로
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(Intent.ACTION_SEND); //공유
                                it.setType("text/plain");
                                it.putExtra(Intent.EXTRA_TEXT, "http://www.cgv.co.kr/");
                                startActivity(it);
                            }
                        }).show();

            }
        });


    }

    //슬라이딩 애니메이션 관련 코드들

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            Log.i("downX", "downX:" + downX);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();
            Log.i("upX", "upX:" + upX);

            if ((upX > downX) && (isPageOpen == true)) {  // in case of right direction
                slidingPage01.startAnimation(translateRightAnim);

            }
            if ((upX < downX) && (isPageOpen == false)) { // in case of left direction
                slidingPage01.setVisibility(View.VISIBLE);
                slidingPage01.startAnimation(translateLeftAnim);
            }
        }
        return super.onTouchEvent(event);
    }

        public void onButton1Clicked(View v) {
        // 애니메이션 적용
        if (isPageOpen) {
            slidingPage01.startAnimation(translateRightAnim);

        } else {
            slidingPage01.startAnimation(translateLeftAnim);
            slidingPage01.setVisibility(View.VISIBLE);
        }

    }

    public void hiding(View v) {

            slidingPage01.setVisibility(View.GONE);
    }
}
