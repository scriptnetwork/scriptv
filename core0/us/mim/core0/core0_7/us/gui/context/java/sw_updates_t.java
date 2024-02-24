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
import androidx.core.app.ActivityCompat;                                                       // ActivityCompat
import android.app.Activity;                                                                   // Activity
import android.app.AlertDialog;                                                                // AlertDialog
import java.util.Arrays;                                                                       // Arrays
import android.os.Build;                                                                       // Build
import java.io.ByteArrayOutputStream;                                                          // ByteArrayOutputStream
import androidx.core.content.ContextCompat;                                                    // ContextCompat
import android.content.Context;                                                                // Context
import android.content.DialogInterface;                                                        // DialogInterface
import java.io.File;                                                                           // File
import java.io.FileOutputStream;                                                               // FileOutputStream
import androidx.core.content.FileProvider;                                                     // FileProvider
import static android.Manifest.permission.*;                                                   // *
import android.content.Intent;                                                                 // Intent
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import android.Manifest;                                                                       // Manifest
import androidx.annotation.NonNull;                                                            // NonNull
import static us.ko.ok;                                                                        // ok
import android.content.pm.PackageManager;                                                      // PackageManager
import us.pair;                                                                                // pair
import android.provider.Settings;                                                              // Settings
import us.string;                                                                              // string
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import android.widget.Toast;                                                                   // Toast
import android.net.Uri;                                                                        // Uri
import java.time.Instant;
import java.time.Duration;

public class sw_updates_t {

    private static void log(final String line) {  //--strip
        CFG.log_android("sw_updates: " + line);   //--strip
    }                                             //--strip

    public sw_updates_t(hmi_t hmi_, cfg_android_private_t cfg_private_, cfg_android_public_t cfg_public_) {
        hmi = hmi_;
        cfg_private = cfg_private_;
        cfg_public = cfg_public_;
        clean_update();
        log("swversion " + us.vcs.version_name); //--strip
    }

    public void forget_curapk() {
        //curapk = "";
        clean_update();
    }

    void clean_update() {
        cfg_private.delete_file("newapk_content");
        log("deleted file newapk_content"); //--strip
    }

    public boolean verify_permissions(String[] permissions) {        // Check if we have write permission
        for (String str : permissions) {
            if (ContextCompat.checkSelfPermission(a.getApplicationContext(), str) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void ask_permission(int req_code, String[] permissions, String rationale) {
        if (a.main == null) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(a.main, permissions[0])) {
            a.new_dlg_builder(a.main).setTitle("Info about automatic updates.")
                .setMessage(rationale)
                .setIcon(android.R.drawable.ic_dialog_info).show();
        }
        log("request Permission"); //--strip
        ActivityCompat.requestPermissions(a.main, permissions, req_code);
    }

    ko download_apk() {
        log("download_apk"); //--strip
        a.assert_worker_thread(); //--strip
        if (!verify_permissions(PERMISSIONS_STORAGE)) {
            if (a.main != null) {
                a.main.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        ask_permission(REQUEST_EXTERNAL_STORAGE, PERMISSIONS_STORAGE, "Please Grant access to external storage. This is needed for temporary storage of apk files (software updates).");
                    }
                });
            }
            log(KO_60599.msg); //--strip
            return KO_60599;
        }
        if (!cfg_public.is_external_storage_writable()) {
            ko r = new ko("KO 78695 External storage is not writable");
            log(r.msg); //--strip
            return r;
        }
        log("Checking for updates."); //--strip
        log("get_updat. curapk " + curapk); //--strip
        if (hmi == null || hmi.rpc_peer == null) {
            ko r0 = new ko("KO 60069 HMI is not active. Aborting download sw-updates.");
            log(r0.msg); //--strip
            return r0;
        }
        string apkfilename = new string(us.vcs.apkfilename());
        log("get_component_update android " + curapk + " blob_id: " + CFG.blob_id); //--strip
        us.wallet.cli.rpc_peer_t.get_component_update_in_t o_in = new us.wallet.cli.rpc_peer_t.get_component_update_in_t(new string(CFG.blob_id), new string("android"), apkfilename);
        us.wallet.cli.rpc_peer_t.get_component_update_out_dst_t o_out = new us.wallet.cli.rpc_peer_t.get_component_update_out_dst_t();
        {
            ko r = hmi.rpc_peer.call_get_component_update(o_in, o_out);
            if (is_ko(r)) {
                log(hmi.rewrite(r)); //--strip
                return r;
            }
        }
        if (o_out.vcsname.value.isEmpty()) {
            ko r = new ko("KO 10992 Unexpected empty filename.");
            log(r.msg); //--strip
            return r;
        }
        if (o_out.vcsname.value.equals(apkfilename.value)) {
            log("Same version."); //--strip
            //is_updateavailable = false;
            return new ko("KO 78868 No newer update blob.");
        }

