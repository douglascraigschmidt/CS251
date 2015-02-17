package vandy.cs251;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * A main Activity that prompts the user for a URL to an image and
 * then uses Intents and other Activities to download the image and
 * view it.
 */
public class MainActivity extends LifecycleLoggingActivity {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * A value that uniquely identifies the request to download an
     * image.
     */
    private static final int DOWNLOAD_IMAGE_REQUEST = 1;

    /**
     * EditText field for entering the desired URL to an image.
     */
    private EditText mUrlEditText;

    /**
     * URL for the image that's downloaded by default if the user
     * doesn't specify otherwise.
     */
    private Uri mDefaultUrl =
        Uri.parse("http://www.dre.vanderbilt.edu/~schmidt/robot.png");

    /**
     * Stores an instance of DownloadHandler.
     */
    private Handler mDownloadHandler = null;

    /**
     * Reference to the request Messenger that's implemented in the
     * DownloadImageService.
     */
    private Messenger mReqMessengerRef = null;

    /**
     * Reference to the ReplyMessenger that's passed to the
     * DownloadImageService and used to process replies from this
     * Service.
     */
    private Messenger mReplyMessenger =
        // Initialize the Messenger to contain a new DownloadHandler.
        // @@ TODO -- you fill in here by replacing null with the
        // proper code.
        null;

    /** 
     * This ServiceConnection is used to receive a Messenger reference
     * after binding to the DownloadImageService using bindService().
     */
    private ServiceConnection mSvcConn = new ServiceConnection() {
            /**
             * Called after the DownloadImageService is connected to
             * convey the result returned from onBind().
             */
            public void onServiceConnected(ComponentName className,
                                           IBinder binder) {
                Log.d(TAG, "onServiceConnected " + className);

                // Create a new Messenger that encapsulates the
                // returned IBinder object and store it for later use
                // in mReqMessengerRef.
                // @@ TODO -- you fill in here.
            }

            /**
             * Called if the Service crashes and is no longer
             * available.  The ServiceConnection will remain bound,
             * but the Service will not respond to any requests.
             */
            public void onServiceDisconnected(ComponentName className) {
                Log.d(TAG, "onServiceDisconnected ");
                mReqMessengerRef = null;
            }
	};

    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * some class scope variable initialization.
     *
     * @param Bundle object that contains saved state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        // @@ TODO -- you fill in here.

        // Set the default layout.
        // @@ TODO -- you fill in here.

        // Cache the EditText that holds the urls entered by the user
        // (if any).
        // @@ TODO -- you fill in here.
    }

    /**
     * Called by the Android Activity framework when the user clicks
     * the "Find Address" button.
     *
     * @param view The view.
     */
    public void downloadImage(View view) {
        try {
            // Hide the keyboard.
            hideKeyboard(this,
                        mUrlEditText.getWindowToken());

            // Create a request Message that indicates the
            // DownloadService should send the reply back to
            // ReplyHandler encapsulated by the Messenger.
            // @@ TODO -- you fill in here.

            if (mReqMessengerRef != null) {
                Log.d(TAG, "sending message");
                // Send the request Message to the DownloadService.
                // @@ TODO -- you fill in here.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A factory method that returns a Message.
     */
    /**
     * @class DownloadHandler
     *
     * @brief An inner class that inherits from Handler and uses its
     *        handleMessage() hook method to process Messages sent from
     *        the DownloadImageService back to the MainActivity.
     */
    private class DownloadHandler extends Handler {
        /**
         * This hook method is dispatched in response to receiving the
         * path to the image file from the DownloadImageService.
         */
        public void handleMessage(Message message) {
            // Extract the path to the image file from the message
            // parameter returned from the DownloadImageService.
            // @@ TODO -- you fill in here.
                
            // Create an Intent that will launch the "Gallery" app by
            // passing in the path to the downloaded image file.
            // @@ TODO -- you fill in here.

            // Start the Gallery Activity.
            // @@ TODO -- you fill in here.
        }
    };

    /**
     * Factory method that returns an Intent for viewing the
     * downloaded image in the Gallery app.
     */
    private static Intent makeGalleryIntent(String pathToImageFile) {
        // Create an intent that will start the Gallery app to view
        // the image.
    	// TODO -- you fill in here, replacing "false" with the proper
    	// code.
    }

    /**
     * Hook method called by Android when this Activity becomes
     * visible.
     */
    @Override
    protected void onStart() {
        super.onStart();

        if (mReqMessengerRef == null) {
            // Create a new intent to an Android Service that can
            // download an image from the URL given by the user.  In
            // this case it's an intent that's implemented by the
            // DownloadImageService.
            // @@ TODO - you fill in here.

            Log.d(TAG, "calling bindService()");

            // Bind to the Service associated with the Intent, which
            // will download the image and then return the Uri for the
            // downloaded image file to the DownloadHandler via the
            // Messenger passed as an "extra" with the Intent.
            // @@ TODO -- you fill in here.
        }
    }

    /**
     * Hook method called by Android when this Activity becomes
     * invisible.
     */
    @Override
    protected void onStop() {
        if (mReqMessengerRef != null) {
            Log.d(TAG, "calling unbindService()");
            // Unbind from the Service.
            // @@ TODO -- you fill in here.

            // Set this field to null to trigger a call to
            // bindService().
            mReqMessengerRef = null;
        }
        super.onStop();
    }

    /**
     * Get the URL to download based on user input.
     */
    protected Uri getUrl() {
        Uri url = null;

        // Get the text the user typed in the edit text (if anything).
        url = Uri.parse(mUrlEditText.getText().toString());

        // If the user didn't provide a URL then use the default.
        String uri = url.toString();
        if (uri == null || uri.equals(""))
            url = mDefaultUrl;

        return url;
    }

    /**
     * This method is used to hide a keyboard after a user has
     * finished typing the url.
     */
    public void hideKeyboard(Activity activity,
                             IBinder windowToken) {
        InputMethodManager mgr =
            (InputMethodManager) activity.getSystemService
            (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken,
                                    0);
    }
}
