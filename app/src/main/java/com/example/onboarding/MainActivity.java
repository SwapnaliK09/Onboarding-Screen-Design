package com.example.onboarding;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout layout;
    Button back_btn,next_btn,skip_btn;

    TextView [] dots;
    ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        back_btn = findViewById(R.id.backbtn);
        next_btn = findViewById(R.id.nextbtn);
        skip_btn = findViewById(R.id.button);



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0)>0){
                    viewPager.setCurrentItem(getitem(-1),true);
                }

            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3){
                    viewPager.setCurrentItem(getitem(1),true);
                }else {
                    Intent intent = new Intent(MainActivity.this,MainScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MainScreen.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager =(ViewPager)findViewById(R.id.slide_view_page);
        layout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position){
        dots = new TextView[4];
        layout.removeAllViews();

        for (int i =0;i< dots.length;i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            layout.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position > 0 ){
                back_btn.setVisibility(View.VISIBLE);
            }else {
                back_btn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){
        return  viewPager.getCurrentItem()+i;
    }
}