package mc750.cronos.apostae.betlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import mc750.cronos.apostae.R;
import mc750.cronos.apostae.bet.BetChooseActivity;

public class BetListForSportChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list_for_sport_choose);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
