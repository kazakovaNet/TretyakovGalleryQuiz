package com.example.android.tretyakovgalleryquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PictureAnswerFragment pictureAnswerFragment = (PictureAnswerFragment) getFragmentManager().findFragmentById(R.id.picture_frag);
        pictureAnswerFragment.setPicture(0);
    }
}
