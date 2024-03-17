package com.example.chatchits;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.*;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    Button langBtn;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        langBtn = view.findViewById(R.id.lang_btn);

        langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });
        return view;
    }
    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.choose_lang));

        String[] languages = {getResources().getString(R.string.en), getResources().getString(R.string.vi), getResources().getString(R.string.de)};
        final int[] selectedLanguage = {0}; // Lựa chọn mặc định là Tiếng Anh

        builder.setSingleChoiceItems(languages, selectedLanguage[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedLanguage[0] = which;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String languageCode = getLanguageCode(selectedLanguage[0]);
                updateLanguage(languageCode);
                restartApp();
            }
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private String getLanguageCode(int selectedLanguage) {
        switch (selectedLanguage) {
            case 0:
                return "en";
            case 1:
                return "vi";
            case 2:
                return "de";
            default:
                return "en";
        }
    }

    private void updateLanguage(String languageCode) {
        // Lưu trữ mã ngôn ngữ vào SharedPreferences
        SharedPreferences preferences = getActivity().getSharedPreferences("language_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language_code", languageCode);
        editor.apply();
    }

    private void restartApp() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}


