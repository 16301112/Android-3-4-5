package com.example.zhaojp.test1;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cn.bmob.v3.Bmob;



//sharedpreference:
//login:是否登陆了
//name:名字
//password:密码

public class MainActivity extends AppCompatActivity {

    //菜单栏
    private BottomNavigationBar mBottomNavigationBar;


    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private home homePage;
    private recyclerview recyclerviewPage;
    private classes classesPage;
    //private baiduMap baiduMapPage;
    private me mePage;
    private map mapPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);//隐藏标题栏 在setContentView()前执行
        setContentView(R.layout.activity_main);

        //连接bmob
        Bmob.initialize(this, "ae92cf5b997c901e4f2a9667356e9728");


        //菜单导航栏
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
               .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //mBottomNavigationBar.setBarBackgroundColor(R.color.colorBlue);
        mBottomNavigationBar.setActiveColor(R.color.colorGreen);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.trainer, "Trainer"))
                .addItem(new BottomNavigationItem(R.drawable.classes, "Classes"))
                .addItem(new BottomNavigationItem(R.drawable.me, "Me"))
                .addItem(new BottomNavigationItem(R.drawable.map, "Find Me"))
                .initialise();
        setDefaultFragment();

        //菜单导航栏监听（跳转）
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //未选中->选中
                if (position == 0) {
                    if (homePage == null) {
                        homePage = new home();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fragment_content, homePage).commitAllowingStateLoss();
                } else if (position == 1) {
                    if (recyclerviewPage == null) {
                        recyclerviewPage = new recyclerview();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fragment_content, recyclerviewPage).commitAllowingStateLoss();
                    //Intent intent=new Intent(MainActivity.this, loginApp.class);
                    //startActivity(intent);
                } else if (position == 2) {
                    if (classesPage == null) {
                        classesPage = new classes();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fragment_content, classesPage).commitAllowingStateLoss();
                    //Intent intent=new Intent(MainActivity.this, recyclerview.class);
                    //startActivity(intent);
                }else if (position == 3) {
                    /*if (registerPage == null) {
                        registerPage = new register();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fragment_content, registerPage).commitAllowingStateLoss();*/
                    /*Intent intent=new Intent(MainActivity.this, loginApp.class);
                    startActivity(intent);*/

                    SharedPreferences sp=getSharedPreferences("User", Context.MODE_PRIVATE);
                    Boolean inApp = sp.getBoolean("login",false);

                    if(inApp==true)
                    {
                        if (mePage == null) {
                            mePage = new me();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.fragment_content, mePage).commitAllowingStateLoss();
                    }
                    else if(inApp==false)
                    {
                        Intent intent=new Intent(MainActivity.this, loginApp.class);
                        startActivity(intent);
                    }
                }else if(position==4){
                    /*if (baiduMapPage == null) {
                        baiduMapPage = new baiduMap();
                    }
                    getFragmentManager().beginTransaction().replace( R.id.fragment_content, baiduMapPage).commitAllowingStateLoss();*/
                    Intent intent=new Intent(MainActivity.this, map1.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(int position) {
                //选中->未选中
            }

            @Override
            public void onTabReselected(int position) {
                //选中->选中
            }
        });

        }


    //默认界面
    private void setDefaultFragment(){
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homePage = new home();
        transaction.add(R.id.fragment_content,homePage);
        transaction.commitAllowingStateLoss();
    }



    //退出判断提示
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Confirm Exit").setMessage(
                "Are you sure you want to exit?")/*.setIcon(R.drawable.trainer)*/.setPositiveButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        SharedPreferences sp=getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        //通过editor对象写入数据
                        edit.putBoolean("login",false);
                        //提交数据存入到xml文件中
                        edit.commit();

                        finish();
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }

}
