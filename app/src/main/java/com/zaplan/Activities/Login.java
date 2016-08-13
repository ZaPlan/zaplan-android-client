package com.zaplan.Activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zaplan.R;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Login extends Activity {

    Button bt_login;
    EditText et_email, et_pass;
    TextInputLayout til_email, til_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void login(String email, String password) {


        // TODO: LOGIN API HERE !!


        Toast.makeText(getApplicationContext(), email + " " + password, Toast.LENGTH_SHORT).show();
    }




    void init() {
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        et_email = (EditText) findViewById(R.id.login_et_email);
        et_pass = (EditText) findViewById(R.id.login_et_password);
        til_email = (TextInputLayout)findViewById(R.id.login_til_email);
        til_pass = (TextInputLayout)findViewById(R.id.login_til_password);
        bt_login = (Button)findViewById(R.id.bt_login_login);
        et_email.addTextChangedListener(new MyTextWatcher(et_email));
        et_pass.addTextChangedListener(new MyTextWatcher(et_pass));
    }

    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateEmail()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }

        login(et_email.getText().toString(), et_pass.getText().toString());
    }

    private boolean validateEmail() {
        String email = et_email.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            til_email.setError("Invalid Email");
            requestFocus(et_email);
            return false;
        } else {
            til_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (et_pass.getText().toString().trim().isEmpty()) {
            til_pass.setError("Password Cannot be Empty");
            requestFocus(et_pass);
            return false;
        } else {
            til_pass.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.login_et_email:
                    validateEmail();
                    break;
                case R.id.login_et_password:
                    validatePassword();
                    break;
            }
        }
    }

}

