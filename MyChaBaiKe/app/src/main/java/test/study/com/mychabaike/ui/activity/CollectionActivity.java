package test.study.com.mychabaike.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.study.com.mychabaike.R;
import test.study.com.mychabaike.adapter.CollectionListViewAdapter;
import test.study.com.mychabaike.utils.DBUtils;

/**
 * create in 2016/6/23 19:37 by xinquan(version 1.0)
 * function:
 */
public class CollectionActivity extends Activity {
    private ListView listView;
    private BaseAdapter adapter;
    private List<String> list_urls,list_dess;
    private DBUtils dbUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        listView = (ListView) findViewById(R.id.collection_listView);
        list_dess = new ArrayList<>();
        list_urls = new ArrayList<>();
        dbUtils = new DBUtils(CollectionActivity.this);
        Cursor cursor = dbUtils.queryAll();
        while(cursor.moveToNext()){
            int des_index = cursor.getColumnIndex("c_des");
            String desStr = cursor.getString(des_index);
            int url_index = cursor.getColumnIndex("c_content");
            String urlStr = cursor.getString(url_index);
            list_dess.add(desStr);
            list_urls.add(urlStr);
        }
        adapter = new CollectionListViewAdapter(CollectionActivity.this,list_dess);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(CollectionActivity.this, DetailActivity.class);
                String url = list_urls.get(position);
                intent.putExtra("url",url);
                String des = list_dess.get(position);
                intent.putExtra("des",des);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onClick(View view){
        if(view.getId() == R.id.collection_iv){
            onBackPressed();
        }
    }
}
