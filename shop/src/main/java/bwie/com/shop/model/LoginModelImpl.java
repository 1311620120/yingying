package bwie.com.shop.model;

import bwie.com.shop.bean.Reg;
import bwie.com.shop.http.OkHttp;

public class LoginModelImpl implements ILoginModel
{

    @Override
    public void loginModel(String url, String name, String pass, final LoginCallBack loginCallBack) {
        OkHttp.getInstance().doPostt(url, name, pass, Reg.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                loginCallBack.LoadSuccess(obj);
            }

            @Override
            public void LoadFail() {
                loginCallBack.LoadFail();
            }
        });
    }
}
