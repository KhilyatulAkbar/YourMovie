package id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.FavoriteAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.NowAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.TopAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Adapter.UpcomingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment.FavoriteFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment.NowFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment.PopularFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment.TopFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Fragment.UpcomingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl124.yourmovie.Model.Favorite;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NowAdapter.IDataAdapter, PopularAdapter.IDataAdapter, TopAdapter.IDataAdapter, FavoriteAdapter.IWatcListAdapter, UpcomingAdapter.IDataAdapter {

    public static final String NOW = "now";
    public static final String NOWTITLE = "nowTitle";
    public static final String NOWOVERVIEW = "nowOverview";
    public static final String NOWDATE = "nowDate";
    public static final String NOWPOSTER = "nowPoster";
    ArrayList<Favorite> mList = new ArrayList<>();
    Favorite favoriteList;
    NowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changePage(R.id.nav_now);
        navigationView.setCheckedItem(R.id.nav_now);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changePage(id);

        return true;
    }

    private void changePage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_now) {
            fragment = new NowFragment();
            setTitle("Now Playing");
        } else if (id == R.id.nav_top) {
            fragment = new TopFragment();
            setTitle("Top Rated");
        } else if (id == R.id.nav_popular) {
            fragment = new PopularFragment();
            setTitle("Popular");
        } else if (id == R.id.nav_upcoming) {
            fragment = new UpcomingFragment();
            setTitle("Upcoming");
        } else if (id == R.id.nav_share) {
            fragment = new FavoriteFragment();
            setTitle("Favorite");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void doClick(String title, String overview, String release, String poster) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(NOWTITLE, title);
        intent.putExtra(NOWOVERVIEW, overview);
        intent.putExtra(NOWDATE, release);
        intent.putExtra(NOWPOSTER, poster);
        startActivity(intent);
    }

    @Override
    public void doSave(String title, String overview) {
        favoriteList = new Favorite(title, overview, ColorUtil.getRandomColor());
        favoriteList.save();
    }

    @Override
    public void doDelete(long id_sugar) {
        Favorite watch = Favorite.findById(Favorite.class, id_sugar);
        watch.delete();

        changePage(R.id.nav_share);
    }
}
