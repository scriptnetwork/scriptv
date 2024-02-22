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
//MIM    'classname': 'accounts__conf__merged'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 2HEcSF8MxEykZdoxp8mNYR4tnNhM (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue

public class accounts__conf__merged__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("accounts__conf__merged__widgets: " + s);            //--strip
    }                                                                //--strip

    public accounts__conf__merged__widgets(final list_view_t.itemclick_listener_t coins_listener_) {
        super();
        coins_listener = coins_listener_;
    }

    //MIM begin token 'create_tree'
    @Override public ViewGroup create_tree(Context ctx) {
        create_papyrus(ctx);
        assert papyrus != null; //--strip
        scroll = new scroll_view_t(ctx);
        scroll.addView(papyrus);
        return scroll;
    }

    private void create_papyrus(Context ctx) {
        papyrus = new canvas_t(ctx, 10, 1); {
            account_view = new account_view_t(ctx, coins_listener);
            papyrus.addView(account_view);
        }
    }


    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    list_view_t.itemclick_listener_t coins_listener;
    public account_view_t account_view;

/*
import us.gov.cash.account_t; //MIM token 'include'
import android.widget.LinearLayout;                                                            // LinearLayout
import android.widget.ImageView;
import android.util.AttributeSet;                                                              // AttributeSet
import android.view.Gravity;
import android.graphics.Typeface;
import android.widget.Space;                                                                 // Spinner

    public canvas_t locking_program_canvas;
    public text_view_t locking_program;
    public text_view_t system_coin;

        papyrus = new canvas_t(ctx, 10, 1); {
            account_view = new account_view_t(ctx);
            canvas_t top = new canvas_t(ctx, 7, 1); {
                locking_program_canvas = new canvas_t(ctx, 7, 1); { //7=vwrap 1=V
                    canvas_t tit = new canvas_t(ctx, 7, 0); { //7=vwrap 0=H
                        button_t blogo = new button_t(ctx, R.raw.key, null); {
                            //final int sz = us.cash.app.a.le.dp2px(25);
                            //LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(28, 28);
                            //layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                            
                           // blogo.setLayoutParams(layout);
                            //blogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }
                        text_view_t title = new text_view_t(ctx, 0); { //0-wrap
                            title.setText(" Locking program:");
//                            title.setTypeface(null, Typeface.BOLD);
                        }
                        locking_program = new text_view_t(ctx, 5); { //5-hexpand_vwrap
                            locking_program.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                            locking_program.setText("P2PKH");
                        }
                        tit.addView(blogo);
                        tit.addView(title);
                        tit.addView(locking_program);
                    }
//                    text_view_t space = new text_view_t(ctx, 0);
                    locking_program_canvas.addView(tit);
  //                  locking_program_canvas.addView(space);
                }
                canvas_t system_coin_canvas = new canvas_t(ctx, 7, 1); {
                    
                    canvas_t tit = new canvas_t(ctx, 7, 0); {
                        button_t blogo = new button_t(ctx, R.raw.token, null); {
                            //final int sz = us.cash.app.a.le.dp2px(25);
                            //LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(28, 28);
                            //layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                            
                            //blogo.setLayoutParams(layout);
                            //blogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }
                        text_view_t title = new text_view_t(ctx, 0); { //0-wrap
                            title.setText(" System coin (" +  us.CFG.UGAS + "):");
                            //title.setTypeface(null, Typeface.BOLD);
                        }
                        system_coin = new text_view_t(ctx, 5); { //5 fill
                            system_coin.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                        }
                        tit.addView(blogo);
                        tit.addView(title);
                        tit.addView(system_coin);
                    }
                    system_coin_canvas.addView(tit);
                }
                //top.addView(left);
                top.addView(locking_program_canvas);
                top.addView(system_coin_canvas);
            }
            
            papyrus.addView(top, 0);

        }
*/
}

