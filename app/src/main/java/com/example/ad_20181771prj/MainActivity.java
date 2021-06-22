package com.example.ad_20181771prj;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageview = (ImageView) findViewById(R.id.movie);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        imageview.startAnimation(anim);





       Button btnlogins = findViewById(R.id.logins);
        btnlogins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);  //어디로 이동할 것인지.
                startActivity(intent);
            }
        });


        Button btnJoin = findViewById(R.id.join);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(getApplicationContext(),JoinActivity.class);  //어디로 이동할 것인지.
                startActivity(intent2);
            }
        });

    }

}
