package com.example.zhaojp.test1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class Trainer_introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_trainer_introduction );


        //文字链接（电话)
        TextView textView1 = findViewById( R.id.tele );
        String text1 = "click here to call trainer";
        SpannableString spannableString1 = new SpannableString( text1 );

        spannableString1.setSpan( new ClickableSpan() {
            @Override
            public void onClick(View view) {
                callPhone("13120032066");
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        textView1.setText( spannableString1 );
        textView1.setMovementMethod( LinkMovementMethod.getInstance() );


        //文字链接（邮件)
        TextView textView2 = findViewById( R.id.email );
        String text2 = "click here to email trainer";
        SpannableString spannableString2 = new SpannableString( text2);

        spannableString2.setSpan( new ClickableSpan() {
            @Override
            public void onClick(View view) {
                email();
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        textView2.setText( spannableString2 );
        textView2.setMovementMethod( LinkMovementMethod.getInstance() );


        //文字链接（短信)
        TextView textView3 = findViewById( R.id.sms );
        String text3= "click here to send SMS message to trainer";
        SpannableString spannableString3= new SpannableString( text3);

        spannableString3.setSpan( new ClickableSpan() {
            @Override
            public void onClick(View view) {
                doSendSMSTo("13120032066");
            }
        }, 0, text3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        textView3.setText( spannableString3);
        textView3.setMovementMethod( LinkMovementMethod.getInstance() );

    }


    //拨打电话
    public void callPhone(String phoneNum) {
        Intent intent = new Intent( Intent.ACTION_CALL );
        Uri data = Uri.parse( "tel:" + phoneNum );
        intent.setData( data );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity( intent );
    }

    //发送邮件
    public void email(){
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        String[] tos = { "3304888960@qq.com" };
        String[] ccs = { "3304888960@qq.com" };
        String[] bccs = {"3304888960@qq.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, tos);
        intent.putExtra(Intent.EXTRA_CC, ccs);
        intent.putExtra(Intent.EXTRA_BCC, bccs);
        intent.putExtra(Intent.EXTRA_TEXT, "body");
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");

        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/a.jpg"));
        intent.setType("image/*");
        intent.setType("message/rfc882");
        Intent.createChooser(intent, "Choose Email Client");
        startActivity(intent);*/

        String[] reciver = new String[] { "3304888960@qq.com" };
        String[] mySbuject = new String[] { "test" };
        String myCc = "cc";
        Intent myIntent = new Intent(android.content.Intent.ACTION_SEND);
        myIntent.setType("plain/text");
        myIntent.putExtra(android.content.Intent.EXTRA_EMAIL, reciver);
        myIntent.putExtra(android.content.Intent.EXTRA_CC, myCc);
        myIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mySbuject);
        startActivity(Intent.createChooser(myIntent, "mail test"));
    }


    //发送短信
    public void doSendSMSTo(String phoneNumber){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            startActivity(intent);
        }
    }

}
