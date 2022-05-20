package com.zjamss.smartcity2.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.databinding.NewsFragmentItemBinding;
import com.zjamss.smartcity2.http.dto.NewsDTO;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragmentItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragmentItem extends Fragment {


    private List<NewsDTO.RowsDTO> list;
    NewsFragmentItemBinding binding;

    public NewsFragmentItem() {
        // Required empty public constructor
    }

    public static NewsFragmentItem newInstance(List<NewsDTO.RowsDTO> list) {
        NewsFragmentItem fragment = new NewsFragmentItem();
        Bundle args = new Bundle();
        args.putSerializable("list", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = (List<NewsDTO.RowsDTO>) getArguments().getSerializable("list");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment_item, container, false);
        binding = NewsFragmentItemBinding.bind(view);
        binding.newsList.setAdapter(new NewsAdapter(list));
        binding.newsList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        return view;
    }
}