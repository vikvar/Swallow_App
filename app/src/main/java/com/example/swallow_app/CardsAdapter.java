package com.example.swallow_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {

    List<Card> cards = new ArrayList<>();
    Context context;
    Intent intent;


    public CardsAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cardName.setText(cards.get(position).getCardName());
        holder.cardNumber.setText(cards.get(position).getCardNumber());
        new DownloadImageTask(holder.cardImage)
                .execute(cards.get(position).getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent= new Intent(context, CardInformationActivity.class);
                intent.putExtra("Name", cards.get(position).getCardName());
                intent.putExtra("Number", cards.get(position).getCardNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cardName;
        TextView cardNumber;
        ImageView cardImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            cardName = itemView.findViewById(R.id.userCardName);
            cardNumber = itemView.findViewById(R.id.userCardNumber);
            cardImage = itemView.findViewById(R.id.userCardImage);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap image = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return image;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
