package com.example.danhpham.group2;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void goToMain(View view) {
        Intent startMain = new Intent(this, HomePageActivity.class);
        startActivity(startMain);
    }

}
