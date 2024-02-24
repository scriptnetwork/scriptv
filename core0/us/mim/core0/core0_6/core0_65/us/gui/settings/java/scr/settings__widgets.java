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
//MIM    'classname': 'settings'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 47BksfjD9deCoxansXVrPn7yxXws (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.widget.Spinner;
import android.content.res.Resources;                                                          // Resources
import android.graphics.Color;                                                                 // Color
import android.widget.TextView;
import android.view.Gravity;


public class settings__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("settings__widgets: " + s);            //--strip
    }                                                                //--strip

    public settings__widgets() {
        super();
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
            {
                canvas_t lang = new canvas_t(ctx, 2, 0); { //H
                    text_view_t label = new text_view_t(ctx, 0); {
                        label.set_text(R.string.select_language);
                    }
                    lang_dropdown = new spinner_t(ctx, 1); {
                        //spinner.getBackground().setTint(Color.BLACK);
                        //spinner.setSpinnerMode(Spinner.MODE_DROPDOWN);
                        lang_dropdown.setPrompt(ctx.getString(R.string.select_language));
                        //TextView hintTextView = new TextView(ctx);
                        //hintTextView.setText(ctx.getString(R.string.select_language));
                        //hintTextView.setTextColor(Color.GRAY);
                        //lang_dropdown.setPromptView(hintTextView);
                        lang_dropdown.setTooltipText(ctx.getString(R.string.select_language));
        //                spinner.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        lang_dropdown.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        
                    }
                    lang.addView(label);
                    lang.addView(lang_dropdown);

                }
                papyrus.addView(lang);
            }
            {
                canvas_t dm = new canvas_t(ctx, 2, 0); { //H
                    text_view_t label = new text_view_t(ctx, 0); {
                        label.set_text("Dark mode");
                    }
                    dm_dropdown = new spinner_t(ctx, 1); {
                        String x = "Darl/Light mode";
                        dm_dropdown.setPrompt("Select " + x);
                        //TextView hintTextView = new TextView(ctx);
                        //hintTextView.setText("Select " + x);
                        dm_dropdown.setTooltipText("Select " + x);
                        dm_dropdown.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        
                    }
                    dm.addView(label);
                    dm.addView(dm_dropdown);
                }
                papyrus.addView(dm);
            }
            {
                canvas_t expertise = new canvas_t(ctx, 2, 0); { //H
                    text_view_t label = new text_view_t(ctx, 0); {
                        label.set_text("User expertise level");
                    }
                    expertise_dropdown = new spinner_t(ctx, 1); {
                        String x = "User expertise level";
                        expertise_dropdown.setPrompt("Select " + x);
                        //TextView hintTextView = new TextView(ctx);
                        //hintTextView.setText("Select " + x);
                        expertise_dropdown.setTooltipText("Select " + x);
                        expertise_dropdown.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        
                    }
                    expertise.addView(label);
                    expertise.addView(expertise_dropdown);
                }
                papyrus.addView(expertise);
            }
        }
    }


    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public spinner_t lang_dropdown;
    public spinner_t dm_dropdown;
    public spinner_t expertise_dropdown;
}

