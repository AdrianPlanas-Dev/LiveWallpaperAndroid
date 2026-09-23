package com.dynamicsky.livewallpaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LandscapeAdapter extends RecyclerView.Adapter<LandscapeAdapter.LandscapeViewHolder> {

    private final String[] landscapeNames = {
            "Montaña",
            "Bosque",
            "Costa",
            "Desierto"
    };

    private final String[] landscapeInfo = {
            "Atardecer · 22°",
            "Noche · 18°",
            "Día · 24°",
            "Atardecer · 27°"
    };

    private final int[] landscapeImages = {
            R.drawable.mountain,
            R.drawable.forest,
            R.drawable.coast,
            R.drawable.desert
    };

    @NonNull
    @Override
    public LandscapeViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_landscape, parent, false);

        return new LandscapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull LandscapeViewHolder holder,
            int position) {

        holder.name.setText(landscapeNames[position]);
        holder.info.setText(landscapeInfo[position]);
        holder.image.setImageResource(landscapeImages[position]);

        holder.itemView.setOnClickListener(v -> {
            String selectedLandscape = landscapeNames[position];
            android.widget.Toast.makeText(
                    v.getContext(),
                    "Has seleccionado: " + selectedLandscape,
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public int getItemCount() {
        return landscapeNames.length;
    }

    static class LandscapeViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView info;

        public LandscapeViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.landscapeImage);
            name = itemView.findViewById(R.id.landscapeName);
            info = itemView.findViewById(R.id.landscapeInfo);
        }
    }
}