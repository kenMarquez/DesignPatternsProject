package com.devf.designpatternsproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    @BindView(R.id.promo_rv)
    RecyclerView rvPromo;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        settingsRecyclerView();
        return view;
    }

    private void settingsRecyclerView() {
        List<String> items = Arrays.asList("e", "e", "e", "e");
        PromoAdapter adapterPromo = new PromoAdapter(items);
        rvPromo.setAdapter(adapterPromo);
        rvPromo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvPromo.setHasFixedSize(true);
    }

}
