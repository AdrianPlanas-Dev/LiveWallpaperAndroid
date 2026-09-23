package com.dynamicsky.livewallpaper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.SeekBar;

public class LandscapePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_preview);

        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content),
                (v, insets) -> {

                    androidx.core.graphics.Insets systemBars =
                            insets.getInsets(
                                    androidx.core.view.WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        ImageView previewImage = findViewById(R.id.previewImage);
        ImageView backButton = findViewById(R.id.backButton);

        TextView previewTitle = findViewById(R.id.previewTitle);
        TextView previewInfo = findViewById(R.id.previewInfo);

        TextView timeLabel = findViewById(R.id.timeLabel);
        SeekBar timeSeekBar = findViewById(R.id.timeSeekBar);

        View timeOverlay = findViewById(R.id.timeOverlay);

        String landscapeName =
                getIntent().getStringExtra("landscape_name");

        String landscapeInfo =
                getIntent().getStringExtra("landscape_info");

        int landscapeImage =
                getIntent().getIntExtra(
                        "landscape_image",
                        R.drawable.landscape_mountain_placeholder
                );


        previewTitle.setText(landscapeName);
        previewInfo.setText(landscapeInfo);
        previewImage.setImageResource(landscapeImage);


        timeSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        int hours = progress / 60;
                        int minutes = progress % 60;

                        String time = String.format(
                                java.util.Locale.getDefault(),
                                "%02d:%02d",
                                hours,
                                minutes
                        );

                        timeLabel.setText(time);

                        if (hours >= 6 && hours < 17) {
                            timeOverlay.setBackgroundColor(0x00000000);
                        } else if (hours >= 17 && hours < 20) {
                            timeOverlay.setBackgroundColor(0x66FF8A3D);
                        } else {
                            timeOverlay.setBackgroundColor(0x990B1645);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        backButton.setOnClickListener(v -> finish());
    }
}