package bwie.com.shop.http;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp
{
    private OkHttpClient client;
    private static volatile OkHttp instance;
    private Handler handler = new Handler();

    //创建拦截器
    private Interceptor getAppInterceptor()
    {
        Interceptor interceptor= new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Log.e("++++++","拦截前");
                Response response = chain.proceed(request);
                Log.e("++++++","拦截后");
                return response;
            }
        };
        return interceptor;
    }
    //添加拦截器
    private OkHttp()
    {
        File file = new File(Environment.getExternalStorageDirectory(), "cache1");
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000,TimeUnit.SECONDS)
                .connectTimeout(3000,TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file,10*1024))
                .build();
    }
    //创建单例
    public  static OkHttp getInstance()
    {
        if(instance == null)
        {
            synchronized (OkHttp.class)
            {
                if(null == instance)
                {
                    instance = new OkHttp();
                }
            }
        }
        return instance;
    }
    //封装doget方法
    public void doGet(String url, final Class clazz, final NetCallBack netCallBack)
    {
        // 2. 创建一个请求对象
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        //创建call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(string, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.LoadSuccess(o);
                    }
                });
            }
        });
    }
    //封装doPost请求
    public void doPost(String url, Map<String,String> params, final Class clazz, final NetCallBack netCallBack)
    {
        FormBody.Builder body = new FormBody.Builder();
        for(String key :params .keySet())
        {
            body.add(key,params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().toString();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.LoadSuccess(o);
                    }
                });
            }
        });
    }
    //封装doPost请求
    public void doPostt(String url,String phone,String pass, final Class clazz, final NetCallBack netCallBack)
    {
        //新建okhttp对象
       client = new OkHttpClient();
        /**
         * 通过体传值
         */
        FormBody build = new FormBody.Builder()
                .add("phone", phone)
                .add("pwd", pass)
                .build();
        //创建request
        Request request = new Request.Builder()
                .url(url)
                .post(build)
                .build();

        //回调方法
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("onResponse", "onResponse: "+response.body().string());
            }
        });
    }
    public interface NetCallBack{
        void LoadSuccess(Object obj);
        void LoadFail();
    }
}
