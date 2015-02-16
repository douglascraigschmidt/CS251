package vandy.cs251;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * An IntentService that downloads an image requested via data in an
 * intent, stores the image in a local file on the local device, and
 * returns the image file's URI back to the MainActivity via the
 * Messenger passed with the intent.
 */
public class DownloadImageService extends IntentService {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * String constant used to extract the Messenger "extra" from an
     * intent.
     */
    private static final String MESSENGER = "MESSENGER";

    /**
     * String constant used to extract the pathname "extra" from an
     * intent.
     */
    private static final String PATHNAME = "PATHNAME";

    /**
     * Constructor initializes superclass.
     */
    public DownloadImageService() {
    	super("DownloadImageService");
    }
    
    /**
     * Factory method that returns an Intent for downloading an image.
     */
    public static Intent makeIntent(Uri url,
                                    Handler downloadHandler) {
        // Create an intent that will download the image from the web.
    	// TODO -- you fill in here, replacing "false" with the proper
    	// code.
        Intent intent = false;

        // Create and put a Messenger as an "extra" to the intent so
        // the DownloadImageService can send the path to the image
        // file back to the MainActivity.
    	// TODO -- you fill in here.

        return intent;
    }

    /**
     * Helper method that returns the path to the image file if it is
     * download successfully.
     */
    public static String getPathname(Message message) {
        // Extract the data from Message, which is in the form of a
        // Bundle that can be passed across processes.
        // @@ TODO -- you fill in here.

        // Extract the path to the image file from the Bundle, which
        // should be stored using the PATHNAME key.
        // @@ TODO -- you fill in here.

        // Check to see if the download succeeded.
        // @@ TODO -- you fill in here.
    }

    /**
     * Hook method dispatched by the IntentService framework to
     * download the image requested via data in an intent, store the
     * image in a local file on the local device, and return the image
     * file's URI back to the MainActivity via the Messenger passed
     * with the intent.
     */
    @Override
    public void onHandleIntent(Intent intent) {

        // Get the URL associated with the Intent data.
        // @@ TODO -- you fill in here.

        // Download the requested image.
        // @@ TODO -- you fill in here.

        // Extract the Messenger stored as an extra in the intent
        // under the key MESSENGER.
        // @@ TODO -- you fill in here.

        // Send the path to the image file back to the MainActivity
        // via the messenger.
        // @@ TODO -- you fill in here.
    }

    /**
     * Send the pathname back to the MainActivity via the
     * messenger.
     */
    private void sendPath(Messenger messenger, 
                          Uri pathToImageFile) {
        // Call the makeReplyMessage() factory method to create
        // Message.
        // @@ TODO -- you fill in here.
        
        try {
            // Send the path to the image file back to the
            // MainActivity.
            // @@ TODO -- you fill in here.
        } catch (RemoteException e) {
            Log.e(getClass().getName(),
                  "Exception while sending reply message back to Activity.",
                  e);
        }
    }

    /**
     * A factory method that creates a Message to return to the
     * MainActivity with the pathname of the downloaded image.
     */
    private Message makeReplyMessage(Uri pathToImageFile){
        Message message = Message.obtain();
        // Return the result to indicate whether the download
        // succeeded or failed.
        // @@ TODO -- you fill in here.

        // Create a new Bundle to handle the result.
        // @@ TODO -- you fill in here.

        // Put the path to the image file into the Bundle via the
        // PATHNAME key.
        // @@ TODO -- you fill in here.

        // Set the Bundle to be the data in the message.
        // @@ TODO -- you fill in here.

        return message;
    }
}
