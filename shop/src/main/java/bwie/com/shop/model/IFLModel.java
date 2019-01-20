package bwie.com.shop.model;

public interface IFLModel
{
    public void getFLModel(String url,FlCallBack flCallBack);
    public void getRightModel(String url,FlCallBack flCallBack);
    interface  FlCallBack{
        void LoadSuccess(Object obj);
        void LoadFail();
    }
}
