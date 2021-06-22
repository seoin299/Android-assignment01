package com.example.ad_20181771prj;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashMap;

import static com.example.ad_20181771prj.DBHelper.SQL_LOAD;
import static com.example.ad_20181771prj.DBHelper.SQL_SELECT;
import static com.example.ad_20181771prj.DBHelper.TABLE_NAME;


public class ReviewActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;

    static public int id = 0;


    //비디오 재생 종료시키는 것
    protected  void onDestroy(){
        VideoView mv = (VideoView)findViewById(R.id.videoView);
        mv.stopPlayback();
        super.onDestroy();

    }

    void loadTable(){
        db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_LOAD, null);

        while(cursor.moveToNext()) {
            id= cursor.getInt(0);
            HashMap<String,String> item = new HashMap<>();
            item.put("id",cursor.getString(0));
            item.put("title",cursor.getString(1));
            item.put("rating",cursor.getString(2));
            item.put("review",cursor.getString(3));

        }

    }

    // 이 값들을 받아오는 거임 리스트액티비티에서
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final TextView stitle = (TextView) findViewById(R.id.title);
        final EditText editReview = (EditText) findViewById(R.id.review);
        final RatingBar rb1 = (RatingBar) findViewById(R.id.rb);
        final Button btn = (Button) findViewById(R.id.result);




        Intent intent = getIntent();
        int num1 = intent.getExtras().getInt("num1"); /*int형*/
        int num2 = intent.getExtras().getInt("num2");
        int num3 = intent.getExtras().getInt("num3");
        int num5 = intent.getExtras().getInt("num5");
        int num7 = intent.getExtras().getInt("num7");
        VideoView mv = (VideoView)findViewById(R.id.videoView);
        String title1;



        // 각각 그 인텐트로 받은 값들이랑 일치하는 것에 맞는 비디오 재생 및 영화제목 변경됨.
        if(num1==1) {
            Uri vuri = Uri.parse("android.resource://" + getPackageName() +
                    "/" + R.raw.video1);
            mv.setVideoURI(vuri);
            mv.start();

            title1 = "걸캅스";
            stitle.setText(title1);



        }else if(num2==2){
            Uri vuri = Uri.parse("android.resource://" + getPackageName() +
                    "/" + R.raw.video2);
            mv.setVideoURI(vuri);
            mv.start();

            title1 = "알라딘";
            stitle.setText(title1);
        }
        else if(num3==3){
            Uri vuri = Uri.parse("android.resource://" + getPackageName() +
                    "/" + R.raw.video3);
            mv.setVideoURI(vuri);
            mv.start();

            title1 = "어벤져스 앤드게임";
            stitle.setText(title1);
        }
        else if(num5==5){
            Uri vuri = Uri.parse("android.resource://" + getPackageName() +
                    "/" + R.raw.video5);
            mv.setVideoURI(vuri);
            mv.start();

            title1 = "이웃집 토토로";
            stitle.setText(title1);
        }
        else if(num7==7){
            Uri vuri = Uri.parse("android.resource://" + getPackageName() +
                    "/" + R.raw.video7);
            mv.setVideoURI(vuri);
            mv.start();

            title1 = "토이스토리 4";
            stitle.setText(title1);
        }


        //레이팅바

        rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"별점:"+rating ,Toast.LENGTH_SHORT).show();
            }
        });


        //영화제목이랑 리뷰내용


        Button ok = findViewById(R.id.result);
        ok.setOnClickListener(new View.OnClickListener() {
            // 버튼이 클릭된 경우 처리할 수 있도록 이벤트 리스너 등록 ,btn객체가 제출하기 버튼임
            @Override
            public void onClick(View v) { // 클릭 이벤트 처리 콜백함수
                // 패러미터 설명 : View v - 클릭 이벤트가 발생된 객체

                float rating = rb1.getRating();
                rb1.setRating(rating);

                String title = stitle.getText().toString(); // 이름입력 에디트텍스트에서 입력된 값을 가져옴
                String review = editReview.getText().toString(); // 학번입력 에디트텍스트에서 입력된 값을 가져옴

                String result;
                result="영화제목 : " + title + "\n평점 : " + rating+ "\n리뷰 : " + review; // 알림창에서 보여줄 결과 메시지*/

                dbHelper = new DBHelper(getApplicationContext());
                db=dbHelper.getReadableDatabase();
                loadTable();

                db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id",++id);
                values.put("title",title);
                values.put("rating",(float)rating);
                values.put("review",review);

                 // (1) table FRIEND has no column named id  Error inserting rating=2.5 id=2 title=걸캅스 review=aa
                                                                        // table FRIEND has no column named id (code 1): , while compiling: INSERT INTO FRIEND(rating,id,title,review) VALUES (?,?,?,?)
                db.execSQL("INSERT INTO FRIEND VALUES( '"+ id +"', '"+title+"' , '" + rating + "', '" + review + "');");
                db.close();


                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                // 영화제목,평점,리뷰내용을 Toast 알림창으로 보여줌
                Intent it = new Intent(getApplicationContext(),GatherActivity.class);
                //리뷰는 result에 저장되어 있음
                //it.putExtra("result",result);
                startActivity(it); //온크리에이트 메소드가 불린다.
                finish();



            }


        });




    }
}
