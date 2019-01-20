package bwie.com.shop;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import bwie.com.shop.fragment.FenLeiFragment;
import bwie.com.shop.fragment.MyFragment;
import bwie.com.shop.fragment.ShopFragment;
import bwie.com.shop.fragment.ShouYeFragment;
import bwie.com.shop.fragment.XunFragment;


public class MainActivity extends AppCompatActivity {
       BottomTabBar bottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabBar =  findViewById(R.id.bottomBtn);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(30,30)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.BLACK,Color.BLUE)
                .setFontSize(10)
                .addTabItem("首页",R.drawable.ic_account_balance_black_24dp,ShouYeFragment.class)
                .addTabItem("分类",R.drawable.ic_widgets_black_24dp,FenLeiFragment.class)
                .addTabItem("购物车",R.drawable.ic_shopping_cart_black_24dp,ShopFragment.class)
                .addTabItem("寻觅",R.drawable.see_false,XunFragment.class)
                .addTabItem("我的",R.drawable.people_flase,MyFragment.class)
               ;
    }
}
