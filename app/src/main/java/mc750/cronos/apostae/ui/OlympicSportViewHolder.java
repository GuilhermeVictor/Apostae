package mc750.cronos.apostae.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mc750.cronos.apostae.R;

public class OlympicSportViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    OlympicSportViewHolder(View view) {
        super(view);

        mTextView = (TextView) view.findViewById(R.id.text);
    }

    public void bindItem(String text) {
        mTextView.setText(text);
    }

    @Override
    public String toString() {
        return mTextView.getText().toString();
    }

}
