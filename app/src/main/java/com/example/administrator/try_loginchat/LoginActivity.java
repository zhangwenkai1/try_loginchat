package com.example.administrator.try_loginchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btn_login;
    private CheckBox rememberPassword;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref= PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        btn_login=(Button)findViewById(R.id.btn_login);
        rememberPassword=(CheckBox)findViewById(R.id.remember_password);

        boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember){
            //将账号和密码都设置到文本框中
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                //如果账号是admin，密码是123456就认为登陆成功
                if (account.equals("admin") && password.equals("123456")){
                    editor=pref.edit();
                    if (rememberPassword.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误，请重新登录！",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
