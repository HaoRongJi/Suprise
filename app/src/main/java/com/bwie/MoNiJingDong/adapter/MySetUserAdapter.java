package com.bwie.MoNiJingDong.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.CartEventBus;
import com.bwie.MoNiJingDong.entity.UserEventBus;
import com.bwie.MoNiJingDong.entity.UserLoginBean;
import com.bwie.MoNiJingDong.ui.activity.UserActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySetUserAdapter extends RecyclerView.Adapter<MySetUserAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;
    private SharedPreferences sp;

    public MySetUserAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MySetUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_setuser_adapter, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MySetUserAdapter.MyViewHolder holder, final int position) {
        holder.mText.setText(list.get(position));
        if (list.get(position).equals("退出登录")) {

            holder.mText.setBackgroundResource(R.drawable.shape);
            holder.mText.setTextColor(R.color.black);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TastyToast.makeText(context, list.get(position), TastyToast.LENGTH_LONG, TastyToast.WARNING);
                Toast.makeText(context,list.get(position), Toast.LENGTH_SHORT).show();
                if (list.get(position).equals("退出登录")) {
                    /*HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("nickName","2");
                    hashMap.put("touxiang","2");
                    hashMap.put("background","2");*/


                    if (Activity.class.isInstance(context)) {
                        // 转化为activity，然后finish就行了
                        UserActivity activity = (UserActivity) context;
                        sp = context.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                        if (sp!=null){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.clear();
                            editor.commit();
                            EventBus.getDefault().post(new UserEventBus());
                            EventBus.getDefault().post(new CartEventBus(false));
                        }
                        activity.finish();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.text1);
        }
    }
}
