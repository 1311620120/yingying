package bwie.com.shop.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import bwie.com.shop.R;

public class MyFragment  extends Fragment
{ ImageView img;
    private View contentView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        img=view.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //找到pop所在的位置   xml
                contentView = View.inflate(getActivity(), R.layout.pop,
                        null);
                //新建pop  三个参数：contentView  宽  高
                PopupWindow popupWindow = new PopupWindow(contentView,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //外部可点击
                popupWindow.setOutsideTouchable(true);
                //显示在下方
                popupWindow.showAsDropDown(v);
                //pop.xml 布局里按钮点击事件
                contentView.findViewById(R.id.came).setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                            }
                        });
                contentView.findViewById(R.id.photo).setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(
                                        Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 2);
                            }
                        });

            }

        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                // 获取位图
                Bitmap bitmap = data.getParcelableExtra("data");
                // 设置图片
                img.setImageBitmap(bitmap);
                break;
            case 2:
                Uri uri = data.getData();
                img.setImageURI(uri);
                break;


            default:
                break;
        }
    }
}