        if (o_out.bin_pkg.value == null) {
            ko r = new ko("KO 10993 file is null.");
            log(r.msg); //--strip
            return r;
        }
        if (o_out.bin_pkg.value.length < 1000000) {
            ko r = new ko("KO 10994 Suspicious length suggests truncated file." + o_out.bin_pkg.value.length);
            log(r.msg); //--strip
            return r;
        }
        synchronized (cfg_android_public_t.downloads_dir_lock_t) {
            log("Saving content to newapk_content. size=" + o_out.bin_pkg.value.length + " bytes."); //--strip
            ko r = cfg_public.write_public_file("newapk_content", o_out.bin_pkg.value);
            if (is_ko(r)) {
                return r;
            }
        }
        return ok;
    }

    void check(boolean informok) {
        a.assert_worker_thread(); //--strip
        log("check"); //--strip
        a.main.runOnUiThread(new Runnable() {
            @Override public void run() {
                ask_install();
            }
        });
    }

    public boolean need_install_local_apk() {
        log("wallet::need_install_local_apk()?"); //--strip
        if (CFG.appstore_edition == 1) {
            log("CFG.appstore_edition"); //--strip
            return false;
        }
        if (!cfg_public.public_file_exists("newapk_content")) {
            log("newapk_content doesn't exist."); //--strip
            return false;
        }
        log("found newapk_content. Yes, we need to install it."); //--strip
        return true;
    }

    public void ask_install() {
        log("ask_install"); //--strip
        a.assert_ui_thread(); //--strip
        if (a.active_ac == null) {
            log("active_ac is null"); //--strip
            return;
        }
        String[] options = {"Install now.", "Remind me later."};
        final sw_updates_t i = this;
        a.new_dlg_builder(a.active_ac).setTitle("An update of the app " + CFG.app_name + " is ready to install...")
            .setItems(options, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    switch(which) {
                        case 0:
                            i.do_inst_ask_permission(a.active_ac);
                            break;
                        case 1:
                            user_selected_remind_me_later = Instant.now();
                            break;
                    }
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public ko do_inst_local(activity0 ac) {
        log("do_inst_local"); //--strip
        a.assert_ui_thread(); //--strip
        {
            ko r = download_apk();
            if (is_ko(r)) { //retry in 
                return r;
            }
        }
        if (!need_install_local_apk()) {
            a.toast(a.getResources().getString(R.string.noupgradepackages));
            return new ko("KO 59588 apk not in disk");
        }
        File f = cfg_public.public_file("newapk_content");
        if (f == null) {
            ko r = new ko("KO 30029 newapk_content doesn't open.");
            log(r.msg + " " + curapk); //--strip
            return r;
        }
        log("Installing file length=" + f.length() + " bytes. " + f.getAbsolutePath()); //--strip
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(FileProvider.getUriForFile(a.getApplicationContext(), a.getApplicationContext().getPackageName()+".provider", f), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            ac.startActivity(intent);
        }
        catch (Exception e) {
            log("KO 60508 Ex. " + e.getMessage()); //--strip
        }
        log("Intent started"); //--strip
        return ok;
    }

    public void do_inst_ask_permission(activity0 ac) {
        log("do_inst_ask_permission");  //--strip
        a.assert_ui_thread(); //--strip
        if (CFG.appstore_edition == 1) {
            final String appPackageName = ac.getPackageName(); // getPackageName() from Context or Activity object
            try {
                ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
            return;
        }
        int n = 5;
        while (true) {
            if (--n == 0) break;
            if (a.getPackageManager().canRequestPackageInstalls()) {
                ko r = do_inst_local(ac);
                if (r != KO_60599) { //retry in 
                    break;
                }
            }
            else {
                log("B");  //--strip
                Intent i = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).setData(Uri.parse(String.format("package:%s", a.getPackageName())));
                ac.startActivityForResult(i, activity.INSTALL_PACKAGES_REQUESTCODE);
                return;
            }

            try {
                Thread.sleep(5000);
            }
            catch(InterruptedException e) {
            }
            log("Probl with permissions. Trying again in 5 secs"); //--strip
            continue;
        }
    }

    void check4updates() {
        a.assert_ui_thread(); //--strip
        a.toast("Checking for updates...");
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                check(true);
            }
        });
        thread.start();
    }
    void check4updates__worker() {
        log("check4updates__worker"); //--strip
        new Exception().printStackTrace();
        a.assert_worker_thread(); //--strip
        Thread thread = new Thread(new Runnable() { //keep runninng the calling thread (work dispatcher)
            @Override public void run() {
                check(true);
            }
        });
        thread.start();
    }

    public void show_ui(final Context ctx) {
        a.assert_ui_thread(); //--strip
        String[] options;
        String[] opt = {a.getResources().getString(R.string.checkforupdates), a.getResources().getString(R.string.cancel)};
        options = opt;
        final sw_updates_t i = this;
        a.new_dlg_builder(ctx).setTitle(a.getResources().getString(R.string.appupdates))
            .setItems(options, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    switch(which) {
                        case 0:
                            i.check4updates();
                            break;
                    }
                }
            }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    static ko KO_60599 = new ko("KO 60599 Permissions problem.");
    static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    static final int REQUEST_EXTERNAL_STORAGE = 1832;

    String curapk = "";
    hmi_t hmi;
    cfg_android_private_t cfg_private;
    cfg_android_public_t cfg_public;
    Instant user_selected_remind_me_later = null;
    static app a;
}

