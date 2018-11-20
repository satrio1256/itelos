package id.ac.telkomuniversity.i_telos;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RegistrasiMahasiswa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Boolean sliderStats;
    View overviewMK;
    View floatingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrasi_mahasiswa);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
        navigationView.getMenu().findItem(R.id.registrasi_mk).setChecked(true);

        final ConstraintLayout mkTingkat1Header = (ConstraintLayout) findViewById(R.id.mkTingkat1Header);
        mkTingkat1Header.setClickable(true);
        mkTingkat1Header.setOnClickListener(new ConstraintLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout mkTingkat1Hideable = (LinearLayout) findViewById(R.id.mkTingkat1Hideable);
                ImageView mkTingkat1Chevron = (ImageView) findViewById(R.id.mkTingkat1Chevron);
                if (mkTingkat1Hideable.getVisibility() == LinearLayout.VISIBLE) {
                    Animation an = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    an.setDuration(500);
                    an.setFillAfter(true);
                    mkTingkat1Chevron.startAnimation(an);
                    mkTingkat1Hideable.setVisibility(LinearLayout.GONE);
                } else {
                    Animation an = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    an.setDuration(500);
                    an.setFillAfter(true);
                    mkTingkat1Chevron.startAnimation(an);
                    mkTingkat1Hideable.setVisibility(LinearLayout.VISIBLE);
                }
            }
        });

        floatingContainer = findViewById(R.id.floatingContainer);
        overviewMK = findViewById(R.id.overviewMataKuliah);
        overviewMK.setVisibility(View.INVISIBLE);
        sliderStats = false;
    }

    public void slideUp(View view, View floatingContainer) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animateContainer = new TranslateAnimation(0, 0, 0, view.getHeight());
        animateContainer.setDuration(500);
        animateContainer.setFillAfter(true);
        TranslateAnimation animate = new TranslateAnimation(0, 0, view.getHeight(), 0);
        animate.setDuration(300);
        animate.setStartOffset(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        floatingContainer.startAnimation(animateContainer);
    }

    public void slideDown(View view, View floatingContainer) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animateContainer = new TranslateAnimation(0, 0, view.getHeight(), 0);
        animateContainer.setDuration(500);
        animateContainer.setStartOffset(300);
        animateContainer.setFillAfter(true);
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, view.getHeight());
        animate.setDuration(300);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        floatingContainer.startAnimation(animateContainer);
    }

    public void onSlideViewButtonClick(View view) {
        if (sliderStats) {
            slideDown(overviewMK, floatingContainer);
        } else {
            slideUp(overviewMK, floatingContainer);
        }

        sliderStats = !sliderStats;
    }

    public void onSiapAccClick(View view) {
        Intent intent = new Intent(RegistrasiMahasiswa.this, BerandaMahasiswa.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (sliderStats) {
            slideDown(overviewMK, floatingContainer);
            sliderStats = !sliderStats;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_mahasiswa_home_drawer, menu);
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
            Intent intent = new Intent(RegistrasiMahasiswa.this, BerandaMahasiswa.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if (id == R.id.tagihan_pembayaran) {
            Intent intent = new Intent(RegistrasiMahasiswa.this, TagihanPembayaranMahasiswa.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.logout) {
            Intent intent = new Intent(RegistrasiMahasiswa.this, Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
