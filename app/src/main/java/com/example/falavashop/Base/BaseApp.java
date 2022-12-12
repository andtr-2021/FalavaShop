package com.example.falavashop.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseApp extends AppCompatActivity {

    public abstract int getLayout();

    public abstract void initView();

    public abstract void setEvents();

    public abstract void setData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        setData();
        setEvents();
    }

}