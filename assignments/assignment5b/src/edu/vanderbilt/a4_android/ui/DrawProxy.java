package edu.vanderbilt.a4_android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import edu.vanderbilt.a4_android.R;


public class DrawProxy {
	
  /**
   * A Canvas to draw to.
   */
  private Canvas mCanvas;

  /**
   * The center of the screen that we're drawing.
   */
  private int mCenterX, mCenterY;

  /**
   * The paint settings we'll use to draw them.
   */
  private Paint mBlackPaint, mWhitePaint;

  /**
   * The bitmap we'll use to draw movable objects.
   */
  Bitmap mMovableImage;

  /**
   * The bitmap we'll use to draw immovable objects.
   */
  Bitmap mImmovableImage;
	
  Rect mReusableRect = new Rect();

  public DrawProxy (Context context, Canvas mCanvas, int mCenterX, int mCenterY) {
    // Provided, do not edit.
    this.mCanvas = mCanvas;
    this.mCenterX = mCenterX;
    this.mCenterY = mCenterY;
		
    // Create the paint settings
    mBlackPaint = new Paint();
    mBlackPaint.setColor(Color.BLACK);
    mWhitePaint = new Paint();
    mWhitePaint.setColor(Color.WHITE);
    mWhitePaint.setTextSize(15);
    mWhitePaint.setTextAlign(Align.CENTER);

    // Get the bitmap from the drawable
    BitmapFactory.Options ops = new BitmapFactory.Options();
    mMovableImage = BitmapFactory.decodeResource(context.getResources(),
                                                 R.drawable.movable_object,
                                                 ops);
    mImmovableImage = BitmapFactory.decodeResource(context.getResources(),
                                                   R.drawable.immovable_object,
                                                   ops);
    mMovableImage.setDensity(Bitmap.DENSITY_NONE);
    mImmovableImage.setDensity(Bitmap.DENSITY_NONE);
  }

  /** Draws the entity on screen. */
  private void draw (Entity ent, Bitmap toDraw) {

    int x = (int) ent.getPosition().getX();
    int y = (int) ent.getPosition().getY();
  	
    // For now, all entities are the same size
    int r = 10;
  	
    mReusableRect.set(x - r + mCenterX, y - r + mCenterY, x + r + mCenterX, y + r + mCenterY);
    mCanvas.drawBitmap(toDraw, null, mReusableRect, mWhitePaint);
    mCanvas.drawText(ent.getName(), x + mCenterX, y + mCenterY, mWhitePaint);
  }

  public void draw (SimpleEntity ent) {
    // TODO: Fill in here, if necessary
  }

  public void draw (ImmobileEntity ent) {
    // TODO: Fill in here, if necessary
  }

  public void draw (AggregateEntity ent) {
    // TODO: Fill in here, if necessary
  }
}
