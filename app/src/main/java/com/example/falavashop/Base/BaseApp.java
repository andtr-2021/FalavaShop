package com.example.falavashop.Base;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.falavashop.Tool.AppTool;

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
        //Using ButterKnife library
        ButterKnife.bind(this);
        initView();
        setData();
        setEvents();
    }

}