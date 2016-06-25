package test.study.com.mychabaike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.study.com.mychabaike.R;

/**
 * create in 2016/6/23 20:59 by xinquan(version 1.0)
 * function:
 */
public class CollectionListViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public CollectionListViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_lv_item,null);
             textView = (TextView) convertView.findViewById(R.id.collection_lv_item_tv_desc);
        }
            textView.setText(list.get(position));

        return convertView;
    }
}
