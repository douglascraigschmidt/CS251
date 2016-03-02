package course.examples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * An Activity that maps a location from an address given by the user.
 */
public class MapLocationActivity extends Activity {
    /**
     * Debugging tag used by the Android logger.
     */
    private String TAG = getClass().getSimpleName();

    /**
     * Address entered by the user.
     */
    private EditText mAddrText;

    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * class scope variable initialization.
     *
     * @param Bundle object that contains saved state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate(savedInstanceState);

        // Set the default layout.
        setContentView(R.layout.main);

        // Cache the EditText object in a field.
        mAddrText = (EditText) findViewById(R.id.location);
    }

    /**
     * Hook method called after onCreate() or after onRestart() (when
     * the activity is being restarted from stopped state).  Should
     * re-acquire resources relinquished when activity was stopped
     * (onStop()) or acquire those resources for the first time after
     * onCreate().
     */	
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "The activity is about to become visible.");
    }

    /**
     * Hook method called after onRestoreStateInstance(Bundle) only if
     * there is a prior saved instance state in Bundle object.
     * onResume() is called immediately after onStart().  onResume()
     * is called when user resumes activity from paused state
     * (onPause()) User can begin interacting with activity.  Place to
     * start animations, acquire exclusive resources, such as the
     * camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "The activity has become visible (it is now \"resumed\")");
    }

    /**
     * Hook method called when an Activity loses focus but is still
     * visible in background. May be followed by onStop() or
     * onResume().  Delegate more CPU intensive operation to onStop
     * for seamless transition to next activity.  Save persistent
     * state (onSaveInstanceState()) in case app is killed.  Often
     * used to release exclusive resources.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,
              "Another activity is taking focus (this activity is about to be \"paused\")");
    }

    /**
     * Called when Activity is no longer visible.  Release resources
     * that may cause memory leak. Save instance state
     * (onSaveInstanceState()) in case activity is killed.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
    }

    /**
     * Hook method called when user restarts a stopped activity.  Is
     * followed by a call to onStart() and onResume().
     */	
    @Override
    protected void onRestart(){
        // Always call super class for necessary
        // initialization/implementation.
        super.onRestart();
        Log.d(TAG, "The activity is about to be restarted()");
    }
	
    /**
     * Hook method that gives a final chance to release resources and
     * stop spawned threads.  onDestroy() may not always be
     * called-when system kills the hosting process.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "The activity is about to be destroyed.");
    }

    /**
     * Called by the Android Activity framework when the user clicks
     * the "Show Map" button.
     */
    public void showMap(View v) {
        try {
            // Get the address entered by the user.
            String address = mAddrText.getText().toString();

            // Replace spaces (' ') with '+' signs to make the browser
            // happy.
            address = address.replace(' ', '+');

            // Hide the keyboard.
            hideKeyboard();

            // Launch the activity by sending an intent.  Android will
            // choose the right one or let the user choose if more
            // than one Activity can handle it.

            // Create an Intent that will launch the "Maps" app.
            final Intent geoIntent = makeGeoIntent(address);

            // Check to see if there's a Map app to handle the "geo"
            // intent.
            if (geoIntent.resolveActivity(getPackageManager()) != null) 
                startActivity(geoIntent);
            else
                // Start the Browser app instead.
                startActivity(makeMapsIntent(address));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hide the keyboard after a user has finished typing the acronym
     * they want expanded.
     */
    protected void hideKeyboard() {
        InputMethodManager mgr =
            (InputMethodManager) getSystemService
            (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mAddrText.getWindowToken(),
                                    0);
    }


    /**
     * Factory method that returns an Intent that designates the "Map"
     * app.  
     */
    private Intent makeGeoIntent(String address) {
        // Note the "loose coupling" between the Intent and the app(s)
        // that handle this Intent.
        return new Intent(Intent.ACTION_VIEW,
                          Uri.parse("geo:0,0?q=" 
                                    + address));
    }

    /**
     * Factory method that returns an Intent that designates the
     * "Browser" app.
     */
    private Intent makeMapsIntent(String address) {
        // Note the "loose coupling" between the Intent and the app(s)
        // that handle this Intent.
        return new Intent(Intent.ACTION_VIEW,
                          Uri.parse("http://maps.google.com/?q=" 
                                    + address));
    }
}
