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
//MIM  ******************************************************************************
//MIM  EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
//MIM  The first version of this file was produced by MIM. You can edit it afterwards.
//MIM
//MIM  Source:
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'classname': 'main_activity'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 49Q7D9GTAjY293RUSQ8MckxzB8BR (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;



public class main_activity__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("main_activity__widgets: " + s);            //--strip
    }                                                                //--strip

    public main_activity__widgets() {
        super();
    }

    //MIM begin token 'create_tree'
    @Override public ViewGroup create_tree(Context ctx) {
        create_papyrus(ctx);
        assert papyrus != null; //--strip
        return papyrus;
    }

    private void create_papyrus(Context ctx) {
        papyrus = new us.cash.scr.canvas_t(ctx, 11, 1); {
            papyrus.setPadding(0, 0, 0, 0);
            Bitmap imageBitmap = BitmapFactory.decodeResource(ctx.getResources(), us.cash.app.a.le.resolve_resid(R.raw.main_tile)); // Load the image from resources (you can replace R.drawable.your_image with the appropriate resource ID)
            BitmapDrawable bitmapDrawable = new BitmapDrawable(ctx.getResources(), imageBitmap); // Create a BitmapDrawable with the image
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT); // Set the tile mode to fill the space
            papyrus.setBackgroundDrawable(bitmapDrawable);
        }
    }


    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

}

