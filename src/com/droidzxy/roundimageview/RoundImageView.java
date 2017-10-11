/*
 * Copyright (C) 2007 droidzxy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.droidzxy.roundimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView {
	
	private Paint mPaint;
	private Paint mPaintOfBorder;
	
	private boolean mShowBorder;
	private int mBorderColor;
	private float mBorderSize;

	
	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		
		mShowBorder = false;
		mBorderColor = Color.BLACK;
		mBorderSize = 8.0f;
		
		mPaintOfBorder = new Paint();
		mPaintOfBorder.setAntiAlias(true);
		mPaintOfBorder.setColor(mBorderColor);
		mPaintOfBorder.setStyle(Style.STROKE);
		mPaintOfBorder.setStrokeWidth(mBorderSize);

	}


		
	@Override  
    protected void onDraw(Canvas canvas) {
  
        Drawable drawable = getDrawable();  
        if (null != drawable) {
        	
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			
			int width = getWidth();
			int height = getHeight();
			int diameter = width < height ? width : height;
			
			final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
			final Rect rectDst = new Rect(0, 0, width, height);
			
			Bitmap bitmapRounded = Bitmap.createBitmap(getWidth(),  
	                getHeight(), Config.ARGB_8888);  
	        Canvas canvasRounded = new Canvas(bitmapRounded);
	        
	        canvasRounded.drawCircle(width / 2, height / 2, diameter / 2, mPaint);
	        mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	        canvasRounded.drawBitmap(bitmap, rectSrc, rectDst, mPaint);	        
	        mPaint.reset();
	        canvas.drawBitmap(bitmapRounded, rectDst, rectDst, mPaint);
	        
	        if(mShowBorder) {
	        	canvas.drawCircle(width / 2, height / 2, diameter / 2 - mBorderSize / 2, mPaintOfBorder);
	        }
  
        } else {  
            super.onDraw(canvas);  
        }  
    }
	
	public void showBorder(int color) {
		mShowBorder = true;
		mBorderColor = color;

		mPaintOfBorder.setColor(mBorderColor);
	}
	
	public void showBorder(int color, float size) {
		mShowBorder = true;
		mBorderColor = color;
		mBorderSize = size;

		mPaintOfBorder.setColor(mBorderColor);
		mPaintOfBorder.setStrokeWidth(mBorderSize);
	}
	


}
