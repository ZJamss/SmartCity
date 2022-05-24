package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.model.DepartmentBook;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-24 16:54
 **/
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    List<DepartmentBook> books;

    BookListAdapter(List<DepartmentBook> list){
        books = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DepartmentBook book = books.get(position);
        holder.name.setText(book.getCategoryName());
        holder.money.setText("挂号费: " + book.getMoney());
        holder.date.setText(book.getDate());

        holder.book.setOnClickListener(v -> {
            JSONObject object = new JSONObject();
            try {
                object.put("categoryId", book.getId());
                object.put("money", book.getMoney());
                object.put("patientName", book.getPatientName());
                object.put("reserveTime", book.getDate());
                object.put("type", book.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            http.request.book(Constants.TOKEN, RequestBody.create(MediaType.parse("application/json"), object.toString()))
                    .enqueue(new CallBackImpl<BasicDTO>() {
                        @Override
                        public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                            super.onResponse(call, response);
                            if (response.body().getCode() != 200) {
                                Toast.makeText(holder.itemView.getContext(), "预约失败", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(holder.itemView.getContext(),BookInfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("book",book);
                                intent.putExtras(bundle);
                                holder.itemView.getContext().startActivity(intent);
                            }
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView money;
        TextView date;
        Button book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bookDepartmentName);
            money = itemView.findViewById(R.id.bookDepartmentMoney);
            date = itemView.findViewById(R.id.bookDate);
            book = itemView.findViewById(R.id.bookDepartment);
        }
    }

}
