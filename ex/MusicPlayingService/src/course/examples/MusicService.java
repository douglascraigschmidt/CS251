package course.examples;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

/**
 * This MusicService extends Service and uses a MediaPlayer to
 * download and play a song in the background.  Although it runs in
 * the UI Thread, it implements MediaPlayer.OnPreparedListener to
 * avoid blocking the UI Thread while a song is initially streamed.
 */
public class MusicService extends Service 
                          implements MediaPlayer.OnPreparedListener {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * Intent used to start the Service.
     */
    private final static String ACTION_PLAY =
        "course.examples.action.PLAY";

    /**
     * Keep track of whether a song is currently playing.
     */
    private static boolean mSongPlaying;

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
        // Create and return an intent that points to the
        // MusicService.
        return new Intent(ACTION_PLAY,
                          Uri.parse(songURL),
                          context,
                          MusicService.class);
    }

    /**
     * Hook method called when a new instance of Service is created.
     * One time initialization code goes here.
     */
    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate() entered");

        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate();

        // Create a MediaPlayer that will play the requested song.
        mPlayer = new MediaPlayer();

        // Indicate the MediaPlayer will stream the audio.
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    /**
     * Hook method called every time startService() is called with an
     * Intent associated with this MusicService.
     */
    @Override
    public int onStartCommand (Intent intent,
                               int flags,
                               int startid) {
        // Extract the URL for the song to play.
        final String songURL = intent.getDataString();

        Log.i(TAG,
              "onStartCommand() entered with song URL "
              + songURL);

        if (mSongPlaying) 
            // Stop playing the current song.
            stopSong();

        try {
            // Indicate the URL indicating the song to play.
            mPlayer.setDataSource(songURL);
                
            // Register "this" as the callback when the designated
            // song is ready to play.
            mPlayer.setOnPreparedListener(this);

            // This call doesn't block the UI Thread.
            mPlayer.prepareAsync(); 
        } catch (IOException e) {
        	e.printStackTrace();
        }

        // Don't restart Service if it shuts down.
        return START_NOT_STICKY;
    }

    /** 
     * Hook method called back when MediaPlayer is ready to play the
     * song.
     */
    public void onPrepared(MediaPlayer player) {
        Log.i(TAG,"onPrepared() entered");

        // Just play the song once, rather than have it loop
        // endlessly.
        player.setLooping(false);

        // Note that song is now playing.
        mSongPlaying = true;

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
        stopSong();

        // Call up to the super class.
        super.onDestroy();
    }

    /**
     * Stops the MediaPlayer from playing the song.
     */
    private void stopSong() {
        Log.i(TAG,"stopSong() entered");

        // Stop playing the song.
        mPlayer.stop();
        
        // Reset the state machine of the MediaPlayer.
        mPlayer.reset();

        // Note that no song is playing.
        mSongPlaying = false;
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
