package mc750.cronos.apostae.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mc750.cronos.apostae.R;

public class OlympicSportViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageButton imageButton;

    OlympicSportViewHolder(View view) {
        super(view);

        textView = (TextView) view.findViewById(R.id.text);

        View v = view.findViewById(R.id.sport_pictogram);
        if (v != null)
            imageButton = (ImageButton) v;
    }

    public void bindItem(String text, int resid) {
        textView.setText(text);
        imageButton.setBackgroundResource(resid);
    }

    public void bindItem(String text) {
        textView.setText(text);
    }
}
