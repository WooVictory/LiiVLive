package app.woovictory.liiv_live.fcm;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import app.woovictory.liiv_live.R;
import app.woovictory.liiv_live.db.pointItemDataList;
import app.woovictory.liiv_live.view.login.LoginActivity;
import app.woovictory.liiv_live.view.popup.LiveFinishPopUpActivity;
import app.woovictory.liiv_live.view.popup.SOSPopupActivity;
import app.woovictory.liiv_live.view.quiz.QuizActivity;
import app.woovictory.liiv_live.view.quiz.QuizAnswerActivity;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

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

        // 설문 푸시일 경우
        if(remoteMessage.getNotification().getTitle().equals("설문푸시")){
            // ### 논리처리 설문 푸시
            Log.v("TAG", "설문푸시에 들어옴");
            Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
//        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
            getApplicationContext().startActivity(intent_);
        }// 퀴즈 푸시일 경우
        else if(remoteMessage.getNotification().getTitle().equals("퀴즈정답푸시")){
            ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            Log.v("TAG", "퀴즈정답푸시일경우 이쪽으로 들어옴1");

//api level 21.. deprecated..

            List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);

            ActivityManager.RunningTaskInfo info = list.get(0);

            if (info.topActivity.getClassName().equals("app.woovictory.liiv_live.view.live.LiveActivity")){
                String quiz_id = remoteMessage.getData().get("quiz_id");
                String user_select_example_id = remoteMessage.getData().get("user_select_example_id");
                String question = remoteMessage.getData().get("question");
                Log.v("TAG", "퀴즈정답푸시일경우 이쪽으로 들어옴2");

                Intent intent = new Intent(getApplicationContext(), QuizAnswerActivity.class);
                intent.putExtra("quiz_id", quiz_id);
                intent.putExtra("select_answer", user_select_example_id);
                intent.putExtra("quiz_title", question);
                // 이거만해
//                intent.putExtra("quiz_title", SharedPreferenceController.INSTANCE.getMyId(getApplicationContext()));
                getApplicationContext().startActivity(intent);
            }
        }
        else{
            ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

//api level 21.. deprecated..

            List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);

            ActivityManager.RunningTaskInfo info = list.get(0);

            if (info.topActivity.getClassName().equals("app.woovictory.liiv_live.view.live.LiveActivity")) {
                Log.v("TAG", "퀴즈 푸시이다.");

                String json = remoteMessage.getNotification().getBody();

                if(json.equals("퀴즈 소진")){
                    Log.v("TAG", "퀴즈 소진");
                }else{
                    int count = pointItemDataList.INSTANCE.getCount();
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("json", json);
                    getApplicationContext().startActivity(intent);
                    if(count >= 3){
                        //Toast.makeText(getApplicationContext(), "3문제를 푸셨습니다.",Toast.LENGTH_LONG).show();
                        Log.v("woo 948","3문제를 푸셨습니당.");
                        pointItemDataList.INSTANCE.setCount(0);
                        Intent intent2 = new Intent(getApplicationContext(), LiveFinishPopUpActivity.class);
                        getApplicationContext().startActivity(intent2);
                    }else {
                        Log.v("woo 327","문제 아직 남았다.");
                    }
                }

            } else {
                //Toast.makeText(getApplicationContext(),"안됨.",Toast.LENGTH_LONG).show();
                Log.v("TAG 839", info.topActivity.getClassName().toString());
            }

            Log.v("TAG", info.topActivity.getClassName().toString());
        }

        Log.v("TAG","푸시들어옴");
        Log.v("TAG", remoteMessage.getNotification().getBody());
//
//        String json = remoteMessage.getNotification().getBody();
//        Intent intent = new Intent(getApplicationContext(), SOSPopupActivity.class);
//        intent.putExtra("json", json);
//        startActivity(intent);


//        Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
////        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
//        getApplicationContext().startActivity(intent_);

        //        Log.v("123456","010101010101001010");
//        String title =remoteMessage.getNotification().getTitle();
//
//        bbb(title);
//        Log.v("123456",title);
//        Log.v("123456", remoteMessage.getData().get("quiz").toString());
//        Log.v("123456", remoteMessage.getData().get("survey").toString());
//        sendPushNotification(remoteMessage.getData().get("message"));
//        Log.v("123456", getApplicationContext().getClass().getSimpleName().trim());


//        ActivityManager manager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
//
////api level 21.. deprecated..
//
//        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);
//
//        ActivityManager.RunningTaskInfo info = list.get(0);
//
//        if (info.topActivity.getClassName().equals("app.woovictory.liiv_live.view.home.HomeActivity")) {
//            Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
////        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
//            getApplicationContext().startActivity(intent_);
//        } else {
//            //Toast.makeText(getApplicationContext(),"안됨.",Toast.LENGTH_LONG).show();
//            Log.v("TAG 839", info.topActivity.getClassName().toString());
//        }
//
//        Log.v("TAG", info.topActivity.getClassName().toString());
////        Log.v("123456","010101010101001010");
////        String title =remoteMessage.getNotification().getTitle();
////
////        bbb(title);
////        Log.v("123456",title);
////        Log.v("123456", remoteMessage.getData().get("quiz").toString());
////        Log.v("123456", remoteMessage.getData().get("survey").toString());
////        sendPushNotification(remoteMessage.getData().get("message"));
////        Log.v("123456", getApplicationContext().getClass().getSimpleName().trim());
//
//
//        ActivityManager manager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
//
////api level 21.. deprecated..
//
//        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);
//
//        ActivityManager.RunningTaskInfo info = list.get(0);
//
//        if (info.topActivity.getClassName().equals("app.woovictory.liiv_live.view.home.HomeActivity")) {
//            Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
////        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
//            getApplicationContext().startActivity(intent_);
//        } else {
//            //Toast.makeText(getApplicationContext(),"안됨.",Toast.LENGTH_LONG).show();
//            Log.v("TAG 839", info.topActivity.getClassName().toString());
//        }
//
//        Log.v("TAG", info.topActivity.getClassName().toString());
///*
//        Intent intent_ = new Intent(getApplicationContext(), SOSPopupActivity.class);
////        intent_.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
//        getApplicationContext().startActivity(intent_);*/
    }

    public String bbb(String title) {
        return title;
    }

    private void sendPushNotification(String message) {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.complete_v_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setContentTitle("Push Title ")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255, 500, 2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    // 통신코드필요.

}
