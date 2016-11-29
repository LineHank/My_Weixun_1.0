package com.xulihao.my_weixun_10.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.News_Juhe_Bean;

import java.util.List;


/**
 * Created by zz on 2016/10/14.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<News_Juhe_Bean.ResultBean.DataBean> mData;
    private LayoutInflater inflater;
    private Context context;
    private NewsAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(NewsAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public NewsAdapter(List<News_Juhe_Bean.ResultBean.DataBean> mData, Context context) {
        this.mData = mData;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context).load(mData.get(position).getThumbnail_pic_s()).into(holder.imageView);
        holder.tv_time.setText(mData.get(position).getDate());
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_type.setText(mData.get(position).getCategory());
        holder.tv_line.setText("- "+(position+1)+" -");
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData!=null){
            return mData.size();
        }
        return 0;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_time;
        TextView tv_type;
        TextView tv_line;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_news_firstImg);
            tv_title = ( TextView) itemView.findViewById(R.id.tv_news_title);
            tv_time = ( TextView) itemView.findViewById(R.id.tv_news_time);
            tv_type = ( TextView) itemView.findViewById(R.id.tv_news_type);
            tv_line = ( TextView) itemView.findViewById(R.id.tv_news_line);
        }
    }
}
