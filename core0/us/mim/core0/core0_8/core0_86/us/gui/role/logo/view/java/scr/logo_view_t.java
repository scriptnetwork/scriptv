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
import android.widget.LinearLayout;
import android.view.ViewGroup;                                                                 // ViewGroup
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import us.cash.account_t; //MIM token 'include'
import us.wallet.wallet.timeseries_entry_index_t;
import static us.stdint.*;
import android.widget.ImageView;
import us.wallet.trader.data_t;
import us.cash.trader__conf;
import android.view.View;                                                                      // View
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap

public class logo_view_t extends canvas_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("logo_view_t: " + line);         //--strip
    }                                                      //--strip

    public logo_view_t(Context ctx) {
        super(ctx, 7, 1); 
        banner = new ImageView(ctx); {
            banner.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
            banner.setClickable(true);
            banner.setScaleType(ImageView.ScaleType.FIT_XY);
            banner.setImageResource(R.raw.loading_role_img);
            banner.setVisibility(View.VISIBLE);
        }   
        addView(banner);
    }

    public void init(trader__conf tr_, View.OnClickListener listener_) {
        us.cash.app.assert_ui_thread(); //--strip
        tr = tr_;
        banner.setOnClickListener(listener_);
    }

    void set_bmp(Bitmap bmp_) {
        us.cash.app.assert_ui_thread(); //--strip
        try {
            log("banner is " + banner.getWidth() + " " + banner.getHeight()); //--strip
            bmp = Bitmap.createScaledBitmap(bmp_, banner.getWidth(), banner.getHeight(), false);
            if (bmp != null) {
                banner.setImageBitmap(bmp);
                log("Logo set"); //--strip
                return;
            }
            log("Bitmap is null (2) "); //--strip
        }
        catch (Exception e) {
            log("KO 6997 - Exception " + e.getMessage()); //--strip
            bmp = null;
        }
        banner.setImageResource(R.raw.loading_role_img);
    }
    
    public void on_logo__worker(byte[] bits) {
        us.cash.app.assert_worker_thread(); //--strip
        final Bitmap b = BitmapFactory.decodeByteArray(bits, 0, bits.length);
        if (b == null) {
            log("KO 50059 Bitmap is null " + bits.length); //--strip
            return;
        }
        final logo_view_t self = this;
        tr.runOnUiThread(new Runnable() {
            @Override public void run() {
                self.set_bmp(b);
            }
        });    
    }

    void query_logo() {
        log("querylogo. tries=" + tries); //--strip
        log("query logo"); //--strip
        if (tries > 3) {
            log("tries " + tries + " Giving up retrieving logo"); //--strip
            //Toast.makeText(rf.getActivity().getApplicationContext(), getResources().getString(R.string.givingupretrievinglogo), 6000).show();
            return;
        }
        tries++;
        log("tries " + tries); //--strip
        bmp = null;
        tr.command_trade("request logo");
    }

    void load_logo() {
        log("loadlogo"); //--strip
        if (bmp != null) {
            log("rlogo already loaded"); //--strip
            return;
        }
        tr.request_logo();
    }

    public void on_data(final data_t data) {
        us.cash.app.assert_ui_thread(); //--strip
        if (data == null) {
            bmp = null;
            banner.setImageResource(R.raw.loading_role_img);
            return;            
        }
        boolean backend_haslogo = false;
        {
            String logoval = data.find("logo");
            if (logoval != null) {
                if (logoval.equals("Y")) {
                    backend_haslogo = true;
                }
            }
        }
        log("backend_haslogo " + backend_haslogo); //--strip
        if (!backend_haslogo) {
            log("refresh->querylogo "); //--strip
            tries = 0;
            query_logo(); //order backend to ask to peer for the logo  
            return;
        }
        if (bmp == null) {
            log("refresh->loadlogo"); //--strip
            load_logo();
            return;
        }
        log("bmp != null -> display image"); //--strip
        banner.setImageBitmap(bmp);
    }

    ImageView banner;
    public Bitmap bmp = null;
    trader__conf tr = null;
    int tries = 0;

}

