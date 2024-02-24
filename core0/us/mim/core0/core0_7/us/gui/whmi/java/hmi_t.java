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
import java.util.Arrays;                                                                       // Arrays
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import java.io.BufferedReader;                                                                 // BufferedReader
import us.gov.socket.busyled_t;                                                                // busyled_t
import java.io.ByteArrayOutputStream;                                                          // ByteArrayOutputStream
//import android.graphics.drawable.ColorDrawable;                                                // ColorDrawable
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import us.gov.crypto.ec;                                                                       // ec
import android.os.Environment;                                                                 // Environment
import java.io.File;                                                                           // File
import java.io.FileInputStream;                                                                // FileInputStream
import java.io.FileOutputStream;                                                               // FileOutputStream
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.id.types.*;                                                               // *
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import java.io.InputStreamReader;                                                              // InputStreamReader
import java.io.IOException;                                                                    // IOException
import us.wallet.engine.ip4_endpoint_t;                                                        // ip4_endpoint_t
import static us.ko.is_ko;                                                                     // is_ko
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import java.io.PrintStream;                                                                    // PrintStream
import java.security.PrivateKey;                                                               // PrivateKey
import us.wallet.trader.qr_t;                                                                  // qr_t
import java.nio.charset.StandardCharsets;                                                      // StandardCharsets
import java.lang.StringBuilder;                                                                // StringBuilder
import us.string;                                                                              // string
import java.io.UnsupportedEncodingException;                                                   // UnsupportedEncodingException
import java.time.Instant;
import java.time.Duration;
import java.util.concurrent.locks.Lock;                                                        // Lock
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock

public final class hmi_t extends us.wallet.cli.hmi {

    public static final ko KO_54503 = new ko("KO 54503 already looking up.");
    public static final ko KO_50493 = new ko("KO 50493 Unknown node pkh. Unable to resolve IP.");
    public static final ko KO_10000 = new ko("KO 10000 Disconnected by peer.");
    public static final ko KO_10001 = new ko("KO 10001 I Disconnected.");
    public static final ko KO_61210 = new ko("KO 61210 Handshake could not be completed.");

    private static void log(final String line) {         //--strip
        CFG.log_android("hmi_t: " + line);               //--strip
    }                                                    //--strip

    public interface status_handler_t {
        void on_hmi__worker(final hmi_t src, final ko status); //Handle 
        void on_status(final hmi_t src, final int led, final String msg);
        void confirmed_subhome(final hmi_t src, final String subhome);
    }

    public interface gov_status_handler_t {
        void on_status(final int led, final String msg);
    }

    public hmi_t(final us.wallet.cli.params p, busyled_t.handler_t ledhandler_send, busyled_t.handler_t ledhandler_recv, status_handler_t status_handler, datagram_dispatcher_t dispatcher_) {
        super(p, System.out);
        dispatcher = dispatcher_;
        busyled_handler_send = ledhandler_send;
        busyled_handler_recv = ledhandler_recv;
        //status_handlers_wallet.add(status_handler_);
        status_handler_ = status_handler;
        set_status(us.cash.scr.leds_t.led_red, "Stopped");
    }

    public String get_msg_log() {
        return msg_log.toString();
    }

    public void clear_msg_log() {
        msg_log = new StringBuilder(100);
    }

    void set_status(final int led, final String msg) {
        app.assert_worker_thread(); //--strip
        log("set_status " + us.cash.scr.leds_t.color_name(led) + " " + msg); //--strip
        if (led == us.cash.scr.leds_t.led_green) {
            freeze = false;
        }
        boolean ign = false; //ignore
        if (freeze && led != us.cash.scr.leds_t.led_off) {
            ign = true;
            msg_log.append(us.cash.scr.leds_t.color_name(led));
            msg_log.append(" ");
            msg_log.append(msg);
            msg_log.append('\n');
        }
        if (led == us.cash.scr.leds_t.led_red) { //report only the first red message
            freeze = true;
        }
        msg_log.append("* ");
        if (ign) {
            msg_log.append("IGN: ");
        }
        msg_log.append(us.cash.scr.leds_t.color_name(led));
        msg_log.append(" ");
        msg_log.append(msg);
        msg_log.append('\n');
        if (!ign) {
            cur_led = led;
            cur_msg = msg;
            report_status();
        }
    }

    public void report_status() {
        app.assert_worker_thread(); //--strip
        log("reporting status: " + cur_msg); //--strip
        status_handler_.on_status(this, cur_led, cur_msg);
        log("end reporting status"); //--strip
    }

