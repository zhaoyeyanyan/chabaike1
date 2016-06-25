package test.study.com.mychabaike.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import test.study.com.httplibs.HttpHelper;
import test.study.com.httplibs.Request;
import test.study.com.mychabaike.R;
import test.study.com.mychabaike.adapter.HomefragmentListViewAdapter;
import test.study.com.mychabaike.beans.Infomation;
import test.study.com.mychabaike.ui.activity.DetailActivity;

/**
 * create in 2016/6/22 20:00 by xinquan(version 1.0)
 * function:
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private PtrClassicFrameLayout mRefreshView;
    private ListView listView;
    private int title_id;
    private List<Infomation> informations;
    private HomefragmentListViewAdapter lv_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        Bundle bundle = getArguments();
        title_id = bundle.getInt("title_id");
        initView(view);
        if (savedInstanceState != null){
            Infomation[] infomations1 = (Infomation[]) savedInstanceState.getParcelableArray("cache");
            if (infomations1 != null && infomations1.length != 0){
                informations = Arrays.asList(infomations1);
                lv_adapter = new HomefragmentListViewAdapter(getContext(),informations);
                listView.setAdapter(lv_adapter);
            }else {
                getDataFromNetwork();
            }
        }else {
            getDataFromNetwork();
        }
        return view;
    }

    private void initView(View view) {
        informations = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.frag_content_lv);
        mRefreshView = (PtrClassicFrameLayout) view.findViewById(R.id.rotate_header_list_view_frame);
        mRefreshView.setResistance(1.7f);
        mRefreshView.setRatioOfHeaderHeightToRefresh(1.2f);
        mRefreshView.setDurationToClose(200);
        mRefreshView.setDurationToCloseHeader(1000);
        // default is false
        mRefreshView.setPullToRefresh(true);
        // default is true
        mRefreshView.setKeepHeaderWhenRefresh(true);

        mRefreshView.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getDataFromNetwork();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DetailActivity.class);
                String url = "http://www.tngou.net/api/info/show?id="+informations.get(position).getId();
                intent.putExtra("url",url);
                String des = informations.get(position).getDescription();
                intent.putExtra("des",des);
                startActivity(intent);
            }
        });

    }
    public void getDataFromNetwork(){
        String url = "http://www.tngou.net/api/info/list?id="+title_id;
        Log.i("getDataFromNetwork",url);
        Request request = new Request(new Request.CallBack() {
            @Override
            public void onError() {

            }
            @Override
            public void onResponse() {
              /*  getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshView.refreshComplete();
                    }
                });*/
            }
        }, Request.Method.GET,url) {
            @Override
            public void dispatchContent(byte[] content) {
                 String result = new String(content);
                 JSONObject jsonobj = null;
                 if(result != null){
                     try {
                         jsonobj = new JSONObject(result);
                         informations.clear();
                         informations = parseJson2List(jsonobj);
                         getActivity().runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 if(lv_adapter == null){
                                     lv_adapter = new HomefragmentListViewAdapter(getContext(),informations);
                                     listView.setAdapter(lv_adapter);
                                 }else{
                                     lv_adapter.notifyDataSetChanged();
                                 }
                             }
                         });
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

            }
        };
        HttpHelper.addRequest(request);
    }
    private List<Infomation> parseJson2List(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null)return  null;
        JSONArray array = jsonObject.getJSONArray("tngou");
        if (array== null ||array.length() ==0)
            return null;
        List<Infomation> list = new ArrayList<>();
        int len = array.length();
        JSONObject obj = null;
        Infomation information =null;
        for (int i = 0; i <len ; i++) {
            obj = array.getJSONObject(i);
            information = new Infomation();
            information.setDescription(obj.optString("description"));
            information.setRcount(obj.optInt("rcount"));
            long time = obj.getLong("time");
            String str = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss").format(time);
            information.setTime(str);
            information.setImg(obj.optString("img"));
            information.setId(obj.optInt("id"));
            list.add(information);
        }

        return list;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (informations == null || informations.size() == 0) return;
        Infomation[] parce = new Infomation[informations.size()];
        informations.toArray(parce);
        outState.putParcelableArray("cache", parce);
    }
}
