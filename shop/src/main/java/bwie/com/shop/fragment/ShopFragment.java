package bwie.com.shop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import bwie.com.shop.R;
import bwie.com.shop.adapter.ShopAdapter;
import bwie.com.shop.bean.ShopBean;
import bwie.com.shop.presenter.ShopPresenterImpl;


public class ShopFragment extends Fragment
{
    RecyclerView shop_rec;
    TextView money,payNum;
    CheckBox all;
    private ShopBean shopBean;
    private ShopAdapter shopAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop, container, false);
        //设置布局
        shop_rec =  view.findViewById(R.id.shop_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shop_rec.setLayoutManager(linearLayoutManager);
        //初始化presenter层
        final ShopPresenterImpl shopPresenter = new ShopPresenterImpl(this);
        shopPresenter.getShopPresenter();
        //得到控件
        money= view.findViewById(R.id.money);
        payNum= view.findViewById(R.id.payNum);
        all = view.findViewById(R.id.all);
        //全选按钮
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  MoneyAndCount(all.isChecked());
                  shopAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    public void getData(Object obj)
    {
        shopBean = (ShopBean) obj;
        shopAdapter = new ShopAdapter(getActivity(), shopBean);
        shop_rec.setAdapter(shopAdapter);
        //计算价格 和数量
        shopAdapter.ShopCallBack(new ShopAdapter.ShopCallBack() {
            @Override
            public void shopCallBack(List<ShopBean.DataBean> list) {
                double totalMoney =0;
                int totalNum = 0;
                int num =0;
                //循环遍历商家
                for(int a = 0; a<list.size() ; a++)
                {
                    //循环遍历商品
                    List<ShopBean.DataBean.ListBean> listAll = list.get(a).getList();
                    for(int  i =0 ; i<listAll.size() ; i++)
                    {
                        totalNum = totalNum + Integer.parseInt(listAll.get(i).getNum());
                        //如果备选中
                        if(listAll.get(i).isChecked())
                        {
                            //价格数量改变
                            totalMoney = totalMoney +(Double.valueOf(listAll.get(i).getNum())*Double.valueOf(listAll.get(i).getBargainPrice()));
                            num = num +Integer.parseInt(listAll.get(i).getNum()) ;
                        }
                    }
                    if(totalNum >num)
                    {
                        all.setChecked(false);
                    }else{
                        all.setChecked(true);
                    }
                    payNum.setText("去结算"+num);
                    money.setText("总价钱"+totalMoney);
                }
            }
        });

    }

    //定义一个方法计算数量个价钱
    public void MoneyAndCount(boolean bool)
    {
            double totalMoney =0;
            int totalNum = 0;
            //循环遍历商家。判断商家的状态
        for(int a = 0; a< shopBean.getData().size(); a++)
        {
            ShopBean.DataBean dataBean = shopBean.getData().get(a);
            dataBean.setChecked(bool);
            List<ShopBean.DataBean.ListBean> list = dataBean.getList();
            //循环遍历商品
            for(int i =0 ; i<list .size(); i++)
            {
                list.get(i).setChecked(bool);
                totalMoney = totalMoney +(Double.valueOf(list.get(i).getBargainPrice())*Double.valueOf(list.get(i).getNum()));
                totalNum = totalNum +Integer.parseInt(list.get(i).getNum());
            }
        }
        if(bool)
        {
            payNum.setText("去结算"+totalNum);
            money.setText("总价钱"+totalMoney);
        }else{
            payNum.setText("去结算  0");
            money.setText("总价钱 0.00");
        }
    }

}
