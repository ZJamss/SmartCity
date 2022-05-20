package com.zjamss.smartcity2.ui.recommendedService;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.databinding.FragmentServiceBinding;


public class ServiceFragment extends Fragment {

    FragmentServiceBinding binding;
    RecommendedServiceViewModel recommendedServiceViewModel;

    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        binding = FragmentServiceBinding.bind(view);
        recommendedServiceViewModel = new ViewModelProvider(requireActivity()).get(RecommendedServiceViewModel.class);
        updateRecommendedService();
        return view;
    }

    private void updateRecommendedService() {
        recommendedServiceViewModel.getRecommendedService().observe(getViewLifecycleOwner(),services -> {
            binding.recommendedServices.setLayoutManager(new GridLayoutManager(this.getContext(), 5));
            binding.recommendedServices.setAdapter(new ServiceAdapter(services.getRows(), false));
        });
    }
}