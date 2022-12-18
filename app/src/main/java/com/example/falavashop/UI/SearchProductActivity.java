package com.example.falavashop.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falavashop.Adapter.SearchAdapter;
import com.example.falavashop.Config.Config;
import com.example.falavashop.Model.Results;
import com.example.falavashop.R;
import com.example.falavashop.retrofit.RetrofitAPI;
import com.example.falavashop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchProductActivity extends AppCompatActivity {

    ImageView button_search;
    EditText edtsearch;


    Toolbar toolbar;
    RecyclerView recyclerView;
    List<Results> ListSearchProduct;
    RetrofitAPI retrofitAPI;

    SearchAdapter searchAdapter;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

    }

    private void initView() {
        ListSearchProduct = new ArrayList<>();
        edtsearch = findViewById(R.id.edtseach);
        recyclerView = findViewById(R.id.recycle_view_search);
        button_search = findViewById(R.id.button_search);


        retrofitAPI = RetrofitClient.getInstance(Config.PRODUCER_BASE_URL).create(RetrofitAPI.class);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataSearch(edtsearch.getText().toString());
            }
        });

    }

    private void getDataSearch(String s) {
        ListSearchProduct.clear();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("searchValue", s);
        compositeDisposable.add(retrofitAPI.getListSearch(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            ListSearchProduct = data.getResults();
                            searchAdapter = new SearchAdapter(getApplicationContext(), ListSearchProduct);
                            recyclerView.setAdapter(searchAdapter);
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}