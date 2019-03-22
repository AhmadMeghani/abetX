package com.example.abetx.Entry;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.abetx.R;
import com.example.abetx.Utilities.SectionStatePageAdapter;


public class Entry extends AppCompatActivity {
    public static SectionStatePageAdapter sectionStatePageAdapter;
    private static ViewPager mViewPager;

    public static void setmViewPager(int fragmentNumber) {
        mViewPager.setAdapter(sectionStatePageAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        mViewPager = findViewById(R.id.container);
        setupFragment();
        setmViewPager(0);
    }

    private void setupFragment() {
        sectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());
        sectionStatePageAdapter.addFragment(new AddEntry(), getString(R.string.add_entry));
        sectionStatePageAdapter.addFragment(new AddHead(), getString(R.string.add_entry));
    }
}
