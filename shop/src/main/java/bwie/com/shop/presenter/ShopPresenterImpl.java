package bwie.com.shop.presenter;


import bwie.com.shop.api.Api;
import bwie.com.shop.fragment.ShopFragment;
import bwie.com.shop.model.IShopModel;
import bwie.com.shop.model.ShopModelImpl;

public class ShopPresenterImpl implements IShopPresenter
{
    ShopFragment shopFragment;
    private final ShopModelImpl shopModel;

    public ShopPresenterImpl(ShopFragment shopFragment)
    {
        this.shopFragment = shopFragment;
        shopModel = new ShopModelImpl();
    }

    @Override
    public void getShopPresenter()
    {
        shopModel.getShopModel(Api.SHOP, new IShopModel.ShopCallBack() {
            @Override
            public void success(Object obj) {
           shopFragment.getData(obj);
            }

            @Override
            public void fail() {

            }
        });

    }
}
