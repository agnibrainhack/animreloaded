package com.darkdragon.recycyclertoviewpagersmooth;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.darkdragon.recycyclertoviewpagersmooth.RecyclerToActivity.AnimalDetailActivity;

public class MainActivity extends AppCompatActivity implements AnimalItemClickListener {
    //RecyclerViewFragment  recyclerViewFragment;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public static final String EXTRA_ANIMAL_ITEM = "animal_image_url";
    public static final String EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "animal_image_transition_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("DarkDragon");
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//
//        recyclerViewFragment = new RecyclerViewFragment();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.content, recyclerViewFragment, "tagg");
//        fragmentTransaction.commit();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Animals Rock");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.holo_blue_bright));
        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
        collapsingToolbarLayout.setStatusBarScrimColor(Color.MAGENTA);


        AnimalGalleryAdapter animalGalleryAdapter = new AnimalGalleryAdapter(Utils.generateAnimalItems(this), this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(animalGalleryAdapter);


    }
    @Override
    public void onAnimalItemClick(int pos, AnimalItem animalItem, ImageView sharedImageView) {
        Intent intent = new Intent(this, AnimalDetailActivity.class);
        intent.putExtra(EXTRA_ANIMAL_ITEM, animalItem);
        intent.putExtra(EXTRA_ANIMAL_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }
}
