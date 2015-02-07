package course.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This MusicActivity is a simple front-end to the MusicService, which
 * plays a song in the background.
 */
public class MusicActivity extends Activity {
    /**
     * Debug Tag for logging debug output to LogCat
     */
    private final String TAG =
        MusicActivity.class.getSimpleName();

    /**
     * URL to the default song to play if the user doesn't enter a URL.
     */
    private static String DEFAULT_SONG = 
        "http://www.dre.vanderbilt.edu/~schmidt/braincandy.m4a";

    /**
     * User's selection of URL to download.
     */
    private EditText mUrlEditText;

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

        // Cache the EditText resource. 
        mUrlEditText = 
            (EditText) findViewById(R.id.mUrlEditText);
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
                                    getUrlString());

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
                                    null);

        // Stop the MusicService.
        stopService (intent);
    }	

    /**
     * Read the URL EditText and return the String it contains.
     * 
     * @return String value in mUrlEditText
     */
    public String getUrlString() {
        // Get the user input (if any).
        String url = mUrlEditText.getText().toString();

        //  Use the default URL if the user doesn't supply one.
        if (url.equals(""))
            url = DEFAULT_SONG;

        return url; 
    }
}
