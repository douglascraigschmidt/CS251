package course.examples;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * An Activity that maps a location from an address of a contact.
 */
public class MapLocationFromContactsActivity extends Activity {
    /**
     * Debugging tag used by the Android logger.
     */
    private String TAG = getClass().getSimpleName();

    /**
     * A "code" that identifies the request.
     */
    private static final int PICK_CONTACT_REQUEST = 0;

    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout.
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
     * the "Find Address" button.
     */
    public void findAddress(View v) {
        try {
            // Create a new Intent that matches with the
            // Contacts ContentProvider.
            Intent intent =
                new Intent(Intent.ACTION_PICK,
                           ContactsContract.Contacts.CONTENT_URI);

            // Start the Contacts ContentProvider Activity, which will
            // prompt the user to pick a contact and then return the
            // Uri for the selected contact via the onActivityResult()
            // hook method.
            startActivityForResult(intent,
                                   PICK_CONTACT_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hook method called back by the Android Activity framework when
     * an Activity that's been launched exits, giving the requestCode
     * it was started with, the resultCode it returned, and any
     * additional data from it.
     */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    final Intent data) {
        // Check if the started Activity completed successfully and
        // the request code is what we're expecting.
        if (resultCode == Activity.RESULT_OK
            && requestCode == PICK_CONTACT_REQUEST) {
            // Create a Runnable so the (potentially) long-duration
            // getAddressFromContact() method can run without blocking
            // the UI Thread.
            final Runnable getAndDisplayAddressFromContact =
                new Runnable() {
                    @Override
                    public void run() {
                        // Extract the address from the contact record
                        // indicated by the Uri associated with the
                        // Intent.
                        final String address =
                            getAddressFromContact(data.getData());

                        runOnUiThread(new Runnable() {
                                public void run() {
                                    // Launch the activity by sending
                                    // an intent.  Android will choose
                                    // the right one or let the user
                                    // choose if more than one
                                    // Activity can handle it.

                                    // Create an Intent that will
                                    // launch the "Maps" app.
                                    final Intent geoIntent =
                                        makeGeoIntent(address);
                                        
                                    // Check to see if there's a Map
                                    // app to handle the "geo" intent.
                                    if (geoIntent.resolveActivity
                                        (getPackageManager()) != null) 
                                        startActivity(geoIntent);
                                    else
                                        // Start the Browser app
                                        // instead.
                                        startActivity(makeMapsIntent(address));
                                }
                            });
                    }
                };
            // Create a new Thread to get the address from the contact
            // and launch the appropriate Activity to display the
            // address.
            new Thread(getAndDisplayAddressFromContact).start();
            // BTW, if you don't want to use a separate Thread just
            // say:
            // getAndDisplayAddressFromContact.run();
        }
    }

    /**
     * Extracts a street address from the Uri of the contact in the
     * Contacts Content Provider.
     */
    private String getAddressFromContact(Uri contactUri) {
        // Obtain a reference to our Content Resolver.
        ContentResolver cr = getContentResolver();

        // Obtain a cursor to the appropriate contact at the
        // designated Uri.
        Cursor cursor =
            cr.query(contactUri,
                     null, null, null, null);

        // Start the cursor at the beginning.
        cursor.moveToFirst();

        // Obtain the id of the contact.
        String id = cursor.getString
            (cursor.getColumnIndex(ContactsContract.Contacts._ID));

        // Create an SQL "where" clause that will search for the
        // street address of the designated contact Id.
        String where = ContactsContract.Data.CONTACT_ID + " = ? AND "
            + ContactsContract.Data.MIMETYPE + " = ?";
        String[] whereParameters = new String[] {
            id,
            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE };

        // Create a cursor that contains the results of a query for
        // the street address of the designated contact Id.
        try (Cursor addrCursor 
             = cr.query(ContactsContract.Data.CONTENT_URI,
                        null,
                        where,
                        whereParameters,
                        null)) {
                // Start the cursor at the beginning.
                addrCursor.moveToFirst();

                // Extract the street name.
                String street = addrCursor
                    .getString(addrCursor
                               .getColumnIndex(ContactsContract.
                                               CommonDataKinds.
                                               StructuredPostal.
                                               STREET));
                // Extract the city name.
                String city = addrCursor
                    .getString(addrCursor
                               .getColumnIndex(ContactsContract.
                                               CommonDataKinds.
                                               StructuredPostal.
                                               CITY));
                // Extract the state.
                String state = addrCursor
                    .getString(addrCursor
                               .getColumnIndex(ContactsContract.
                                               CommonDataKinds.
                                               StructuredPostal.
                                               REGION));
                // Extract the zip code.
                String postalCode = addrCursor
                    .getString(addrCursor
                               .getColumnIndex(ContactsContract.
                                               CommonDataKinds.
                                               StructuredPostal.
                                               POSTCODE));
                // Create an address from the various pieces obtained
                // above.
                String address = 
                    street 
                    + "+" 
                    + city 
                    + "+" 
                    + state 
                    + "+" 
                    + postalCode;
 
                // Return the address.
                return address;
            }
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
