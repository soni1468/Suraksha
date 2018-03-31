package hu.pe.yummykart.surakshauser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

public class Broadcast extends BroadcastReceiver
{
    public static boolean wasScreenOn = true;
    MapsActivity mapsActivity;
    Vibrator vibe;
    long a = System.currentTimeMillis();
    long seconds_screenoff = a;
    long seconds_screenon = 0;
    long OLD_TIME = seconds_screenoff;
    final boolean OFF_SCREEN = true;
    final boolean ON_SCREEN = true;
    boolean sent_msg;
    long actual_diff;
    long diffrence;
    boolean flag = false;
    int i = 0, j = 0;

    @Override
    public void onReceive(Context context, final Intent intent) {
        mapsActivity = MapsActivity.instance;

     /*   if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            mainActivity.sendMySMS();
            wasScreenOn = false;
            Log.e("LOB","wasScreenOn"+wasScreenOn);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            mainActivity.sendMySMS();
            wasScreenOn = true;

        }else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.e("LOB","userpresent");
            Log.e("LOB","wasScreenOn"+wasScreenOn);
            String url = "http://www.stackoverflow.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }  */
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        Log.v("onReceive", "Power button is pressed.");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            i++;

            if (i >= 2) {
                i = 0;
                mapsActivity.sendMySMS();
            }
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            j++;
            if (j >= 2) {
                j = 0;
                mapsActivity.sendMySMS();
            }
        }


           /* new CountDownTimer(5000, 200) {

                public void onTick(long millisUntilFinished) {


                    if (ON_SCREEN) {
                        if (seconds_screenon != 0 && seconds_screenoff != 0) {

                            actual_diff = cal_diff(seconds_screenon, seconds_screenoff);
                            if (actual_diff <= 2000) {
                                sent_msg = true;
                                if (sent_msg) {

                                    mainActivity.sendMySMS();
                                     // Toast.makeText(Broadcast.this, "POWER BUTTON CLICKED 2 TIMES", Toast.LENGTH_LONG).show();
                                    vibe.vibrate(100);
                                    seconds_screenon = 0;
                                    seconds_screenoff = 0;
                                    sent_msg = false;

                                }
                            } else {
                                seconds_screenon = 0;
                                seconds_screenoff = 0;

                            }
                        }
                    }
                }

                public void onFinish() {

                    seconds_screenoff = 0 ;
                }
            }.start();


        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            a = System.currentTimeMillis();
            seconds_screenon = a;
            OLD_TIME = seconds_screenoff;

            new CountDownTimer(5000, 200) {

                public void onTick(long millisUntilFinished) {
                    if (OFF_SCREEN) {
                        if (seconds_screenon != 0 && seconds_screenoff != 0) {
                            actual_diff = cal_diff(seconds_screenon, seconds_screenoff);
                            if (actual_diff <= 2000) {
                                sent_msg = true;
                                if (sent_msg) {

                                    mainActivity.sendMySMS();
                                   // Toast.makeText(cntx, "POWER BUTTON CLICKED 2 TIMES", Toast.LENGTH_LONG).show();
                                    vibe.vibrate(100);
                                    seconds_screenon = 0 ;
                                    seconds_screenoff = 0 ;
                                    sent_msg = false;


                                }
                            } else {
                                seconds_screenon = 0 ;
                                seconds_screenoff = 0 ;

                            }
                        }
                    }

                }

                public void onFinish() {

                    seconds_screenon = 0 ;
                }
            }.start();


        }  */


    }

    private long cal_diff(long seconds_screenon2, long seconds_screenoff2) {
        if (seconds_screenon2 >= seconds_screenoff2) {
            diffrence = (seconds_screenon2) - (seconds_screenoff2);
            seconds_screenon2 = 0;
            seconds_screenoff2 = 0;
        } else {
            diffrence = (seconds_screenoff2) - (seconds_screenon2);
            seconds_screenon2 = 0;
            seconds_screenoff2 = 0;
        }

        return diffrence;
    }

}
