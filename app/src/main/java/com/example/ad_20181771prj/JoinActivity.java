package com.example.ad_20181771prj;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    RadioGroup gender;
    String gen;

    CheckBox pan;
    CheckBox ho;
    CheckBox ro;
    CheckBox sf;
    CheckBox da;

    RadioButton female;
    RadioButton male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);



        //아이디와 비밀번호 맞는지 확인여부
        Button btnJoin = findViewById(R.id.joinok);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = findViewById(R.id.editID);
                EditText pwd = findViewById(R.id.editPWD);
                EditText cpwd = findViewById(R.id.editCPWD);
                EditText mail = findViewById(R.id.editEmail);
                EditText phone = findViewById(R.id.editPhone);

                String sID = id.getText().toString().trim();
                String sPWD = pwd.getText().toString().trim();
                String sCPWD = cpwd.getText().toString().trim();
                String sEmail = mail.getText().toString().trim();
                String sPhone = phone.getText().toString().trim();




                if(sID.isEmpty() || sPWD.isEmpty() || sCPWD.isEmpty()){ //isEmpty() 비어있다면
                    Toast.makeText(getApplicationContext(),"ID와 비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(! sPWD.equals(sCPWD)) { //둘이 같지않으면 equals같다

                    Toast.makeText(getApplicationContext(),"비밀번호를 정확히 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(sEmail.isEmpty() ){ //isEmpty() 비어있다면
                    Toast.makeText(getApplicationContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(sPhone.isEmpty() ){ //isEmpty() 비어있다면
                    Toast.makeText(getApplicationContext(),"핸드폰 번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!(pan.isChecked() || ho.isChecked()|| ro.isChecked()|| sf.isChecked()|| da.isChecked()) ){ //isEmpty() 비어있다면
                    Toast.makeText(getApplicationContext(),"선호장르를 1가지 이상 선택하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }




                Toast toast = Toast.makeText(getApplicationContext(),"환영합니다 당신의 영화리뷰를 남겨주세요.",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,100);
                toast.show();
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);  //어디로 이동할 것인지.
                startActivity(intent);

            }
        });

        //성별체크
        gender = findViewById(R.id.gender);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.gender);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male) {
                    gen = "남성";
                    Toast toast = Toast.makeText(getApplicationContext(), "남성", Toast.LENGTH_LONG);
                    View tView = toast.getView();

                    TextView tTView = tView.findViewById(android.R.id.message);
                    tTView.setTextColor(Color.WHITE);
                    toast.show();
                }else if(i == R.id.female){
                    gen = "여성";
                    Toast toast = Toast.makeText(getApplicationContext(), "여성", Toast.LENGTH_LONG);
                    View tView = toast.getView();

                    TextView tTView = tView.findViewById(android.R.id.message);
                    tTView.setTextColor(Color.WHITE);
                    toast.show();
                }
            }
        });

        pan = (CheckBox)findViewById(R.id.pan);
        ho = (CheckBox)findViewById(R.id.ho);
        ro = (CheckBox)findViewById(R.id.ro);
        sf = (CheckBox)findViewById(R.id.sf);
        da = (CheckBox)findViewById(R.id.da);

        CompoundButton.OnCheckedChangeListener occl = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    switch (compoundButton.getId()){ //체크 박스버튼의 아이디가 웹이면 웹선택했다고 토스트창 뜨게하는거임
                        case R.id.pan :
                            Toast.makeText(getApplicationContext(),"판타지 선택",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.ho :
                            Toast.makeText(getApplicationContext(),"공포 선택",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.ro :
                            Toast.makeText(getApplicationContext(),"로맨스 선택",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.sf :
                            Toast.makeText(getApplicationContext(),"SF 선택",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.da :
                            Toast.makeText(getApplicationContext(),"다큐 선택",Toast.LENGTH_SHORT).show();
                            break;

                    }
                }



            }
        };

        pan.setOnCheckedChangeListener(occl);
        ho.setOnCheckedChangeListener(occl);
        ro.setOnCheckedChangeListener(occl);
        sf.setOnCheckedChangeListener(occl);
        da.setOnCheckedChangeListener(occl);




    }
}
