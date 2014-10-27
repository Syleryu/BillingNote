package com.axybn.billingnote;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
    private final static String TAG = "BillingNote-MainActivity";

    private ViewPager mViewPager;
    private Button mTodayButton;
    private Button mAddButton;
    private Button mHistoryButton;
    private LinearLayout mTodayLinearLayout;
    private LinearLayout mAddLinearLayout;
    private LinearLayout mHistoryLinearLayout;
    private List<Fragment> mDatas;
    private FragmentPagerAdapter mAdapter;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);

        initView();

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            Resources resources = getResources();
            Drawable drawable;
            int upid = Resources.getSystem().getIdentifier("up", "id",
                    "android");
            ImageView img = (ImageView) findViewById(upid);
            // drawable = resources yuxy1 test
            // .getDrawable(R.drawable.actionbar_home_as_up_indicator_background);
            actionBar.setDisplayHomeAsUpEnabled(true);
            // actionBar.setHomeAsUpIndicator(img);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setTitle("返回");
            drawable = resources.getDrawable(R.drawable.actionbar_background);
            actionBar.setBackgroundDrawable(drawable);
        }

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTodayButton = (Button) findViewById(R.id.button_today);
        mAddButton = (Button) findViewById(R.id.button_add);
        mHistoryButton = (Button) findViewById(R.id.button_history);
        mTodayButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
        mHistoryButton.setOnClickListener(this);
        mTodayLinearLayout = (LinearLayout) findViewById(R.id.tab_today);
        mAddLinearLayout = (LinearLayout) findViewById(R.id.tab_add);
        mHistoryLinearLayout = (LinearLayout) findViewById(R.id.tab_history);

        mDatas = new ArrayList<Fragment>();

        TodayTabFragment tabToday = new TodayTabFragment();
        AddTabFragment tabAdd = new AddTabFragment();
        HistoryTabFragment tabHistory = new HistoryTabFragment();

        mDatas.add(tabToday);
        mDatas.add(tabAdd);
        mDatas.add(tabHistory);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                case 0:
                    Log.d(TAG, "onPageSelected" + position);
                    break;
                case 1:
                    Log.d(TAG, "onPageSelected" + position);
                    break;
                case 2:
                    Log.d(TAG, "onPageSelected" + position);
                    break;

                }

                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "onPageScrollStateChanged" + arg0);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPx) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        case 0:
            Toast.makeText(this, "action menu", Toast.LENGTH_SHORT).show();
            return true;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        createMenu(menu);
        return true;
    }

    public void createMenu(Menu menu) {
        MenuItem menuItem = menu.add(0, 0, 0, "item1");
        menuItem.setIcon(R.drawable.ic_settings);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "view:" + v.getId());
        switch (v.getId()) {
        case R.id.button_today:
            mViewPager.setCurrentItem(0);
            break;
        case R.id.button_add:
            mViewPager.setCurrentItem(1);
            break;
        case R.id.button_history:
            mViewPager.setCurrentItem(2);
            break;

        default:
            break;
        }
    }

}
