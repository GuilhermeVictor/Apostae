package mc750.cronos.apostae.library;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    private boolean forcedShow = false;
    private int totalScrolled = 0;

    private ActionBarHelper actionBarHelper;

    public RecyclerViewOnScrollListener(ActionBarHelper actionBarHelper) {
        this.actionBarHelper = actionBarHelper;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (forcedShow || Math.abs(actionBarHelper.getToolbarTranslationY()) < actionBarHelper.getToolbarHeight()) {
                actionBarHelper.showToolbar();
            } else {
                actionBarHelper.hideToolbar();
            }

            forcedShow = false;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalScrolled += dy;

        Log.i("totalscrolled", totalScrolled + "");
        Log.i("min", Utils.convertDpToPixel(120, actionBarHelper.getApplicationContext()) + "");

        if (totalScrolled < Utils.convertDpToPixel(120, actionBarHelper.getApplicationContext())) {
            actionBarHelper.showToolbar();
            forcedShow = true;
        }
        else
            forcedShow = false;

        if (!forcedShow) {
            if (dy > 0) {
                actionBarHelper.hideToolbarBy(dy);
            } else {
                actionBarHelper.showToolbarBy(dy);
            }
        }
    }
}