package com.example.alice.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNum;
    private EditText etContent;
    private Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = findViewById(R.id.etContent);
        etNum = findViewById(R.id.etNum);
        btSend = findViewById(R.id.btSend);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSend:
                String content = etContent.getText().toString().trim();
                String number = etNum.getText().toString().trim();

                if (TextUtils.isEmpty(content) || TextUtils.isEmpty(number)) {
                    Toast.makeText(this, "电话号码和短信内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> contents = smsManager.divideMessage(content);
                    for (String str : contents){
                        smsManager.sendTextMessage(number, null, str, null, null);
                    }
                }
        }
    }
}