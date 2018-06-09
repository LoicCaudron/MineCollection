package com.example.einore.minecollection.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.einore.minecollection.Model.User;
import com.example.einore.minecollection.Model.User_DAO;
import com.example.einore.minecollection.R;

public class RegisterActivity extends AppCompatActivity {

    Button signUp_button;
    EditText username_editText;
    EditText pinCode_editText;
    EditText name_editText;
    EditText surname_editText;
    EditText email_editText;
    EditText phone_editText;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        //Components
        signUp_button = (Button)findViewById(R.id.button_register);
        username_editText = (EditText)findViewById(R.id.editText_username);
        pinCode_editText = (EditText)findViewById(R.id.editText_pinCode);
        name_editText= (EditText)findViewById(R.id.editText_name);
        surname_editText = (EditText)findViewById(R.id.editText_surname);
        email_editText = (EditText)findViewById(R.id.editText_email);
        phone_editText = (EditText)findViewById(R.id.editText_phone);
        test = (TextView)findViewById(R.id.textView3);

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_editText.getText().toString();
                String pinCode = pinCode_editText.getText().toString();
                String name = name_editText.getText().toString();
                String surname = surname_editText.getText().toString();
                String email = email_editText.getText().toString();
                String phone = phone_editText.getText().toString();

                User user = new User();
                user.setUser_username(username);
                user.setUser_pinCode(pinCode);
                user.setUser_name(name);
                user.setUser_surname(surname);
                user.setUser_email(email);
                user.setUser_phone(phone);

                Context context = getApplicationContext();
                User_DAO mUser_dao = new User_DAO(context);
                mUser_dao.openForWrite();
                mUser_dao.insertUser( user );
                mUser_dao.close();

                Intent connexionActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(connexionActivity);

            }
        });
    }
}
