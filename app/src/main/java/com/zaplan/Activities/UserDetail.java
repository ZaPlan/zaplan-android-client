package com.zaplan.Activities;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zaplan.R;

import java.util.Calendar;

/**
 * Created by prasang7 on 13/8/16.
 */
public class UserDetail extends Activity {

    TextView tv_choosePrefs, tv_whenfree, tv_chooseBudget, tv_chooseDistance;
    SeekBar seekBar_budget, seekBar_distance;
    Button bt_next, bt_startTime, bt_endTime , bt_logout;
    Calendar myCalendar;

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
                    launchLoginActivity();
                }
            }
        };

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(new Intent(UserDetail.this, Login.class)));
            }
        });

        bt_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(UserDetail.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour > 12) {
                            selectedHour = selectedHour - 12;
                            bt_startTime.setText(selectedHour + " : " + selectedMinute + "  PM");
                        }
                        else
                            bt_startTime.setText(selectedHour + " : " + selectedMinute + "  AM");
                    }
                }, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), false).show();
            }
        });

        bt_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(UserDetail.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour > 12) {
                            selectedHour = selectedHour - 12;
                            bt_endTime.setText(selectedHour + " : " + selectedMinute + "  PM");
                        }
                        else
                            bt_endTime.setText(selectedHour + " : " + selectedMinute + "  AM");
                    }
                }, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), false).show();
            }
        });

        seekBar_budget.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int budget;
                if (progress == 0) {
                    budget = 100;
                }
                else {
                    budget = (progress*1000);
                }
                Toast.makeText(getApplicationContext(), "Approx Budget set: Rs." + budget, Toast.LENGTH_SHORT).show();
            }
        });

        seekBar_distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int distance;
                if (progress == 0) {
                    distance = 10; // in km
                }
                else {
                    distance = (progress*10);
                }

                Toast.makeText(getApplicationContext(), "Approx Distance: " + distance + " km", Toast.LENGTH_SHORT).show();
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDetail.this, Display.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

    }

    void launchLoginActivity() {
        Intent i = new Intent(UserDetail.this, Login.class);
        startActivity(i);
    }

    String getUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String email = user.getEmail();
            return email;
        }
        else {
            return null;
        }
    }

    void init() {

        setContentView(R.layout.activity_userdetail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        seekBar_budget = (SeekBar) findViewById(R.id.seekBar_budget);
        seekBar_distance = (SeekBar) findViewById(R.id.seekBar_distance);
        bt_next = (Button) findViewById(R.id.bt_userdetail_next);
        bt_startTime = (Button) findViewById(R.id.bt_startTime);
        bt_endTime = (Button) findViewById(R.id.bt_EndTime);
        bt_logout = (Button) findViewById(R.id.bt_details_logout);

        tv_whenfree = (TextView) findViewById(R.id.tv_details_whenFree);
        tv_choosePrefs = (TextView) findViewById(R.id.tv_details_chooseprefs);
        tv_chooseBudget = (TextView) findViewById(R.id.tv_chooseBudget);
        tv_chooseDistance = (TextView) findViewById(R.id.tv_chooseDistance);

        Typeface MontReg = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Regular.otf");
        Typeface MontBold = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Bold.otf");
        Typeface MontHair = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Hairline.otf");
        tv_choosePrefs.setTypeface(MontBold);
        tv_chooseBudget.setTypeface(MontReg);
        tv_chooseDistance.setTypeface(MontReg);
        bt_startTime.setTypeface(MontReg);
        bt_endTime.setTypeface(MontReg);
        tv_whenfree.setTypeface(MontReg);
        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(1);
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


}
