package vandy.cs251;

import java.io.File;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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

        // Initialize the downloadHandler.
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

            // Create a new intent to a Service that can download an
            // image from the URL given by the user.  In this case
            // it's an intent that's implemented by the
            // DownloadImageService.
            // @@ TODO - you fill in here.

            // Start the Service associated with the Intent, which
            // will download the image and then return the Uri for the
            // downloaded image file to the DownloadHandler via the
            // Messenger passed as an "extra" with the Intent.
            // @@ TODO -- you fill in here.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        return false;
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
