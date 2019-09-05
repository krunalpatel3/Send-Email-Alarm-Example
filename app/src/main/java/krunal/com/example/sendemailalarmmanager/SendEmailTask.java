package krunal.com.example.sendemailalarmmanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

public class SendEmailTask extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            Log.e("onReceive", "onReceive call");

            if (intent.getAction() != null && context != null) {
                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                    // Set the alarm here.
                    Log.d("onReceive", "onReceive: BOOT_COMPLETED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();

                    Log.e("ACTION_BOOT", "ACTION_BOOT_COMPLETED call");
                    NotificationManager notificationManager = (NotificationManager)
                            context.getSystemService(Context.NOTIFICATION_SERVICE);

                    //Create the content intent for the notification, which launches this activity
                    Intent contentIntent = new Intent(context, MainActivity.class);
                    PendingIntent contentPendingIntent = PendingIntent.getActivity
                            (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    //Build the notification
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle(context.getString(R.string.notification_text_boot))
                            .setContentText(context.getString(R.string.notification_text_boot))
                            .setContentIntent(contentPendingIntent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setDefaults(NotificationCompat.DEFAULT_ALL);

                    //Deliver the notification
                    notificationManager.notify(NOTIFICATION_ID, builder.build());

                    SharedPreferenceTime localData = new SharedPreferenceTime(context);
                    Scheduler.setReminder(context, SendEmailTask.class, localData.get_hour(),
                            localData.get_min());
                    return;
                }

                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_TIME_CHANGED)) {
                    // Set the alarm here.
                    Log.d("onReceive", "onReceive: ACTION_TIME_CHANGED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                    SharedPreferenceTime localData = new SharedPreferenceTime(context);
                    Scheduler.setReminder(context, SendEmailTask.class,
                            localData.get_hour(), localData.get_min());
                    return;
                }

                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_TIMEZONE_CHANGED)) {
                    // Set the alarm here.
                    Log.d("onReceive", "onReceive: ACTION_TIMEZONE_CHANGED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                    SharedPreferenceTime localData = new SharedPreferenceTime(context);
                    Scheduler.setReminder(context, SendEmailTask.class, localData.get_hour(), localData.get_min());
                    return;
                }

                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_REBOOT)) {
                    // Set the alarm here.
                    Log.d("onReceive", "onReceive: ACTION_REBOOT");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                    SharedPreferenceTime localData = new SharedPreferenceTime(context);
                    Scheduler.setReminder(context, SendEmailTask.class, localData.get_hour(),
                            localData.get_min());
                    return;
                }

                if (intent.getAction().equalsIgnoreCase("android.intent.action.QUICKBOOT_POWERON")) {
                    // Set the alarm here.
                    Log.d("onReceive", "onReceive: android.intent.action.QUICKBOOT_POWERON");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                    SharedPreferenceTime localData = new SharedPreferenceTime(context);
                    Scheduler.setReminder(context, SendEmailTask.class,
                            localData.get_hour(), localData.get_min());
                    return;
                }
            }





                NotificationManager notificationManager = (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);

                //Create the content intent for the notification, which launches this activity
                Intent contentIntent = new Intent(context, MainActivity.class);
                PendingIntent contentPendingIntent = PendingIntent.getActivity
                        (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                //Build the notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(context.getString(R.string.notification_title))
                        .setContentText(context.getString(R.string.notification_text))
                        .setContentIntent(contentPendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

                //Deliver the notification
                notificationManager.notify(NOTIFICATION_ID, builder.build());

                Log.e("outside", "outside");

                Toast.makeText(context, "sdf", Toast.LENGTH_SHORT).show();
                Log.e("Sent", "Send Email from BackGround call onReceive");

                List<String> emaiList = new ArrayList<>();
                emaiList.add("abcd1@gmail.com");
                emaiList.add("abcd2@gmail.com");
                emaiList.add("abcd3@gmail.com");


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //check schedule time here : 02:20 pm
                        //get schedule time         : execute only when compare
                        //compare with currunt time  :
                        //check send status

                        Log.e("run", "run call");
                        Email androidEmail = new Email("senderEmail@gmail.com",
                                "sender_email_pass_here", emaiList,
                                "Email for final Testing using AlarmManager Moto G2",
                                "a.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, p1);" +
                                        "Oreo api 28 time Ever 1:30 PM Boot");

                        try {
                            androidEmail.createEmailMessage();
                            androidEmail.sendEmail();
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }






    }
}
