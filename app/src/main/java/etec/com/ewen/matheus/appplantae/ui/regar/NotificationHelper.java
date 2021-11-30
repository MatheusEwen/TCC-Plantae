package etec.com.ewen.matheus.appplantae.ui.regar;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import etec.com.ewen.matheus.appplantae.MainActivity;
import etec.com.ewen.matheus.appplantae.R;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Plantas";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    //tela do conteudo da notificação

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Plantae!")
                .setContentText("Não se esqueça de regar as plantas.")
                .setSmallIcon(R.drawable.logon)
                .setContentIntent(pIntent)
                .setAutoCancel(true);
    }

    final Intent ii = new Intent(this, MainActivity.class);

    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),0,ii,0);

}
