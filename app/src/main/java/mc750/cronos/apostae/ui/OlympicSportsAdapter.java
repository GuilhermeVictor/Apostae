package mc750.cronos.apostae.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.GridSLM;

import java.util.ArrayList;

import mc750.cronos.apostae.R;

public class OlympicSportsAdapter extends RecyclerView.Adapter<OlympicSportViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0x01;

    private static final int VIEW_TYPE_CONTENT = 0x00;

    private final ArrayList<LineItem> mItems;

    private boolean mMarginsFixed;

    private final Context mContext;

    public OlympicSportsAdapter(Context context) {
        mContext = context;

        final String[] sportNames = context.getResources().getStringArray(R.array.sport_names);
        final TypedArray popularDrawables = context.getResources().obtainTypedArray(R.array.sport_drawables);

        mItems = new ArrayList<>();

        //Insert headers into list of items.
        String lastHeader = "";
        int sectionManager = -1;
        int headerCount = 0;
        int sectionFirstPosition = 0;
        for (int i = 0; i < sportNames.length; i++) {
            String header = sportNames[i].substring(0, 1);
            if (!TextUtils.equals(lastHeader, header)) {
                // Insert new header view and update section data.
                sectionManager = (sectionManager + 1) % 2;
                sectionFirstPosition = i + headerCount;
                lastHeader = header;
                headerCount += 1;
                mItems.add(new LineItem(header, 0, true, sectionManager, sectionFirstPosition));
            }
            mItems.add(new LineItem(sportNames[i], popularDrawables.getResourceId(i, -1), false, sectionManager, sectionFirstPosition));
        }
    }

    public boolean isItemHeader(int position) {
        return mItems.get(position).isHeader;
    }

    public String itemToString(int position) {
        return mItems.get(position).text;
    }

    @Override
    public OlympicSportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_olympicsportlist_header_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_olympicsportlist_line_item, parent, false);
        }
        return new OlympicSportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OlympicSportViewHolder holder, int position) {
        final LineItem item = mItems.get(position);
        final View itemView = holder.itemView;

        if (!item.isHeader)
            holder.bindItem(item.text, item.resid);
        else
            holder.bindItem(item.text);

        final GridSLM.LayoutParams lp = GridSLM.LayoutParams.from(itemView.getLayoutParams());
        // Overrides xml attrs, could use different layouts too.
        if (item.isHeader) {
            lp.headerDisplay = GridSLM.LayoutParams.HEADER_STICKY;// ALIGN_START;

            lp.headerEndMarginIsAuto = false;
            lp.headerStartMarginIsAuto = false;
        }
        lp.setSlm(GridSLM.ID);
        lp.setColumnWidth(mContext.getResources().getDimensionPixelSize(R.dimen.grid_column_width));
        lp.setFirstPosition(item.sectionFirstPosition);
        itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).isHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setMarginsFixed(boolean marginsFixed) {
        mMarginsFixed = marginsFixed;
        notifyHeaderChanges();
    }

    private void notifyHeaderChanges() {
        for (int i = 0; i < mItems.size(); i++) {
            LineItem item = mItems.get(i);
            if (item.isHeader) {
                notifyItemChanged(i);
            }
        }
    }

    private static class LineItem {

        public int sectionManager;

        public int sectionFirstPosition;

        public boolean isHeader;

        public String text;

        private final int resid;

        public LineItem(String text, int resid, boolean isHeader, int sectionManager, int sectionFirstPosition) {
            this.isHeader = isHeader;
            this.text = text;
            this.resid = resid;
            this.sectionManager = sectionManager;
            this.sectionFirstPosition = sectionFirstPosition;
        }
    }
}
