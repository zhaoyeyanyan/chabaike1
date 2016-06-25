package test.study.com.mychabaike.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.study.com.httplibs.HttpHelper;
import test.study.com.httplibs.Request;
import test.study.com.mychabaike.R;
import test.study.com.mychabaike.utils.DBUtils;

/**
 * create in 2016/6/23 14:51 by xinquan(version 1.0)
 * function:
 */
public class DetailActivity extends Activity {
    private TextView textView;
    private ImageView iv_share,iv_collect,iv_back;
    private String url;
    private String des;
    private DBUtils dbUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        iv_share = (ImageView) findViewById(R.id.detail_iv_share);
        iv_collect = (ImageView) findViewById(R.id.detail_iv_collection);
        iv_back = (ImageView) findViewById(R.id.detail_iv);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        des = intent.getStringExtra("des");
        Log.i("onCreate", url);
        textView = (TextView) findViewById(R.id.detail_textview);
        Request request = new Request(new Request.CallBack() {
            @Override
            public void onError() {

            }

            @Override
            public void onResponse() {

            }
        }, Request.Method.GET, url) {
            @Override
            public void dispatchContent(byte[] content) {
                final String text = new String(content);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                  textView.setText(text);
              //    textView.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
                  textView.setText(Html.fromHtml(text));
                    }
                });
            }
        };
        HttpHelper.addRequest(request);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.detail_iv:
                onBackPressed();
                break;
            case R.id.detail_iv_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "请选择分享的地方"));
                break;
            case R.id.detail_iv_collection:
                dbUtils = new DBUtils(DetailActivity.this);
                ContentValues values = new ContentValues();
                values.put("c_des",des);
                values.put("c_content",url);
                dbUtils.insert(values);
                Toast.makeText(DetailActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
