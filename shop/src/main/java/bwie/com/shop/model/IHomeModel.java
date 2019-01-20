package bwie.com.shop.model;

import bwie.com.shop.fragment.ShouYeFragment;

public interface IHomeModel
{
    public void getSYModel(String url, HomeCallBack homeCallBack);
    interface HomeCallBack
    {
        void LoadSuccess(Object obj);
        void LoadFail();
    }
}