    public void report_status__ui() {
        app.assert_ui_thread(); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                report_status();
            }
        });
        thread.start();
    }

    void report_status_hmi__worker(ko err) {
        log("ZZH6 report_status_hmi__worker " + (is_ko(err) ? err.msg : "")); //--strip
        report_status();
        status_handler_.on_hmi__worker(this, err);
    }

    @Override public pair<ko, us.gov.io.cfg1> load_cfg(String home, boolean gen) {
        return new pair<ko, us.gov.io.cfg1>(ok, cfg);
    }

    public cfg_android_private_t get_cfg() {
        return (cfg_android_private_t) cfg;
    }

    public ko restart(ip4_endpoint_t ep, pin_t pin) {
        app.assert_worker_thread(); //--strip
        log("restart hmi pin " + ep.to_string() + " pin>0 " + (pin.value > 0 ? "Yes" : "No")); //--strip
        freeze = false;
        set_status(us.cash.scr.leds_t.led_amber, "restarting HMI. " + ep.to_string());
        stop();
        join();
        log("starting hmi once stopped"); //--strip
        ip4_endpoint = ep;
        ko r = start(ep, pin);
        log("hmi start return code is " + ko.as_string(r)); //--strip
        return r;
    }

    static pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t> load_netinfo(final cfg_android_private_t cfg, final channel_t channel) {
        log("load_netinfo(channel=" + channel.value + ")"); //--strip
        byte[] fc = cfg.read_file2("netinfo_ch_" + channel.value);
        if (fc == null) {
            ko r = new ko("KO 66094 netinfo cached file unavailable.");
            log(r.msg); //--strip
            return new pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t>(r, null);
        }
        blob_t blob = new blob_t(fc);
        blob_reader_t reader = new blob_reader_t(blob);
        us.wallet.cli.rpc_peer_t.net_info_out_dst_t o = new us.wallet.cli.rpc_peer_t.net_info_out_dst_t();
        {
            ko r = reader.read(o);
            if (is_ko(r)) {
                return new pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t>(r, null);
            }
        }
        return new pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t>(ok, o);
    }

    pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t> load_netinfo(final channel_t channel) {
        return load_netinfo((cfg_android_private_t)cfg, channel);
    }

    ko save_netinfo(final us.wallet.cli.rpc_peer_t.net_info_out_dst_t data, final channel_t channel) {
        log("save_netinfo " + data.wallet_address.encode() + "." + data.subhome + " " + data.seeds.size() + " seeds.");  //--strip
        us.wallet.cli.rpc_peer_t.net_info_out_t o = new us.wallet.cli.rpc_peer_t.net_info_out_t(data.wallet_address, data.subhome, data.seeds);
        blob_t blob = new blob_t();
        blob_writer_t writer = new blob_writer_t(blob, o.blob_size());
        writer.write(o);
        ko r = get_cfg().write_file("netinfo_ch_" + channel.value, blob.value);
        if (r != null) {
            return new ko("KO 25934 Unable to write netinfo file in private storage.");
        }
        return ok;
    }

    public ko start(final ip4_endpoint_t ep, final pin_t pin) {
        log("start"); //--strip
        if (cfg == null) {
            return new ko("KO 80799 cfg is null.");
        }
        freeze = false; //show the first error, then ign the rest
        is_online = false;
        ip4_endpoint = ep;
        p.pin = pin;
        if (ip4_endpoint != null) {
            p.walletd_host = ip4_endpoint.shost;
            p.walletd_port = ip4_endpoint.port;
            p.channel = ip4_endpoint.channel;
        }
        else {
            p.walletd_host = new shost_t("localhost");
            p.walletd_port = us.CFG.WALLET_PORT;
            p.channel = us.CFG.CHANNEL;
        }
        set_status(us.cash.scr.leds_t.led_amber, "Starting HMI. " + ip4_endpoint.to_string());

        cfg_android_public_t cfg_pub = new cfg_android_public_t(get_cfg().home);
        sw_updates = new sw_updates_t(this, get_cfg(), cfg_pub);

        log("super.start. stop_on_disconnection = " + p.rpc__stop_on_disconnection); //--strip
        ko r = super.start(busyled_handler_send, busyled_handler_recv, dispatcher);
        if (is_ko(r)) {
            log("super returned " + r.msg); //--strip
            set_status(us.cash.scr.leds_t.led_red, r.msg);
            report_status_hmi__worker(r);
            return r;
        }
        set_status(us.cash.scr.leds_t.led_amber, "Loading previous network info for channel " + p.channel.value);
        update_network_info_from_cache(p.channel);

/*
        r = sw_updates.start();
        if (is_ko(r)) {
            set_status(leds_t.led_red, r.msg);
            on_hmi__worker(r);
            sw_updates = null;
            super.stop();
            super.join();
            return r;
        }
*/
        set_status(us.cash.scr.leds_t.led_amber, "HMI running. Peer " + ip4_endpoint.to_string());
        log("super returned ok"); //--strip
        return ok;
    }

    @Override public void stop() {
//        if (sw_updates != null) sw_updates.stop();
        is_online = false;
        super.stop();
        set_status(us.cash.scr.leds_t.led_amber, "Stopping HMI.");
    }

    @Override public void join() {
//        if (sw_updates != null) sw_updates.join();
        sw_updates = null;
        super.join();
        freeze = false;
        set_status(us.cash.scr.leds_t.led_off, "Off.");
    }

    void on_connect__worker(final ko error) {
        app.assert_worker_thread(); //--strip
        log("ZZH6 on_connect. " + (error == ok ? "ok" : error.msg)); //--strip
        if (is_ko(error)) {
            log("ZZH6 try_renew_ip_on_connect_failed = " + try_renew_ip_on_connect_failed); //--strip
            if (try_renew_ip_on_connect_failed) {
                //set_status(us.cash.scr.leds_t.led_blue, "Trying to renew IP");
                final hmi_t o = this;
                set_status(us.cash.scr.leds_t.led_blue, "looking up IP address...");
                final ko err = error;
                Thread task = new Thread(new Runnable () {
                    @Override public void run() {
                        log("ZZH6 looking up IP address..."); //--strip
                        pair<ko, ip4_endpoint_t> r = o.lookup_ip();
                        if (is_ko(r.first)) {
                            log("ZZH6 lookup task returned: " + r.first.msg); //--strip
                            if (r.first == KO_54503) { //reentry
                                log("ZZH6 reentry. lookupip. ignored. " + r.first.msg); //--strip
                            }
                            //else if (r.first.equals(KO_50493)) { //no pkh
                            //    o.set_status(us.cash.scr.leds_t.led_red, err.msg);
                            //}
                            else {
                            log("ZZH6 set_status red " + err.msg); //--strip
                                o.set_status(us.cash.scr.leds_t.led_red, err.msg);
                            }
                            o.report_status_hmi__worker(err);
                            return;
                        }
                    }
                });
                task.start();
            }
            else {
                set_status(us.cash.scr.leds_t.led_red, error.msg);
                report_status_hmi__worker(error);
            }
        }
    }

    @Override public void on_connect(final ko error) {
        super.on_connect(error);
        if (app.is_ui_thread()) {
            Thread task = new Thread(new Runnable () {
                @Override public void run() {
                    on_connect__worker(error);
                }
            });
            task.start();
        }
        else {
            on_connect__worker(error);
        }
    }

    public ko update_network_info_from_cache(final channel_t channel) {
        log("update_network_info_from_cache ch=" + channel.value); //--strip
        pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t> o = load_netinfo(channel);
        if (is_ko(o.first)) {
            return o.first;
        }
        seeds = o.second.seeds;
        log("seeds length=" + seeds.size()); //--strip
        wallet_address = o.second.wallet_address;
        //if (!dep.subhome.equals(o.second.subhome.value)) {
        //    log("KO 78968 Receiving contradictory results from net_info. " + o.second.subhome.value + " should be " + dep.subhome); //--strip
        //}
        log("wallet_address=" + wallet_address.encode() + " subhome=" + o.second.subhome.value);  //--strip
        return ok;
    }

    public ko update_network_info(channel_t channel) {
        set_status(us.cash.scr.leds_t.led_amber, "Fetching network data...");
        us.wallet.cli.rpc_peer_t.net_info_out_dst_t o = new us.wallet.cli.rpc_peer_t.net_info_out_dst_t();
        ko r = rpc_peer.call_net_info(o);
        if (is_ko(r)) {
            return r;
        }
        save_netinfo(o, channel);
        seeds = o.seeds;
        log("seeds length=" + seeds.size()); //--strip
        wallet_address = o.wallet_address;
        //if (!dep.subhome.equals(o.subhome.value)) {
        //    log("KO 78968 Receiving contradictory results from net_info. " + o.subhome.value + " should be " + dep.subhome); //--strip
        //}
        log("wallet_address=" + wallet_address.encode() + " subhome=" + o.subhome.value);  //--strip
        return ok;
    }

    @Override public void on_I_disconnected(final reason_t reason) {
        super.on_I_disconnected(reason);
        log("I disconnected. Reason: " + reason.value); //--strip
        is_online = false;
        set_status(us.cash.scr.leds_t.led_red, KO_10001.msg + " Reason: " + reason.value);
        report_status_hmi__worker(KO_10001);
    }

    @Override public void on_peer_disconnected(final reason_t reason) {
        log("peer disconnected. Reason: " + reason.value); //--strip
        is_online = false;
        set_status(us.cash.scr.leds_t.led_red, KO_10000.msg + " Reason given: " + reason.value);
        report_status_hmi__worker(KO_10000);
        super.on_peer_disconnected(reason);
    }

    @Override public void verification_completed(boolean verok) { //boolean means if peer pubkey is verified
        log("verification_completed " + verok); //--strip
        super.verification_completed(verok);
        is_online = verok;
        set_status(us.cash.scr.leds_t.led_amber, "handshake completed with " + ip4_endpoint.to_string());
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                if (!is_online) {
                    log("Not online"); //--strip
                    ko r = new ko("KO 82110 Device verification wasn't successful.");
                    set_status(us.cash.scr.leds_t.led_red, r.msg);
                    report_status_hmi__worker(r);
                    return;
                }
                log("online task"); //--strip
                ko r = update_network_info(p.channel);
                if (is_ko(r)) {
                    set_status(us.cash.scr.leds_t.led_red, r.msg);
                    report_status_hmi__worker(r);
                    return;
                }
                set_status(us.cash.scr.leds_t.led_green, "online with " + ip4_endpoint.to_string());
                report_status_hmi__worker(ok);
            }
        });
        thread.start();
    }

    @Override public void verification_result(request_data_t request_data) {
        log("verification_result " + request_data.value); //--strip
        status_handler_.confirmed_subhome(this, request_data.value);
    }

    public pair<ko, String> ping() {
        string ans = new string();
        ko r = rpc_peer.call_ping(new string("ping"), ans);
        if (is_ko(r)) {
            return new pair<ko, String>(r, "");
        }
        return new pair<ko, String>(ok, ans.value);
    }

    public pair<ko, String> balance(final uint16_t detail) {
        string ans = new string();
        ko r = rpc_peer.call_balance(detail, ans);
        if (is_ko(r)) {
            return new pair<ko, String>(r, "");
        }
        return new pair<ko, String>(ok, ans.value);
    }

    public pair<ko, blob_t> pay(int amount, int fee, hash_t rcpt_address, String sig) {
        us.wallet.cli.rpc_peer_t.transfer_in_t o = new us.wallet.cli.rpc_peer_t.transfer_in_t(rcpt_address, new int64_t(amount+fee), new hash_t(0), new uint8_t((short)1));
        us.gov.io.types.blob_t blob = new us.gov.io.types.blob_t();
        ko r = rpc_peer.call_transfer(o, blob);
        if (is_ko(r)) {
            return new pair<ko, blob_t>(r, null);
        }
        return new pair<ko, blob_t>(ok, blob);
    }

    public boolean isAddressValid(final String addr) {
        try {
            byte[] decoded = base58.decode(addr);
            return decoded.length > 0;
        }
        catch(Exception e) {
            return false;
        }
    }

    public pair<ko, String> list_trades() {
        string s = new string();
        ko r = rpc_peer.call_list_trades(s);
        if (is_ko(r)) {
            return new pair<ko, String>(r, null);
        }
        return new pair<ko, String>(ok, s.value);
    }

    public pair<ko, us.gov.io.types.vector_hash> list_wallets() {
        us.gov.io.types.vector_hash o = new us.gov.io.types.vector_hash();
        ko r = rpc_peer.call_world(o);
        if (is_ko(r)) {
            return new pair<ko, us.gov.io.types.vector_hash>(r, null);
        }
        return new pair<ko, us.gov.io.types.vector_hash>(ok, o);
    }

    public ko command_trade(final hash_t tradeid, final String command) {
        log("command_trade " + command); //--strip
        if (tradeid == null) return new ko("KO 78684 Missing Trade id");
        us.wallet.cli.rpc_peer_t.exec_trade_in_t o = new us.wallet.cli.rpc_peer_t.exec_trade_in_t(tradeid, new string(command));
        return rpc_peer.call_exec_trade(o);
    }

    public pair<ko, hash_t> new_trade(final hash_t parent_trade, final String datasubdir, final qr_t qr) {
        log("new trade in datasubdir = " + datasubdir); //--strip
        string dsubdir = new string(datasubdir);
        blob_t blob = new blob_t();
        blob_writer_t.write(qr, blob);
        hash_t tid = new hash_t(0);
        us.wallet.cli.rpc_peer_t.trade_in_t o = new us.wallet.cli.rpc_peer_t.trade_in_t(parent_trade, dsubdir, blob);
        log("new_trade " + qr.to_string()); //--strip
        ko r = rpc_peer.call_trade(o, tid);
        if (is_ko(r)) {
            return new pair<ko, hash_t>(r, null);
        }
        return new pair<ko, hash_t>(ok, tid);
    }

    public pair<ko, String> kill_trade(final hash_t tradeid) {
        string data = new string();
        ko r = rpc_peer.call_kill_trade(tradeid, data);
        if (is_ko(r)) {
            return new pair<ko, String>(r, null);
        }
        return new pair<ko, String>(ok, data.value);
    }

