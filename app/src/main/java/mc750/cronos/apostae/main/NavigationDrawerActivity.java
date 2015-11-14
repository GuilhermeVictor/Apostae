package mc750.cronos.apostae.main;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import mc750.cronos.apostae.R;
import mc750.cronos.apostae.library.OnCreateListViewListener;
import mc750.cronos.apostae.library.Utils;
import mc750.cronos.apostae.ui.PagerAdapter;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OlympicSportListFragment.OnFragmentInteractionListener, PopularListFragment.OnFragmentInteractionListener {

    private EditText toolbarSearchView;
    private View searchContainer;
    private ImageView searchClearButton;
    private Menu menu;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private NavigationDrawerActivity self;

    private boolean toolbarHomeButtonAnimating = false;
    private View toolbarContainer;
    private View tabBar;

    private enum ActionDrawableState {
        BURGER, ARROW
    }

    private void init() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbarContainer = findViewById(R.id.toolbar_container);
        this.tabBar = findViewById(R.id.tab_layout);

        this.menu = toolbar.getMenu();
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        this.searchContainer = findViewById(R.id.search_container);
        this.toolbarSearchView = (EditText) findViewById(R.id.search_view);
        this.searchClearButton = (ImageView) findViewById(R.id.search_clear);

        this.self = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        init();

        setSupportActionBar(this.toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.olimpic_sport));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.popular));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.main_pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), new OnCreateListViewListener() {
            @Override
            public void onCreateListViewListener(RecyclerView recyclerView) {

                recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

                    private boolean showed = false;
                    private int totalScrolled = 0;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        Log.i("asd",

                        "\nscrolled: "+ totalScrolled + "\npx: " + Utils.convertDpToPixel(120, self.getApplicationContext()) + "\n\n");

                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            if (showed || Math.abs(toolbarContainer.getTranslationY()) < toolbar.getHeight()) {
                                showToolbar();
                            } else {
                                hideToolbar();
                            }

                            showed = false;
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        totalScrolled += dy;

                        if (totalScrolled < Utils.convertDpToPixel(120, self.getApplicationContext())) {
                            showToolbar();
                            showed = true;
                        }

                        if (!showed) {
                            if (dy > 0) {
                                hideToolbarBy(dy);
                            } else {
                                showToolbarBy(dy);
                            }
                        }
                    }

                    private void hideToolbarBy(int dy) {
                        if (cannotHideMore(dy)) {
                            toolbarContainer.setTranslationY(-tabBar.getBottom());
                        } else {
                            toolbarContainer.setTranslationY(toolbarContainer.getTranslationY() - dy);
                        }
                    }

                    private boolean cannotHideMore(int dy) {
                        return Math.abs(toolbarContainer.getTranslationY() - dy) > tabBar.getBottom();
                    }

                    private void showToolbarBy(int dy) {
                        if (cannotShowMore(dy)) {
                            toolbarContainer.setTranslationY(0);
                        } else {
                            toolbarContainer.setTranslationY(toolbarContainer.getTranslationY() - dy);
                        }
                    }
                    private boolean cannotShowMore(int dy) {
                        return toolbarContainer.getTranslationY() - dy > 0;
                    }
                });
            }
        });

        viewPager.setAdapter(adapter);
        //TODO change page change listener
        final ViewPager.OnPageChangeListener tabListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                tabListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                tabListener.onPageScrollStateChanged(state);
                showToolbar(100);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setup search container view
        try {
            // Set cursor colour to white
            // http://stackoverflow.com/a/26544231/1692770
            // https://github.com/android/platform_frameworks_base/blob/kitkat-release/core/java/android/widget/TextView.java#L562-564
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(toolbarSearchView, R.drawable.color_cursor_white);
        } catch (Exception ignored) {
        }

        // Search text changed listener
        toolbarSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchClearButton.setVisibility((s.length() > 0) ? View.VISIBLE : View.GONE);
            }
        });

        // Clear search text when clear button is tapped
        searchClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarSearchView.setText("");
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // toolbarHomeButtonAnimating is a boolean that is initialized as false. It's used to stop the user pressing the home button while it is animating and breaking things.
                if (!toolbarHomeButtonAnimating) {
                    // TODO Here you'll want to check if you have a search query set, if you don't then hide the search box.

                    if (searchContainer.getVisibility() == View.VISIBLE) {
                        displaySearchView(false);
                        return;
                    }
                }

                //TODO fix this
                if (drawer.isDrawerOpen(findViewById(R.id.nav_view)))
                    drawer.closeDrawer(findViewById(R.id.nav_view));
                else
                    drawer.openDrawer(findViewById(R.id.nav_view));
            }
        });

        // Hide the search view
        searchContainer.setVisibility(View.GONE);
        searchClearButton.setVisibility(View.GONE);
    }

    private void showToolbar() {
        toolbarContainer
                .animate()
                .translationY(0).setDuration(100)
                .start();
    }

    private void showToolbar(long duration) {
        toolbarContainer
                .animate()
                .translationY(0).setDuration(duration)
                .start();
    }

    private void hideToolbar() {
        toolbarContainer
                .animate()
                .translationY(-tabBar.getBottom())
                .start();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);

        this.menu = menu;

        menu.findItem(R.id.action_search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                displaySearchView(true);
                return false;
            }
        });

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void displaySearchView(boolean visible) {

        if (visible) {
            // Stops user from being able to open drawer while searching
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            // Hide search button, display EditText
            menu.findItem(R.id.action_search).setVisible(false);
            searchContainer.setVisibility(View.VISIBLE);

            // Animate the home icon to the back arrow
            toggleActionBarIcon(ActionDrawableState.ARROW, toggle, true);

            // Shift focus to the search EditText
            toolbarSearchView.requestFocus();

            // Pop up the soft keyboard
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    toolbarSearchView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                    toolbarSearchView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
                }
            }, 200);
        } else {
            // Allows user to open drawer again
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Hide the EditText and put the search button back on the Toolbar.
            // This sometimes fails when it isn't postDelayed(), don't know why.
            toolbarSearchView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toolbarSearchView.setText("");
                    searchContainer.setVisibility(View.GONE);
                    menu.findItem(R.id.action_search).setVisible(true);
                }
            }, 200);

            // Turn the home button back into a drawer icon
            toggleActionBarIcon(ActionDrawableState.BURGER, toggle, true);

            // Hide the keyboard because the search box has been hidden
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(toolbarSearchView.getWindowToken(), 0);
        }
    }

    private void toggleActionBarIcon(final ActionDrawableState state, final ActionBarDrawerToggle toggle, boolean animate) {
        if (animate) {
            float start = state == ActionDrawableState.BURGER ? 1.0f : 0f;
            float end = Math.abs(start - 1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ValueAnimator offsetAnimator = ValueAnimator.ofFloat(start, end);
                offsetAnimator.setDuration(300);
                offsetAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float offset = (Float) animation.getAnimatedValue();
                        toggle.onDrawerSlide(null, offset);
                    }
                });
                offsetAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        toolbarHomeButtonAnimating = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                toolbarHomeButtonAnimating = true;
                offsetAnimator.start();
            }
        } else {
            if (state == ActionDrawableState.BURGER) {
                toggle.onDrawerClosed(null);
            } else {
                toggle.onDrawerOpened(null);
            }
        }
    }

    /* OnFragmentInteractionListener */

    @Override
    public void onFragmentInteraction(String id) {

    }
}
