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
import java.util.ArrayList;                                                                    // ArrayList
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.types.blob_t;                                                                 // blob_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import java.io.BufferedInputStream;                                                            // BufferedInputStream
import java.io.BufferedReader;                                                                 // BufferedReader
import java.util.Collections;                                                                  // Collections
import java.util.Comparator;                                                                   // Comparator
import android.content.Context;                                                                // Context
import java.util.Date;                                                                         // Date
import us.gov.crypto.ec;                                                                       // ec
import java.io.FileNotFoundException;                                                          // FileNotFoundException
import static us.gov.id.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import us.stdint.*;                                                                            // *
import java.io.InputStream;                                                                    // InputStream
import java.io.InputStreamReader;                                                              // InputStreamReader
import java.io.IOException;                                                                    // IOException
import us.wallet.engine.ip4_endpoint_t;                                                        // ip4_endpoint_t
import static us.ko.is_ko;                                                                     // is_ko
import org.json.JSONArray;                                                                     // JSONArray
import org.json.JSONException;                                                                 // JSONException
import org.json.JSONObject;                                                                    // JSONObject
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import java.io.OutputStream;                                                                   // OutputStream
import java.io.OutputStreamWriter;                                                             // OutputStreamWriter
import us.pair;                                                                                // pair
import us.gov.io.types.blob_t.serid_t;                                                         // serid_t
import us.string;                                                                              // string
import static us.gov.io.types.blob_t.version_t;                                                // version_t
import us.wallet.engine.wallet_connections_t;                                                  // wallet_connections_t
import us.wallet.engine.wallet_connection_t;                                                   // wallet_connection_t
import us.gov.socket.busyled_t;                                                                // busyled_t

public final class device_endpoints_t extends ArrayList<device_endpoint_t> implements us.gov.io.seriable {

    static final String device_endpoints_file = "device_endpoints";

    private static void log(final String line) {                         //--strip
        CFG.log_android("device_endpoints_t: " + line);                  //--strip
    }                                                                    //--strip

    public static interface monitor_handler_t {
        void on_hmi__worker(final device_endpoint_t src, final ko status);
        void on_status(final device_endpoint_t src, int led_status, final String msg);
        void confirmed_subhome(final device_endpoint_t src, final String subhome__backend_choice);
        void on_send(boolean busy);
        void on_recv(boolean busy);
    }

    private static class walletd_led_handler implements busyled_t.handler_t {

        walletd_led_handler(device_endpoints_t deps_, final int i_) {
            deps = deps_;
            i = i_;
        }

        @Override public void on_busy() {
            if (i == 0) {
                deps.monitor_handlers.on_send(true);
                return;
            }
            deps.monitor_handlers.on_recv(true);
        }

        @Override public void on_idle() {
            if (i == 0) {
                deps.monitor_handlers.on_send(false);
                return;
            }
            deps.monitor_handlers.on_recv(false);
        }

        int i;
        device_endpoints_t deps;
    }

    private static class walletd_status_handler_t implements hmi_t.status_handler_t {

        public walletd_status_handler_t(device_endpoints_t deps_) {
            deps = deps_;
        }

        @Override public void on_hmi__worker(final hmi_t src, final ko status) {
            log("walletd_status_handler_t: on_hmi__worker."); //--strip
            if (deps.cur != null && deps.cur.hmi == src) {
                deps.monitor_handlers.on_hmi__worker(deps.cur, status);
            }
            deps.a.on_hmi__worker(src, status);
        }

        int last_led_status = -1;

        @Override public void on_status(final hmi_t src, int led_status, final String msg) {
            deps.a.assert_worker_thread(); //--strip
            log("walletd_status_handler_t: on_status. " + msg); //--strip
            if (deps.cur == null) return;
            log("info src:" + deps.cur.hmi + " " + src); //--strip
            if (deps.cur.hmi != src) return;
            deps.monitor_handlers.on_status(deps.cur, led_status, msg);
            if (led_status != last_led_status) {
                last_led_status = led_status; 
                deps.a.on_hmi_status__worker();
            }
        }

        @Override public void confirmed_subhome(final hmi_t src, final String subhome__backend_choice) {
            assert deps.cur.hmi == src; //--strip
            log("walletd_status_handler_t: confirmed_subhome. " + subhome__backend_choice); //--strip
            deps.monitor_handlers.confirmed_subhome(deps.cur, subhome__backend_choice);
        }

