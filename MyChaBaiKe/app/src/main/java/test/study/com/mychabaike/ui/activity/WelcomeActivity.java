package test.study.com.mychabaike.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import test.study.com.mychabaike.R;
import test.study.com.mychabaike.adapter.WelcomeVpAdapter;

/**
 * create in 2016/6/22 17:17 by xinquan(version 1.0)
 * function:
 */
public class WelcomeActivity extends Activity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private List<ImageView> list_images;
    private int[] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3} ;
    private int position_index = 0;
    private WelcomeVpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        getData();
        adapter = new WelcomeVpAdapter(list_images);
        viewPager.setAdapter(adapter);
    }
    private void getData() {
        for(int i = 0;i<images.length;i++){
            ViewGroup.LayoutParams vp_params =new  ViewGroup.LayoutParams(
                    ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setLayoutParams(vp_params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if(i == images.length-1){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            list_images.add(imageView);
            //*********************
            ImageView pointView = new ImageView(this);
            LinearLayout.LayoutParams point_params =new  LinearLayout.LayoutParams(20, 20);
            if(i == 0){
            pointView.setImageResource(R.drawable.page_now);
            }else{
            pointView.setImageResource(R.drawable.page);
            }

            pointView.setScaleType(ImageView.ScaleType.FIT_XY);
            point_params.leftMargin = 10;
            pointView.setLayoutParams(point_params);
            linearLayout.addView(pointView);
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.welcome_vp);
        linearLayout = (LinearLayout) findViewById(R.id.welcome_ll);
        list_images = new ArrayList<>();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView imageView = (ImageView) linearLayout.getChildAt(position);
                imageView.setImageResource(R.drawable.page_now);
                ImageView imageView2 = (ImageView) linearLayout.getChildAt(position_index);
                imageView2.setImageResource(R.drawable.page);
                position_index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
