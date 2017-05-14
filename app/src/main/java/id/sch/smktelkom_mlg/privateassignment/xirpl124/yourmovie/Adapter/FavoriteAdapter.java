package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Favorite;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.R;

/**
 * Created by user on 14/05/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    ArrayList<Favorite> favoriteList;
    IWatcListAdapter mIWatchListAdapter;

    public FavoriteAdapter(Context context, ArrayList<Favorite> favoriteList) {

        this.favoriteList = favoriteList;
        mIWatchListAdapter = (IWatcListAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_2, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Favorite watch = favoriteList.get(position);
        holder.tvJudulWL.setText(watch.title);
        holder.tvDeskripsiWL.setText(watch.overview);
        holder.itemView.setBackgroundColor(watch.color);
    }

    @Override
    public int getItemCount() {
        if (favoriteList != null)
            return favoriteList.size();
        return 0;

    }

    public interface IWatcListAdapter {
        void doDelete(long id_sugar);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJudulWL;
        TextView tvDeskripsiWL;
        ImageButton ibDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudulWL = (TextView) itemView.findViewById(R.id.textViewJudulWL);
            tvDeskripsiWL = (TextView) itemView.findViewById(R.id.textViewOverviewWL);
            ibDelete = (ImageButton) itemView.findViewById(R.id.buttonWatch);

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Favorite watch = favoriteList.get(getAdapterPosition());
                    long id_sugar = watch.getId();
                    mIWatchListAdapter.doDelete(id_sugar);
                }
            });
        }
    }
}