package bwie.com.shop.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bwie.com.shop.LoginActivity;
import bwie.com.shop.R;
import bwie.com.shop.bean.ShopBean;

public class Bar extends RelativeLayout
{

    private final ImageView jia;
    private final ImageView jian;
    private final TextView shiliang;
    Context context;
    List<ShopBean.DataBean.ListBean> list;
    int position;

    public Bar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bar,this);
        jia = findViewById(R.id.jia);
        jian = findViewById(R.id.jian);
        shiliang = findViewById(R.id.shiliang);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Bar);
        int add = typedArray.getResourceId(R.styleable.Bar_jia, 0);
        int remove = typedArray.getResourceId(R.styleable.Bar_jian, 0);
        final String num = typedArray.getString(R.styleable.Bar_num);
        typedArray.recycle();
        jian.setImageResource(remove);
        jia.setImageResource(add);
        shiliang.setText(num);
        //点击加
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = list.get(position).getNum();
                int i = Integer.parseInt(string.toString());
                i++;
                shiliang.setText(i+"");
                list.get(position).setNum(i+"");
                barCallBack.callBack();
            }
        });
        //点击减
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = list.get(position).getNum();
                Log.e("lallalallal",string);
                int i = Integer.parseInt(string.toString());
                if(i<=1)
                {
                    Toast.makeText(context,"宝贝不能再少了呢",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    i--;
                    shiliang.setText(i+"");
                    list.get(position).setNum(i+"");
                    barCallBack.callBack();
                }
            }
        });
    }
    public void setData(Context context,List<ShopBean.DataBean.ListBean> list,int position)
    {
        this.context =context;
        this.list =list;
        String count = list.get(position).getNum();
        shiliang.setText(count);
    }

    public interface BarCallBack{
        public void callBack();
    }
    BarCallBack barCallBack;
    public void BarCallBack(BarCallBack barCallBack)
    {
        this.barCallBack = barCallBack;
    }
}
