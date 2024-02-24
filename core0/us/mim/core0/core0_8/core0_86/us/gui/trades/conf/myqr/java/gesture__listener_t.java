//===-                           S C R I P T  T.V.
//===-                           https://script.tv
//===-
//===-            Copyright (C) 2017-2024 Script Network
//===-            Copyright (C) 2017-2024 manicberet@gmail.com
//===-
//===-                      GNU GENERAL PUBLIC LICENSE
//===-                       Version 3, 29 June 2007
//===-
//===-    This program is free software: you can redistribute it and/or modify
//===-    it under the terms of the GPLv3 License as published by the Free
//===-    Software Foundation.
//===-
//===-    This program is distributed in the hope that it will be useful,
//===-    but WITHOUT ANY WARRANTY; without even the implied warranty of
//===-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//===-
//===-    You should have received a copy of the General Public License
//===-    along with this program, see LICENCE file.
//===-    see https://www.gnu.org/licenses
//===-
//===----------------------------------------------------------------------------
//===-
package us.cash;
import android.view.GestureDetector.SimpleOnGestureListener;                                   // SimpleOnGestureListener
import android.view.MotionEvent;                                                               // MotionEvent
import android.view.View.OnTouchListener;                                                      // OnTouchListener

public final class gesture__listener_t extends SimpleOnGestureListener {

    private static void log(final String line) {           //--strip
        CFG.log_android("gesture__listener_t: " + line);         //--strip
    }                                                      //--strip

    public gesture__listener_t(swipe_touch__listener_t listener_) {
        listener = listener_;
    }

    @Override public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        log("onFling"); //--strip
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (distanceX > 0)
                listener.on_swipe_right();
            else
                listener.on_swipe_left();
            return true;
        }
        return false;
    }

    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    swipe_touch__listener_t listener;

}

