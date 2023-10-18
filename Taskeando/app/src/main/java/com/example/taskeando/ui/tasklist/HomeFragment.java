package com.example.taskeando.ui.tasklist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskeando.ListActivity;
import com.example.taskeando.R;
import com.example.taskeando.databinding.FragmentConfigBinding;
import com.example.taskeando.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView txtView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), txtView::setText);

        final Button btnHome = binding.btnTask;
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(v.getContext(), ListActivity.class);
             // Intent itn = new Intent(R.layout.fragment_home, container, ListActivity.class);
                startActivity(itn);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}