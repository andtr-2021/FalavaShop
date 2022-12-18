package com.example.falavashop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.falavashop.Config.Config;
import com.example.falavashop.UI.SearchProductActivity;
import com.example.falavashop.retrofit.RetrofitAPI;
import com.example.falavashop.retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.internal.Utils;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewTrangChinh;
    CardView searchBar;


    RetrofitAPI api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App();
        ActionBar();
    }


    @SuppressLint("ResourceType")
    private void ActionViewFlipper() {
        List<String> ads = new ArrayList<>();
        ads.add("https://cf.shopee.vn/file/c433f40e9fc218b0f96b9ed0243bd01c_xxhdpi");
        ads.add("https://cf.shopee.vn/file/a4c94acbe2facdc761e59289fa4d3d68_xxhdpi");
        ads.add("https://cf.shopee.vn/file/2c7ab4a8b863ba26f0500986c7ab6074_xxhdpi");
        ads.add("https://cf.shopee.vn/file/f67d7dafa22186390fc37daaaa7f08d3_xxhdpi");
        for (int i=0; i<ads.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(ads.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.drawable.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.drawable.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return wifi != null && wifi.isConnected() || (mobile != null && mobile.isConnected());
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void App() {
        toolbar = findViewById(R.id.tool_bar_controller);
        viewFlipper = findViewById(R.id.vewlipper);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        listViewTrangChinh = findViewById(R.id.listviewtrangchinh);

        searchBar = findViewById(R.id.search_product);

        if(isConnected(this)){
            ActionViewFlipper();
            api = RetrofitClient.getInstance(Config.PRODUCER_BASE_URL).create(RetrofitAPI.class);
        }else{
            Toast.makeText(getApplicationContext(), "Không có Internet, vui lòng kết nối Internet", Toast.LENGTH_LONG).show();
        }

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchProduct = new Intent(getApplicationContext(), SearchProductActivity.class);
                startActivity(searchProduct);
            }
        });

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}