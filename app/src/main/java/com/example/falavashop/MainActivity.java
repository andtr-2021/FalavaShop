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


    // Slider View

    // Urls of our images.
    String url1 = "https://www.akc.org/wp-content/uploads/2017/11/Golden-Retriever-Puppy.jpg";
    String url2 = "https://www.akc.org/wp-content/uploads/2017/11/Golden-Retriever-Puppy.jpg";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopee_mall);

        // we are creating array list for storing our image urls.
        ArrayList<SliderModel> sliderDataArrayList = new ArrayList<>();


        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
            sliderDataArrayList.add(new SliderModel(url1));
            sliderDataArrayList.add(new SliderModel(url2));
            sliderDataArrayList.add(new SliderModel(url3));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
            sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
            sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
            sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
            sliderView.startAutoCycle();
    }
}