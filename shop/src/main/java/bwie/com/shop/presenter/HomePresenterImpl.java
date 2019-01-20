package bwie.com.shop.presenter;

import bwie.com.shop.api.Api;
import bwie.com.shop.fragment.ShouYeFragment;
import bwie.com.shop.model.HomeModelImpl;
import bwie.com.shop.model.IHomeModel;

public class HomePresenterImpl implements IHomePresenter{
    ShouYeFragment shouYeFragment;
    private final HomeModelImpl homeModel;

    public HomePresenterImpl(ShouYeFragment shouYeFragment)
    {
        this.shouYeFragment = shouYeFragment;
        homeModel = new HomeModelImpl();
    }

    @Override
    public void getDatas()
    {
        homeModel.getSYModel(Api.SHOU, new IHomeModel.HomeCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
             shouYeFragment.getHomeData(obj);
            }

            @Override
            public void LoadFail() {

            }
        });

    }
}
