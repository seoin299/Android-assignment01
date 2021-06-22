package com.example.ad_20181771prj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editID; //앞에 private 붙여도 된다
    EditText editPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = findViewById(R.id.editID);
        editPWD = findViewById(R.id.editPWD);



        Button btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sID = editID.getText().toString().trim(); //문자열 공백 제거
                String sPWD = editPWD.getText().toString();

                sPWD = sPWD.trim();

                if(sID.isEmpty() || sPWD.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent =new Intent(getApplicationContext(),ListActivity.class);  //어디로 이동할 것인지.
                startActivity(intent);
            }
        });



        }


    }

