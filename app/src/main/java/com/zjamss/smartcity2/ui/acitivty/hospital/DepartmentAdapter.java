package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.http.dto.OutpatientDepartment;
import com.zjamss.smartcity2.model.DepartmentBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-24 16:34
 **/
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    List<OutpatientDepartment.RowsDTO> list;
    String patientName;

    DepartmentAdapter(List<OutpatientDepartment.RowsDTO> departments, String patientName) {
        list = departments;
        this.patientName = patientName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.outpatient_department_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OutpatientDepartment.RowsDTO department = list.get(position);
        holder.name.setText(department.getCategoryName());
        holder.money.setText("挂号费: " + department.getMoney() + "元");
        holder.itemView.setOnClickListener(v -> {
            List<DepartmentBook> list = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm ");
            for (int i = 0; i < 8; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + i);
                String date =  sdf.format(calendar.getTime());
                list.add(new DepartmentBook(
                        department.getId(),
                        department.getType(),
                        department.getCategoryName(),
                        department.getMoney(),
                        date,
                        patientName
                ));
            }
            BookListActivity.actionStart(holder.itemView.getContext(),list);
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView money;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.departmentName);
            money = itemView.findViewById(R.id.departmentMoney);
        }
    }


}
