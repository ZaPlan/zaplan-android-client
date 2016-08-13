package com.zaplan.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zaplan.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Login extends Activity {

    ImageButton ib_facebook;
    TextView tv_appName, tv_appSlogan;
    EditText et_email, et_password;
    TextInputLayout til_email, til_password;
    Button bt_Login;
    private ProgressDialog pDialog;


    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        mAuth = FirebaseAuth.getInstance();
        // If user already logged in, go to landing page!
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                }
                else {
                }
            }
        };



        // real-time check by key watcher
        et_email.addTextChangedListener(new MyTextWatcher(et_email));
        et_password.addTextChangedListener(new MyTextWatcher(et_password));

        // Normal Login with email - password
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        ib_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void launchLandingPage() {
        Intent i = new Intent(Login.this, UserDetail.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    void init() {
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv_appName = (TextView)findViewById(R.id.tv_appName);
        tv_appSlogan = (TextView)findViewById(R.id.tv_appSlogan);
        ib_facebook = (ImageButton) findViewById(R.id.ib_login_facebook);
        et_email = (EditText)findViewById(R.id.login_et_email);
        et_password = (EditText)findViewById(R.id.login_et_password);
        til_email = (TextInputLayout) findViewById(R.id.login_til_email);
        til_password = (TextInputLayout) findViewById(R.id.login_til_password);
        bt_Login = (Button) findViewById(R.id.bt_login_login);

        Typeface MontReg = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Regular.otf");
        Typeface MontBold = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Bold.otf");
        Typeface MontHair = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Hairline.otf");
        tv_appName.setTypeface(MontBold);
        tv_appSlogan.setTypeface(MontBold);
        bt_Login.setTypeface(MontBold);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loggin in...");
        pDialog.setCancelable(false);

        setStatusBarTranslucent(true);
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

        // if email & pass format correct - create account.
        createAccount(et_email.getText().toString(), et_password.getText().toString());
    }

    private void createAccount(final String email, final String password) {

        showpDialog();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            // if unable to create account (duplicate users), try to login user!
                            signIn(email, password);
                        }
                        else {
                            Toast.makeText(Login.this, "Account Created!", Toast.LENGTH_SHORT).show();
                            hidepDialog();
                            launchLandingPage();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            launchLandingPage();
                        }
                        hidepDialog();
                    }
                });
    }

    /**
       VALIDATIONS
     **/

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
        if (et_password.getText().toString().trim().isEmpty() || et_password.getText().length() < 6) {
            til_password.setError("Password must be atleast 6 characters long!");
            requestFocus(et_password);
            return false;
        } else {
            til_password.setErrorEnabled(false);
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

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(1);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

