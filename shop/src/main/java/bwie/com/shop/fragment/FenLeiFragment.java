package bwie.com.shop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bwie.com.shop.R;
import bwie.com.shop.adapter.LeftAdapter;
import bwie.com.shop.adapter.RightAdapter;
import bwie.com.shop.bean.FenLeiBean;
import bwie.com.shop.bean.RightBean;
import bwie.com.shop.presenter.FLpresenterImpl;

public class FenLeiFragment extends Fragment
{

    private RecyclerView fen1;
    private RecyclerView fen2;
    private FLpresenterImpl fLpresenter;
    private RightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fenlei, container, false);
        //左侧布局
        fen1 = view.findViewById(R.id.fen1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        fen1.setLayoutManager(linearLayoutManager);
        //初始化p层
        fLpresenter = new FLpresenterImpl(this);
        fLpresenter.getFlData();
        fLpresenter.getightData(1);
        //右侧布局
        fen2 = view.findViewById(R.id.fen2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        fen2.setLayoutManager(gridLayoutManager);
        return view;
    }
    public void getLeftData(Object obj)
    {
        FenLeiBean fenLeiBean = (FenLeiBean) obj;
        LeftAdapter leftAdapter = new LeftAdapter(getActivity(), fenLeiBean);
        leftAdapter.ClickListener(new LeftAdapter.ClickListener() {
            @Override
            public void CallBack(int position, List<FenLeiBean.DataBean> fenLeiBean) {
                fLpresenter.getightData(fenLeiBean.get(position).getCid());
                rightAdapter.notifyDataSetChanged();

            }
        });
        fen1.setAdapter(leftAdapter);
    }
    public  void getRightData(Object obj)
    {
       RightBean rightBean = (RightBean) obj;
        rightAdapter = new RightAdapter(getActivity(), rightBean);
        fen2.setAdapter(rightAdapter);


    }
}
