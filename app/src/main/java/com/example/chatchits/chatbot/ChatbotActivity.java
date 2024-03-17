package com.example.chatchits.chatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chatchits.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChatbotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatbot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText queryEditText = findViewById(R.id.queryEditText);
        Button sendPromt_btn = findViewById(R.id.sendPromt_btn);
        TextView responsiveTextView = findViewById(R.id.modelResponsive_textView);
        ProgressBar progressBar = findViewById(R.id.sendPromt_progressBar);

        sendPromt_btn.setOnClickListener(v -> {
            GeminiProAPI model = new GeminiProAPI();

            String query = queryEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            responsiveTextView.setText("");
            queryEditText.setText("");

            model.getResponsive(query, new ResponseCallback() {
                @Override
                public void onResponse(String response) {
                    responsiveTextView.setText(response);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable throwable) {
                    Toast.makeText(ChatbotActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

    }
}