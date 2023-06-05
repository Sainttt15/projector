package com.example.projector;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegWind extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_wind);
        mAuth = FirebaseAuth.getInstance();
    }

    public void RegApply(View view) {
        EditText email = findViewById(R.id.LogEditText);
        EditText password = findViewById(R.id.PassEditText);
        EditText FIO = findViewById(R.id.FIOEditText);
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:successs");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest prof = new UserProfileChangeRequest.Builder().setDisplayName(FIO.getText().toString()).build();
                            user.updateProfile(prof);
                            Toast.makeText(RegWind.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                            Intent back = new Intent(RegWind.this, MainActivity.class);
                            startActivity(back);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegWind.this, "Не удалось зарегестрироваться.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Button bt = findViewById(R.id.Back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RegWind.this, MainActivity.class);
            }
        });

    }

}
