package vandy.mooc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * An Activity that Downloads an image, stores it in a local file on
 * the local device, and returns a Uri to the image file.
 */
public class DownloadImageActivity 
       extends LifecycleLoggingActivity {
    /**
     * Name of the Intent Action that wills start this Activity.
     */
    public static String ACTION_DOWNLOAD_IMAGE =
        "vandy.cs251.action.DOWNLOAD_IMAGE";

    /**
     * Display progress.
     */
    private ProgressBar mLoadingProgressBar;

    /**
     * Constants used by RetainFragmentManager.
     */
    private static String URL = "url";
    private static String IMAGEPATH = "imagePath";
    private static String THREAD = "thread";
    
    /**
     * Retain state information between configuration changes.
     */
    protected RetainedFragmentManager mRetainedFragmentManager =
        new RetainedFragmentManager(this.getFragmentManager(),
                                    "DownloadImageActivityTag");

    /**
     * A key used to save/restore necessary state.
     */
    private static String URI = "uri";

    /**
     * Hook method called when a new instance of Activity is
     * created. One time initialization code goes here, e.g., UI
     * layout and some class scope variable initialization.
     *
     * @param savedInstanceState
     *            object that contains saved state information.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // This is how you use logcat logging.
        Log.d(TAG, "onCreate()");

        // Always call super class for necessary
        // initialization/implementation.
        // TODO -- you fill in here.

        // Set the default layout.
        // TODO -- you fill in here.

        // Store the ProgressBar in a field for fast access.
        mLoadingProgressBar = (ProgressBar)
            findViewById(R.id.progressBar_loading);

        // If this method returns true then this is the first time the
        // Activity has been created.
        if (mRetainedFragmentManager.firstTimeIn()) {
            // Store the Url into the RetainedFragmentManager.
            mRetainedFragmentManager.put(URL,
                                         getIntent().getData());
            
            Log.d(TAG,
                  "first time onCreate() " 
                  + (Uri) mRetainedFragmentManager.get(URL));
        } else {
            // The RetainedFragmentManager was previously initialized,
            // which means that a configuration change occured, so
            // obtain its data and figure out the next steps.

            Log.d(TAG,
                  "second time onCreate() " 
                  + (Uri) mRetainedFragmentManager.get(URL));

            Uri pathToImage =
                mRetainedFragmentManager.get(IMAGEPATH);

            // If the pathToImage is non-null then we're done, so set
            // the result of the Activity and finish it.
            if (pathToImage != null) {
                Log.d(TAG,
                      "finishing activity since result computed "
                      + pathToImage);

                // Set the result of the Activity.
                setActivityResult(pathToImage);

                // Stop the Activity from running and return.
                finish();
            } else {
                Log.d(TAG,
                      "continuing since result is NOT yet computed");
            }
        }
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
        // Always call super class for necessary
        // initialization/implementation.
        // TODO - you fill in here.

        // Make progress bar visible.
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        Thread thread = (Thread)
            mRetainedFragmentManager.get(THREAD);

        if (thread == null) {
            Log.d(TAG,
                  "onStart() creating and executing a Thread");

            // Download the image in the background, create an Intent
            // that contains the path to the image file, and set this
            // as the result of the Activity.
            // TODO -- you fill in here.

            // Create and start a new thread to Download and process
            // the image.
            mRetainedFragmentManager.put(THREAD,
                                         thread);
            thread.start();
        } else {
            Log.d(TAG,
                  "onStart() NOT executing a new Thread");
        }
    }
    /**
     * Called when Activity is no longer visible.  Release resources
     * that may cause memory leak. Save instance state
     * (onSaveInstanceState()) in case activity is killed.
     */
    @Override
    protected void onStop(){
        // Always call super class for necessary
        // initialization/implementation and then log which lifecycle
        // hook method is being called.
        // TODO - you fill in here.

        // Dismiss the progress bar.
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Set the result of the Activity to indicate whether the image
     * operation succeeded or not.
     * 
     * @param pathToImage
     *          The pathname to the image file.
     */
    private void setActivityResult(Uri pathToImage) {
        if (pathToImage == null)
            // Indicate the image operation was unsuccessful or was
            // cancelled.
            setResult(RESULT_CANCELED);
        else
            // Set the result of the Activity to designate the path to
            // the image file resulting from a successful operation.
            setResult(RESULT_OK,
                      new Intent("",
                                 pathToImage));
    }

    /**
     * Download the image in a background Thread and set the result of
     * the Activity.
     */
    private class DownloadRunnable implements Runnable {
        // TODO -- you fill in here using the Android "HaMeR"
        // concurrency framework. Note that the finish() method should
        // be called in the UI thread, whereas the other methods
        // should be called in the background thread. See
        // http://stackoverflow.com/questions/20412871/is-it-safe-to-finish-an-android-activity-from-a-background-thread
        // for more discussion about this topic.
        public void run() {

        }
    }
}
