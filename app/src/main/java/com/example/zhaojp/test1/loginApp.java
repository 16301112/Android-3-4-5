package com.example.zhaojp.test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class loginApp extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mUsername,mPassword;
    private Button mButton;
    private me mePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //mEmailView=findViewById(R.id.email);
        mUsername=findViewById(R.id.username);
        mPassword=findViewById(R.id.password);
        mButton=findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String email=mEmailView.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                canLogin(username,password);
            }
        });


        //文字跳转（注册)
        TextView textView2=findViewById(R.id.login);
        String text2="Login";
        SpannableString spannableString1=new SpannableString(text2);

        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginApp.this,registerApp.class);
                startActivity(intent);
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(spannableString1);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());




        //自动补充密码
        /*mPassword.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    SharedPreferences sp=getSharedPreferences("User",MODE_PRIVATE);
                    if(sp.getBoolean("rememberPassword",false)==true) {
                        String getPassword = sp.getString( "password", null );
                        mPassword.setText( getPassword );
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });*/

    }


    public void canLogin(final String username, final String password){
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.addWhereEqualTo("username",username);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if(e==null){
                    if(username.equals(object.get(0).getUsername())&&password.equals(object.get(0).getPassword()))
                    {
                        Toast.makeText(loginApp.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        /*Intent intent=new Intent(loginApp.this, MainActivity.class);
                        startActivity(intent);*/
                        /*if (mePage == null) {
                            mePage = new me();
                        }
                        getFragmentManager().beginTransaction().add(R.id.fragment_content, mePage).commitAllowingStateLoss();*/

                        //存储信息
                        SharedPreferences sp=getSharedPreferences("User", Context.MODE_PRIVATE);
                        final SharedPreferences.Editor edit = sp.edit();
                        //通过editor对象写入数据
                        edit.putBoolean("login",true);


                        //是否保存密码
                        /*CheckBox rememberPassword=findViewById(R.id.rememberPassword);
                        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                // TODO Auto-generated method stub
                                if(isChecked){//选中
                                    edit.putString("name",username);
                                    edit.putString("password",password);

                                    edit.putBoolean("rememberPassword",true);
                                }else{//未选中

                                }
                            }
                        });*/


                        //提交数据存入到xml文件中
                        edit.commit();

                        Intent intent=new Intent(loginApp.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(loginApp.this, "User name or password error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(loginApp.this, "User name or password error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
