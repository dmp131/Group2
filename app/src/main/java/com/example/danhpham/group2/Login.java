package com.example.danhpham.group2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private TextView mTextMessage;
    Button bLogin;
    EditText etEmail, etPassword;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Below are the sign-up fields as EditText Objects (to be leveraged later)
        etEmail = (EditText) findViewById(R.id.input_email);
        etPassword = (EditText) findViewById(R.id.input_password);
    
        // Login button
        bLogin = (Button) findViewById(R.id.btn_login);

        mTextMessage = (TextView) findViewById(R.id.message);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbLogin();
            }
        });

    }

    public void setbLogin(){
        //StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.101/loginapp/login.php",
        StringRequest request = new StringRequest(Request.Method.POST, "http://dmp131.000webhostapp.com/login_online.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "this is reponses" + response, Toast.LENGTH_SHORT).show();
                        if (response.contains("1")){
                            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Wrong username or password",
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
                params.put("username",etEmail.getText().toString());
                params.put("password",etPassword.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);


    }

    public void goToMain(View view) {
        Intent startMain = new Intent(this, HomePageActivity.class);
        startActivity(startMain);
    }
    public void goToSignup(View view) {
        Intent startMain = new Intent(this, SignUp.class);
        startActivity(startMain);
    }

}
