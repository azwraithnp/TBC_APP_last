package com.example.b50i7d.tbcapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Dell on 7/15/2016.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    private List<ChatMessage> chatMessages;
    private Context mContext;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String user_name = "user_name";
    LinearLayout mLayout;
    Bitmap bitmap;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, message,date;
        public CardView cardView;
        public ImageView imgV;


        public MyViewHolder(View view)
        {
            super(view);
            imgV = (ImageView) view.findViewById(R.id.imageView);
            username = (TextView) view.findViewById(R.id.username);
            message = (TextView) view.findViewById(R.id.chatMessage);
            date = (TextView) view.findViewById(R.id.date);
            mLayout = (LinearLayout)view.findViewById(R.id.mainView);
            cardView = (CardView)view.findViewById(R.id.my_message);
        }
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Context context)
    {
        this.chatMessages = chatMessages;
        this.mContext  =  context;
        //notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        sp= PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sp.edit();
        String imgSett = sp.getString(user_name, "user_name");

        ChatMessage chat = chatMessages.get(position);
        if(imgSett.equals(chat.getUsername())){
            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.light_blue));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mLayout.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mLayout.setLayoutParams(params);

        }
        else if(!imgSett.equals(chat.getUsername())){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mLayout.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mLayout.setLayoutParams(params);
            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.light_grey));
        }
        holder.username.setText(chat.getUsername());
        holder.message.setText(chat.getMessage());
        holder.date.setText(chat.getDate());
        try{
            InputStream stream = new ByteArrayInputStream(Base64.decode(chat.getImg().getBytes(), Base64.DEFAULT));
            bitmap = BitmapFactory.decodeStream(stream);
            holder.imgV.setImageBitmap(bitmap);
        }catch(Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

}
