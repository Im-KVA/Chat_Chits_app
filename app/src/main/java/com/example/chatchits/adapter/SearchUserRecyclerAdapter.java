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
import com.example.chatchits.models.UserModel;
import com.example.chatchits.utils.AndroidUtil;
import com.example.chatchits.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel, SearchUserRecyclerAdapter.UserModelViewHolder> {
    Context context;
    public SearchUserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder userModelViewHolder, int i, @NonNull UserModel userModel) {
        userModelViewHolder.usernameText.setText(userModel.getUsername());
        userModelViewHolder.phoneText.setText(userModel.getPhone());
        if(userModel.getUserId().equals(FirebaseUtil.currentUserId())){
            userModelViewHolder.usernameText.setText(userModel.getUsername()+"(Me)");
        }

        FirebaseUtil.getOtherProfilePicStorageRef(userModel.getUserId()).getDownloadUrl()
                .addOnCompleteListener(pic -> {
                    if(pic.isSuccessful()){
                        Uri uri = pic.getResult();
                        AndroidUtil.setProfilePic(context,uri,userModelViewHolder.profilePic);
                    }
                });

        userModelViewHolder.itemView.setOnClickListener(v -> {
            //Chuyen sang chat activity
            Intent intent = new Intent(context, ChatActivity.class);
            AndroidUtil.passUnderModelAsIntent(intent,userModel);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);
        return new UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView phoneText;
        ImageView profilePic;
        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.username_text);
            phoneText = itemView.findViewById(R.id.phone_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
