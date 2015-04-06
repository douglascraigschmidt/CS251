package edu.vanderbilt.a4_android.ui;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class DrawingArea extends View {	
    /**
     * The width and height of the view.
     */
    int mWidth, mHeight;
    
    /**
     * A proxy for drawing objects to this view. 
     */
    DrawProxy mProxy;
    
    public DrawingArea(Context context) {
        super(context);        
    }
    
    /**
     * Invalidates this view, calling the onDraw method. 
     */
    public void drawUniverse() {
        // TODO: Fill in here, if necessary
    	DrawingArea.this.postInvalidate();
    }

    /**
     * Called each time the view is invalidated.
     */
    protected void onDraw(Canvas canvas) {
    	DrawProxy proxy = new DrawProxy(getContext(), canvas, mWidth / 2, mHeight / 2);
        // TODO: Fill in here, if necessary
    }
    
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO: Fill in here, if necessary
    }
}
