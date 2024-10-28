package com.example.lab2_views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final Map<String, String> usernameAndPassword = Map.of(
            "admin", "admin",
            "user", "user"
    );
    private ActivityResultLauncher<Intent> registerActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        HashMap<String, String> updatedusernameAndPassword =
                                (HashMap<String, String>) data.getSerializableExtra("updatedUsernameAndPassword");
                        usernameAndPassword.putAll(updatedusernameAndPassword);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText usernameEditText = findViewById(R.id.et_username);
        EditText passwordEditText = findViewById(R.id.et_password);
        CheckBox rememberCheckBox = findViewById(R.id.chk_remember);
        Button loginButton = findViewById(R.id.btn_login);
        Button registerButton = findViewById(R.id.btn_go_to_register);

        loginButton.setOnClickListener(e -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (usernameAndPassword.containsKey(username) && Objects.equals(usernameAndPassword.get(username), password)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No such user.", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            registerActivityLauncher.launch(intent);
        });
    }
}