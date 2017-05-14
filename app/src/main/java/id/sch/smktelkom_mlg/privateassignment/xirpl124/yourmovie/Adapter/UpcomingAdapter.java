package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Upcoming;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.R;

/**
 * Created by user on 14/05/2017.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {

    ArrayList<Upcoming> upcomingList;
    IDataAdapter mIDataAdapter;
    Context context;

    public UpcomingAdapter(Context context, ArrayList<Upcoming> upcomingList) {
        this.upcomingList = upcomingList;
        this.context = context;
        mIDataAdapter = (IDataAdapter) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Upcoming list = upcomingList.get(position);
//        holder.tvTitle.setText(list.title);
//        holder.tvOverview.setText(list.overview);
//        holder.tvDate.setText(list.release_date);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + list.poster_path)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (upcomingList != null)
            return upcomingList.size();
        return 0;
    }

    public interface IDataAdapter {
        void doClick(String title, String overview, String release, String poster);

        void doSave(String title, String overview);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        ImageButton ibButton;
        TextView tvTitle, tvOverview, tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewNow);
            ibButton = (ImageButton) itemView.findViewById(R.id.ImageButtonSave);
//            tvTitle = (TextView) itemView.findViewById(R.id.textViewNowTitle);
//            tvOverview = (TextView) itemView.findViewById(R.id.textViewNowDetail);
//            tvDate = (TextView) itemView.findViewById(R.id.textViewNowDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Upcoming now = upcomingList.get(getAdapterPosition());
                    mIDataAdapter.doClick(now.title, now.overview, now.release_date, now.poster_path);
                }
            });

            ibButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Upcoming nowPlaying = upcomingList.get(getAdapterPosition());
                    mIDataAdapter.doSave(nowPlaying.title, nowPlaying.overview);
                }
            });
        }
    }
}
