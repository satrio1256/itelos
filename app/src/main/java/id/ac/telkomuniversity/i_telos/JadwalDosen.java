package id.ac.telkomuniversity.i_telos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class JadwalDosen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View floatingContainer;
    Boolean sliderStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dosen);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView image = (ImageView) findViewById(R.id.menuToggle);
        image.setClickable(true);
        image.bringToFront();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        if (navigationView.getCheckedItem() != null) {
            navigationView.getCheckedItem().setChecked(false);
        }
        navigationView.getMenu().findItem(R.id.jadwal).setChecked(true);

        floatingContainer = findViewById(R.id.floatingContainer);
        floatingContainer.setVisibility(View.INVISIBLE);
        sliderStats = false;

        View clickableCardView = findViewById(R.id.clickable_card_child);
        clickableCardView.setClickable(true);
        clickableCardView.bringToFront();
        clickableCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sliderStats) {
                    slideUp(floatingContainer);
                } else {
                    slideDown(floatingContainer);
                }
            }
        });
    }

    public void slideUp(View floatingContainer) {
        TranslateAnimation animateContainer = new TranslateAnimation(0, 0, floatingContainer.getHeight(), 0);
        animateContainer.setDuration(300);
        animateContainer.setFillAfter(true);
        floatingContainer.startAnimation(animateContainer);
        sliderStats = !sliderStats;
    }

    public void slideDown(View floatingContainer) {
        TranslateAnimation animateContainer = new TranslateAnimation(0, 0, 0, floatingContainer.getHeight());
        animateContainer.setDuration(300);
        animateContainer.setFillAfter(true);
        floatingContainer.startAnimation(animateContainer);
        sliderStats = !sliderStats;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else if (sliderStats) {
            slideDown(floatingContainer);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jadwal_dosen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_page) {
            Intent intent = new Intent(this, Dosen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if (id == R.id.jadwal) {
            Intent intent = new Intent(this, JadwalDosen.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.pengaturan_akun) {
            Intent intent = new Intent(this, ProfilDosen.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.penilaian) {
            Intent intent = new Intent(this, PenilaianDosen.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.logout) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