/*
    public pair<ko, bookmarks_t> bookmarks_me() {
        log("bookmarks_me"); //--strip
        if (rpc_peer == null) {
            ko r = new ko("KO 25534 HMI is not connected.");
            log(r.msg); //--strip
            return new pair<ko, bookmarks_t>(r, null);
        }
        pair<ko, bookmarks_t> ret = new pair<ko, bookmarks_t>();
        ret.second = new bookmarks_t();
        ko r = rpc_peer.call_qr(ret.second);
        if (is_ko(r)) {
            ret.first = r;
            ret.second = null;
            return ret;
        }
        ret.first = ok;
        return ret;
    }
*/

    public static pair<us.gov.io.types.vector_tuple_hash_host_port, hash_t>  get_seeds(cfg_android_private_t cfg, final channel_t channel) {
        pair<ko, us.wallet.cli.rpc_peer_t.net_info_out_dst_t> o = load_netinfo(cfg, channel);
        if (is_ko(o.first)) {
            return new pair<us.gov.io.types.vector_tuple_hash_host_port, hash_t>(null, null);
        }
        return new pair<us.gov.io.types.vector_tuple_hash_host_port, hash_t>(o.second.seeds, o.second.wallet_address);
    }

    public static pair<ko, ip4_endpoint_t> lookup_ip(cfg_android_private_t cfg, final channel_t channel, hmi_t.gov_status_handler_t h) {
        log("lookup_ip"); //--strip
        h.on_status(us.cash.scr.leds_t.led_blue, "Updating IP address");
        pair<us.gov.io.types.vector_tuple_hash_host_port, hash_t> seeds = get_seeds(cfg, channel);
        if (seeds.first == null) {
            h.on_status(us.cash.scr.leds_t.led_blue, "I can't look it up. I've never seen any node for channel " + channel.value + "yet.");
            return new pair<ko, ip4_endpoint_t>(new ko("KO 87996 Missing seeds file."), null);
        }

        log("seeds length=" + seeds.first.size()); //--strip
        hash_t wallet_address = seeds.second;
//        log("wallet_address=" + wallet_address.encode() + " subhome=" + o.second.subhome.value);  //--strip
        if (wallet_address == null || wallet_address.is_zero()) {
            h.on_status(us.cash.scr.leds_t.led_blue, KO_50493.msg);
            log(KO_50493.msg); //--strip
            return new pair<ko, ip4_endpoint_t>(KO_50493, null);
        }
        h.on_status(us.cash.scr.leds_t.led_blue, "Solving IP for " + wallet_address.encode());
        log("seeds=" + seeds.first.size()); //--strip
        return gov_client_t.lookup_wallet_ip(wallet_address, cfg.keys, seeds.first, channel);
    }

    public pair<ko, ip4_endpoint_t> lookup_ip() {
        if (lookingup) {
            log(KO_54503.msg); //--strip
            return new pair<ko, ip4_endpoint_t>(KO_54503, null);
        }
        lookingup = true;
        final hmi_t self = this;
        pair<ko, ip4_endpoint_t> r = lookup_ip((cfg_android_private_t)cfg, ip4_endpoint.channel, new hmi_t.gov_status_handler_t() {
            @Override public void on_status(final int led, final String msg) {
                if (msg != null) {
                    status_handler_.on_status(self, led, msg);
                }
            }
        });
        if (r.first != ok) {
            lookingup = false;
            return r;
        }
        if (r.second.equals(ip4_endpoint)) {
            log("IP hasn't changed. " + ip4_endpoint.to_string()); //--strip
            lookingup = false;
            return r;
        }
        String msg = "Wallet IP address has changed (from " + ip4_endpoint.to_string() + " to " + r.second.to_string() + "). Reconnecting...";
        log(msg); //--strip
        log("restarting hmi"); //--strip
        ko r2 = restart(r.second, new pin_t(0));
        if (r2 != ok) {
            lookingup = false;
            return new pair<ko, ip4_endpoint_t>(r2, r.second);
        }
        log("hmi started " + r.second.to_string()); //--strip
        lookingup = false;
        return new pair<ko, ip4_endpoint_t>(ok, r.second);
    }

    public void set_manual_mode(boolean b) {
        manual_mode = b;
        if (manual_mode) {
            log("manual mode is ON"); //--strip
            try_renew_ip_on_connect_failed = false;
            p.rpc__stop_on_disconnection = true;
            try_renew_ip_on_connect_failed = false;
        }
        else {
            log("manual mode is OFF"); //--strip
            try_renew_ip_on_connect_failed = true;
            p.rpc__stop_on_disconnection = false;
            try_renew_ip_on_connect_failed = true;
        }
        if (is_active()) {
            rpc_daemon.handler.stop_on_disconnection = p.rpc__stop_on_disconnection;
        }
    }

    public String techinfo() {
        log("techinfo"); //--strip
        StringBuilder b = new StringBuilder();
        b.append("  ip4_endpoint:\n    " + (ip4_endpoint == null ? "Null" : "" + ip4_endpoint.to_string()) + '\n');
        b.append("  active: " + is_active() + '\n');
        b.append("  online: " + is_online + '\n');
        b.append("  manual_mode: " + manual_mode + '\n');
        b.append("  try_renew_ip: " + try_renew_ip_on_connect_failed + '\n');
        b.append("  stop_on_disconnection: " + p.rpc__stop_on_disconnection + '\n');
        b.append("  wallet_address:\n    " + (wallet_address == null ? "KO null" : wallet_address.encode()) + '\n');
        return b.toString();
    }

    @Override public void upgrade_software() {
        log("Peer is signaling the existence of a upgrade_software. Calling check4updates."); //--strip
        log("rpc_daemon.api_v.value " + rpc_daemon.api_v.value); //--strip

        if (sw_updates == null) {
            log("sw_updates is null"); //--strip
            return;
        }
        if (!is_online) {
            log("signal upgrade_software ignored bcs offline"); //--strip
            return;
        }
        if (sw_updates.user_selected_remind_me_later != null) {
            Duration d = Duration.between(sw_updates.user_selected_remind_me_later, Instant.now());
            Duration d2 = Duration.ofHours(8);
            if (d.compareTo(d2) == -1) {
                log("ignoring updates warning for 8 hours. User choice."); //--strip
                return;
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(10000);
                }
                catch(Exception e) {
                }
                if (sw_updates != null) {
                    sw_updates.check4updates__worker();
                }
            }
        });
        thread.start();
/*
        sw_updates_t sw_updates = new sw_updates_t(a, this, get_cfg(), cfg_pub);
        r = sw_updates.start();
        if (is_ko(r)) {
            set_status(leds_t.led_red, r.msg);
            on_hmi__worker(r);
            sw_updates = null;
            super.stop();
            super.join();
            return r;
        }
*/
    }

    device_endpoint_t dep;
    public boolean is_online = false;
    public boolean try_renew_ip_on_connect_failed = true;

    int cur_led = us.cash.scr.leds_t.led_off;
    String cur_msg = null;
    StringBuilder msg_log = new StringBuilder(100);
    boolean freeze = false;
    busyled_t.handler_t busyled_handler_send = null;
    busyled_t.handler_t busyled_handler_recv = null;
    status_handler_t status_handler_ = null;
    public datagram_dispatcher_t dispatcher = null;
    boolean lookingup = false;
    hash_t wallet_address = null;
    us.gov.io.types.vector_tuple_hash_host_port seeds;
    public ip4_endpoint_t ip4_endpoint = null;
    //node_pairing manual_mode_ui = null;
    boolean manual_mode = false;
    sw_updates_t sw_updates = null;
}

