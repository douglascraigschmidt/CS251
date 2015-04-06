package edu.vanderbilt.a4_android.ui;

import mikera.vectorz.Vector2;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import edu.vanderbilt.a4_android.R;

/** The dialog that will be used to edit entities **/
public class EditEntityDialog extends DialogFragment {

	// The entity this dialog will edit
	Entity mEntity;

	// The edit text holding the position of the entity
	EditText mPositionXET;
	EditText mPositionYET;

	// The edit text holding the velocity of the entity
	EditText mVelocityXET;
	EditText mVelocityYET;

	// Lets the caller set the entity name before showing the entity
	public void setEntityName(String en) throws Exception{
		mEntity = Universe.instance().getEntity(en);
		
		if (mEntity == null)
			throw new Exception();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();		
		final View content = inflater
			.inflate(R.layout.edit_entity_dialog, null);
		
		mPositionXET = (EditText) content.findViewById(R.id.position_x_et);
		mPositionYET = (EditText) content.findViewById(R.id.position_y_et);
		mVelocityXET = (EditText) content.findViewById(R.id.velocity_x_et);
		mVelocityYET = (EditText) content.findViewById(R.id.velocity_y_et);
		
		Vector2 pos = mEntity.getPosition();
		Vector2 vel = mEntity.getVelocity();
		
		// Update text boxes with current values.
		mPositionXET.setText(String.valueOf(pos.getX()));
		mPositionYET.setText(String.valueOf(pos.getY()));
		mVelocityXET.setText(String.valueOf(vel.getX()));
		mVelocityYET.setText(String.valueOf(vel.getY()));
		
		builder.setView(content)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						double positionX = Double.parseDouble(mPositionXET.getText().toString());
						double positionY = Double.parseDouble(mPositionYET.getText().toString());
						double velocityX = Double.parseDouble(mVelocityXET.getText().toString());
						double velocityY = Double.parseDouble(mVelocityYET.getText().toString());
						Entity.Memento mem = mEntity.update();
						mem.setPosition(new Vector2(positionX, positionY));
						mem.setVelocity(new Vector2(velocityX, velocityY));
						mem.apply();
					}
				})
			.setNegativeButton("Cancel",
					   new DialogInterface.OnClickListener() {
						   @Override
						   public void onClick(DialogInterface dialog,
								       int which) {
							   EditEntityDialog.this.getDialog().cancel();
						   }
					   });

		return builder.create();
	}
}
