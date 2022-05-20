package com.zjamss.smartcity2.ui.index;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;
import com.zjamss.smartcity2.SmartCityApplication;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.http.dto.BannerDTO;

import java.util.List;

/**
 * @Program: SmartCity
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-03-18 10:55
 **/
public class BannerAdapterImpl extends BannerAdapter<BannerDTO.RowsDTO,BannerAdapterImpl.BannerViewHolder> {
    Context context;

    public BannerAdapterImpl(List<BannerDTO.RowsDTO> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerDTO.RowsDTO data, int position, int size) {
//        holder.imageView.setImageResource(data.getImg());
        Glide.with(context).load(Constants.BASE_URL +data.getAdvImg()).into(holder.imageView);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public BannerViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}
