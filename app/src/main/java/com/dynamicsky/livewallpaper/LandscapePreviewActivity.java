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


                        timeOverlay.setBackgroundColor(getOverlayColor(progress));
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
    private int getOverlayColor(int minutes) {

        if (minutes < 360) {
            return 0x990B1645;
        }

        if (minutes < 480) {
            float progress = (minutes - 360) / 120f;
            int alpha = (int) (153 * (1f - progress));

            return (alpha << 24) | 0x0B1645;
        }

        if (minutes < 1020) {
            return 0x00000000;
        }

        if (minutes < 1200) {
            float progress = (minutes - 1020) / 180f;
            int alpha = (int) (102 * progress);

            return (alpha << 24) | 0xFF8A3D;
        }

        if (minutes < 1320) {
            float progress = (minutes - 1200) / 120f;

            int orangeAlpha = (int) (102 * (1f - progress));
            int blueAlpha = (int) (153 * progress);

            int red = (int) (255 * (1f - progress) + 11 * progress);
            int green = (int) (138 * (1f - progress) + 22 * progress);
            int blue = (int) (61 * (1f - progress) + 69 * progress);

            int alpha = Math.max(orangeAlpha, blueAlpha);

            return (alpha << 24)
                    | (red << 16)
                    | (green << 8)
                    | blue;
        }

        return 0x990B1645;
    }
}