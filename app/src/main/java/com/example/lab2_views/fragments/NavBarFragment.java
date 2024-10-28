package com.example.lab2_views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab2_views.R;
import com.example.lab2_views.fragments.pages.AboutPageFragment;
import com.example.lab2_views.fragments.pages.HomePageFragment;
import com.example.lab2_views.fragments.pages.SettingsPageFragment;

public class NavBarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        Button homeButton = view.findViewById(R.id.btn_home);
        Button aboutButton = view.findViewById(R.id.btn_about);
        Button settingsButton = view.findViewById(R.id.btn_settings);

        homeButton.setOnClickListener(v -> navigateToFragment(new HomePageFragment()));
        aboutButton.setOnClickListener(v -> navigateToFragment(new AboutPageFragment()));
        settingsButton.setOnClickListener(v -> navigateToFragment(new SettingsPageFragment()));

        return view;
    }

    // Method to handle fragment transactions
    private void navigateToFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(null) // Allows going back to previous fragment
                .commit();
    }
}