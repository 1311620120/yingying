package bwie.com.shop.model;

import bwie.com.shop.RegActivity;

public interface IRegModel
{
    public void regModel(String url, String name, String pass, RegCallBack regCallBack);
    interface RegCallBack
    {
        void LoadSuccess(Object data);
        void LoadFail();
    }
}
