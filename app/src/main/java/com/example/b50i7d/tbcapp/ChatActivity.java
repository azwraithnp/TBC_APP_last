package com.example.b50i7d.tbcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by B50i7D on 11/28/2016.
 */
public class ChatActivity extends Fragment {
    private static final int RESULT_OK = 0;
    private List<ChatMessage> list = new ArrayList<>();
    private RecyclerView recycleView;
    private ChatAdapter cAdapter;
    private EditText messageBox;
    Bitmap bitmap;
    private Button sendButton,imgB;
    private int count;
    TextView txt;
    CardView cv;    public static String name = "fname";


    public  String user,course;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    //Context applicationContext = MainActivity.getContextOfApplication();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chats_activity,container,false);
        sp= getActivity().getApplicationContext().getSharedPreferences("name", MODE_PRIVATE);
        editor = sp.edit();
        txt = (TextView)v.findViewById(R.id.chatname);
        user = sp.getString(name, "default123");
        course =  sp.getString("courses","courses");
        Toast.makeText(getContext(), user, Toast.LENGTH_SHORT).show();
        if(user.equals("default"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Enter your username");
            final EditText edit = new EditText(getActivity());
            builder.setView(edit);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editor.putString("user_name", edit.getText().toString());
                    editor.commit();

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    Intent intent = NavUtils.getParentActivityIntent(getActivity());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    NavUtils.navigateUpTo(getActivity(), intent);
                }
            });
            builder.show();
        }
        final String spp = sp.getString(user,"user_name");
        if(course.equals("courses"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Enter your course");
            final EditText edit = new EditText(getActivity());
            builder.setView(edit);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editor.putString("courses", edit.getText().toString());
                    editor.apply();
                }
            });
            String a =sp.getString(course,"");
            txt.setText(a);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    Intent intent = NavUtils.getParentActivityIntent(getActivity());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    NavUtils.navigateUpTo(getActivity(), intent);
                }
            });
            builder.show();
        }

        Firebase.setAndroidContext(getActivity());

        Firebase ref = null;

        switch (course){
            case "bba":
                ref = new Firebase(Config.FIREBASE_URL_bba);
                break;
            case "a level":
                ref = new Firebase(Config.FIREBASE_URL_alevel);
                break;
            case "bsc":
                ref = new Firebase(Config.FIREBASE_URL_bsc);
                break;
            default:
                ref = new Firebase(Config.FIREBASE_URL_others);
                break;
        }
        messageBox = (EditText)v.findViewById(R.id.chatMessageBox);
        sendButton = (Button)v.findViewById(R.id.sendButton);
        imgB = (Button) v.findViewById(R.id.imageButton);

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        recycleView = (RecyclerView)v.findViewById(R.id.recycler_view);
        cAdapter = new ChatAdapter(list, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(cAdapter);



        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                list.add(chatMessage);
                count = list.size();
                cAdapter.notifyDataSetChanged();
                if(!(list.size() == 0))
                {
                    recycleView.scrollToPosition(list.size() - 1);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        final Firebase finalRef = ref;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageFile = null;
                String message = messageBox.getText().toString();
                try{
                    ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bYtE);
                    bitmap.recycle();
                    byte[] byteArray = bYtE.toByteArray();
                    imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }catch(Exception e) {

                }
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setImg(imageFile);
                chatMessage.setUsername(user);
                chatMessage.setMessage(message);
                chatMessage.setCourses(course);
                SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
                String currentDateandTime = sdf.format(new Date());
                chatMessage.setDate(currentDateandTime);

                finalRef.push().setValue(chatMessage);
                messageBox.setText("");
                cAdapter.notifyDataSetChanged();
            }
        });


        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this.getActivity(),"asdfasdfasd",Toast.LENGTH_SHORT).show();

        Uri targetUri = data.getData();
        //textTargetUri.setText(targetUri.toString());
        try {

            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
