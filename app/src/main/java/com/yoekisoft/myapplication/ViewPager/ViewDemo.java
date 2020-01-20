package com.yoekisoft.myapplication.ViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.yoekisoft.myapplication.R;
import com.yoekisoft.myapplication.ViewPager.AppleFragment;
import com.yoekisoft.myapplication.ViewPager.OrangeFragment;
import com.yoekisoft.myapplication.ViewPager.ViewPagerAdapter;

public class ViewDemo extends AppCompatActivity{
    ViewPager viewPager;
    TabLayout tabLayout;
    private int[] tabIcons = {R.drawable.ic_chocolate,
            R.drawable.ic_donut};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
        viewPager = findViewById(R.id.viewPager);
        addTabs(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AppleFragment(), "APPLE");
        adapter.addFrag(new OrangeFragment(), "ORANGE");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }


}