package bwie.com.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import bwie.com.shop.bean.ShopBean;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    Context context; ShopBean shopBean;
    public ShopAdapter(Context context, ShopBean shopBean) {
        this.context = context;
        this.shopBean = shopBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.shangjia, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        holder.shop_name.setText(shopBean.getData().get(position).getSellerName());


        //加载子布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        holder.product.setLayoutManager(linearLayoutManager);
        List<ShopBean.DataBean.ListBean> list = shopBean.getData().get(position).getList();
        final ProductAdapter productAdapter = new ProductAdapter(context, list);
        holder.product.setAdapter(productAdapter);
        holder.shangjia.setChecked(shopBean.getData().get(position).isChecked());
        //产品适配器回调jiekou
        productAdapter.ShopCallBack(new ProductAdapter.ShopCallBack() {
            @Override
            public void shopCallBack() {
                if(shopCallBack != null)
                {
                    shopCallBack.shopCallBack(shopBean.getData());
                }
                //得到产品的集合
                List<ShopBean.DataBean.ListBean> product = shopBean.getData().get(position).getList();
                boolean isAllChecked =true;
                //循环遍历产品  判断产品是否被选中
                for (ShopBean.DataBean.ListBean beans :product)
                {
                    //如果有一个没有被选中，商家就不选
                    if(!beans.isChecked())
                    {
                        isAllChecked =false;
                        break;
                    }
                }
                holder.shangjia.setChecked(isAllChecked);
                shopBean.getData().get(position).setChecked(isAllChecked);
            }
        });
        holder.shangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给商家赋值
                shopBean.getData().get(position).setChecked(holder.shangjia.isChecked());
                productAdapter.AllorRemove(holder.shangjia.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return shopBean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shop_name;
        CheckBox shangjia;
        RecyclerView product;
        public ViewHolder(View itemView) {
            super(itemView);
           shangjia= itemView.findViewById(R.id.shangjia);
           shop_name= itemView.findViewById(R.id.shop_name);
            product =itemView.findViewById(R.id.product);
        }
    }
    public interface ShopCallBack{
        void shopCallBack(List<ShopBean.DataBean> list);
    }
    ShopCallBack shopCallBack;
    public void ShopCallBack(ShopCallBack shopCallBack)
    {
        this.shopCallBack = shopCallBack ;

    }
}
