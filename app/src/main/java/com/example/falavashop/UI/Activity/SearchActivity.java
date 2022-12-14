package com.example.falavashop.UI.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falavashop.Base.BaseApp;
import com.example.falavashop.MainActivity;
import com.example.falavashop.R;
import com.example.falavashop.Tool.AppTool;
import com.example.falavashop.Tool.HttpHandler;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseApp {

    private String searchKey="";
    private String status ="";

    @BindView(R.id.add_search)
    EditText text_search;

    @Override
    public int getLayout() {
        return R.layout.search_results;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void setData() {

    }

    private void SearchProduct() {
        String searchValue = text_search.getText().toString();


        if (TextUtils.isEmpty(searchValue)) {
            AppTool.toast(this, "Need input name medicine !");
            return;
        }

        new PostSearch().execute();
    }


    @OnClick({R.id.button_search})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button_search:
                SearchProduct();
                finish();
                break;
        }
    }

    private class PostSearch extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            status = HttpHandler.postRequestSearchProduct("http://localhost:2020/api/v1/get/search",searchKey);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(SearchActivity.this, status, Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            setResult(101, intent);
            finish();
        }
    }













}
