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
import java.util.Date;                                                                         // Date
import us.gov.crypto.ec;                                                                       // ec
import static us.gov.crypto.types.*;                                                           // *
import static us.gov.id.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import us.stdint.*;                                                                            // *
import us.wallet.engine.ip4_endpoint_t;                                                        // ip4_endpoint_t
import static us.ko.is_ko;                                                                     // is_ko
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import java.security.PrivateKey;                                                               // PrivateKey
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import us.string;                                                                              // string
import us.wallet.engine.wallet_connection_t;                                                   // wallet_connection_t
import us.pair;
import android.content.Context;                                                                // Context
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t


public final class device_endpoint_t extends wallet_connection_t {

    private static void log(final String line) {                        //--strip
        CFG.log_android("device_endpoint_t: " + line);                  //--strip
    }                                                                   //--strip

    public device_endpoint_t(device_endpoints_t parent_, int for_read_from_blob) {
        parent = parent_;
        cfg = null;
    }

    public device_endpoint_t(device_endpoints_t parent_, String k_b58, String subhome, wallet_connection_t wallet_connection) {
        super(wallet_connection.name_.value, subhome, wallet_connection.ip4_endpoint);
        parent = parent_;
        cfg = new cfg_android_private_t(parent.a.getApplicationContext(), k_b58);
        cfg.home = get_home();
    }

    public device_endpoint_t(device_endpoints_t parent_, String name, String subhome, ip4_endpoint_t ip4_endpoint) {
        super(name, subhome, ip4_endpoint);
        parent = parent_;
        cfg = new cfg_android_private_t(parent.a.getApplicationContext());
        cfg.home = get_home();
    }

    public device_endpoint_t(device_endpoints_t parent_, wallet_connection_t wallet_connection) {
        super(wallet_connection.name_.value, wallet_connection.subhome.value, wallet_connection.ip4_endpoint);
        parent = parent_;
        cfg = new cfg_android_private_t(parent.a.getApplicationContext());
        cfg.home = get_home();
    }

    public device_endpoint_t(device_endpoints_t parent_) {
        parent = parent_;
        cfg = new cfg_android_private_t(parent.a.getApplicationContext());
        cfg.home = get_home();
    }

    private device_endpoint_t(device_endpoint_t other) {
    }

    public void on_hmi__worker_(final ko status) {
        app.assert_worker_thread(); //--strip
        log("on_hmi__worker"); //--strip
        if (status == ok) {
            log("HMI is ok. Node is up."); //--strip
            if (hmi.wallet_address != null) {
                addr = new string(hmi.wallet_address.encode());
                log("addr " + addr.value); //--strip
            }
            ts = new uint64_t(new Date().getTime());
        }
        else { //--strip
            log("HMI is not ok. Node is down. " + status.msg); //--strip
        } //--strip
    }

/*
    @Override public void on_hmi__worker(final ko status) {
    }
*/
    public void sw_updates_ui(Context ctx) {
        if (hmi == null) return;
        assert hmi.sw_updates != null;
        hmi.sw_updates.show_ui(ctx);
   }


    public us.gov.io.types.vector_tuple_hash_host_port get_seeds(channel_t channel) {
        pair<us.gov.io.types.vector_tuple_hash_host_port, hash_t> seeds = hmi_t.get_seeds(cfg, channel);
        if (seeds.first == null) {
            return null;
        }
        return seeds.first;
    }

    public pair<ko, ip4_endpoint_t> lookup_ip(hmi_t.gov_status_handler_t h) {
        if (ip4_endpoint == null) {
            pair<ko, ip4_endpoint_t> r = new pair<ko, ip4_endpoint_t>(new ko("KO 54091 invalid ep in settings."), null);
            log(r.first.msg); //--strip
            return r;
        }
        return hmi_t.lookup_ip(cfg, ip4_endpoint.channel, h);
    }

