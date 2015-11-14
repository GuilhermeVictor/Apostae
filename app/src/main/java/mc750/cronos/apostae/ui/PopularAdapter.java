package mc750.cronos.apostae.ui;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mc750.cronos.apostae.R;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {

    private final ArrayList<PopularLineItem> mItems;

    private boolean mMarginsFixed;

    private final Context mContext;

    public PopularAdapter(Context context) {
        mContext = context;

        final String[] popularTitle = context.getResources().getStringArray(R.array.popular_title);
        final String[] popularDescription = context.getResources().getStringArray(R.array.popular_description);
        final String[] popularOdds = context.getResources().getStringArray(R.array.popular_odds);

        mItems = new ArrayList<>();

        for (int i = 0; i < popularTitle.length; i++) {
            mItems.add(new PopularLineItem(popularTitle[i], popularDescription[i], popularOdds[i]));
        }
    }

    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_popular_line_item, parent, false);

        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        final PopularLineItem item = mItems.get(position);
        final View itemView = holder.itemView;

        holder.bindItem(item.title, item.description, item.odds);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private static class PopularLineItem {

        private final String title;
        private final String description;
        private final String odds;

        public PopularLineItem(String title, String description, String odds) {
            this.title = title;
            this.description = description;
            this.odds = odds;
        }
    }
}
