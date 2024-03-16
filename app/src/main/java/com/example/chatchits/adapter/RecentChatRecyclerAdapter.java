package com.example.chatchits.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatchits.ChatActivity;
import com.example.chatchits.R;
import com.example.chatchits.models.ChatroomModel;
import com.example.chatchits.models.UserModel;
import com.example.chatchits.utils.AndroidUtil;
import com.example.chatchits.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class RecentChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatroomModel, RecentChatRecyclerAdapter.ChatroomModelViewHolder> {
    Context context;
    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatroomModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder chatroomModelViewHolder, int i, @NonNull ChatroomModel chatroomModel) {
        FirebaseUtil.getOtherUserFromChatroom(chatroomModel.getUserIds())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            boolean lastMessageSentByMe = chatroomModel.getLastMessageSenderId().equals(FirebaseUtil.currentUserId());


                            UserModel otherUserModel = task.getResult().toObject(UserModel.class);

                            FirebaseUtil.getOtherProfilePicStorageRef(otherUserModel.getUserId()).getDownloadUrl()
                                    .addOnCompleteListener(pic -> {
                                        if(pic.isSuccessful()){
                                            Uri uri = pic.getResult();
                                            AndroidUtil.setProfilePic(context,uri,chatroomModelViewHolder.profilePic);
                                        }
                                    });

                            chatroomModelViewHolder.usernameText.setText(otherUserModel.getUsername());
                            if (lastMessageSentByMe)
                                chatroomModelViewHolder.lastMessageText.setText("You : "+chatroomModel.getLastMessage());
                            else
                                chatroomModelViewHolder.lastMessageText.setText(chatroomModel.getLastMessage());
                            chatroomModelViewHolder.lastMessageTime.setText(FirebaseUtil.timestampToString(chatroomModel.getLastMessageTimestamp()).toString());

                            chatroomModelViewHolder.itemView.setOnClickListener(v -> {
                                //Chuyen sang chat activity
                                Intent intent = new Intent(context, ChatActivity.class);
                                AndroidUtil.passUnderModelAsIntent(intent,otherUserModel);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            });

                        }
                    }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView lastMessageText;
        TextView lastMessageTime;
        ImageView profilePic;
        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.username_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
