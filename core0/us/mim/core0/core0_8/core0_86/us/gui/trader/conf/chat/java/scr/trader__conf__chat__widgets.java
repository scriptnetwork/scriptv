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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  Params:
//MIM    'classname': 'trader__conf__chat'
//MIM    'include': 'import static us.wal...'
//MIM  kickoff code hash: 231ZMcPw6nzTrxDUJovH8Hfi13Xm (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
//MIM begin token 'include'
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'
import android.text.InputType;

public class trader__conf__chat__widgets extends trader__conf__chat0__widgets  {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trader__conf__chat__widgets: " + s);            //--strip
    }                                                                //--strip

    public trader__conf__chat__widgets(final list_view_t.itemclick_listener_t list_listener_, View.OnClickListener send_listener_) {
        super();
        listener = list_listener_;
        send_listener = send_listener_;
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert papyrus == null;
        papyrus = new canvas_t(ctx, 10, 1); {
            empty_list = new text_view_t(ctx, 1); {
                empty_list.setText("Empty");
            }
            canvas_t list_c = new canvas_t(ctx, 17, 1); {
                list = new list_view_t(ctx, 4, listener, empty_list);
                list_c.addView(list); 
            }
            canvas_t input_c = new canvas_t(ctx, 13, 0); {
                input = new edit_text_t(ctx, 5); {
                    input.setHint("Type message ..."); // Set the hint text
                    input.setVerticalScrollBarEnabled(true); // Enable vertical scrollbars
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE); // Set input type
                    input.setMaxLines(Integer.MAX_VALUE); // Set the maximum lines to Integer.MAX_VALUE (unlimited)
                }
                button_t send_button = new button_t(ctx, R.raw.send, send_listener);
                input_c.addView(input);
                input_c.addView(send_button);
            }
            papyrus.addView(empty_list);
            papyrus.addView(list_c);
            papyrus.addView(input_c);
        }
        return papyrus;
    }

    public list_view_t list;
    private text_view_t empty_list;
    public edit_text_t input;
    private list_view_t.itemclick_listener_t listener;
    private View.OnClickListener send_listener;

}

