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

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Top;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.R;

/**
 * Created by user on 14/05/2017.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyViewHolder> {

    ArrayList<Top> topList;
    IDataAdapter mIDataAdapter;
    Context context;

    public TopAdapter(Context context, ArrayList<Top> nowList) {
        this.topList = nowList;
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
        Top list = topList.get(position);
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
        if (topList != null)
            return topList.size();
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
                    Top now = topList.get(getAdapterPosition());
                    mIDataAdapter.doClick(now.title, now.overview, now.release_date, now.poster_path);
                }
            });

            ibButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Top nowPlaying = topList.get(getAdapterPosition());
                    mIDataAdapter.doSave(nowPlaying.title, nowPlaying.overview);
                }
            });
        }
    }
}
