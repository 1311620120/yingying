package bwie.com.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bwie.com.shop.presenter.RegPresenterImpl;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private Button reg;
    private EditText phone;
    private EditText pass;
    private RegPresenterImpl regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        reg = findViewById(R.id.reg);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        reg.setOnClickListener(this);
        regPresenter = new RegPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
           case  R.id.reg:
               String dian = phone.getText().toString().trim();
               String mima = pass.getText().toString().trim();
               if(dian.equals("") || mima .equals(""))
               {
                   Toast.makeText(RegActivity.this,"手机号和密码不能为空",Toast.LENGTH_SHORT).show();
                   return;
               }
               if (dian.length() >11 || dian.length() <10)
               {
                   Toast.makeText(RegActivity.this,"手机号必须是是一位",Toast.LENGTH_SHORT).show();
                   return;
               }
               regPresenter.regPresenter(dian,mima);
               Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(RegActivity.this, LoginActivity.class);
               startActivity(intent);
               break;
        }
    }
}
