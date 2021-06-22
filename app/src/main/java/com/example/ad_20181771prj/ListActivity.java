package com.example.ad_20181771prj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),ReviewActivity.class);
                intent.putExtra("num1",1);
                startActivity(intent);
            }
        });


        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(getApplicationContext(),ReviewActivity.class);
                intent2.putExtra("num2",2);
                startActivity(intent2);
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 =new Intent(getApplicationContext(),ReviewActivity.class);
                intent3.putExtra("num3",3);
                startActivity(intent3);
            }
        });

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 =new Intent(getApplicationContext(),ReviewActivity.class);
                intent5.putExtra("num5",5);
                startActivity(intent5);
            }
        });

        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 =new Intent(getApplicationContext(),ReviewActivity.class);
                intent7.putExtra("num7",7);
                startActivity(intent7);
            }
        });






    }
}
