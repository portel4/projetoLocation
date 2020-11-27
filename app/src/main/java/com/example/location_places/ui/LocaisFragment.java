package com.example.location_places.ui;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.location_places.R;
import com.example.location_places.model.Local;
import com.example.location_places.model.LocalViewModel;

import java.util.List;


public class LocaisFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LocalViewModel localViewModel;
    private List<Local> locais;
    private LocalAdapter adapter;
    private ProgressBar progressBar;

    public LocaisFragment() {
        // Required empty public constructor
    }

    public static LocaisFragment newInstance(String param1, String param2) {
        LocaisFragment fragment = new LocaisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        adapter = new LocalAdapter();
        localViewModel = new ViewModelProvider(this).get(LocalViewModel.class);
        localViewModel.getLocaisResponseLiveData().observe(this, new Observer<List<Local>>() {
            @Override
            public void onChanged(List<Local> locaisList) {
                if (locaisList != null) {
                    adapter.setResults(locaisList);
                }

                progressBar.setVisibility(View.GONE);
            }
        });
        adapter.setOnItemClickListener(new LocalAdapter.ItemClickListener() {
            @Override
        public void onItemClick(int position, Local local) {
                replaceFragment(R.id.frameLayoutMain,
                        CadastroLocalFragment.newInstance("",local),
                        "LOCAISFRAGMENT",
                        "LOCAIS");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locais, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLocais);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        localViewModel.getLocais();
    }
    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}