package bwie.com.shop.model;

import bwie.com.shop.bean.Reg;
import bwie.com.shop.http.OkHttp;

public class RegModelImpl implements IRegModel
{

    @Override
    public void regModel(String url, String name, String pass, final RegCallBack regCallBack) {
        OkHttp.getInstance().doPostt(url, name, pass, Reg.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                regCallBack.LoadSuccess(obj);
            }

            @Override
            public void LoadFail() {
              regCallBack.LoadFail();
            }
        });
    }
}
