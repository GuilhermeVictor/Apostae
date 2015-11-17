package mc750.cronos.apostae.bet;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;


import mc750.cronos.apostae.R;
import mc750.cronos.apostae.library.Utils;

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
    private void makeDialog(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //builder.setTitle(getString(R.string.bet_dialogTitle));

        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_bet, null);
        builder.setView(dialogView);

        builder.setPositiveButton(getString(R.string.bet_positive), null);
        //builder.setNeutralButton(getString(R.string.bet_neutral), null);
        builder.setNegativeButton(getString(R.string.bet_negative), null);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                //Button neutral = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                Button negative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                positive.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                //neutral.setTextColor(getResources().getColor(R.color.colorPrimary));
                negative.setTextColor(getResources().getColor(R.color.colorPrimaryDark));


                positive.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Utils.snackBar(dialogView, getString(R.string.not_implemented));
                    }
                });

                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.snackBar(dialogView, getString(R.string.not_implemented));
                    }
                });*/
            }
        });


        dialog.show();
    }
}
