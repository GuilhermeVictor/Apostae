package mc750.cronos.apostae.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mc750.cronos.apostae.R;
import mc750.cronos.apostae.bet.BetChooseActivity;
import mc750.cronos.apostae.main.NavigationDrawerActivity;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView oddsTextView;
    private ImageView imageView;

    public PopularViewHolder(View view, final Context context) {
        super(view);

        titleTextView = (TextView) view.findViewById(R.id.fragment_item_popular_title_text);
        descriptionTextView = (TextView) view.findViewById(R.id.fragment_item_popular_description_text);
        oddsTextView = (TextView) view.findViewById(R.id.fragment_item_popular_odds_text);
        imageView = (ImageView) view.findViewById(R.id.popularBackgroundImage);

        view.findViewById(R.id.popular_clickable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BetChooseActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public void bindItem(String title, String description, String odds, int resid) {
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        oddsTextView.setText(odds);
        imageView.setImageResource(resid);
    }
}
