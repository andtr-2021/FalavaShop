package com.example.falavashop.UI.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.falavashop.Adapter.RecommendAdapter;
import com.example.falavashop.Config.Config;
import com.example.falavashop.Model.Results;
import com.example.falavashop.R;
import com.example.falavashop.retrofit.RetrofitAPI;
import com.example.falavashop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FragmentMainActivity extends Fragment {

    Context context;


    RecyclerView recyclerViewRecommend;
    RecyclerView recycleViewFalava;

    RecyclerView recyclerViewSpecial;
    ViewFlipper viewFlipper;


    RecommendAdapter recommendAdapter;

    RetrofitAPI api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FragmentMainActivity() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewFlipper = view.findViewById(R.id.vewlipper);
        recyclerViewRecommend = view.findViewById(R.id.recycle_view_recommend);
        recycleViewFalava = view.findViewById(R.id.recycle_view_falava);

        List<Results> list_recommend_product = new ArrayList<>();
        Results rs1 = new Results("https://cf.shopee.vn/file/f2f227a59f6a5fb1eb1dc2893057aeab", "199.000", "Tai nghe chụp tai không dây bluetooth STN28");
        Results rs2 = new Results("https://cf.shopee.vn/file/sg-11134201-22100-ojtg2floc8iv11", "199.000", "Đồng Hồ Thông Minh Y68 / D20 Kết Nối Bluetooth");
        Results rs3 = new Results("https://cf.shopee.vn/file/sg-11134201-22100-e3jofkj6csivb9_tn", "199.000", "Tai Nghe Bluetooth M10 Pro Tai Nghe Không M10 Pro");
        Results rs4 = new Results("https://cf.shopee.vn/file/7a53ba5acaf8eeab526851d742c9b62c", "199.000", "Loa Bluetooth B5+ Hình Gấu Bearbrick Kaws B5 Plus");
        Results rs5 = new Results("https://cf.shopee.vn/file/2bf9ddd983c20ee071b76ad6e30bd62e", "199.000", "Máy Chơi Game SUP 400 trò chơi G1 Plus 400 In 1");
        Results rs6 = new Results("https://cf.shopee.vn/file/15748c6b74455c9582673464cd32e396", "199.000", "Bộ bàn phím kèm chuột gaming có dây giả cơ mới nhất");
        Results rs7 = new Results("https://cf.shopee.vn/file/sg-11134201-22100-uewao1wh69iv3f", "199.000", "TWS X15 Tai nghe chơi game Tai nghe Bluetooth không dây");
        Results rs8 = new Results("https://cf.shopee.vn/file/aaefef28a938dcfd975521ca7df105d8", "199.000", "Loa Bluetooth Ssr Charge 3+ V5.0 Loa Mini Không Dây");
        Results rs9 = new Results("https://cf.shopee.vn/file/sg-11134201-22090-4zw37vv26yhvfa", "199.000", "Apple iPhone 14 Pro Max 128GB giảm 4% đơn 500K");
        Results rs10 = new Results("https://cf.shopee.vn/file/de0303fc957aff1ab5cd76b4c7d6a34c", "199.000", "Máy game Nintendo Switch OLED Kèm quà tặng");

        list_recommend_product.add(rs1);
        list_recommend_product.add(rs2);
        list_recommend_product.add(rs3);
        list_recommend_product.add(rs4);
        list_recommend_product.add(rs5);
        list_recommend_product.add(rs6);
        list_recommend_product.add(rs7);
        list_recommend_product.add(rs8);
        list_recommend_product.add(rs9);
        list_recommend_product.add(rs10);

        if(isConnected(view.getContext())){
            ActionViewFlipper(view);
            getRecommendProduct(view, recyclerViewRecommend, list_recommend_product);
            getFalavaShop(view, recycleViewFalava, list_recommend_product);

            api = RetrofitClient.getInstance(Config.PRODUCER_BASE_URL).create(RetrofitAPI.class);
        }else{
            Toast.makeText(context.getApplicationContext(), "Không có Internet, vui lòng kết nối Internet", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @SuppressLint("ResourceType")
    private void ActionViewFlipper(View view) {
        List<String> ads = new ArrayList<>();
        ads.add("https://cf.shopee.vn/file/c433f40e9fc218b0f96b9ed0243bd01c_xxhdpi");
        ads.add("https://cf.shopee.vn/file/a4c94acbe2facdc761e59289fa4d3d68_xxhdpi");
        ads.add("https://cf.shopee.vn/file/2c7ab4a8b863ba26f0500986c7ab6074_xxhdpi");
        ads.add("https://cf.shopee.vn/file/f67d7dafa22186390fc37daaaa7f08d3_xxhdpi");
        for (int i=0; i<ads.size(); i++){
            ImageView imageView = new ImageView(view.getContext());
            Glide.with(view.getContext()).load(ads.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(view.getContext(), R.drawable.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(view.getContext(), R.drawable.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return wifi != null && wifi.isConnected() || (mobile != null && mobile.isConnected());
    }


    private void getFalavaShop(View view, RecyclerView recycleViewFalava, List<Results> list){
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false);
        recycleViewFalava.setLayoutManager(horizontalLayoutManagaer);
        recycleViewFalava.setClipToPadding(false);
        recycleViewFalava.setHasFixedSize(true);

        recommendAdapter = new RecommendAdapter(view.getContext(), list);
        recycleViewFalava.setAdapter(recommendAdapter);
    }

    private void getRecommendProduct(View view, RecyclerView recyclerViewRecommend, List<Results> list_recommend_product){
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommend.setLayoutManager(horizontalLayoutManagaer);
        recyclerViewRecommend.setClipToPadding(false);
        recyclerViewRecommend.setHasFixedSize(true);
        recommendAdapter = new RecommendAdapter(view.getContext(), list_recommend_product);
        recyclerViewRecommend.setAdapter(recommendAdapter);
    }


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}