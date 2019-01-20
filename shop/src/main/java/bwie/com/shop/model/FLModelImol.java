package bwie.com.shop.model;

import bwie.com.shop.bean.FenLeiBean;
import bwie.com.shop.bean.RightBean;
import bwie.com.shop.http.OkHttp;

public class FLModelImol implements IFLModel
{

    @Override
    public void getFLModel(String url, final FlCallBack flCallBack) {
        OkHttp.getInstance().doGet(url, FenLeiBean.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                flCallBack.LoadSuccess(obj);
            }

            @Override
            public void LoadFail() {
                flCallBack.LoadFail();
            }
        });
    }

    @Override
    public void getRightModel(String url, final FlCallBack flCallBack) {
        OkHttp.getInstance().doGet(url, RightBean.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                flCallBack.LoadSuccess(obj);
            }

            @Override
            public void LoadFail() {
                flCallBack.LoadFail();
            }
        });
    }
}
