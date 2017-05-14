package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Popular;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.PopularResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    PopularAdapter mIDataAdapter;
    ArrayList<Popular> popularList = new ArrayList<>();

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView rvChord = (RecyclerView) getView().findViewById(R.id.mRecycler);
        rvChord.setLayoutManager(new GridLayoutManager(this.getActivity(), 1));
        mIDataAdapter = new PopularAdapter(this.getActivity(), popularList);
        rvChord.setAdapter(mIDataAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        downloadDataArticles();

    }

    private void downloadDataArticles() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=c328695daa3d5020ecccc67d0f2b4d49&language=en-US&page=1";

        GsonGetRequest<PopularResponse> myRequest = new GsonGetRequest<PopularResponse>
                (url, PopularResponse.class, null, new Response.Listener<PopularResponse>() {

                    @Override
                    public void onResponse(PopularResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        popularList.addAll(response.results);
                        mIDataAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }


}
