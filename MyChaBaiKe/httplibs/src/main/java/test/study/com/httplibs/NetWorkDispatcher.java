package test.study.com.httplibs;

import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingDeque;

/**
 * create in 2016/6/21 20:36 by xinquan(version 1.0)
 * function:
 */
public class NetWorkDispatcher extends Thread {
    private static final String TAG = "";
    public boolean flag = true;
    private BlockingDeque<Request> blockingDeque;
    @Override
    public void run() {
       while(flag && !isInterrupted()){
           try {
               Request request = blockingDeque.take();
               byte[] result = null;
               result = getNetWorkResponse(request);
               if(result != null){
                   request.dispatchContent(result);
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (Exception e) {
               e.printStackTrace();
               flag = false;
           }
       }
    }
    public NetWorkDispatcher(BlockingDeque<Request> blockingDeque){
        this.blockingDeque = blockingDeque;
    }
    public byte[] getNetWorkResponse(Request request) throws Exception {
        if(TextUtils.isEmpty(request.getUrl())){
           throw new Exception("url is null");
        }
        if(request.getMethod() == Request.Method.GET){

            return getResponseByGET(request);
        }
        if(request.getMethod() == Request.Method.POST){

        }
        return null ;
    }
    private byte[] getResponseByGET(Request request)throws Exception{
        URL url = null;
        HttpURLConnection conn = null;
        InputStream iis = null;
        url = new URL(request.getUrl());
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        int code = conn.getResponseCode();

        if (code != 200){
            Log.d(TAG, "getNetworkResponse() returned: response code error code=" +code );
            throw new Exception("code eror");
        }
        iis = conn.getInputStream();
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[2048];
        while ((len=iis.read(buf))!=-1){
            bos.write(buf,0,len);
        }


        iis.close();
        return bos.toByteArray();
    }

}
