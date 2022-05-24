package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.http.dto.PatientCardDTO;

import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-23 16:08
 **/
public class PatientCardAdapter extends RecyclerView.Adapter<PatientCardAdapter.ViewHolder> {

    List<PatientCardDTO.RowsDTO> list;

    PatientCardAdapter(List<PatientCardDTO.RowsDTO> patients){
        list = patients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PatientCardDTO.RowsDTO patient = list.get(position);
        holder.name.setText("姓名："+patient.getName());
        holder.sex.setText("性别："+(patient.getSex().equals("0")?"男":"女"));
        holder.tel.setText("电话号码："+patient.getTel());

        if(patient.getId() == 0){
            holder.itemView.setOnClickListener(v->{
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),CreatePatientCardActivity.class));
            });
        }
        holder.service.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(),OutpatientDepartmentListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("patientName",patient.getName());
            intent.putExtras(bundle);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView sex;
        TextView tel;
        ImageView service;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patientName);
            sex = itemView.findViewById(R.id.patientSex);
            tel = itemView.findViewById(R.id.patientTel);
            service = itemView.findViewById(R.id.outpatientService);
        }
    }


}
