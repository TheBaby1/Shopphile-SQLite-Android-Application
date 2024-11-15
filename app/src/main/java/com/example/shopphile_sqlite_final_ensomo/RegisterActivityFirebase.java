package com.example.shopphile_sqlite_final_ensomo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivityFirebase extends AppCompatActivity {

    private EditText username, password, retypePass;
    private Button loginButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_firebase);

        firebaseAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        retypePass = findViewById(R.id.retypePasswordText);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String pass = password.getText().toString().trim();
                String rePass = retypePass.getText().toString().trim();

                if (!pass.equals(rePass)) {
                    Toast.makeText(RegisterActivityFirebase.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isValidPassword(password.getText().toString().trim())) {
                    Toast.makeText(RegisterActivityFirebase.this, "Password is Valid", Toast.LENGTH_SHORT).show();
                    registerNewUser();
                } else {
                    Toast.makeText(RegisterActivityFirebase.this, "Password is InValid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void registerNewUser(){
        String user, pass, rePass;

        user = username.getText().toString();
        pass = password.getText().toString();
        rePass = retypePass.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(RegisterActivityFirebase.this, "Please Enter your Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(RegisterActivityFirebase.this, "Please Enter your Password.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (task.isSuccessful()){
                    Intent Login = new Intent(RegisterActivityFirebase.this, LoginActivityFirebase.class);
                    startActivity(Login);
                    Toast.makeText(RegisterActivityFirebase.this, "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivityFirebase.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

}