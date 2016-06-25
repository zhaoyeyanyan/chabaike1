package test.study.com.httplibs;

/**
 * create in 2016/6/22 16:59 by xinquan(version 1.0)
 * function:
 */
public class HttpHelper {
    private static HttpHelper httpHelper;
    private RequestMeque mquene;
    public static HttpHelper getInstance(){
        if(httpHelper == null){
           synchronized (HttpHelper.class){
               if(httpHelper == null){
                   httpHelper = new HttpHelper();
               }
           }
        }
        return httpHelper;
    }
    private HttpHelper(){
        mquene = new RequestMeque();
    }
    public static void addRequest(Request request){

        getInstance().mquene.addRequest(request);
    }
}