        device_endpoints_t deps;
    }

    public device_endpoints_t(app a_) {
        a = a_;
        hmi__add_listener(new monitor_handler_t() {
            public void on_hmi__worker(final device_endpoint_t src, final ko status) {
                on_hmi__worker_(src, status);
            }
            public void on_status(final device_endpoint_t src, int led_status, final String msg) {}
            public void confirmed_subhome(final device_endpoint_t src, final String subhome__backend_choice) {
                confirmed_subhome_(src, subhome__backend_choice);
            }
            public void on_send(boolean busy) {}
            public void on_recv(boolean busy) {}
        });
    }

    void add_default_wallet_connection2() {
        if (CFG.default_wallet_connections.isEmpty()) {
            return;
        }
        blob_t blob = new blob_t(base58.decode(CFG.default_wallet_connections));
        if (blob.value == null) {
            log("default connection blob is null"); //--strip
            return;
        }
        wallet_connections_t wallet_connections = new wallet_connections_t();
        blob_reader_t reader = new blob_reader_t(blob);
        ko r = reader.read(wallet_connections);
        if (is_ko(r)) {
            log(r.msg); //--strip
            return;
        }
        log("adding default device_endpoints "); //--strip
        for (wallet_connection_t entry: wallet_connections) {
            add(new device_endpoint_t(this, entry));
        }
    }

    void add_default_wallet_connection() {
        add_default_wallet_connection2();
        if (isEmpty()) {
            log("No valid default connection found."); //--strip
            new_endpoint();
//            add(new device_endpoint_t(this));
        }
    }

    public ko init() {
        home = "device_endpoints";
        log("loading configuration. sz=" + size()); //--strip
        ko r = load_();
        log("loaded configuration. sz=" + size()); //--strip
        if (isEmpty()) {
            log("load returned empty set"); //--strip
            add_default_wallet_connection();
/*
            if (!isEmpty()) {
                set_cur(-1);
                return ok;
            }
*/
        }
        return r;
    }

    public ko erase(int position) {
        log("erase pos " + position + " cur_index " + cur_index.value); //--strip
        device_endpoint_t p = get(position);
        assert cur_index.value != position;
        assert cur != p;
        if (p.hmi != null) {
            ko r = new ko("KO 82711 Cannot delete an active connection.");
            log(r.msg); //--strip
            return r;
        }
        if (size() < 2) {
            ko r = new ko("KO 82712 Cannot delete the only one existing connection.");
            log(r.msg); //--strip
            return r;
        }
        remove(position);
        if (cur_index.value > position) {
            --cur_index.value;
        }
        need_save = true; //--strip
        save();
        return ok;
    }

    public void set_cur(int position) {
        log("set_cur " + position); //--strip
        if (position >=0 && position < size()) {
            cur_index.value = position;
            cur = get(cur_index.value);
        }
        else {
            cur = null;
            cur_index.value = -1;
        }
    }

    public ko new_endpoint() {
        log("new_endpoint"); //--strip
//        try {
            add(new device_endpoint_t(this));
/*        }
        catch (Exception e) {
            ko r = new ko("KO 82752.");
            log(r.msg); //--strip
            return r;
        }
*/
        log("save"); //--strip
        need_save = true; //--strip
        save();
        return ok;
    }

    public ko copy_device_endpoint(int ndx) {
        log("copy_endpoint " + ndx); //--strip
        device_endpoint_t dep = get(ndx);
        device_endpoint_t copy = new device_endpoint_t(this, "Copy of " + dep.name_.value, dep.subhome.value, dep.ip4_endpoint);
        add(copy);
        log("save"); //--strip
        need_save = true; //--strip
        save();
        return ok;
    }

    public ko poweron(int pos, final pin_t pin) {
        if (pos < 0 || pos >= size()) {
            return new ko("KO 50499 Invalid connection index");
        }
        log("poweron pos " + pos); //--strip
        set_cur(pos);
        log("============== cur.ip4_endpoint.to_string()= " + cur.ip4_endpoint.to_string()); //--strip
        return cur.poweron(pin, datagram_dispatcher);
    }

