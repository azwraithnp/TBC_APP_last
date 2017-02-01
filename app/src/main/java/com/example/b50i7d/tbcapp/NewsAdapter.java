package com.example.b50i7d.tbcapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dell on 7/15/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    private List<NewsMessage> newsMessages;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, message,date;
        ImageView img;
        String downloadUrl="http://www1.uwe.ac.uk/images/logo_the_british_college_cyan244.jpg";
        public MyViewHolder(View view)
        {
            super(view);
            username = (TextView) view.findViewById(R.id.userid);
            message = (TextView) view.findViewById(R.id.newsMessage);
            date = (TextView) view.findViewById(R.id.date);
            img = (ImageView) view.findViewById(R.id.mDownloadImageView);
            //Picasso.with(view.getContext()).load(downloadUrl.toString()).into(img);
        }
    }

    public NewsAdapter(List<NewsMessage> chatMessages)
    {
        this.newsMessages = chatMessages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsMessage chat = newsMessages.get(position);
        holder.username.setText(chat.getUsername());
        holder.message.setText(chat.getMessage());
        holder.date.setText(chat.getDate());
        Picasso.with().load(chat.getUrl().toString()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return newsMessages.size();
    }

}
