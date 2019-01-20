package bwie.com.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.shop.R;
import bwie.com.shop.bean.FenLeiBean;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder>
{
    Context context; FenLeiBean fenLeiBean;
    public LeftAdapter(Context context, FenLeiBean fenLeiBean) {
        this.context = context ;
        this.fenLeiBean =fenLeiBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.left, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.left_name.setText(fenLeiBean.getData().get(position).getName());
        final List<FenLeiBean.DataBean> data = fenLeiBean.getData();
        holder.left_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CallBack(position,data);
            }
        });

    }

    @Override
    public int getItemCount() {
            return fenLeiBean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView left_name;
        public ViewHolder(View itemView) {
            super(itemView);
          left_name =   itemView.findViewById(R.id.left_name);
        }
    }
    public interface ClickListener{
        void CallBack(int position, List<FenLeiBean.DataBean> fenLeiBean);
    }
    ClickListener listener;
    public void ClickListener(ClickListener listener)
    {
        this.listener =listener;
    }
}
