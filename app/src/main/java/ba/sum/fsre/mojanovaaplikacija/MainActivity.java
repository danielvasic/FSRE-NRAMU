package ba.sum.fsre.mojanovaaplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        EditText emailTxt = findViewById(R.id.emailTxt);
        EditText passwordTxt = findViewById(R.id.passwordTxt);

        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(getApplicationContext(), "Molimo unesite oba polja", Toast.LENGTH_LONG).show();
                else
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Uspješno ste se prijavili.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Pogrešni korisnički podaci.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }
        });

    }
}