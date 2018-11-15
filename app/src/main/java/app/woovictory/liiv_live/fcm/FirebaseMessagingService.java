package app.woovictory.liiv_live.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import app.woovictory.liiv_live.R;
import app.woovictory.liiv_live.SOSPopupActivity;
import app.woovictory.liiv_live.view.login.LoginActivity;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.v("123456","010101010101001010");
//        String title =remoteMessage.getNotification().getTitle();
//
//        bbb(title);
//        Log.v("123456",title);
//        Log.v("123456", remoteMessage.getData().get("quiz").toString());
//        Log.v("123456", remoteMessage.getData().get("survey").toString());
//        sendPushNotification(remoteMessage.getData().get("message"));
//        Log.v("123456", getApplicationContext().getClass().getSimpleName().trim());


        Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
//        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
        getApplicationContext().startActivity(intent_);
    }

    public String bbb(String title){
        return title;
    }

    private void sendPushNotification(String message) {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.complete_v_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                .setContentTitle("Push Title ")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
