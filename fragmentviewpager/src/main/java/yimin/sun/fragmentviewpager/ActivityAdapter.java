package yimin.sun.fragmentviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于将各个Activity平铺出来，独立打开
 *
 * Created by yzsh-sym on 2017/8/25.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.VH> {

    private List<DataModel> data;

    /**
     * 列表项上展示pageName和clazz.getSimpleName，点击时触发onItemClick，此时第二个参数仅作展示用
     */
    public void addItem(String pageName, Class clazz, Callback1 onItemClick) {
        if (data == null) {
            data  = new ArrayList<>();
        }

        data.add(new DataModel(pageName, clazz, onItemClick));
    }

    /**
     * 列表项上展示pageName和clazz.getSimpleName，点击时启动pageClass所指的activity
     */
    public void addActivityItem(String pageName, final Class<? extends Activity> clazz) {
        addItem(pageName, clazz, new Callback1() {
            @Override
            public void onCall(Object arg) {
                Context context = (Context) arg;
                context.startActivity(new Intent(context, clazz));
            }
        });
    }

    /**
     * 列表项上展示pageName和clazz.getSimpleName，点击时启动以fragment为内容的FragmentShellActivity
     */
    public void addFragmentItem(String pageName, final Class<? extends Fragment> clazz) {
        addFragmentItem(pageName, clazz, null);
    }

    /**
     * 列表项上展示pageName和clazz.getSimpleName，点击时启动以fragment为内容的FragmentShellActivity
     */
    public void addFragmentItem(String pageName, final Class<? extends Fragment> clazz, final Bundle fBundle) {
        addItem(pageName, clazz, new Callback1() {
            @Override
            public void onCall(Object arg) {
                Context context = (Context) arg;
                Intent intent = FragmentShellActivity.getStartIntent(context, clazz, fBundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tv1.setText(data.get(position).pageName);
        holder.tv2.setText(data.get(position).pageClass.getSimpleName());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv1, tv2;

        VH(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(android.R.id.text1);
            tv2 = (TextView) itemView.findViewById(android.R.id.text2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataModel dm = data.get(getLayoutPosition());
                    Context context = v.getContext();

                    if (dm.onClick != null) {
                        dm.onClick.onCall(context);
                    }
                }
            });
        }
    }

    private static class DataModel {

        DataModel(String pageName, Class pageClass, Callback1 onClick) {
            this.pageName = pageName;
            this.pageClass = pageClass;
            this.onClick = onClick;
        }

        String pageName;
        Class pageClass;
        Callback1 onClick;
    }
}