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

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class outer_recycler_view extends RecyclerView {

    private static void log(final String line) {                     //--strip
        CFG.log_android("outer_recycler_view: " + line);             //--strip
    }                                                                //--strip

    public outer_recycler_view(Context context) {
        super(context);
    }

    public outer_recycler_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public outer_recycler_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
/*
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                log("MotionEvent.ACTION_DOWN " + e.getX() + " " + e.getY()); //--strip
                break;
            case MotionEvent.ACTION_MOVE:
                log("MotionEvent.ACTION_MOVE " + e.getX() + " " + e.getY()); //--strip
                break;
            default:
                log("unk action"); //--strip
        }
        boolean s = super.onInterceptTouchEvent(e);
        log("super returned " + s); //--strip
        return s;
*/
    }

}

