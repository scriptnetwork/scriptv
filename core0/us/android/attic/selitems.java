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
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import android.widget.AdapterView;                                                             // AdapterView
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import java.util.ArrayList;                                                                    // ArrayList
import android.os.Build;                                                                       // Build
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;                                                                  // Button
import java.util.Collections;                                                                  // Collections
import android.content.Intent;                                                                 // Intent
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import android.widget.TextView;                                                                // TextView
import androidx.appcompat.widget.Toolbar;                                                      // Toolbar
import android.net.Uri;                                                                        // Uri
import android.view.View;                                                                      // View
import us.gov.cash.account_t;
import us.cash.scr.*;                                                                // Context

public class selitems extends activity {

    static void log(final String line) {         //--strip
        CFG.log_android("selitems: " + line);    //--strip
    }                                            //--strip

    //-----------------------------------------
    public void set_listeners(toolbar_t.buttons_t buttons) {
        super.set_listeners(buttons);
        buttons.add_stock_button(R.drawable.ic_cancel, () -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    protected View get_content() {
        list = new list_view_t(ctx, 3);
        return list;
    }
    //-----------------------------------------

/*
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.button.MaterialButton
        android:id="@+id/cancel"
        android:text="@string/cancel"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="10dp"
        android:layout_marginBottom="5dp"
        android:drawableLeft="@drawable/ic_cancel"
        android:paddingLeft="20dp"
        android:drawablePadding="20dp"
        android:textAlignment="textStart"
        android:textColor="@android:color/white"/>

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
*/
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        set_content_layout(R.layout.activity_selitems);

        cancel = findViewById(R.id.cancel);
        Bundle bundle = getIntent().getExtras();
        set_title(bundle.getString("title"));
        String[] sourceshit = bundle.getStringArray("sourceshit");
        shit = new ArrayList<String>();
        Collections.addAll(shit, sourceshit);
        Collections.sort(shit);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shit);

        //lv = (no_scroll_list_view) findViewById(R.id.listview);
        //lv.setAdapter(adapter);
/*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                Intent data = new Intent();
                data.setData(Uri.parse(shit.get(position)));
                setResult(RESULT_OK, data);
                finish();
            }
        });
*/


    }
/*
    void fetch() {
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                log("call_balance"); //--strip
                account_t account = new account_t();
                ko r = a.hmi().rpc_peer.call_merge_accounts(account);
                if (is_ko(r)) {
                    report_ko__worker(r);
                    return;
                }
                on_account_data__worker(account);
            }
        });
        thread.start();
    }

    void on_account_data__worker(final account_t account) {
        app.assert_worker_thread();  //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                on_account_data(account);
            }
        });
    }

    void on_account_data(final account_t account) {
        app.assert_ui_thread();  //--strip
        //final String[] ashit = ans.value.split("\\r?\\n");
    }
*/

    //no_scroll_list_view lv;
    list_view_t list;
    ArrayList<String> shit;
    //private MaterialButton cancel;
}

