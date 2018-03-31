package hu.pe.yummykart.surakshauser;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class BackgroundService extends Service
{
    MapsActivity mapsActivity;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"BIND",Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  Toast.makeText(this,"CREATE",Toast.LENGTH_LONG).show();
        mapsActivity = MapsActivity.instance;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // Toast.makeText(this,"onStartCommand",Toast.LENGTH_LONG).show();
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        final BroadcastReceiver mReceiver = new Broadcast();
        registerReceiver(mReceiver, filter);
        return super.onStartCommand(intent, flags, startId);
       // mainActivity.sendMySMS();
    }

}
