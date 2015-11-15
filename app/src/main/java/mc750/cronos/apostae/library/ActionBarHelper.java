package mc750.cronos.apostae.library;


import android.content.Context;

public interface ActionBarHelper {

    Context getApplicationContext();

    void showToolbar();

    void hideToolbar();

    void showToolbarBy(int dy);

    void hideToolbarBy(int dy);

    int getToolbarHeight();

    float getToolbarTranslationY();
}
