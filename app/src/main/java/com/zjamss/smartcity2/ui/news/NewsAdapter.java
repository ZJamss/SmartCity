package com.zjamss.smartcity2.ui.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.SmartCityApplication;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.http.dto.NewsDTO;

import java.util.List;

/**
 * @Program: SmartCity
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-03-22 09:55
 **/
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<NewsDTO.RowsDTO> list;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
        TextView date;
        TextView comment;
        TextView like;
        TextView read;
        ViewHolder(View view){
            super(view);
            newsImage = view.findViewById(R.id.newsImage);
            newsTitle = view.findViewById(R.id.newsTitle);
            date = view.findViewById(R.id.date);
            comment = view.findViewById(R.id.comment);
            like = view.findViewById(R.id.like);
            read = view.findViewById(R.id.read);
        }
    }

    public NewsAdapter(List<NewsDTO.RowsDTO> list) {
        super();
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsDTO.RowsDTO bean = list.get(position);
        Glide.with(holder.newsImage).load(Constants.BASE_URL +bean.getCover()).into(holder.newsImage);
        holder.itemView.setOnClickListener(v->{

        });
        holder.newsTitle.setText(bean.getTitle());
        holder.comment.setText(bean.getCommentNum()+"评");
        holder.date.setText(bean.getPublishDate());
        holder.like.setText(bean.getLikeNum()+"赞");
        holder.read.setText(bean.getReadNum()+"阅");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
