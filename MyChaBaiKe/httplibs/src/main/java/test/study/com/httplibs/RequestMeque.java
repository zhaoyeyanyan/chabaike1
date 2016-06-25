package test.study.com.httplibs;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * create in 2016/6/21 20:34 by xinquan(version 1.0)
 * function:
 */
public class RequestMeque {
    private BlockingDeque<Request> mdeque = new LinkedBlockingDeque();
    private static final int MAX_THREAD = 3;
    private NetWorkDispatcher[] works = new NetWorkDispatcher[MAX_THREAD];
    public RequestMeque(){
         initDispatcher();
    }

    private void initDispatcher() {

        for (int i = 0; i <works.length ; i++) {
            works[i] = new NetWorkDispatcher(mdeque);
            works[i].start();
         }
    }
    public void addRequest(Request request){

        mdeque.add(request);
    }
    public void stop(){
        for (int i = 0; i <works.length ; i++) {
            works[i].flag = false;
            works[i].interrupt();
        }
    }
}
