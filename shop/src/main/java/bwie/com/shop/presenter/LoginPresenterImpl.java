package bwie.com.shop.presenter;

import bwie.com.shop.LoginActivity;
import bwie.com.shop.RegActivity;
import bwie.com.shop.api.Api;
import bwie.com.shop.model.ILoginModel;
import bwie.com.shop.model.IRegModel;
import bwie.com.shop.model.LoginModelImpl;
import bwie.com.shop.model.RegModelImpl;

public class LoginPresenterImpl implements ILoginPresenter
{
    LoginActivity loginActivity;
    private final LoginModelImpl loginModel;


    public LoginPresenterImpl(LoginActivity loginActivity)
    {
        this.loginActivity =loginActivity;
        loginModel = new LoginModelImpl();

    }

    @Override
    public void loginPresenter(String phone, String pass)
    {
        loginModel.loginModel(Api.LOGIN, phone, pass, new ILoginModel.LoginCallBack() {
            @Override
            public void LoadSuccess(Object data) {

            }

            @Override
            public void LoadFail() {

            }
        });

    }
}
