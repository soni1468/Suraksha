package hu.pe.yummykart.surakshauser;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FakeCallActivity extends AppCompatActivity
{

    MediaPlayer mp;
    Button butAccept,butDecline,butEndCall;
    long totalSeconds = 40;
    long intervalSeconds = 1;
    CountDownTimer timer;
    TextView tvCallTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fake_call);

        butAccept = (Button) findViewById(R.id.buttonAccept);
        butDecline = (Button) findViewById(R.id.buttonDecline);
        butEndCall = (Button) findViewById(R.id.buttonEndCall);
        tvCallTime = (TextView) findViewById(R.id.textCallTime);

        butEndCall.setVisibility(View.INVISIBLE);
        tvCallTime.setVisibility(View.INVISIBLE);

        if(mp==null){
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

            mp = MediaPlayer.create(this,R.raw.original_iphone);
            mp.start();
            mp.setLooping(true);
        }
    }
    public void acceptCall(View v){
        butEndCall.setVisibility(View.VISIBLE);
        tvCallTime.setVisibility(View.VISIBLE);
        butAccept.setVisibility(View.INVISIBLE);
        butDecline.setVisibility(View.INVISIBLE);
        if(mp!=null){
            mp.stop();
            mp=null;
        }
        timer = new CountDownTimer(totalSeconds * 1000, intervalSeconds * 1000) {

            public void onTick(long millisUntilFinished) {
                long t = (totalSeconds * 1000 - millisUntilFinished) / 1000;
                tvCallTime.setText("00:"+t);
                //Toast.makeText(getApplicationContext(),""+t,Toast.LENGTH_SHORT).show();
                //  Log.d("seconds elapsed: " , (totalSeconds * 1000 - millisUntilFinished) / 1000);
            }

            public void onFinish() {
                finish();
                // Log.d( "done!", "Time's up!");
            }

        };
        timer.start();
    }
    public void declineCall(View v){

        if(mp!=null){
            mp.stop();
            mp=null;
        }

        finish();
    }
    public void endCall(View v){
        if(mp!=null){
            mp.stop();
            mp=null;
        }
        timer.cancel();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null){
            mp.stop();
            mp=null;
        }
    }
}
