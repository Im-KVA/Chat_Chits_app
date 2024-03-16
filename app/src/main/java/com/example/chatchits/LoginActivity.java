package com.example.chatchits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtpBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        countryCodePicker = findViewById(R.id.login_cc);
        phoneInput = findViewById(R.id.login_phone_number);
        sendOtpBtn = findViewById(R.id.login_send_otp_btn);
        progressBar = findViewById(R.id.login_progress_bar);

        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        setInProgress(true);
        sendOtpBtn.setOnClickListener((v)->{
            setInProgress(false);
            if(!countryCodePicker.isValidFullNumber()){
                phoneInput.setError("Phone number is not valid");
                return;
            }
            Intent intent = new Intent(LoginActivity.this,LoginOTPActivity.class);
            intent.putExtra("phone",countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });
    }
    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.GONE);
            sendOtpBtn.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.VISIBLE);
            sendOtpBtn.setVisibility(View.GONE);
        }
    }
}