package mc750.cronos.apostae.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mc750.cronos.apostae.library.OnCreateListViewListener;
import mc750.cronos.apostae.main.OlympicSportListFragment;
import mc750.cronos.apostae.main.PopularListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private OnCreateListViewListener createListViewListener;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        this(fm, NumOfTabs, null);
    }

    public PagerAdapter(FragmentManager fm, int NumOfTabs, OnCreateListViewListener createListViewListener) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.createListViewListener = createListViewListener;
    }

    public Fragment getItem(int position) {
        Fragment tab = null;
        switch (position) {
            case 0:
                OlympicSportListFragment tab1 = new OlympicSportListFragment();
                tab1.setOnCreateViewListener(this.createListViewListener);
                tab = tab1;
                break;

            case 1:
                PopularListFragment tab2 = new PopularListFragment();
                tab2.setOnCreateViewListener(this.createListViewListener);
                tab = tab2;
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