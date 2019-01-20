package bwie.com.shop.model;

import bwie.com.shop.bean.HomeBean;
import bwie.com.shop.http.OkHttp;

public class HomeModelImpl implements IHomeModel
{
    @Override
    public void getSYModel(String url, final HomeCallBack homeCallBack) {
        OkHttp.getInstance().doGet(url, HomeBean.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                homeCallBack.LoadSuccess(obj);
            }
            @Override
            public void LoadFail() {
                homeCallBack.LoadFail();
            }
        });
    }
}
