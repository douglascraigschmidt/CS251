package edu.vanderbilt.a4_android.ui;

import mikera.vectorz.Vector2;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import edu.vanderbilt.a4_android.R;

/**
 * @class MainActivity
 * 
 * @brief The main activity of the application. Displays the bouncy balloon
 *        board to the user, along with buttons to add bouncy balloons and clear
 *        the board. The user can also set the durability of barriers that are
 *        created when the user touches the bouncy area.
 */
public class MainActivity extends Activity {
  /**
   * The root layout of our activity.
   */
  LinearLayout mLayout;

  /**
   * The view that shows the balloons and the barriers.
   */
  DrawingArea mDrawingArea;
    
  /**
   * The edit text that holds the timestep for the simulation
   */
  EditText mTimeStepET;
    
  /**
   * The edit text that holds the name of the entity we want to edit.
   */
  EditText mEntityET;
  
  /**
   * Hook method called when the Activity is launched.
   */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Get the views from the framework
    mLayout = (LinearLayout) findViewById(R.id.layout);
    mTimeStepET = (EditText) findViewById(R.id.timestep_et);
    mEntityET = (EditText) findViewById(R.id.entity_et);
        
    mDrawingArea = new DrawingArea(this);

    // Set the layout params appropriately.
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                                     LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

    // Add the area to the linear layout.
    mLayout.addView(mDrawingArea, 0, params);
  }

  public void runSimulation(View v) {
    // TODO - You fill in here to continue doing timesteps until the user presses reset.
    	
    Universe.instance().addEntity(new SimpleEntity("Test 1", 500, new Vector2(0, 0), new Vector2(0,0)));
    Universe.instance().addEntity(new SimpleEntity("Test 2", 500, new Vector2(20, 20), new Vector2(0,0)));
    	
    doStep();
  }
    
  /**
   * Updates the simulation state by one time step.
   */
  private void doStep() {
    // TODO: you fill in here to do one timestep of the simulation.

    // TODO: Draw the updated universe on the screen.
  }

  /**
   * Called when "Do Step" button is pressed.
   */
  public void doStepButton(View v) {
    doStep();
  }
    
  public void reset(View v) {
    // TODO - you fill in here to stop the simulation and reset the simulation state.
  }

  /**
   * Called when the "Edit Entity" button is pressed. Brings up a dialog.
   */
  public void editEntity(View v) {
    EditEntityDialog dialog = new EditEntityDialog();
    try {
      dialog.setEntityName(mEntityET.getText().toString());
    }
    catch (Exception e) {
      Toast.makeText(this, "Invalid entity name", Toast.LENGTH_SHORT).show();
      return;
    }
    	
    dialog.show(getFragmentManager(), "Entity Editor");
  }
    
  /**
   * Returns the value of the text box with the user-specified time step.
   */
  private int getTimeStep() {
    return Integer.parseInt(mTimeStepET.getText().toString());
  }
    
}
