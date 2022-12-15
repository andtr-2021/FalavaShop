package com.example.falavashop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.falavashop.Base.BaseApp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.falavashop.adapters.SliderAdapter;
import com.example.falavashop.models.SliderModel;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;

public class MainActivity extends BaseApp {


    @Override
    public int getLayout() { return R.layout.activity_main;}

    @Override
    public void initView() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void setData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}