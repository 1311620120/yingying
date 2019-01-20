package bwie.com.shop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recker.flybanner.FlyBanner;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import bwie.com.shop.LoginActivity;
import bwie.com.shop.R;
import bwie.com.shop.adapter.FenLeiAdapter;
import bwie.com.shop.adapter.MiaoShaAdapter;
import bwie.com.shop.bean.HomeBean;
import bwie.com.shop.presenter.HomePresenterImpl;
import bwie.com.shop.view.CustomTitle;
import bwie.com.shop.view.CustomView;

public class ShouYeFragment extends Fragment
{

    private View view;
    private ArrayList<String> list;
    private RecyclerView rec1;
    private RecyclerView rec2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shouye, container, false);
        //最上面的轮播图
        FlyBanner ban1 =  view.findViewById(R.id.ban1);
        list = new ArrayList<>();
        list.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        ban1.setImagesUrl(list);
        HomePresenterImpl homePresenter = new HomePresenterImpl(this);
        homePresenter.getDatas();
        //请求标题
        rec1 = view.findViewById(R.id.rec1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),5);
        rec1.setLayoutManager(gridLayoutManager);
        //请求京东秒杀
        rec2 =view.findViewById(R.id.rec2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rec2.setLayoutManager(linearLayoutManager);
        //请求推荐
        FlyBanner ban2 = view.findViewById(R.id.ban2);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://m.360buyimg.com/n0/jfs/t6700/155/2098998076/156185/6cf95035/595dd5a5Nc3a7dab5.jpg!q70.jpg");
        strings.add("http://m.360buyimg.com/n0/jfs/t8830/106/1760940277/195595/5cf9412f/59bf2ef5N5ab7dc16.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5428/70/1520969931/274676/b644dd0d/591128e7Nd2f70da0.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5566/365/1519564203/36911/620c750c/591128eaN54ac3363.jpg!q70.jpg");
        strings.add("http://m.360buyimg.com/n12/jfs/t7768/184/1153704394/148460/f42e1432/599a930fN8a85626b.jpg!q70.jpg");
        strings.add("http://m.360buyimg.com/n0/jfs/t6130/97/1370670410/180682/1109582a/593276b1Nd81fe723.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5698/110/2617517836/202970/c9388feb/593276b7Nbd94ef1f.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5698/110/2617517836/202970/c9388feb/593276b7Nbd94ef1f.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5815/178/2614671118/51656/7f52d137/593276c7N107b725a.jpg!q70.jpg|http://m.360buyimg.com/n0/jfs/t5878/60/2557817477/30873/4502b606/593276caN5a7d6357.jpg!q70.jpg");
        ban2.setImagesUrl(strings);
        //搜索历史和自定义控件
       CustomTitle title = view.findViewById(R.id.home_title);
        final CustomView lishi = view.findViewById(R.id.lishi);
        title.setonclickButton(new CustomTitle.onclickButton() {
            @Override
            public void getdata(String str) {
                TextView textView=new TextView(getActivity());
                textView.setText(str);
                textView.setBackgroundResource(R.drawable.bd_bg);
                lishi.addView(textView);
            }
        });
        return view;
    }
  public void  getHomeData(Object obj)
  {
      HomeBean homeBean  = (HomeBean) obj;
      List<HomeBean.DataBean.FenleiBean> fenlei = homeBean.getData().getFenlei();
      //标题
      FenLeiAdapter fenLeiAdapter = new FenLeiAdapter(getActivity(), fenlei);
      rec1.setAdapter(fenLeiAdapter);
      //京东秒杀
      HomeBean.DataBean.MiaoshaBean miaosha = homeBean.getData().getMiaosha();
      MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(getActivity(), miaosha);
      rec2.setAdapter(miaoShaAdapter);
  }
}
