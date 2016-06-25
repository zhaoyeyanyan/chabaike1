package test.study.com.mychabaike.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import test.study.com.mychabaike.beans.TabTitle;
import test.study.com.mychabaike.ui.fragment.HomeFragment;

/**
 * create in 2016/6/23 9:25 by xinquan(version 1.0)
 * function:
 */
public class HomeFragAdapter extends FragmentStatePagerAdapter{
    private List<TabTitle> list;
    public HomeFragAdapter(List<TabTitle> list, FragmentManager fm) {
        super(fm);
        this.list = list ;
    }

    @Override
    public Fragment getItem(int position) {
        HomeFragment hf = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("title_id",list.get(position).getTitle_id());
        hf.setArguments(bundle);
        return hf;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}
