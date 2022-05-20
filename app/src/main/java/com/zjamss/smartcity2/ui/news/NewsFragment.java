package com.zjamss.smartcity2.ui.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.databinding.FragmentNewsBinding;
import com.zjamss.smartcity2.http.dto.NewsDTO;
import com.zjamss.smartcity2.http.dto.NewsTagDTO;

import java.util.ArrayList;

public class NewsFragment extends Fragment {


    FragmentNewsBinding binding;
    NewsViewModel newsViewModel;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        binding = FragmentNewsBinding.bind(view);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        updateNews();
        return view;
    }

    private void updateNews() {
        newsViewModel.getNewsTags().observe(getViewLifecycleOwner(),dto->{
            for(NewsTagDTO.DataDTO tag : dto.getData()){
                binding.tapLayout.addTab(binding.tapLayout.newTab().setText(tag.getName()));
            }
        });

        newsViewModel.getNews().observe(getViewLifecycleOwner(),dto->{

            ArrayList<NewsDTO.RowsDTO> todayNews = new ArrayList<>();
            ArrayList<NewsDTO.RowsDTO> tagNews = new ArrayList<>();
            ArrayList<NewsDTO.RowsDTO> policyNews = new ArrayList<>();
            ArrayList<NewsDTO.RowsDTO> economicNews = new ArrayList<>();
            ArrayList<NewsDTO.RowsDTO> cultureNews = new ArrayList<>();
            ArrayList<NewsDTO.RowsDTO> technologyNews = new ArrayList<>();
            for(NewsDTO.RowsDTO newsBean : dto.getRows()){
                switch (Integer.parseInt(newsBean.getType())){
                    case 9: todayNews.add(newsBean); break;
                    case 17: tagNews.add(newsBean); break;
                    case 19: policyNews.add(newsBean); break;
                    case 20: economicNews.add(newsBean); break;
                    case 21: cultureNews.add(newsBean); break;
                    case 22: technologyNews.add(newsBean); break;
                }
            }
            NewsFragmentItem todayFrag = NewsFragmentItem.newInstance(todayNews);
            NewsFragmentItem tagFrag = NewsFragmentItem.newInstance(tagNews);
            NewsFragmentItem policyFrag = NewsFragmentItem.newInstance(policyNews);
            NewsFragmentItem economicFrag = NewsFragmentItem.newInstance(economicNews);
            NewsFragmentItem cultureFrag = NewsFragmentItem.newInstance(cultureNews);
            NewsFragmentItem technologyFrag = NewsFragmentItem.newInstance(technologyNews);

            ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
            fragmentArrayList.add(todayFrag);
            fragmentArrayList.add(tagFrag);
            fragmentArrayList.add(policyFrag);
            fragmentArrayList.add(economicFrag);
            fragmentArrayList.add(cultureFrag);
            fragmentArrayList.add(technologyFrag);
            FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), getLifecycle(), fragmentArrayList);
            binding.newsVp.setAdapter(fragmentAdapter);
        });

        binding.newsVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tapLayout.getTabAt(position).select();
            }
        });
        binding.tapLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.newsVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}