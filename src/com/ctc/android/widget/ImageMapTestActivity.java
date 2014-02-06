/*
 * Copyright (C) 2011 Scott Lund
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

package com.ctc.android.widget;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;

public class ImageMapTestActivity extends Activity {
	ImageMap mImageMap;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // find the image map in the view
        mImageMap = (ImageMap)findViewById(R.id.map);
	    mImageMap.setImageResource(R.drawable.usamap);
        
        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler()
        {
			@Override
			public void onImageMapClicked(int id, ImageMap imageMap)
			{
				// when the area is tapped, show the name in a 
				// text bubble
				mImageMap.showBubble(id);
			}

			@Override
			public void onBubbleClicked(int id)
			{
				// react to info bubble for area being tapped
			}
		});
    }
}