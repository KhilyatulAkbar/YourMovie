package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.FavoriteAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Favorite;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    FavoriteAdapter mAdapter;
    ArrayList<Favorite> mList = new ArrayList<>();

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.mRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FavoriteAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        refreshData(null);
    }

    private void refreshData(String query) {
        mList.clear();

        if (query == null || query.isEmpty())
            mList.addAll(Favorite.listAll(Favorite.class));

        mAdapter.notifyDataSetChanged();
    }
}
