package bwie.com.shop.presenter;

import bwie.com.shop.RegActivity;
import bwie.com.shop.api.Api;
import bwie.com.shop.model.IRegModel;
import bwie.com.shop.model.RegModelImpl;

public class RegPresenterImpl implements IRegPresenter
{
    RegActivity regActivity;
    private final RegModelImpl regModel;

    public RegPresenterImpl(RegActivity regActivity)
    {
        this.regActivity =regActivity;
        regModel = new RegModelImpl();
    }

    @Override
    public void regPresenter(String phone, String pass)
    {
        regModel.regModel(Api.LOGIN, phone, pass, new IRegModel.RegCallBack() {
            @Override
            public void LoadSuccess(Object data) {

            }

            @Override
            public void LoadFail() {

            }
        });

    }
}
