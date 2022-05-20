package com.zjamss.smartcity2.ui.acitivty.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.http.dto.ActivityCommentDTO;

import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-19 17:16
 **/
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    List<ActivityCommentDTO.RowsDTO> list;

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView nickname;
        TextView content;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nickname = itemView.findViewById(R.id.user_nickname);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
        }
    }

    public CommentAdapter(List<ActivityCommentDTO.RowsDTO> rows) {
        super();
        list = rows;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        ActivityCommentDTO.RowsDTO comment = list.get(position);
        Glide.with(holder.avatar).load(comment.getAvatar()).into(holder.avatar);
        holder.nickname.setText(comment.getNickName());
        holder.content.setText(comment.getContent());
        holder.date.setText(comment.getCommentTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
