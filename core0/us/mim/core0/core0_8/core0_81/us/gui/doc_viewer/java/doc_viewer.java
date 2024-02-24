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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'apifetch': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'doc_viewer'
//MIM    'controller': 'activity' @ core0/core0_8/core0_81/us/gui/doc_viewer/mim.h
//MIM    'datatype_decl': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': 'protected void on_me...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Doc Viewer' @ core0/core0_8/core0_81/us/gui/doc_viewer/mim.h
//MIM  kickoff code hash: 37zRBSNTGZChUqpmdqyTzJFrWz9G (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import java.util.ArrayList;                                                                    // ArrayList
import java.util.concurrent.atomic.AtomicInteger;                                              // AtomicInteger
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import android.os.Build;                                                                       // Build
import android.os.Bundle;                                                                      // Bundle
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import static android.graphics.BitmapFactory.decodeResource;                                   // decodeResource
import android.os.Environment;                                                                 // Environment
import java.io.File;                                                                           // File
import java.io.FileNotFoundException;                                                          // FileNotFoundException
import java.io.FileOutputStream;                                                               // FileOutputStream
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.io.types.*;                                                               // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import java.io.IOException;                                                                    // IOException
import us.ko;                                                                                  // ko
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import androidx.core.app.NotificationCompat;                                                   // NotificationCompat
import androidx.core.app.NotificationManagerCompat;                                            // NotificationManagerCompat
import us.pair;                                                                                // pair
import android.app.PendingIntent;                                                              // PendingIntent
import us.wallet.protocol;                                                                     // protocol
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import us.string;                                                                              // string
import androidx.core.app.TaskStackBuilder;                                                     // TaskStackBuilder
import android.widget.TextView;                                                                // TextView
import android.widget.EditText;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import androidx.appcompat.widget.Toolbar;                                                      // Toolbar
import android.net.Uri;                                                                        // Uri
import android.view.View;                                                                      // View
import android.view.inputmethod.InputMethodManager;

