package test.study.com.httplibs;

import java.lang.reflect.Method;

/**
 * create in 2016/6/21 19:10 by xinquan(version 1.0)
 * function:
 */
abstract public class Request<T> {
    private String url;
    private Method method;
    private CallBack callBack;

    public Request(CallBack callBack, Method method, String url) {
        this.callBack = callBack;
        this.method = method;
        this.url = url;
    }
    public enum Method{
        GET,POST
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public interface CallBack {
        public void onError();

        public void onResponse();
    }

    abstract public void dispatchContent(byte[] content);
}

