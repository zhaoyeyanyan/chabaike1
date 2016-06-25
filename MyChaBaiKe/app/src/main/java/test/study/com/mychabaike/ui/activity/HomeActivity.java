package test.study.com.mychabaike.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.study.com.mychabaike.R;
import test.study.com.mychabaike.adapter.HomeFragAdapter;
import test.study.com.mychabaike.beans.TabTitle;

/**
 * create in 2016/6/22 17:22 by xinquan(version 1.0)
 * function:
 */
public class HomeActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<TabTitle> tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = (TabLayout) findViewById(R.id.home_fg_tab);
        viewPager = (ViewPager) findViewById(R.id.home_fg_vp);
        tabs = new ArrayList<>();
        getData();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(new HomeFragAdapter(tabs,getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getData() {
        tabs.add(new TabTitle("社会热点",6));
        tabs.add(new TabTitle("企业要闻",1));
        tabs.add(new TabTitle("医疗新闻",2));
        tabs.add(new TabTitle("生活贴士",3));
        tabs.add(new TabTitle("药品新闻",4));
        tabs.add(new TabTitle("疾病快讯",7));
        tabs.add(new TabTitle("食品新闻",5));
}
    public void onClick(View view){
        if(view.getId() == R.id.home_activity_collection){
            Intent intent = new Intent();
            intent.setClass(HomeActivity.this,CollectionActivity.class);
            startActivity(intent);
        }
    }
}
