package mc750.cronos.apostae.library;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;

public interface ObservableScrollViewFragment {

    ObservableListView getListView();

    void setOnCreateViewListener(OnCreateListViewListener l);
}
