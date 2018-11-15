package app.woovictory.liiv_live.fcm;

import android.util.Log;
import app.woovictory.liiv_live.Network.ApplicationController;
import app.woovictory.liiv_live.Network.NetworkService;
import app.woovictory.liiv_live.Post.PostRefreshFcmTokenResponse;
import app.woovictory.liiv_live.db.SharedPreferenceController;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private static String token;

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        token = FirebaseInstanceId.getInstance().getToken();
        Log.v("fcmToken", "refresh됨");
        refreshFcmToekn(SharedPreferenceController.INSTANCE.getMyId(getApplicationContext()),token);
    }

    public String tokenReceive(){
        return token;
    }

    public void refreshFcmToekn(String userID, final String fcmToken){
        NetworkService networkService = ApplicationController.instance.networkService;
        Call<PostRefreshFcmTokenResponse> postRefreshFcmTokenResponse = networkService.postRefreshFcmTokenResponse(userID, fcmToken);

        postRefreshFcmTokenResponse.enqueue(new Callback<PostRefreshFcmTokenResponse>() {
            @Override
            public void onResponse(Call<PostRefreshFcmTokenResponse> call, Response<PostRefreshFcmTokenResponse> response) {
                if(response.isSuccessful()){
                    SharedPreferenceController.INSTANCE.setMyFcmToken(getApplicationContext(), fcmToken);
                    Log.v("fcmToken =" , fcmToken);
                }
            }

            @Override
            public void onFailure(Call<PostRefreshFcmTokenResponse> call, Throwable t) {
                Log.e("FCM REFRESH 통신 실패", t.toString());
            }
        });
    }
}
