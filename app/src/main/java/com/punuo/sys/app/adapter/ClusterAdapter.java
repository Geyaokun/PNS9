package com.punuo.sys.app.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.punuo.sys.app.R;
import com.punuo.sys.app.model.Cluster;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 2017/9/12.
 */

public class ClusterAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cluster> list=new ArrayList<>();
    Object obj=new Object();
    public ClusterAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void appendData(List<Cluster> clusters){
        synchronized (obj) {
            if (clusters.isEmpty()) return;
            list.clear();
            list.addAll(clusters);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        synchronized (obj) {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listfragmentitem, parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.devIcon.setImageResource(R.drawable.icon_online);
        holder.devName.setText(list.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.devIcon)
        ImageView devIcon;
        @Bind(R.id.devName)
        TextView devName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
