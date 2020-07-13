package com.projects.ebookapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.projects.ebookapp.R

class HomePageFragment : Fragment() {
    var trending: RecyclerView? = null
    var popular: RecyclerView? = null
    var latest: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.homepage_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popular = view.findViewById(R.id.rv_popular)
        trending = view.findViewById(R.id.rv_trending)
        latest = view.findViewById(R.id.rv_latestMangaReleases)
    }
}