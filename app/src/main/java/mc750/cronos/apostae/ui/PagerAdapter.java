package mc750.cronos.apostae.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;

import mc750.cronos.apostae.library.ActionBarHelper;
import mc750.cronos.apostae.library.OnCreateListViewListener;
import mc750.cronos.apostae.library.RecyclerViewOnScrollListener;
import mc750.cronos.apostae.main.OlympicSportListFragment;
import mc750.cronos.apostae.main.PopularListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private ActionBarHelper actionBarHelper;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        this(fm, NumOfTabs, null);
    }

    public PagerAdapter(FragmentManager fm, int NumOfTabs, ActionBarHelper actionBarHelper) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.actionBarHelper = actionBarHelper;
    }

    public Fragment getItem(int position) {
        Fragment tab = null;
        switch (position) {

            case 0:
                PopularListFragment tab2 = new PopularListFragment();
                tab2.setOnCreateViewListener(new OnCreateListViewListener() {
                    @Override
                    public void onCreateListViewListener(RecyclerView recyclerView) {
                        recyclerView.setOnScrollListener(new RecyclerViewOnScrollListener(actionBarHelper));
                    }
                });
                tab = tab2;
                break;

            case 1:
                OlympicSportListFragment tab1 = new OlympicSportListFragment();
                tab1.setOnCreateViewListener(new OnCreateListViewListener() {
                    @Override
                    public void onCreateListViewListener(RecyclerView recyclerView) {
                        recyclerView.setOnScrollListener(new RecyclerViewOnScrollListener(actionBarHelper));
                    }
                });
                tab = tab1;
                break;

            default:
                tab = null;
        }

        //this.setScrollCallbacks(tab);

        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}