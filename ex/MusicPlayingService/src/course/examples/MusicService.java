package course.examples;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * This MusicService uses a MediaPlayer to download and play a song in
 * the background.
 */
public class MusicService extends Service 
                          implements MediaPlayer.OnPreparedListener {
    /**
     * Debugging tag used by the Android logger.
     */
    private String TAG = getClass().getSimpleName();

    /**
     * Key used to identify the extra in an intent that contains the
     * URL for the requested song.
     */
    private static String SONG_URL = "SONG_URL";

    /**
     * The MediaPlayer that plays a song in the background.
     */
    private MediaPlayer mPlayer;

    /**
     * This factory method returns an intent used to play and stop
     * playing a song, which is designated by the @a songURL.
     */
    public static Intent makeIntent(final Context context,
                                    String songURL) {
        // Create an intent that points to the MusicService.
        Intent intent =
            new Intent(context,
                       MusicService.class);

        // Add the SONG_URL as an "extra" to the intent.
        intent.putExtra(SONG_URL,
                        songURL);
        return intent;
    }

    /**
     * Hook method called when a new instance of Service is created.
     * One time initialization code goes here.
     */
    @Override
    public void onCreate() {
        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate();

        // Create a MediaPlayer that will play the requested song.
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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
        String songURL = intent.getStringExtra(SONG_URL);
        try {
            // Indicate the URL indicating the song to play.
            mPlayer.setDataSource(songURL);
                
            // Register the callback.
            mPlayer.setOnPreparedListener(this);

            // This call doesn't block the UI Thread.
            mPlayer.prepareAsync(); 
        } catch (IOException e) {
        	e.printStackTrace();
        }

        // Don't restart Service if it shuts down
        return START_NOT_STICKY;
    }

    /** 
     * Called back when MediaPlayer is ready.
     */
    public void onPrepared(MediaPlayer player) {
        // Just play the song once, rather than have it loop
        // endlessly.
        player.setLooping(false);

        // Start playing the song.
        player.start();
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
