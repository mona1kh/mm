package com.example.tawaqapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity {
    public static final String TAG ="TAG";
    EditText userNameInp, userNationalIdInp, universityNameInp, majorInp, userEmailInp, passwordInp, rePasswordInp, state;
    Button registerBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //start register
        userNameInp=findViewById(R.id.userNameInp);
        userNationalIdInp=findViewById(R.id.userNationalIdInp);
        universityNameInp=findViewById(R.id.universityNameInp);
        majorInp=findViewById(R.id.majorInp);
        userEmailInp=findViewById(R.id.userEmailInp);
        passwordInp=findViewById(R.id.passwordInp);
        rePasswordInp=findViewById(R.id.rePasswordInp);
        registerBtn=findViewById(R.id.registerBtn);
        state=findViewById(R.id.state);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


       /* if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
        }*/

        registerBtn.setOnClickListener((v) ->  {

            final String user_Name = userNameInp.getText().toString();
            final String user_NationalIdInp = userNationalIdInp.getText().toString();
            final String university_NameInp = universityNameInp.getText().toString();
            final String major_Inp = majorInp.getText().toString();
            final String Sstate = state.getText().toString();
            final String user_EmailInp = userEmailInp.getText().toString().trim();
                String password_Inp = passwordInp.getText().toString().trim();
                String rePassword_Inp = rePasswordInp.getText().toString();

                if(TextUtils.isEmpty(user_Name)){
                    userNameInp.setError("User Name is Required. ");
                    return;
                }
                if(TextUtils.isEmpty(user_NationalIdInp)){
                    userNationalIdInp.setError("ID National Required. ");
                    return;
                }
                if(user_NationalIdInp.length() != 10 ){
                    userNationalIdInp.setError("ID National Must be 10 Number ");
                    return;
                }
                if(TextUtils.isEmpty(university_NameInp)){
                    universityNameInp.setError("University Name is Required. ");
                    return;
                }
                if(TextUtils.isEmpty(major_Inp)){
                    majorInp.setError(" Major is Required. ");
                    return;
                }
            if(TextUtils.isEmpty(Sstate)){
                state.setError("State is Required. ");
                return;
            }
                if(TextUtils.isEmpty(user_EmailInp)){
                    userEmailInp.setError("Email is Required. ");
                    return;
                }
                if(TextUtils.isEmpty(password_Inp)){
                    passwordInp.setError("Password is Required. ");
                    return;
                }
                if(password_Inp.length()< 6){
                    passwordInp.setError("Password Must be >= 6 Characters ");
                    return;
                }
                 if(!password_Inp.equals(rePassword_Inp)){
                    rePasswordInp.setError("Password does not match. ");
                    return;
                }
                 fAuth.createUserWithEmailAndPassword(user_EmailInp, password_Inp).addOnCompleteListener((task) ->  {


                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("UserName",user_Name);
                            user.put("National ID",user_NationalIdInp);
                            user.put("university Name",university_NameInp);
                            user.put("major",major_Inp);
                            user.put("Email",user_EmailInp);
                            user.put("State",Sstate);
                            documentReference.set(user).addOnSuccessListener( (OnSuccessListener) (aVoid) -> {
                                Log.d(TAG, "onSuccess: user Profile is created for "+userID);
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure "+e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), home.class));
                        }else{
                            Toast.makeText(register.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                });


        }); //last register

    }


    public void backToMain(View V){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

}

