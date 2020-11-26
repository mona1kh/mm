package com.example.tawaqapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class userAccount extends AppCompatActivity {
    TextView userNationalIdTV,userEmailTV;
    EditText universityNameInpEdt, majorInpEdt, userNameTV , state;
    Button editAccountBtn;
    DocumentReference documentReference;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        userNameTV=findViewById(R.id.userNameTV);
        state=findViewById(R.id.state);
        userNationalIdTV=findViewById(R.id.userNationalIdTV);
        userEmailTV=findViewById(R.id.userEmailTV);
        universityNameInpEdt =findViewById(R.id.universityNameInpEdt);
        editAccountBtn =findViewById(R.id.editAccountBtn);
        majorInpEdt =findViewById(R.id.majorInpEdt);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID =fAuth.getCurrentUser().getUid();
        user =fAuth.getCurrentUser();

        documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                userNameTV.setText(documentSnapshot.getString("UserName"));
                userNationalIdTV.setText(documentSnapshot.getString("National ID"));
                userEmailTV.setText(documentSnapshot.getString("Email"));
                universityNameInpEdt.setText(documentSnapshot.getString("university Name"));
                majorInpEdt.setText(documentSnapshot.getString("major"));
                state.setText(documentSnapshot.getString("State"));

            }
        });

        //update account
        editAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String email =userEmailTV.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("Email",email);
                        edited.put("UserName",userNameTV.getText().toString());
                        edited.put("university Name",universityNameInpEdt.getText().toString());
                        edited.put("major",majorInpEdt.getText().toString());
                        edited.put("State",state.getText().toString());
                        docRef.update(edited);
                        Toast.makeText(userAccount.this, "Email changed.. ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(userAccount.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    public void backToMenu(View V){
        Intent i = new Intent(this,theMenu.class);
        startActivity(i);

    }

    public void delProfil(View V){
        showDialog();

    }
    private void showDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(userAccount.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete profile");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(userAccount.this, "Profile deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });    startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();

    }

}