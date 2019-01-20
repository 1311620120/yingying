package bwie.com.shop.presenter;

import android.support.v4.app.Fragment;

import bwie.com.shop.api.Api;
import bwie.com.shop.bean.FenLeiBean;
import bwie.com.shop.fragment.FenLeiFragment;
import bwie.com.shop.model.FLModelImol;
import bwie.com.shop.model.IFLModel;

public class FLpresenterImpl implements IFLpresenter
{
    FenLeiFragment fenLeiFragment;
    private final FLModelImol flModelImol;

    public FLpresenterImpl(FenLeiFragment fenLeiFragment)
    {
        this.fenLeiFragment = fenLeiFragment;
        flModelImol = new FLModelImol();
    }

    @Override
    public void getFlData() {
        flModelImol.getFLModel(Api.FENLEI, new IFLModel.FlCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                fenLeiFragment.getLeftData(obj);

            }

            @Override
            public void LoadFail() {

            }
        });

    }

    @Override
    public void getightData(int id) {
        flModelImol.getRightModel(Api.Right+"?cid="+id, new IFLModel.FlCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                fenLeiFragment.getRightData(obj);

            }

            @Override
            public void LoadFail() {

            }
        });
    }


}
