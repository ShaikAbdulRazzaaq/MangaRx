package com.projects.ebookapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.ebookapp.R;

public class HomePageFragment extends Fragment {
    RecyclerView trending;
    RecyclerView popular;
    RecyclerView latest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popular=view.findViewById(R.id.rv_popular);
        trending=view.findViewById(R.id.rv_trending);
        latest=view.findViewById(R.id.rv_latestMangaReleases);
    }
}
