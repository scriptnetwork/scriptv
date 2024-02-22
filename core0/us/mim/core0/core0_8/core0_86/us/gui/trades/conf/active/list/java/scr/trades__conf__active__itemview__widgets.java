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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/java/scr/[classname]__itemview__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/mim.h
//MIM  Params:
//MIM    'classname': 'trades__conf__active'
//MIM    'include': 'import us.gov.crypto...'
//MIM    'itemico': 'R.raw.trade' @ core0/core0_8/core0_86/us/gui/trades/conf/active/mim.h
//MIM  kickoff code hash: 2517PgdKrpQFStnBNDJN5MJJKtXS (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
//MIM begin token 'include'
import us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.trader_brief_t;
import us.wallet.trader.trades_index_t;
//MIM end token 'include'

public class trades__conf__active__itemview__widgets extends list_view__itemview__widgets {

    private static void log(final String s) {                             //--strip
        CFG.log_scr("trades__conf__active__itemview__widgets: " + s);            //--strip
    }                                                                     //--strip


    public static interface listener_t {
        //TODO: customize functions
        void on_confitem_button_click(int pos);
    }

    public trades__conf__active__itemview__widgets(listener_t listener_) {
        listener = listener_;
    }

    //    +-----+------------------------------+-----+
    //    | ico | head                         | btn |
    //    |     |------------------------------+-----+
    //    |     | tail                               |
    //    +-----+------------------------------------+
    @Override public ViewGroup create_tree(Context ctx) {
        super.create_tree(ctx); {
            canvas_t left = new canvas_t(ctx, 12, 1); {
                ico = new button_t(ctx, R.raw.trade, null); {
                    final int sz = us.cash.app.a.le.dp2px(25);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(sz, sz);
                    layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                    ico.setLayoutParams(layout);
                    ico.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                left.addView(ico);
            }
            canvas_t right = new canvas_t(ctx, 13, 1); {
                canvas_t rt = new canvas_t(ctx, 13, 0); {
                    head = new text_view_t(ctx, 5); {
                        head.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size_header);
                    }
                    button_t confitem_button = new button_t(ctx, R.raw.gear, new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            log("on_confitem_button_click"); //--strip
                            if (listener == null) return;
                            listener.on_confitem_button_click(list_view__itemview.getBindingAdapterPosition());
                        }
                    }); {
                    }
                    rt.addView(head);
                    rt.addView(confitem_button);
                }
                canvas_t tail = new canvas_t(ctx, 13, 1); {
                    canvas_t qr_c = new canvas_t(ctx, 13, 0); {
                        text_view_t lbl =  new text_view_t(ctx, 0); {
                            lbl.setText("QR:");
                        }
                        qr = new text_view_t(ctx, 6); {
                            qr.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size);
                        }
                        qr_c.addView(lbl);
                        qr_c.addView(qr);
                    }
                    canvas_t ts_creation_c = new canvas_t(ctx, 13, 0); {
                        text_view_t lbl =  new text_view_t(ctx, 0); {
                            lbl.setText("Created:");
                        }
                        ts_creation = new text_view_t(ctx, 6); {
                            ts_creation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size);
                        }
                        ts_creation_c.addView(lbl);
                        ts_creation_c.addView(ts_creation);
                    }
                    canvas_t ts_updated_c = new canvas_t(ctx, 13, 0); {
                        text_view_t lbl =  new text_view_t(ctx, 0); {
                            lbl.setText("Updated:");
                        }
                        ts_updated = new text_view_t(ctx, 6); {
                            ts_updated.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size);
                        }
                        ts_updated_c.addView(lbl);
                        ts_updated_c.addView(ts_updated);
                    }
                    tail.addView(qr_c);
                    tail.addView(ts_creation_c);
                    tail.addView(ts_updated_c);
                }
                right.addView(rt);
                right.addView(tail);
            }
            papyrus.addView(left);
            papyrus.addView(right);

        }
        return papyrus;
    }

    public button_t ico;
    public text_view_t head;
    public text_view_t qr;
    public text_view_t ts_creation;
    public text_view_t ts_updated;

    listener_t listener;
}
