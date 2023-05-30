package com.example.projector;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void swch(View view){
        Intent swch = new Intent(MainActivity.this, RegWind.class);
        startActivity(swch);
    }
    public void Enter(View view){
        EditText email = findViewById(R.id.LogCheck);
        EditText pass = findViewById(R.id.PassCheck);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "singInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent gotomain = new Intent(MainActivity.this, MainPage.class);
                            startActivity(gotomain);
                        }else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}