package com.example.fot_news_hub;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    private ImageView backBtn;
    private TextView tvUserInfo;
    private Button btnEditInfo, btnSignOut;

    SharedPreferences prefs;
    String username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        backBtn = findViewById(R.id.backBtn);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        btnEditInfo = findViewById(R.id.btnEditInfo);
        btnSignOut = findViewById(R.id.btnSignOut);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        loadUserInfo();

        backBtn.setOnClickListener(v -> onBackPressed());

        btnEditInfo.setOnClickListener(v -> showEditDialog());

        btnSignOut.setOnClickListener(v -> showSignOutDialog());
    }

    private void loadUserInfo() {
        username = prefs.getString("username", "N/A");
        email = prefs.getString("email", "N/A");

        tvUserInfo.setText("Name: " + username + "\n\nEmail: " + email);
    }

    private void showEditDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_edit_info, null);

        EditText etEditUsername = view.findViewById(R.id.etEditUsername);
        EditText etEditEmail = view.findViewById(R.id.etEditEmail);

        etEditUsername.setText(username);
        etEditEmail.setText(email);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Info")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newUsername = etEditUsername.getText().toString().trim();
                    String newEmail = etEditEmail.getText().toString().trim();

                    if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", newUsername);
                        editor.putString("email", newEmail);
                        editor.apply();

                        Toast.makeText(this, "Info updated", Toast.LENGTH_SHORT).show();
                        loadUserInfo();
                    } else {
                        Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton("Dismiss", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