    public int find_index(device_endpoint_t o) {
        int pos = -1;
        int n = 0;
        for (device_endpoint_t i: this) {
            if (i == o) {
                pos = n;
                break;
            }
            ++n;
        }
        return pos;
    }

    public ko poweron(device_endpoint_t o, final pin_t pin) {
        log("poweron dep"); //--strip
        int pos = find_index(o);
        if (pos == -1) {
            return new ko("KO 50419 Invalid device_endpoint_t object");
        }
        set_cur(pos);
        log("============== cur.ip4_endpoint.to_string()= " + cur.ip4_endpoint.to_string()); //--strip
        return cur.poweron(pin, datagram_dispatcher);
    }

    public ko poweroff(int pos, boolean save_) {
        return poweroff(get(pos), save_);
    }

    public ko poweroff(device_endpoint_t o, boolean save_) {
        ko r = o.poweroff();
        if (is_ko(r)) {
            return r;
        }
        assert o.hmi == null; //--strip
        set_cur(-1);
        if (save_) {
            need_save = true; //--strip
            save();
        }
        return ok;
    }

    void write(blob_t blob) {
        log("writable::write to blob"); //--strip
        serid_t serid = serial_id();
        int sz = (serid.value != 0 ? blob_writer_t.header_size() : 0) + blob_size();
        if (sz == 0) {
            blob.clear();
            return;
        }
        blob_writer_t w = new blob_writer_t(blob, sz);
        if (serid.value != 0) {
            w.write_header(serid);
        }
        to_blob(w);
        assert w.cur == blob.value.length;
    }

    void confirmed_subhome_(final device_endpoint_t src, final String subhome__backend_choice) {
        assert src == cur;
        if (subhome__backend_choice.equals(cur.subhome)) {
            log("backend didn't change subhome. Stopping signal propagation. subhome=" + cur.subhome.value); //--strip
            return;
        }
        log("backend changed subhome. subhome prev=" + cur.subhome.value + " new=" + subhome__backend_choice); //--strip
        cur.subhome.value = subhome__backend_choice;
        need_save = true; //--strip
        //save();
    }

    void on_hmi__worker_(final device_endpoint_t src, final ko status) {
        app.assert_worker_thread(); //--strip
        log("on_hmi__worker_"); //--strip
        assert src == cur; //--strip
        cur.on_hmi__worker_(status);
        if (status == ok) {
            need_save = true; //--strip
            save();
        }
    }

    public boolean is_valid_index(final int index) {
        if (index < 0) return false;
        if (index >= size()) return false;
        return true;
    }

    void sort() {
        log("sort"); //--strip
        Collections.sort(this,
            new Comparator<device_endpoint_t>() {
                @Override public int compare(device_endpoint_t o1, device_endpoint_t o2) {
                    if (o1.ts.value < o2.ts.value) return 1;
                    if (o1.ts.value == o2.ts.value) return 0;
                    return -1;
                }
        });
        int newindex = -1;
        for (device_endpoint_t d: this) {
            ++newindex;
            if (d == cur) {
                break;
            }
        }
        if (newindex >= size()) { //--strip
            log("KO 78688 Unexpected. newindex " + newindex + " sz " + size()); //--strip
        }  //--strip
        assert newindex < size(); //--strip
        cur_index.value = newindex;
    }

    static int savecounter = 0; //--strip

    ko save() {
        assert need_save = true;      //--strip
        ++savecounter;                //--strip
        assert need_save;             //--strip
        need_save = false;            //--strip
        log("save endpoints. "+ savecounter); //--strip
        blob_t blob = new blob_t();
        //sort();
        write(blob);
        return cfg_android_private_t.write_file(a.getApplicationContext(), "", device_endpoints_file, blob.value);
    }

    public ko read(final blob_t blob) {
        log("readable::read from blob " + blob.size());  //--strip
        blob_reader_t reader = new blob_reader_t(blob);
        serid_t serid = serial_id();
        if (serid.value != 0) {
            ko r = reader.read_header(serid);
            if (ko.is_ko(r)) {
                return r;
            }
        }
        return reader.read(this);
    }

