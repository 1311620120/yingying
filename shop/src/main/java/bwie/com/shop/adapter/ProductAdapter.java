package bwie.com.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import bwie.com.shop.R;
import bwie.com.shop.bar.Bar;
import bwie.com.shop.bean.ShopBean;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context; List<ShopBean.DataBean.ListBean> list;
    public ProductAdapter(Context context, List<ShopBean.DataBean.ListBean> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.product_name.setText(list.get(position).getTitle());
        holder.product_price.setText(list.get(position).getBargainPrice());
        Glide.with(context)
                .load(list.get(position).getImages().split("\\|")[0].replace("https", "http"))
                .into(holder.img);
        //把数量传给自定义view
        holder.bar.setData(context,list,position);
        // 1：首先 设置产品的复选框
        holder.product_ch.setChecked(list.get(position).isChecked());
        // 2：点击事件
        holder.product_ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //给复选赋值  刚刚这里写错了
                list.get(position).setChecked(isChecked);
                //接口回调 判断非空
                if(shopCallBack != null)
                {
                    shopCallBack.shopCallBack();
                    //到这里之后去商家适配器里面  接口复制过去
                }
            }
        });
        //回调接口
        holder.bar.BarCallBack(new Bar.BarCallBack() {
            @Override
            public void callBack() {
                if(shopCallBack !=null)
                {
                    shopCallBack.shopCallBack();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_price,product_name;
        CheckBox product_ch;
        ImageView img;
        Bar bar ;
        public ViewHolder(View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.product_img);
            product_ch=  itemView.findViewById(R.id.product_ch);
            product_name= itemView.findViewById(R.id.product_name);
            product_price=  itemView.findViewById(R.id.product_price);
            bar = itemView.findViewById(R.id.bar);
        }
    }
    interface ShopCallBack{
        void shopCallBack();
    }
    ShopCallBack shopCallBack;
    public void ShopCallBack(ShopCallBack shopCallBack)
    {
        this.shopCallBack = shopCallBack ;
    }
    public void AllorRemove(boolean isChecked)
    {
        //循环遍历商品
        for(ShopBean.DataBean.ListBean beans : list)
        {
            beans.setChecked(isChecked);
        }
        //刷新
        notifyDataSetChanged();
    }
}
