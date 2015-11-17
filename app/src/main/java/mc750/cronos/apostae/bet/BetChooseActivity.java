package mc750.cronos.apostae.bet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import mc750.cronos.apostae.R;

public class BetChooseActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_choose_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.athletics);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog(v.getContext());
            }
        };

        findViewById(R.id.btn_bet1).setOnClickListener(listener);
        findViewById(R.id.btn_bet2).setOnClickListener(listener);
        findViewById(R.id.btn_bet3).setOnClickListener(listener);
        findViewById(R.id.btn_bet4).setOnClickListener(listener);
        findViewById(R.id.btn_bet5).setOnClickListener(listener);
        findViewById(R.id.btn_bet6).setOnClickListener(listener);
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
    private void makeDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Test");
        builder.setIcon(R.drawable.apostae);
        builder.setMessage("test");
        builder.setPositiveButton("Call Now",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        builder.setNeutralButton("Setup",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        builder.show();
    }
}
