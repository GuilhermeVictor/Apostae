package mc750.cronos.apostae.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import mc750.cronos.apostae.library.ObservableScrollViewFragment;
import mc750.cronos.apostae.library.OnCreateListViewListener;
import mc750.cronos.apostae.main.OlympicSportListFragment;

public class ObservablePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private OnCreateListViewListener createListViewListener;

    public ObservablePagerAdapter(FragmentManager fm, int NumOfTabs) {
        this(fm, NumOfTabs, null);
    }

    public ObservablePagerAdapter(FragmentManager fm, int NumOfTabs, OnCreateListViewListener createListViewListener) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.createListViewListener = createListViewListener;
    }

    public Fragment getItem(int position) {
        Fragment tab = null;
        switch (position) {
            case 0:
                OlympicSportListFragment tab1 = new OlympicSportListFragment();
                tab = tab1;
                break;

            case 1:
                OlympicSportListFragment tab2 = new OlympicSportListFragment();
                tab = tab2;
                break;

            default:
                tab = null;
        }

        this.setScrollCallbacks(tab);

        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void setScrollCallbacks(Fragment fragment) {
        if (fragment != null) {
            ObservableScrollViewFragment viewFragment = (ObservableScrollViewFragment) fragment;
            viewFragment.setOnCreateViewListener(this.createListViewListener);
        }
    }
}