package com.zjamss.smartcity2.ui.recommendedService;

import android.content.Intent;
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
import com.zjamss.smartcity2.http.dto.RecommendedServiceDTO;
import com.zjamss.smartcity2.ui.MainActivity;
import com.zjamss.smartcity2.ui.acitivty.event.EventActivity;

import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-10 10:19
 **/
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<RecommendedServiceDTO.RowsDTO> services;

    public ServiceAdapter(List<RecommendedServiceDTO.RowsDTO> rows, boolean isNeedSub) {
        super();
        //添加更多服务
        if (isNeedSub) {
            RecommendedServiceDTO.RowsDTO bean = new RecommendedServiceDTO.RowsDTO();
            bean.setSort(0);
            bean.setServiceName("更多服务");
            bean.setImgUrl(R.drawable.ic_baseline_more + "");
            List<RecommendedServiceDTO.RowsDTO> sub = rows.subList(0, 9);
            sub.add(bean);
            services = sub;
        } else {
            services = rows;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecommendedServiceDTO.RowsDTO bean = services.get(position);
        holder.serviceName.setText(bean.getServiceName());
        switch (bean.getServiceName()) {
            case "更多服务":
                holder.itemView.setOnClickListener(v -> MainActivity.binding.mainVp.setCurrentItem(1));
                Glide.with(holder.serviceImage).load(R.drawable.ic_baseline_more).into(holder.serviceImage);
                break;
            case "活动管理":
                holder.itemView.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), EventActivity.class)));
            default:
                Glide.with(holder.serviceImage).load(Constants.BASE_URL + bean.getImgUrl()).into(holder.serviceImage);

        }
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImage;
        TextView serviceName;

        ViewHolder(View view) {
            super(view);
            serviceImage = view.findViewById(R.id.serviceImage);
            serviceName = view.findViewById(R.id.serviceName);
        }
    }
}
