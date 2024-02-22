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
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.view.Gravity;
import android.graphics.Typeface;
##include##

public class ##classname##__widgets extends ##classname##__base__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("##classname##__widgets: " + s);            //--strip
    }                                                                //--strip

    public ##classname##__widgets() {
        super();
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        papyrus = new canvas_t(ctx, 11, 1); {
            header = new text_view_t(ctx, 1); {
                header.setVisibility(View.GONE);
                header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                header.setGravity(Gravity.CENTER_HORIZONTAL);
                header.setTypeface(null, Typeface.BOLD);
            }
            tabs = new tabs_t(ctx); {
            }
            frame = new frame_t(ctx, 1); {
            }
            papyrus.addView(header);
            papyrus.addView(tabs);
            papyrus.addView(frame);
        }
        return papyrus;
    }

    public void show_header(final String text) {
        header.setText(text);
        header.setVisibility(View.VISIBLE);
    }

    private text_view_t header = null;
    public tabs_t tabs;
    public frame_t frame;

}

