package course.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This MusicActivity is a simple front-end to the MusicService, which
 * plays a song in the background.
 */
public class MusicActivity extends Activity {
    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * class scope variable initialization.
     *
     * @param Bundle object that contains saved state information.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate(savedInstanceState);

        // Set the default layout.
        setContentView(R.layout.main);
    }
	
    /**
     * Play a song via the MusicService.
     */
    public void playSong (View src) {
        // Create an intent that will start the MusicService to play a
        // requested song ("real" application wouldn't hard-code the
        // song!)
        Intent intent =
            MusicService.makeIntent(this,
                                    R.raw.braincandy);

        // Start the MusicService.
        startService(intent);
    }

    /**
     * Stop playing a song via the MusicService.
     */
    public void stopSong (View src) {
        // Create an intent that will stop the MusicService.
        Intent intent =
            MusicService.makeIntent(this,
                                    0);

        // Stop the MusicService.
        stopService (intent);
    }	
}
