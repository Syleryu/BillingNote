package com.axybn.billingnote;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
    private final static String TAG = "BillingNote-MainActivity";

    private ImageView mTabline;
    private int mScreen1_3;
    private ViewPager mViewPager;
    private TextView mTodayTextView;
    private ImageButton mAddButton;
    private TextView mHistoryTextView;
    private LinearLayout mTodayLinearLayout;
    private LinearLayout mAddLinearLayout;
    private LinearLayout mHistoryLinearLayout;
    private List<Fragment> mDatas;
    private FragmentPagerAdapter mAdapter;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabLine();
        initView();

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            Resources resources = getResources();
            Drawable drawable = resources
                    .getDrawable(R.drawable.actionbar_background);
            actionBar.setBackgroundDrawable(drawable);
        }

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    private void initTabLine() {
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTodayTextView = (TextView) findViewById(R.id.tv_today);
        mAddButton = (ImageButton) findViewById(R.id.button_add);
        mAddButton.setOnClickListener(this);
        mHistoryTextView = (TextView) findViewById(R.id.tv_history);
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
                resetTextView();
                switch (position) {
                case 0:
                    mTodayTextView.setTextColor(Color.parseColor("#008000"));
                    break;
                case 1:
                    break;
                case 2:
                    mHistoryTextView.setTextColor(Color.parseColor("#008000"));
                    break;

                }

                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPx) {

                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0) {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 2 && position == 1) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                }
                mTabline.setLayoutParams(lp);

            }
        });
    }

    protected void resetTextView() {
        mTodayTextView.setTextColor(Color.BLACK);
        mHistoryTextView.setTextColor(Color.BLACK);
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
        menuItem.setIcon(R.drawable.ic_launcher);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "view:" + v.getId());
        switch (v.getId()) {
        case R.id.button_add:
            Toast.makeText(this, "Add menu clicked", Toast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
    }

}
