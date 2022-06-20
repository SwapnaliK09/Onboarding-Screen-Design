package com.example.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int images[]={
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
    };

    int heading [] ={
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth,

    };

    int description [] = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth,
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView slide_Image = (ImageView) view.findViewById(R.id.titleImage);
        TextView text = (TextView) view.findViewById(R.id.text_title);
        TextView text1 = (TextView) view.findViewById(R.id.text_description);

        slide_Image.setImageResource(images[position]);
        text.setText(heading[position]);
        text1.setText(description[position]);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
