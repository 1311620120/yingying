package bwie.com.shop.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import bwie.com.shop.R;

public class CustomTitle extends LinearLayout implements View.OnClickListener{
    private Context context;
    private EditText editText;
    private ImageView sao;
    private ImageView sou;

    public CustomTitle(Context context) {
        super(context);
        init(context);
    }

    public CustomTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context=context;
        View view = inflate(context, R.layout.zidingyi, null);
        editText = view.findViewById(R.id.title);
        sao = view.findViewById(R.id.sao);
        sou = view.findViewById(R.id.sou);
        sao.setOnClickListener(this);
        sou.setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.sou:
                monclickButton.getdata(editText.getText().toString());
                break;
            case R.id.sao:
                break;
            default:break;
        }
    }

    //接口回调
    public onclickButton monclickButton;
    public void setonclickButton(onclickButton monclickButton){
        this.monclickButton=monclickButton;
    }

    public interface onclickButton{
        void getdata(String str);
    }
}
