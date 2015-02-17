package vandy.cs251;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * A bound Service that downloads an image requested via a Message
 * passed to a Messenger, stores the image in a local file on the
 * local device, and returns the image file's URI back to the
 * MainActivity via the Messenger passed with the original Message.
 */
public class DownloadImageService extends Service {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * String constant used to get/put the path to an image in a
     * downloaded image to/from a Bundle.
     */
    private static final String PATHNAME = "PATHNAME";

    /**
     * String constant used to get/put the URL to an image to/from a
     * Bundle.
     */
    private static final String URL = "URL";

    /**
     * A Messenger that encapsulates the RequestHandler used to handle
     * request Messages sent from the MainActivity.
     */
    private Messenger mReqMessenger = null;

    /**
     * Factory method that returns an Intent for downloading an image.
     */
    public static Intent makeIntent() {
        // Create an intent that will download the image from the web.
    	// TODO -- you fill in here, replacing null with the proper
    	// code.
        return null;
    }

    /**
     * Hook method called when the Service is created.
     */
    @Override
    public void onCreate() {
        // A Messenger that encapsulates the RequestHandler used to
        // handle request Messages sent from the
        // UniqueIDGeneratorActivity.
    	// TODO -- you fill in here.
    }

    /**
     * @class RequestHandler
     *
     * @brief This class handles messages sent by the MainActivity.
     */
    private class RequestHandler extends Handler {
        /**
         * Hook method called back when a request message arrives from
         * the MainActivity.  The message it receives contains the
         * Messenger used to reply to the Activity and the URI of the
         * image to download.  This image is stored in a local file on
         * the local device and image file's URI is sent back to the
         * MainActivity via the Messenger passed with the message.
         */
        public void handleMessage(Message request) {
            // Store the reply Messenger so it doesn't change out from
            // underneath us.
            final Messenger replyMessenger = request.replyTo;

            // Get the URL associated with the Intent data.
            final Uri url = getUrl(request); 

            // A Runnable that downloads the image, stores it in a
            // file, and sends the path to the file back to the
            // MainActivity.
            final Runnable downloadImageAndReply = 
                new Runnable() {
                    /**
                     * This method runs in a background Thread.
                     */
                    @Override
                    public void run() {
                        // Download and store the requested image.
                        // @@ TODO -- you fill in here.

                        // Send the path to the image file back to the
                        // MainActivity via the replyMessenger.
                        // @@ TODO -- you fill in here.
                    }
                };
            // Create and start a new Thread to download the image and
            // reply.
            // @@ TODO -- you fill in here.
        }
    }

    /**
     * Factory method that creates a Message containing the @a
     * replyMessenger and @a url parameters.
     */
    public static Message makeRequestMessage(Messenger replyMessenger,
                                             Uri url) {
        // Create a new Message object.
        // @@ TODO -- you fill in here, replacing null with the proper
        // code.
        Message reqMessage = null;

        // Set the replyTo field to the mReplyMessenger.
        // @@ TODO -- you fill in here.

        // Create a new Bundle to handle the result.
        // @@ TODO -- you fill in here.

        // Put the URL to the image file into the Bundle via the URL
        // key.
        // @@ TODO -- you fill in here.

        // Set the Bundle to be the data in the message.
        // @@ TODO -- you fill in here.

        return reqMessage;
    }

    /**
     * Helper method that extracts and returns the URL to the image
     * from a Message.
     */
    public static Uri getUrl(Message message) {
        // Extract the data from Message, which is in the form of a
        // Bundle that can be passed across processes.
        // @@ TODO -- you fill in here.

        // Extract and return the URL to the image from the Bundle,
        // which should be stored using the URL key.
        // @@ TODO -- you fill in here, replacing null with the proper
        // code.
        return null;
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
        // Create a new Message object.
        // @@ TODO -- you fill in here, replacing null with the proper
        // code.
        Message replyMessage = null;

        // Update the arg1 field in the reply message to indicate
        // whether the download succeeded or failed.
        // @@ TODO -- you fill in here.

        // Create a new Bundle to handle the result.
        // @@ TODO -- you fill in here.

        // Put the path to the image file into the Bundle via the
        // PATHNAME key.
        // @@ TODO -- you fill in here.

        // Set the Bundle to be the data in the message.
        // @@ TODO -- you fill in here.

        return replyMessage;
    }

    /**
     * Factory method that returns the underlying IBinder associated
     * with the request Messenger.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mReqMessenger.getBinder();
    }
}
