package com.gitstudy.fragmenttabhostutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
/**
 * Tabhost工具类
 * github地址：https://github.com/open-android/FragmentTabhostUtils
 */
public class FragmentTabhostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tabhost);
    }

    public void quick_start(View view){
        startActivity(new Intent(this,QuickStartActivity.class));
    }
    public void custom_in_xml(View view){
        startActivity(new Intent(this,CustomInXmlActivity.class));
    }
    public void custom_in_java(View view){
        startActivity(new Intent(this,CustomInJavaActivity.class));
    }
    public void use_in_fragment(View view){
        startActivity(new Intent(this,UseInFragment.class));
    }


}
