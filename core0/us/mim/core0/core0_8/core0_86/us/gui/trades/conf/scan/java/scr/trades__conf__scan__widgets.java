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
//MIM    'classname': 'trades__conf__scan'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: Q1fbxaziUoW8bRCa3GUchKVVCdA (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import android.util.TypedValue;                                                                // TypedValue
import android.view.Gravity;
import android.widget.ImageView;

public class trades__conf__scan__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trades__conf__scan__widgets: " + s);            //--strip
    }                                                                //--strip

    public trades__conf__scan__widgets(View.OnClickListener qr_paste__listener_) {
        super();
        qr_paste__listener = qr_paste__listener_;
    }

    //MIM begin token 'create_tree'
    @Override public ViewGroup create_tree(Context ctx) {
        create_papyrus(ctx);
        assert papyrus != null; //--strip
        return papyrus;
    }

    private void create_papyrus(Context ctx) {
        papyrus = new canvas_t(ctx, 7, 1); { //V
            canvas_t paste_canvas = new canvas_t(ctx, 7, 0); { //H
                qr_paste = new edit_text_t(ctx, 5); {
                    qr_paste.setHint("Scan or paste here.");
                }
                button_t enter = new button_t(ctx, R.raw.enter, qr_paste__listener); {
                }
                paste_canvas.addView(qr_paste);
                paste_canvas.addView(enter);
            }
            scanner = new DecoratedBarcodeView(ctx); { //ZXingScannerView(this); {
            }
            papyrus.addView(paste_canvas);
            papyrus.addView(scanner);
        }
    }


    public canvas_t papyrus = null;
    //MIM end token 'create_tree'
    public edit_text_t qr_paste;
    public DecoratedBarcodeView scanner;
    View.OnClickListener qr_paste__listener;

}

