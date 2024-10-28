package com.example.lab2_views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText usernameEditText = findViewById(R.id.et_username_reg);
        EditText emailEditText = findViewById(R.id.et_email);
        EditText passwordEditText = findViewById(R.id.et_password_reg);
        EditText confirmEditText = findViewById(R.id.et_password_confirm);
        Button loginButton = findViewById(R.id.btn_back_to_login);
        Button registerButton = findViewById(R.id.btn_register);

        Map<String, String> usernameAndPassword = (Map<String, String>) getIntent().getSerializableExtra("usernameAndPassword");

        loginButton.setOnClickListener(e -> {
            finish();
        });

        registerButton.setOnClickListener(e -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirm = confirmEditText.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (usernameAndPassword.containsKey(username)) {
                Toast.makeText(this, "Username already exists.", Toast.LENGTH_SHORT).show();
                return;
            }

            usernameAndPassword.put(username, password);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedUsernameAndPassword", (Serializable) usernameAndPassword);
            setResult(RESULT_OK, resultIntent);

            finish();
        });
    }
}