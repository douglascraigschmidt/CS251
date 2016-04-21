package vandy.mooc.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Samuel Lijin on 4/20/2016.
 *
 * Code derived from answer at http://stackoverflow.com/a/33164852/3577414.
 */
public class GetPermissions {
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static boolean hasPermission;

    public static void requestPermissions(Context activity) {
        if (hasPermission) return;
        
        Log.d("GetPermissions", "requesting permissions");
        // check if write_external_storage permissions have been granted
        hasPermission = (
                ContextCompat.checkSelfPermission(activity,
                                                  Manifest
                                                      .permission
                                                      .WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        );

        Activity parentActivity = (Activity) activity;

        // request permissions if they have not been granted
        if (!hasPermission) {
            ActivityCompat.requestPermissions(
                    parentActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE
            );
        }

    }

    // some callback - currently unused
    public static void onRequestPermissionsResult(Context parentActivity,
                                                  int requestCode,
                                                  String[] permissions,
                                                  int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use
                    // the features what required the permission
                }
                else
                {
                    Toast.makeText(parentActivity, "The app was not allowed " +
                            "to write to your storage. Hence, it cannot " +
                            "function properly. Please consider granting it " +
                            "this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
