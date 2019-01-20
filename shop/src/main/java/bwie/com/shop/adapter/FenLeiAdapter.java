package bwie.com.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.shop.R;
import bwie.com.shop.bean.HomeBean;

public class FenLeiAdapter  extends RecyclerView.Adapter<FenLeiAdapter.ViewHolder> {
    Context context; List<HomeBean.DataBean.FenleiBean> fenlei;
    public FenLeiAdapter(Context context, List<HomeBean.DataBean.FenleiBean> fenlei)
    {
        this.context =context ;
        this.fenlei =fenlei;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_fenlei, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(fenlei.get(position).getIcon())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return fenlei.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
          img =   itemView.findViewById(R.id.home_img);
        }
    }



}
