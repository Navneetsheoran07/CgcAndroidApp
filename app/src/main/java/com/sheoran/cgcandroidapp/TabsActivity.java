package com.sheoran.cgcandroidapp;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabsActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabs);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("Home");
        arrayList.add("Status");
        arrayList.add("Calles");
        tabLayout.setupWithViewPager(viewPager);
        prepareViewPager(viewPager, arrayList);
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        MainFragment mainFragment = new MainFragment();
        for (int i=0;i<arrayList.size();i++){
            Bundle bundle= new Bundle();
            bundle.putString("tittle",arrayList.get(i));
            mainFragment.setArguments(bundle);
            adapter.addFragment(mainFragment,arrayList.get(i));
            mainFragment= new MainFragment();
        }
        viewPager.setAdapter(adapter);

    }
    public class MainAdapter  extends FragmentPagerAdapter {


        ArrayList<Fragment>fragmentArrayList= new ArrayList<>();
        ArrayList<String>stringArrayList= new ArrayList<>();

        int [] imageList={
                R.drawable.home,R.drawable.satus,R.drawable.call
        };


        public void addFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }
        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public MainAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),imageList[position]);
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());

            SpannableString spannableString = new SpannableString(""+stringArrayList.get(position));

            ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return  spannableString;
        }
    }



}