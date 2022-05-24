package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zjamss.smartcity2.databinding.ActivityBookListBinding;
import com.zjamss.smartcity2.model.DepartmentBook;

import java.io.Serializable;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    ActivityBookListBinding binding;
    List<DepartmentBook> books;

    public static void actionStart(Context context, List<DepartmentBook> list){
        Intent intent = new Intent(context,BookListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("books", (Serializable) list);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        books = (List<DepartmentBook>) getIntent().getExtras().getSerializable("books");
        binding.bookList.setLayoutManager(new LinearLayoutManager(this));
        binding.bookList.setAdapter(new BookListAdapter(books));

        binding.common.setOnClickListener(v->{
            binding.expertPage.setVisibility(View.GONE);
            binding.bookList.setVisibility(View.VISIBLE);
        });
        binding.expert.setOnClickListener(v->{
            binding.bookList.setVisibility(View.GONE);
            binding.expertPage.setVisibility(View.VISIBLE);
        });
    }



}