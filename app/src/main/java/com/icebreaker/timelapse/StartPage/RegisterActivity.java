package com.icebreaker.timelapse.StartPage;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.githang.statusbar.StatusBarCompat;
import com.icebreaker.timelapse.R;

/**
 * 注册界面
 * @author Marhong
 * @time 2018/5/25 0:35
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mUserName,mPassword,mConfirmPassword;
    private Button mNextStep;
    private ImageView mBackLogin;
    private RelativeLayout mBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        setListener();
    }
    
    /**
     * 初始化注册界面所有控件
     * @author Marhong
     * @time 2018/5/25 0:37
     */
    private void initViews(){
        mUserName = (EditText)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);
        mNextStep = (Button)findViewById(R.id.nextStep);
        // 设置ActionBar
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        mBarView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.actionbar_register,null);
        mBackLogin = (ImageView)mBarView.findViewById(R.id.back_login);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(mBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }
    private void setListener(){
        mUserName.setOnClickListener(this);
        mPassword.setOnClickListener(this);
        mBackLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_login:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }
}
