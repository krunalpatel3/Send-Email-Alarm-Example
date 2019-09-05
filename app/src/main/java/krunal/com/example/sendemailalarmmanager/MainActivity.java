package krunal.com.example.sendemailalarmmanager;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textView2;
    private int mHour, mMinte;
    private EditText editText;
    private Button button;
    private int hours = 0, min = 0;
    SharedPreferenceTime localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        localData = new SharedPreferenceTime(MainActivity.this);

        updateTime(localData.get_hour(),localData.get_min());

        textView.setOnClickListener(v -> {
            final Calendar calendar1 = Calendar.getInstance();

            mHour = calendar1.get(Calendar.HOUR_OF_DAY);
            mMinte = calendar1.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
//
//                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                calendar.set(Calendar.MINUTE, minute);
//
//                Log.e("Time_IN_Minisecon", String.valueOf(calendar.getTimeInMillis()));
//                long time = calendar.getTimeInMillis();
                hours = hourOfDay;
                min = minute;
                updateTime(hourOfDay, minute);

            }, mHour, mMinte, false);
            timePicker.show();
        });

        button.setOnClickListener(v -> {

//                Toast.makeText(this,"inside",Toast.LENGTH_SHORT).show();
//                Log.e("TimeInHoursAndMin", String.valueOf(hours) + String.valueOf(min));
////        long getsetTimeInMillis = TimeUnit.HOURS.toMillis((long) hours) +
////                TimeUnit.MINUTES.toMillis((long) mins);
//
//                Calendar calendar2 = Calendar.getInstance();
//                calendar2.set(Calendar.HOUR_OF_DAY, hours);
//                calendar2.set(Calendar.MINUTE, min);
////
////        long currentTime = System.currentTimeMillis();
////        long specificTimeToTrigger = calendar2.getTimeInMillis();
////        long delayToPass = specificTimeToTrigger - currentTime;
//
//                Intent intent = new Intent(MainActivity.this, SendEmailTask.class);
//                AlarmManager a = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(),
//                        101, intent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//
//                //   Log.e("delayToPass", String.valueOf(delayToPass));
//                Log.e("HourMinInMillisecond", String.valueOf(calendar2.getTimeInMillis()));
//                // Checking if editText is Empty.
//
//                // a.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, p1);
//                a.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                        calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, p1);



            // Set up Alarm.
            Scheduler.setReminder(MainActivity.this,
                    SendEmailTask.class, localData.get_hour(), localData.get_min());



        });
    }

    private void updateTime(int hours, int mins) {

        // Save hours and mins to SharedPreference file.
        localData.set_hour(hours);
        localData.set_min(mins);

//        Scheduler.setReminder(MainActivity.this, SendEmailTask.class
//                , localData.get_hour(), localData.get_min());


        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        textView2.setText(aTime);
    }
}
