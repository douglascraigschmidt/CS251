package course.examples;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * This MusicService uses a MediaPlayer to play a song in the
 * background.
 */
public class MusicService extends Service {
    /**
     * Debugging tag used by the Android logger.
     */
    private String TAG = getClass().getSimpleName();

    /**
     * The MediaPlayer that plays a song in the background.
     */
    private MediaPlayer player;

    /**
     * This factory method returns an intent used to play and stop
     * playing a song, which is designated by the @a resId.
     */
    public static Intent makeIntent(final Context context,
                                    int resId) {
        // Create an intent that points to the MusicService.
        Intent intent =
            new Intent(context,
                       MusicService.class);

        if (resId != 0) 
            // Add the SongID as an "extra" to the intent.
            intent.putExtra("SongID",
                            resId);
        return intent;
    }

    /**
     * Hook method called when a new instance of Service is created.
     * One time initialization code goes here.
     */
    @Override
    public void onCreate() {	
        super.onCreate();

        Log.i(TAG,"onCreate() entered");

        // Create a MediaPlayer that will play a particular song.
        // Naturally, a "real" application wouldn't hard-code the
        // song!
        player = MediaPlayer.create(this, R.raw.braincandy);

        // Just play the song once, rather than have it loop
        // endlessly.
        player.setLooping(false);
    }

    /**
     * Hook method called every time the MusicService is started.
     */
    @Override
    public int onStartCommand (Intent intent,
                               int flags,
                               int startid) {
        Log.i(TAG,"onStartCommand() entered");

        // Start playing the song.
        player.start();

        return START_NOT_STICKY;
    }
	
    /**
     * Hook method called when the MusicService is stopped.
     */
    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy() entered");

        // Stop playing the song.
        player.stop();

        // Call up to the super class.
        super.onDestroy();
    }
	
    /**
     * This no-op method is necessary since MusicService is a
     * so-called "Started Service".
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
