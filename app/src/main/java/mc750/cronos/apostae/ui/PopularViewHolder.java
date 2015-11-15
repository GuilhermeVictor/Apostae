package mc750.cronos.apostae.ui;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mc750.cronos.apostae.R;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView oddsTextView;
    private ImageView imageView;

    public PopularViewHolder(View view) {
        super(view);

        titleTextView = (TextView) view.findViewById(R.id.fragment_item_popular_title_text);
        descriptionTextView = (TextView) view.findViewById(R.id.fragment_item_popular_description_text);
        oddsTextView = (TextView) view.findViewById(R.id.fragment_item_popular_odds_text);
        imageView = (ImageView) view.findViewById(R.id.popularBackgroundImage);
    }

    public void bindItem(String title, String description, String odds, int resid) {
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        oddsTextView.setText(odds);
        imageView.setImageResource(resid);
    }
}
