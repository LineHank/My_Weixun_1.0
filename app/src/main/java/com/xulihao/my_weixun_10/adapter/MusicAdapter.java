package com.xulihao.my_weixun_10.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.Music_Bean;


import java.util.List;


/**
 * Created by zz on 2016/10/14.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private List<Music_Bean.SongListBean> mData;
    private LayoutInflater inflater;
    private Context context;
    private MusicAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(MusicAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MusicAdapter(List<Music_Bean.SongListBean> mData, Context context) {
        this.mData = mData;
        this.context=context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_music, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context).load(mData.get(position).getPic_big()).into(holder.music_image);
        holder.music_title.setText(mData.get(position).getAlbum_title());
        holder.music_ratingbar.setProgress(Integer.parseInt(mData.get(position).getHot())/10000);
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
        ImageView music_image;
        TextView music_title;
        RatingBar music_ratingbar;
        public MyViewHolder(View itemView) {
            super(itemView);
            music_image = (ImageView) itemView.findViewById(R.id.music_image);
            music_title = ( TextView) itemView.findViewById(R.id.music_title);
            music_ratingbar = ( RatingBar) itemView.findViewById(R.id.music_ratingbar);
        }
    }
}
