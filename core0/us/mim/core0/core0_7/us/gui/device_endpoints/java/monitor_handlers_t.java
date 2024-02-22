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
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import java.util.concurrent.locks.Lock;                                                        // Lock
import us.gov.socket.busyled_t;                                                                // busyled_t

public class monitor_handlers_t implements device_endpoints_t.monitor_handler_t {

    private static void log(final String s) {                     //--strip
        CFG.log_android("monitor_handlers_t: " + s);              //--strip
    }                                                             //--strip

    device_endpoints_t.monitor_handler_t find__locked(device_endpoints_t.monitor_handler_t o) { //--strip
        for(device_endpoints_t.monitor_handler_t i: handlers) {                                 //--strip
            if (i == o) return i;                                                               //--strip
        }                                                                                       //--strip
        return null;                                                                            //--strip
    }                                                                                           //--strip

    public void add(device_endpoints_t.monitor_handler_t o) {
        if (o == null) {                                                        //--strip
            new Exception().printStackTrace();                                  //--strip
            assert false;                                                       //--strip
            return;                                                             //--strip
        }                                                                       //--strip
        log("add " + o); //--strip
        mx.lock();
        try {
            if (find__locked(o) != null) {                                  //--strip
                ko r = new ko("KO 40039 handler is already added.");        //--strip
                log(r.msg);                                                 //--strip
                new Exception().printStackTrace();                          //--strip
                assert false;                                               //--strip
            }                                                               //--strip
            handlers.add(o);
            log("listeners size: " +  handlers.size()); //--strip
        }
        finally {
            mx.unlock();
        }
    }

    public void remove(device_endpoints_t.monitor_handler_t o) {
        if (o == null) {                                                     //--strip
            new Exception().printStackTrace();                               //--strip
            assert false;                                                    //--strip
            return;                                                          //--strip
        }                                                                    //--strip
        log("remove" + o); //--strip
        mx.lock();
        try {
            if (find__locked(o) == null) {                                       //--strip
                ko r = new ko("KO 40039 There is no handler to delete.");        //--strip
                log(r.msg);                                                      //--strip
                new Exception().printStackTrace();                               //--strip
                assert false;                                                    //--strip
            }                                                                    //--strip
            handlers.remove(o);
            log("listeners size: " +  handlers.size()); //--strip
        }
        finally {
            mx.unlock();
        }
    }

    public void clean() {
        log("clean"); //--strip
        mx.lock();
        try {
            handlers.clear();
            log("listeners size: " +  handlers.size()); //--strip
        }
        finally {
            mx.unlock();
        }
    }

    @Override public void on_hmi__worker(final device_endpoint_t src, final ko status) {
        mx.lock();
        log("on_hmi__worker -> listeners size: " +  handlers.size()); //--strip
        try {
            for (device_endpoints_t.monitor_handler_t i : handlers) {
                i.on_hmi__worker(src, status);
            }
        }
        finally {
            mx.unlock();
        }
    }

    @Override public void on_status(final device_endpoint_t src, final int led, final String msg) {
        mx.lock();
        log("on_status -> listeners size: " +  handlers.size()); //--strip
        try {
            for (device_endpoints_t.monitor_handler_t i : handlers) {
                i.on_status(src, led, msg);
            }
        }
        finally {
            mx.unlock();
        }
    }

    @Override public void confirmed_subhome(final device_endpoint_t src, final String subhome) {
        mx.lock();
        log("confirmed_subhome -> listeners size: " +  handlers.size()); //--strip
        try {
            for (device_endpoints_t.monitor_handler_t i : handlers) {
                i.confirmed_subhome(src, subhome);
            }
        }
        finally {
            mx.unlock();
        }
    }

    @Override public void on_send(final boolean busy) {
        mx.lock();
        try {
            for (device_endpoints_t.monitor_handler_t i : handlers) {
                i.on_send(busy);
            }
        }
        finally {
            mx.unlock();
        }
    }

    @Override public void on_recv(final boolean busy) {
        mx.lock();
        try {
            for (device_endpoints_t.monitor_handler_t i : handlers) {
                i.on_recv(busy);
            }
        }
        finally {
            mx.unlock();
        }
    }

    ArrayList<device_endpoints_t.monitor_handler_t> handlers = new ArrayList<device_endpoints_t.monitor_handler_t>();
    Lock mx = new ReentrantLock();

}

