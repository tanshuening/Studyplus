package com.example.studyplus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyplus.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<String> videoUrls;
    private Context context;

    public VideoAdapter(List<String> videoUrls, Context context) {
        this.videoUrls = videoUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoUrl = videoUrls.get(position);
        holder.videoButton.setText("View Video " + (position + 1));
        holder.videoButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button videoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoButton = itemView.findViewById(R.id.videoButton);
        }
    }
}
