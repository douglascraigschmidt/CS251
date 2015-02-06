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

    private static String SONG_ID = "SongID";

    /**
     * The MediaPlayer that plays a song in the background.
     */
    private MediaPlayer mPlayer;

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
            // Add the SONG_ID as an "extra" to the intent.
            intent.putExtra(SONG_ID,
                            resId);
        return intent;
    }

    /**
     * Hook method called every time the MusicService is started.
     */
    @Override
    public int onStartCommand (Intent intent,
                               int flags,
                               int startid) {
        Log.i(TAG,"onStartCommand() entered");

        // Extract the resId for the requested song from the â€œextraâ€�.
        int songId = intent.getIntExtra(SONG_ID, 0);

        // Create a MediaPlayer that will play the requested song.
        mPlayer = MediaPlayer.create(this,
                                     songId);

        // Just play the song once, rather than have it loop
        // endlessly.
        mPlayer.setLooping(false);

        // Start playing the song.
        mPlayer.start();

        // Donâ€™t restart Service if it shuts down
        return START_NOT_STICKY;
    }
	
    /**
     * Hook method called when the MusicService is stopped.
     */
    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy() entered");

        // Stop playing the song.
        mPlayer.stop();

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
