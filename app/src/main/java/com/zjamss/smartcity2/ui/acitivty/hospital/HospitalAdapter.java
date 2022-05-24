package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.http.dto.HospitalListDTO;

import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-22 19:14
 **/
public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    List<HospitalListDTO.RowsDTO> hospitals;

    HospitalAdapter(List<HospitalListDTO.RowsDTO> list) {
        hospitals = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalListDTO.RowsDTO hospital = hospitals.get(position);
        holder.name.setText(hospital.getHospitalName());
        holder.level.setText(hospital.getLevel()+"");
        holder.cardView.setRadius(30);
        holder.cardView.setCardElevation(10);//设置阴影部分大小
        holder.cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
        Glide.with(holder.image).load(Constants.BASE_URL+hospital.getImgUrl()).into(holder.image);
        holder.itemView.setOnClickListener(v->{
            HospitalDetailActivity.actionStart(holder.itemView.getContext(),hospital);
        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView level;
        ImageView image;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hospital_name);
            level = itemView.findViewById(R.id.hospital_level);
            image = itemView.findViewById(R.id.hospital_image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
