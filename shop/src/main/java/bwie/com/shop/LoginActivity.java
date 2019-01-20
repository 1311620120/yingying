package bwie.com.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bwie.com.shop.presenter.LoginPresenterImpl;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button reg;
    private EditText phone;
    private EditText pass;
    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.login:
                String dian = phone.getText().toString().trim();
                String mima = pass.getText().toString().trim();
                if(dian.equals("") || mima .equals(""))
                {
                    Toast.makeText(LoginActivity.this,"手机号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dian.length() >11 || dian.length() <10)
                {
                    Toast.makeText(LoginActivity.this,"手机号必须是是一位",Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenter.loginPresenter(dian,mima);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.reg:
                Intent intent1 = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