public final class doc_viewer extends activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("doc_viewer: " + line);         //--strip
    }                                                      //--strip

    public doc_viewer() {
        super();
        log("doc_viewer constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.doc_viewer__widgets) super.w;
        assert w != null; //--strip
        from_intent();
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        hide_keyboard();
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        if (mode == 1) {
            show_keyboard(w.content_rw);
        }
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return title;
    }


    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        if (mode != 0) return ok; //new doc
        log("contentcommand=" + contentcommand); //--strip
        if (tid == null || tid.is_zero()) {
            if (contentcommand == null || contentcommand.isEmpty()) {
                if (nft != null) {
                    string content_ = new string();
                    hash_t nft_ = new hash_t(nft);
                    ko r = a.hmi().rpc_peer.call_cert_show(nft_, content_);
                    if (is_ko(r)) {
                        log(r.msg); //--strip
                        return r;
                    }
                    content = content_.value;
                }
                else {
                    content = "";
                }
            }
        }
        else {
            a.hmi().command_trade(tid, contentcommand);
        }
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        if (is_ko(load_result)) {
            toast(load_result.msg);
            w.content_rw.setVisibility(View.GONE);
            w.content_ro.setVisibility(View.GONE);
            return;
        }
        if (mode == 0) {
            w.content_rw.setVisibility(View.GONE);
            w.content_ro.setVisibility(View.VISIBLE);
            w.content_ro.setText(content);
        }
        else {
            w.content_rw.setVisibility(View.VISIBLE);
            w.content_ro.setVisibility(View.GONE);
        }
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        us.cash.scr.menu_t m;
        if (mode == 0) {
            m = us.cash.scr.doc_viewer__menu___reader_t.get_instance(this);
            m.header.tail.setText(fname);
        }
        else {
            m = us.cash.scr.doc_viewer__menu___editor_t.get_instance(this);
        }
        return m;
    }
    //MIM end token 'get_menu_override'

    //MIM begin token 'on_menu__handling'
    protected void on_menu__reader__copy() { //Copy to clipboard
        log("on_menu__reader__copy"); //--strip
        a.set_clipboard(w.content_ro.getText().toString(), w.title.getText().toString());
    }

    protected void on_menu__editor__enter() { //Sign, save & close
        log("on_menu__editor__enter"); //--strip
        doaction();
    }


    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.copy) { //Copy to clipboard
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__reader__copy();
                    }
                }, 100);
        }
        else if (id == R.raw.enter) { //Sign, save & close
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__editor__enter();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }
    //MIM end token 'on_menu__handling'

    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.doc_viewer__widgets();
    }

    void from_intent() {
        Intent intent = getIntent();
        if (intent == null) {
            log("KO 60959 Invalid intent"); //--strip
            finish();
            return;
        }
        if (!intent.hasExtra("tid")) {
            finish();
            return;
        }
        tid = (hash_t) intent.getSerializableExtra("tid");
        Bundle bundle = intent.getExtras();
        actioncommand = bundle.getString("action_cmd");
        contentcommand = bundle.getString("fetch_content_cmd");
        fname = bundle.getString("fname");
        action_caption = bundle.getString("action_caption", "Send");
        icon = bundle.getInt("icon");
        mode = bundle.getInt("mode", 0); //0 viewer; 1 input
        log("mode " + mode);
        title = bundle.getString("title", "");
        if (title.equals("")) {
            if (mode ==1) {
                title = "Doc Editor";
            }
            else {
                title = "Doc Viewer";
            }
        }
        if (contentcommand == null || contentcommand.isEmpty()) {
            nft = (String) getIntent().getSerializableExtra("nft");
        }
    }
 
    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        super.on_push(target_tid, code, payload);
        if (!tid.equals(target_tid)) return;
        switch (code.value) {
            case us.wallet.trader.workflow.trader_protocol.push_doc: {
                string s = new string();
                final ko r = blob_reader_t.parse(new blob_t(payload), s);
                if (is_ko(r)) {
                    log(r.msg); //--strip
                    return;
                }
                content = s.value;
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        on_ready(r);
                    }
                });
            }
        }
    }

    void on_workflow_item(String payload) {
        log("on_workflow_item" + payload); //--strip
    }

    void doaction() {
        app.assert_ui_thread(); //--strip
        log("doaction"); //--strip
        Intent data = new Intent();
        data.putExtra("actioncommand", actioncommand);
        String s = w.content_rw.getText().toString();
        data.putExtra("msg", "" + s.trim());
        setResult(RESULT_OK, data);
        finish();
    }


    public static class actions_helper {

        public static enum attachment_type { IMAGE, AUDIO, VIDEO, WORD, EXCEL, POWERPOINT, TXT }

        public static Intent open_file(Context context, File file) {
            String fileName = file.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String type = "application/" + extension;
            attachment_type atype = get_attachment_type(fileName);
            if (atype == attachment_type.IMAGE) {
                type = "image/*";
            }
            else if (atype == attachment_type.AUDIO) {
                type = "audio/*";
            }
            else if (atype == attachment_type.VIDEO) {
                type = "video/*";
            }
            else if (atype == attachment_type.WORD) {
                type = "application/msword";
            }
            else if (atype == attachment_type.EXCEL) {
                type = "application/vnd.ms-excel";
            }
            else if (atype == attachment_type.POWERPOINT) {
                type = "application/vnd.ms-powerpoint";
            }
            else if (atype == attachment_type.TXT) {
                type = "text/plain";
            }
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent chooser = Intent.createChooser(intent, "Open with");
            intent.setDataAndType(path, type);
            return chooser;
        }

        public static attachment_type get_attachment_type(String filename) {
            String ext =  filename.substring(filename.lastIndexOf("."));
            switch (ext){
                case "jpg":
                case "png":
                case "gif":
                    return attachment_type.IMAGE;
                case "mp4":
                case "mov":
                case "avi":
                    return attachment_type.VIDEO;
                case "wav":
                case "mp3":
                    return attachment_type.AUDIO;
                case "doc":
                case "docx":
                    return attachment_type.WORD;
                case "xls":
                    return attachment_type.EXCEL;
                case "txt":
                    return attachment_type.TXT;
            }
            return attachment_type.TXT;
        }
    }

    private void show_notification(File file) {
        Intent resultIntent = actions_helper.open_file(this, file);
        if (resultIntent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        }
        String notifTitle = getResources().getString(R.string.downloadedfilefrom)+" " + this.getResources().getString(R.string.app_name);
        String notifBody = getResources().getString(R.string.youhavenewdownloadedfile)+" " + file.getAbsolutePath();
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_download_24dp)
            .setContentTitle(notifTitle)
            .setContentText(notifTitle)
            .setStyle(new NotificationCompat.BigTextStyle().bigText(notifBody))
            .setContentIntent(resultPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        AtomicInteger c = new AtomicInteger(0);
        int NOTIFICATION_ID = c.incrementAndGet();
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    void save(View v) {
        String text =  w.content_ro.getText().toString();
        FileOutputStream fos = null;
        String filename = System.currentTimeMillis() + "_" +  this.getResources().getString(R.string.app_name).replace(" ","") + "_" + fname + ".txt";
        File downpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        try {
            File f = new File(downpath, filename);
            fos = new FileOutputStream(f);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to " + downpath + "/" + filename, Toast.LENGTH_LONG).show();
            show_notification(f);
        }
        catch (FileNotFoundException e) {
            log("KO 58488 File not found.");  //--strip
        }
        catch (IOException e) {
            log("KO 58489 ");  //--strip
        }
        finally {
            if (fos != null) {
                try{
                    fos.close();
                }
                catch (IOException e) {
                    log("KO 58490 ");
                }
            }
        }
    }


    private static final String CHANNEL_ID = "app_notify_channel0";

    hash_t tid;
    String actioncommand;
    String contentcommand;
    String fname;
    String nft = null;
    String command;
    String title;
    uint16_t doccode = new uint16_t(0);
    int icon = -1;
    int mode = 0; //0 viewer; 1 input
    String action_caption = "";
    String content = "";

    us.cash.scr.doc_viewer__widgets w = null;
}

