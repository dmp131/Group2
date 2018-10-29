package com.example.danhpham.group2;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends Activity {

    EditText firstName, lastName, userName, phone, email, password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Connecting edit text variable to framework
        firstName = (EditText) findViewById(R.id.input_fName);
        lastName = (EditText) findViewById(R.id.input_lName);
        userName = (EditText) findViewById(R.id.input_userName);
        phone = (EditText) findViewById(R.id.input_phoneNumber);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        signup = (Button) findViewById(R.id.btn_signup);

        // Connecting button variable to frame work
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignup();
            }
        });

    }
    public void goToMain(View view) {
        Intent startMain = new Intent(this, HomePageActivity.class);
        startActivity(startMain);
    }

    public void setSignup(){
        //StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.101/loginapp/login.php",
        StringRequest request = new StringRequest(Request.Method.POST, "http://dmp131.000webhostapp.com/register_online_danh.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "this is reponses" + response, Toast.LENGTH_SHORT).show();
                        if (response.contains("1")){
                            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Not successful!!!!!!",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("firstname",firstName.getText().toString());
                params.put("lastname",lastName.getText().toString());
                params.put("username",userName.getText().toString());
                params.put("password",password.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("email",email.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);


    }


}
