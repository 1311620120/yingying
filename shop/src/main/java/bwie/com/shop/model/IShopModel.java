package bwie.com.shop.model;

public interface IShopModel
{
    public void getShopModel(String url, ShopCallBack shopCallBack);
    interface   ShopCallBack {
        void success(Object obj);
        void fail();
    }
}
