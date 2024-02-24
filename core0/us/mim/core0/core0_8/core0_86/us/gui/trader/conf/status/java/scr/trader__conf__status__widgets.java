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
//MIM    'classname': 'trader__conf__status'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 3rD7aVRcFcFjvMxiYNGagex8Duy7 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.widget.Switch;                                                                  // Switch
import android.widget.CompoundButton;                                                          // CompoundButton
import android.view.Gravity;

public class trader__conf__status__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trader__conf__status__widgets: " + s);            //--strip
    }                                                                //--strip

    public trader__conf__status__widgets(CompoundButton.OnCheckedChangeListener connect_listener_, View.OnClickListener data_click_listener_, View.OnClickListener log_click_listener_) {
        super();
        connect_listener = connect_listener_;
        data_click_listener = data_click_listener_;
        log_click_listener = log_click_listener_;
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
            canvas_t tid_c = new canvas_t(ctx, 13, 0); {
                text_view_t lbl =  new text_view_t(ctx, 0); {
                    lbl.setText("Trade Id (tid):");
                }
                tid = new text_view_t(ctx, 6);
                tid_c.addView(lbl);
                tid_c.addView(tid);
            }
            canvas_t onoffline_c = new canvas_t(ctx, 13, 0); {
                connect = new Switch(ctx); {
                    connect.setLayoutParams(us.cash.app.a.le.layout_params_hwrap);
//                    connect.setLayoutParams(us.cash.app.a.le.layout_params_hwrap_vexpand);

                    //poweron.setSwitchTextAppearance(ctx, R.style.switch_theme); // set switch theme
                    connect.setText("Connected"); // set empty text
                    connect.setOnCheckedChangeListener(connect_listener);
                    connect.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                }
                canvas_t ind_c = new canvas_t(ctx, 18, 1); {
                    //ind_c.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                    canvas_t drawing_c = new canvas_t(ctx, 13, 0); {
                        drawing_c.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                        button_t w1 = new button_t(ctx, R.raw.wallet, null); {
                        }
                        cable = new button_t(ctx, R.raw.disconnected_cable, null); {
                        }
                        button_t w2 = new button_t(ctx, R.raw.wallet, null); {
                        }
                        drawing_c.addView(w1);
                        drawing_c.addView(cable);
                        drawing_c.addView(w2);
                    }
                    online_time =  new text_view_t(ctx, 1); {
                        online_time.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                        //lbl.setText("");
                    }
                    ind_c.addView(drawing_c);
                    ind_c.addView(online_time);
                }
                onoffline_c.addView(connect);
                onoffline_c.addView(ind_c);
            }
            canvas_t data_c = new canvas_t(ctx, 13, 0); {
                text_view_t lbl =  new text_view_t(ctx, 0); {
                    lbl.setText("data:");
                }
                data = new text_view_t(ctx, 7); {
                }
                button_t data_b = new button_t(ctx, R.raw.refresh, data_click_listener); {
                }
                data_c.addView(lbl);
                data_c.addView(data);
                data_c.addView(data_b);
            }
            canvas_t log_c = new canvas_t(ctx, 13, 0); {
                text_view_t lbl =  new text_view_t(ctx, 0); {
                    lbl.setText("log:");
                }
                log = new text_view_t(ctx, 7); {
                }
                button_t log_b = new button_t(ctx, R.raw.refresh, log_click_listener); {
                }
                log_c.addView(lbl);
                log_c.addView(log);
                log_c.addView(log_b);
            }
            papyrus.addView(tid_c);
            papyrus.addView(onoffline_c);
            papyrus.addView(data_c);
            papyrus.addView(log_c);
        }

    }

   
    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public text_view_t tid;
    public button_t cable;
    public Switch connect;
    public text_view_t online_time;
    public text_view_t data;
    public text_view_t log;

    CompoundButton.OnCheckedChangeListener connect_listener;
    View.OnClickListener data_click_listener;
    View.OnClickListener log_click_listener;

}

