package com.sty.dial.number;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 知识点：
 * 1、拨打电话
 * 2、新启动一个view
 * 3、Toast
 * 4、Button等View点击事件的四种写法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNumber;
    private Button btnCallPhone;
    private Button btnOpenView;
    private Button btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews(){
        etNumber = (EditText) findViewById(R.id.et_number);
        btnCallPhone = (Button) findViewById(R.id.bt_callphone);
        btnOpenView = (Button) findViewById(R.id.bt_startview);
        btnToast = (Button) findViewById(R.id.bt_toast);
    }

    private void setListeners(){
        //点击事件的第一种写法：匿名内部类
        btnCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               makeCall();
            }
        });

        //点击事件的第二种写法：传入对象
        btnOpenView.setOnClickListener(new MyOnclickListener());

        //点击事件的第三种写法：实现OnClickListener接口（推荐）
        btnToast.setOnClickListener(this);
    }

    private void makeCall(){
        //获取用户输入的电话号码
        String number = etNumber.getText().toString().trim();
        //拨打电话
        Intent intent = new Intent(); //创建一个Intent对象
        intent.setAction(Intent.ACTION_CALL); //设置意图对象的动作，打电话
        intent.setData(Uri.parse("tel:" + number)); //设置意图对象的数据
        startActivity(intent); //启动一个意图对象
    }

    /**
     * view点击事件的第二种写法
     */
    class MyOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            startNewView();
        }
    }

    private void startNewView(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    /**
     * view点击事件的第三种写法（推荐）
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_toast:
                makeToast();
                break;
            default:
                break;
        }
    }

    private void makeToast(){
        Toast.makeText(this, "Toast按钮被点击了", Toast.LENGTH_SHORT).show();
    }

    /**
     * view点击事件的第四种写法：在布局文件中写onClick属性[public 传参:view]
     */
    public void tellYou(View view){
        Toast.makeText(this, "点击事件的第四种写法", Toast.LENGTH_SHORT).show();
    }
}
