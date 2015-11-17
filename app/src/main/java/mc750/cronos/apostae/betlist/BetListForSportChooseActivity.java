package mc750.cronos.apostae.betlist;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import mc750.cronos.apostae.R;
import mc750.cronos.apostae.bet.BetChooseActivity;
import mc750.cronos.apostae.library.Utils;
import mc750.cronos.apostae.main.NavigationDrawerActivity;

public class BetListForSportChooseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list_for_sport_choose_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.athletics);

        findViewById(R.id.bet_card1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        findViewById(R.id.bet_card2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        findViewById(R.id.bet_card3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        findViewById(R.id.bet_card4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        findViewById(R.id.bet_card5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        findViewById(R.id.bet_card6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BetChooseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
            finish();

            return true;
        }
        else {
            
            Utils.snackBar(findViewById(android.R.id.content), getString(R.string.not_implemented));
        }
        
        return false;
    }
}
