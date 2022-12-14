package com.example.falavashop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;


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
        if(wifi != null && wifi.isConnected() || (mobile !=null && mobile.isConnected())){
            return true;
        }else{
            return false;
        }
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
/*        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void App() {
        viewFlipper = findViewById(R.id.vewlipper);
        toolbar = findViewById(R.id.tool_bar_controller);
        viewFlipper = findViewById(R.id.vewlipper);
        drawerLayout = findViewById(R.id.drawer_layout);

        if(isConnected(this)){
            ActionViewFlipper();
        }else{
            Toast.makeText(getApplicationContext(), "Không có Internet, vui lòng kết nối Internet", Toast.LENGTH_LONG).show();
        }

    }



    




}