    public ko load_() {
        log("load_"); //--strip
        blob_t blob = new blob_t();
        int pwr = -1;
        blob.value = cfg_android_private_t.read_file(a.getApplicationContext(), "", device_endpoints_file);
        if (blob.value == null) {
            ko r = new ko("KO 65714 Could not load settings");
            log(r.msg); //--strip
            return r; //new pair<ko, Integer>(r, null);
        }
        return read(blob);
    }

    int req_pwr() {
        int pwr = -1;
        int p = -1;
        for (device_endpoint_t i: this) {
            ++p;
            if (i.hmi_req_on) {
                pwr = p;
                i.hmi_req_on = false;
                break;
            }
        }
        return pwr;
    }

    public ko set_name(String name) {
        cur.name_.value = name;
        need_save = true; //--strip
        return save();
    }

    public ko set_ssid(String ssid) {
        cur.ssid.value = ssid;
        need_save = true; //--strip
        return save();
    }

    public static serid_t my_serid_id = new serid_t((short)'X');

    @Override public serid_t serial_id() { return my_serid_id; }

    @Override public int blob_size() {
        int sz = blob_writer_t.sizet_size(size());
        for (device_endpoint_t entry: this) {
            sz += blob_writer_t.blob_size(entry);
        }
        sz += blob_writer_t.blob_size(cur_index);
        return sz;
    }

    @Override public void to_blob(blob_writer_t writer) {
        log("to_blob " + size() + " entries"); //--strip
        writer.write_sizet(size());
        for (device_endpoint_t entry: this) {
            writer.write(entry);
        }
        writer.write(cur_index);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        clear();
        log("from_blob"); //--strip
        uint64_t sz = new uint64_t();
        {
            ko r = reader.read_sizet(sz);
            if (is_ko(r)) return r;
        }
        log("loading " + sz.value + " items from blob"); //--strip
        try {
            for (long i = 0; i < sz.value; ++i) {
                log("loading item " + i); //--strip
                device_endpoint_t device_endpoint = new device_endpoint_t(this, 1);
                {
                    ko r = reader.read(device_endpoint);
                    if (is_ko(r)) {
                        log(r.msg); //--strip
                        return r;
                    }
                }
                log("adding device_endpoint " + device_endpoint.cfg.home); //--strip
                add(device_endpoint);
            }
        }
        catch (Exception e) {
            return new ko("KO 89782 Invalid read. device_endpoint.");
        }
        log("num items " + size()); //--strip
        {
            ko r = reader.read(cur_index);
            if (is_ko(r)) return r;
        }
        log("cur_index " + cur_index.value); //--strip
        set_cur(cur_index.value);
        return ok;
    }

    public void hmi__add_listener(device_endpoints_t.monitor_handler_t listener) {
        log("hmi__add_listener " + listener); //--strip
        a.assert_ui_thread(); //--strip
        monitor_handlers.add(listener);
        report_status();
    }

    public void hmi__add_listener__worker(device_endpoints_t.monitor_handler_t listener) {
        log("hmi__add_listener__worker " + listener); //--strip
        a.assert_worker_thread(); //--strip
        monitor_handlers.add(listener);
        report_status__worker();
    }

    public void hmi__remove_listener(device_endpoints_t.monitor_handler_t listener) {
        log("hmi__remove_listener " + listener); //--strip
        monitor_handlers.remove(listener);
    }

    public void report_status__worker() {
        if (cur == null || cur.hmi == null) {
            monitor_handlers.on_status(cur, us.cash.scr.leds_t.led_off, "Off.");
            return;
        }
        cur.hmi.report_status();
    }

    public void report_status() {
        a.assert_ui_thread(); //--strip
        Thread task = new Thread(new Runnable () {
            @Override public void run() {
                report_status__worker();
            }
        });
        task.start();
    }

    public boolean is_cur(device_endpoint_t o) {
        return o == cur;
    }

    public busyled_t.handler_t ledhandler_send = new walletd_led_handler(this, 0);
    public busyled_t.handler_t ledhandler_recv = new walletd_led_handler(this, 1);
    public walletd_status_handler_t status_handler_ = new walletd_status_handler_t(this);

    device_endpoint_t cur = null;
    int16_t cur_index = new int16_t(-1);
    String home;
    app a;
    monitor_handlers_t monitor_handlers = new monitor_handlers_t();
    datagram_dispatcher_t datagram_dispatcher = new datagram_dispatcher_t(3);

    public boolean need_save = false; //--strip
}

