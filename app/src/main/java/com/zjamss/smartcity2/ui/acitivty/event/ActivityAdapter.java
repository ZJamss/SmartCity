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
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.http.dto.ActivityDTO;

import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-19 15:45
 **/
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    List<ActivityDTO.RowsDTO> list;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        TextView signUpNum;
        TextView like;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.activityTitle);
            image = itemView.findViewById(R.id.activityImage);
            signUpNum = itemView.findViewById(R.id.signUpNum);
            like = itemView.findViewById(R.id.activityLike);
            date = itemView.findViewById(R.id.activityPunishDate);
        }
    }

    public ActivityAdapter(List<ActivityDTO.RowsDTO> rows) {
        super();
        list = rows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityDTO.RowsDTO activity = list.get(position);
        holder.title.setText(activity.getName());
        holder.like.setText(activity.getLikeNum()+"赞");
        holder.signUpNum.setText(activity.getSignupNum()+"参与");
        holder.date.setText(activity.getPublishTime());
        Glide.with(holder.image).load(Constants.BASE_URL+activity.getImgUrl()).into(holder.image);

        holder.itemView.setOnClickListener(v->{
            EventDetailActivity.actionStart(holder.itemView.getContext(),activity);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
