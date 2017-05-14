package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String nowPoster = getIntent().getStringExtra(MainActivity.NOWPOSTER);
        String nowTitle = getIntent().getStringExtra(MainActivity.NOWTITLE);
        String nowOverview = getIntent().getStringExtra(MainActivity.NOWOVERVIEW);
        String nowDate = getIntent().getStringExtra(MainActivity.NOWDATE);
        ImageView ivDetail = (ImageView) findViewById(R.id.imageViewPoster);
        ImageView ivScroll = (ImageView) findViewById(R.id.imageViewScroll);
        TextView tvTitle = (TextView) findViewById(R.id.textViewTitle);
        TextView tvOverview = (TextView) findViewById(R.id.textViewOverview);
        TextView tvDate = (TextView) findViewById(R.id.textViewDate);

        setTitle(nowTitle);

        tvTitle.setText(nowTitle);
        tvOverview.setText(nowOverview);
        tvDate.setText(nowDate);
        Glide.with(getApplication()).load("http://image.tmdb.org/t/p/w500" + nowPoster)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(ivDetail);
        Glide.with(getApplication()).load("http://image.tmdb.org/t/p/w500" + nowPoster)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(ivScroll);
    }
}