    public ko poweron(pin_t pin, datagram_dispatcher_t dis) {
        log("poweron"); //--strip
        app.assert_worker_thread();  //--strip
        if (hmi != null) {
            if (hmi.is_active()) {
                log("Already powered ON"); //--strip
                return ok;
            }
            log("found HMI instantiated but not active. Destroying it."); //--strip
            hmi = null;
            log("hmi is now null. XXII."); //--strip
        }
        if (ip4_endpoint == null) {
            ko r = new ko("KO 54093 invalid ep in settings. Restart of HMI's been aborted.");
            log(r.msg); //--strip
            return r;
        }
        log("Instantiating new HMI ..."); //--strip
        us.wallet.cli.params p = new us.wallet.cli.params();
        p.daemon = false;
        p.homedir = cfg.home + "/hmi";
        p.subhome = subhome.value;
        log("hmi homedir=" + p.homedir); //--strip
        p.rpc__connect_for_recv = true;
        p.rpc__stop_on_disconnection = false;
        hmi = new hmi_t(p, parent.ledhandler_send, parent.ledhandler_recv, parent.status_handler_, dis);
        hmi.cfg = cfg;
        log("start HMI"); //--strip
        hmi.set_manual_mode(manual_mode);
        log("Starting connection to " + (ip4_endpoint == null ? "null" : ip4_endpoint.to_string()) + "; pin " + pin.value); //--strip
        ko r = hmi.start(ip4_endpoint, pin);
        if (is_ko(r)) {
            log("HMI Failed to start: " + r.msg + " " + (ip4_endpoint == null ? "null" : ip4_endpoint.to_string())); //--strip
            return r;
        }
        log("hmi started"); //--strip
        return ok;
    }

    public ko poweroff() {
        log("poweroff"); //--strip
        app.assert_worker_thread();  //--strip
        if (hmi == null) {
            ko r = new ko("KO 88192 hmi is null.");
            log(r.msg); //--strip
            return r;
        }
        hmi.set_manual_mode(true);
        hmi.stop();
        log("hmi stopping"); //--strip
        hmi.join();
        log("hmi stopped"); //--strip
        hmi = null;
        log("hmi is now null. XXII."); //--strip
        return ok;
    }

    String get_home() {
        return parent.home + "/" + us.gov.crypto.ec.instance.to_encoded_address(cfg.keys.getPublic());
    }

    public ko set_ip4_endpoint(ip4_endpoint_t ip4_endpoint_) {
        log("set_ip4_endpoint " + ip4_endpoint_.to_string()); //--strip
        ip4_endpoint = ip4_endpoint_;
        return ok;
    }

    public void set_subhome(String subhome_) {
        subhome.value = subhome_;
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return super.blob_size() + priv_t.ser_size + uint8_t.size();
    }

    @Override public void to_blob(blob_writer_t writer) {
        log("to_blob"); //--strip
        uint8_t pwr = new uint8_t(hmi != null ? (short)1 : (short)0);
        if (hmi_req_on) {
            log("hmi_req_on is 1"); //--strip
            pwr.value = 1;
        }
        priv_t priv = new priv_t(cfg.keys.getPrivate());
        super.to_blob(writer);
        writer.write(priv);
        writer.write(pwr);
        log("to_blob ok. name=" + name_.value + " pwr=" + pwr.value); //--strip
    }

    @Override public ko from_blob(blob_reader_t reader) {
        log("from_blob"); //--strip
        if (cfg != null) {
            return new ko("KO 89986 Object already initialized.");
        }
        {
            ko r = super.from_blob(reader);
            if (ko.is_ko(r)) return r;
        }
        {
            priv_t priv = new priv_t();
            ko r = reader.read(priv);
            if (ko.is_ko(r)) return r;
            cfg = new cfg_android_private_t(parent.a.getApplicationContext(), priv.value, "");
            cfg.home = get_home();
            log("cfg home " + cfg.home); //--strip
        }
        {
            uint8_t pwr = new uint8_t((short)0);
            ko r = reader.read(pwr);
            if (ko.is_ko(r)) return r;
            hmi_req_on = pwr.value != 0;
            log("hmi_req_on " + hmi_req_on); //--strip
        }
        log("from_blob ok. name=" + name_.value + " hmi_req_on=" + hmi_req_on); //--strip
        return ok;
    }

    public boolean is_powered_on() {
        return hmi != null;
    }

    public void set_manual_mode(boolean set_manual) { //Disables the autoconnect logic while configuring the interface.
        log("set_manual_mode " + set_manual); //--strip
        manual_mode = set_manual;
        if (hmi == null) {
            log("HMI is null"); //--strip
            return;
        }
        hmi.set_manual_mode(set_manual);
    }

    public String techinfo() {
        log("techinfo"); //--strip
        StringBuilder b = new StringBuilder();
        b.append("HMI: " + (hmi == null ? "KO Not" : "OK ") + " present.\n");
        if (hmi != null) {
            b.append(hmi.techinfo());
        }
        b.append("  wloc: " + (subhome.value.isEmpty() ? "[Non Custodial (empty)]" : subhome.value) + '\n');
        return b.toString();
//        a.new_dlg_builder().setTitle("HMI Info:").setMessage(b.toString()).show();
    }


    boolean manual_mode = false;
    public hmi_t hmi = null;
    public boolean hmi_req_on = false;
    device_endpoints_t parent;
    cfg_android_private_t cfg;

}

