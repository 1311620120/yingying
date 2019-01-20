package bwie.com.shop.model;


import bwie.com.shop.bean.ShopBean;
import bwie.com.shop.http.OkHttp;

public class ShopModelImpl implements  IShopModel {
    @Override
    public void getShopModel(String url, final ShopCallBack shopCallBack) {
        OkHttp.getInstance().doGet(url, ShopBean.class, new OkHttp.NetCallBack() {

            @Override
            public void LoadSuccess(Object obj) {
             shopCallBack.success(obj);
            }

            @Override
            public void LoadFail() {
                shopCallBack.fail();
            }
        });
    }
}
