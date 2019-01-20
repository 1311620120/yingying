package bwie.com.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bwie.com.shop.LoginActivity;
import bwie.com.shop.R;
import bwie.com.shop.bean.HomeBean;

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.ViewHolder>
{
    Context context; HomeBean.DataBean.MiaoshaBean miaosha;
    public MiaoShaAdapter(Context context, HomeBean.DataBean.MiaoshaBean miaosha)
    {
        this.context =context ;
        this .miaosha =miaosha;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.miaosha, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {


        holder.price.setText(miaosha.getList().get(position).getBargainPrice());
        Glide.with(context)
                .load(miaosha.getList().get(position).getImages().split("\\|")[0].replace("https", "http"))
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return miaosha.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

           price = itemView.findViewById(R.id.price);
           img =  itemView.findViewById(R.id.img);
        }
    }
}
