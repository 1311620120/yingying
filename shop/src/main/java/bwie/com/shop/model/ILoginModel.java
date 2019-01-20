package bwie.com.shop.model;

public interface ILoginModel
{
    public void loginModel(String url, String name, String pass, LoginCallBack loginCallBack);
    interface LoginCallBack
    {
        void LoadSuccess(Object data);
        void LoadFail();
    }
}
