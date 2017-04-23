package com.rgapps.dailynotes.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rgapps.dailynotes.R;


/**
 * Created by rajat on 9/2/17.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    /**
     * The generic Abstract Implementation for any activity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Write neat and minimal code. Good Luck !
        setContentView(R.layout.activity_fragment);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        initCollapsingToolbar();


        try{
            Glide.with(this).load(R.drawable.notebook_cover).into((ImageView) findViewById(R.id.backdrop));

        }catch (Exception e){
            e.printStackTrace();
        }

        //FragmentManager and its Transactions.
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.container);
        if (fragment == null){
            fragment = createFragment();
            manager.beginTransaction().add(R.id.container, fragment).commit(); //Hook this guy up

        }

    }
    protected  abstract Fragment createFragment();


    public void initCollapsingToolbar(){
        final CollapsingToolbarLayout mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbar.setTitle("");
        final AppBarLayout mAppBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        mAppBarLayout.setExpanded(true);

        //Hiding or showing the title when the toolbar is expanded or collapsed:
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false; int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    mAppBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    mCollapsingToolbar.setTitle("yehivha");

                    isShow = true; //The title is visible

                }
                else if (isShow){
                    mCollapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });


    }
}
