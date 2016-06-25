package test.study.com.mychabaike.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.study.com.httplibs.HttpHelper;
import test.study.com.httplibs.Request;
import test.study.com.mychabaike.R;
import test.study.com.mychabaike.beans.Infomation;
import test.study.com.mychabaike.utils.ConstantKey;

/**
 * create in 2016/6/23 10:19 by xinquan(version 1.0)
 * function:
 */
public class HomefragmentListViewAdapter extends BaseAdapter {
    private List<Infomation> infomations;
    private Context context;

    public HomefragmentListViewAdapter(Context context, List<Infomation> infomations) {
        this.context = context;
        this.infomations = infomations;
    }

    @Override
    public int getCount() {
        return infomations.size();
    }

    @Override
    public Object getItem(int position) {
        return infomations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.homefragment_lv_item,null);
            viewHolder = new ViewHolder();
            viewHolder.des = (TextView) convertView.findViewById(R.id.home_fg_lv_item_tv_desc);
            viewHolder.count = (TextView) convertView.findViewById(R.id.home_fg_lv_item_tv_rc);
            viewHolder.time = (TextView) convertView.findViewById(R.id.home_fg_lv_item_tv_time);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.home_fg_lv_item_iv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.des.setText(infomations.get(position).getDescription());
        viewHolder.count.setText("阅读数："+infomations.get(position).getRcount());
        viewHolder.time.setText(infomations.get(position).getTime());
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        loadImage(ConstantKey.IMAGE_URL+infomations.get(position).getImg(),viewHolder.imageView);
        return convertView;
    }
   class ViewHolder{
       public TextView des,count,time;
       public ImageView imageView;
   }
    public void loadImage(final String url, final ImageView imageView){
        Log.i("loadImage",url);
        imageView.setTag(url);
        Request request = new Request(new Request.CallBack() {
            @Override
            public void onError() {

            }

            @Override
            public void onResponse() {

            }
        }, Request.Method.GET,url) {
            @Override
            public void dispatchContent(byte[] content) {
                if (imageView != null && content != null && ((String)imageView.getTag()).equals(url)){
                final Bitmap bitmap = BitmapFactory.decodeByteArray(content,0,content.length);
                    Log.i("loadImage","22222222222"+"");
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });

                }
            }
        };
        HttpHelper.addRequest(request);
    }
}
