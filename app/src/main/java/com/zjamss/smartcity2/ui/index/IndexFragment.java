package com.zjamss.smartcity2.ui.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.youth.banner.indicator.CircleIndicator;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.databinding.FragmentIndexBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BannerDTO;
import com.zjamss.smartcity2.http.dto.NewsDTO;
import com.zjamss.smartcity2.http.dto.NewsTagDTO;
import com.zjamss.smartcity2.http.dto.RecommendedServiceDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.ui.acitivty.user.NewsListActivity;
import com.zjamss.smartcity2.ui.news.FragmentAdapter;
import com.zjamss.smartcity2.ui.news.NewsFragmentItem;
import com.zjamss.smartcity2.ui.news.NewsViewModel;
import com.zjamss.smartcity2.ui.recommendedService.RecommendedServiceViewModel;
import com.zjamss.smartcity2.ui.recommendedService.ServiceAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class IndexFragment extends Fragment {

    FragmentIndexBinding binding;

    public static final int BANNER_CODE = 1;

    BannerDTO bannerDTO;
    RecommendedServiceViewModel recommendedServiceViewModel;
    NewsViewModel newsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        binding = FragmentIndexBinding.bind(view);
        recommendedServiceViewModel = new ViewModelProvider(requireActivity()).get(RecommendedServiceViewModel.class);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        updateBannerList();
        updateRecommendedService();
        updateNews();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.equals("")){
                    NewsListActivity.actionStart(getActivity(),query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private void updateNews() {
        newsViewModel.getNewsTags().observe(getViewLifecycleOwner(),dto->{
            for(NewsTagDTO.DataDTO tag : dto.getData()){
                binding.newsFrag.tapLayout.addTab(binding.newsFrag.tapLayout.newTab().setText(tag.getName()));
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
            binding.newsFrag.newsVp.setAdapter(fragmentAdapter);
        });

        binding.newsFrag.newsVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.newsFrag.tapLayout.getTabAt(position).select();
            }
        });
        binding.newsFrag.tapLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.newsFrag.newsVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void updateBannerList(){
        http.request.getBannerList().enqueue(new CallBackImpl<BannerDTO>() {
            @Override
            public void onResponse(Call<BannerDTO> call, Response<BannerDTO> response) {
                super.onResponse(call, response);
                if(response.isSuccessful()){
                    bannerDTO = response.body();
                    List<BannerDTO.RowsDTO> rows = bannerDTO.getRows();
                    binding.banner.addBannerLifecycleObserver(IndexFragment.this)
                            .setAdapter(new BannerAdapterImpl(rows))
                            .setIndicator(new CircleIndicator(IndexFragment.this.getContext()));
                }
            }
        });
    }

    private void updateRecommendedService(){
        recommendedServiceViewModel.getRecommendedService().observe(getViewLifecycleOwner(),services->{
            List<RecommendedServiceDTO.RowsDTO> rows = services.getRows();
            rows.sort(Comparator.comparingInt(RecommendedServiceDTO.RowsDTO::getSort));
            binding.recommendedService.setLayoutManager(new GridLayoutManager(this.getContext(),5));
            binding.recommendedService.setAdapter(new ServiceAdapter(rows,true));
        });
    }

}