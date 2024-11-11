package com.example.iqra;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JilidSatu extends AppCompatActivity {

    private ImageView pageImageView;
    private int pageIndex = 1;
    private RelativeLayout layout;
    private Button btn1,btn2,previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jilid_satu);

        pageImageView = findViewById(R.id.iVJilid1); // ImageView for the page
        pageImageView.setImageResource(R.drawable.j1p1); // Set initial page image

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.mTjilid1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (pageIndex == 1){
            btn1= findViewById(R.id.button1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPronunciation("ba");
                }
            });
            btn2= findViewById(R.id.button2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPronunciation("a");
                }
            });
        }

        if(pageIndex == 1){
            previousButton = findViewById(R.id.previousButton);
            previousButton.setVisibility(View.INVISIBLE);
        }


        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNextPage();
            }
        });
        previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousPage();
            }
        });
    }

    // Method to dynamically create a clickable button for a character


    // Method to play pronunciation audio
    private void playPronunciation(String audioFileName) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("android.resource://" + getPackageName() + "/raw/" + audioFileName));
        mediaPlayer.start();
    }

    // Method to load the next page and update content
    private void loadNextPage() {
        if (pageIndex < 12) { // Limit the page index to 12
            pageIndex++; // Increment page index
            int pageResource = getResources().getIdentifier("j1p" + pageIndex, "drawable", getPackageName());
            pageImageView.setImageResource(pageResource); // Update the page image

            // Update button visibility
            if (pageIndex > 1) {
                previousButton.setVisibility(View.VISIBLE); // Show the previous button
            }

            // Hide btn1 and btn2 for all pages except the first one
            if (pageIndex > 1) {
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
            }
        } else {
            // If pageIndex is 12, you can add logic here to notify the user or disable the Next button.
            // For example, you can disable the Next button like this:
            Button nextButton = findViewById(R.id.nextButton);
            nextButton.setEnabled(false); // Disable the next button when at the last page
        }

    }

    private void previousPage() {
        pageIndex--;
        int pageResource = getResources().getIdentifier("j1p" + pageIndex, "drawable", getPackageName());
        pageImageView.setImageResource(pageResource);
        if(pageIndex == 1){
            previousButton.setVisibility(View.INVISIBLE);
        }
        if (pageIndex == 1) {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }

    }

    // Method to update clickable areas for each page
//    private void updateClickableAreasForPage(int pageIndex) {
//        // Example: update the clickable areas based on the current page index
//        if (pageIndex == 2) {
//            // For example, add a clickable character to page 2
//            createClickableCharacterButton(200, 150, "character3_audio.mp3");
//        }
//        // Add more conditions for other pages if necessary
//    }
}
