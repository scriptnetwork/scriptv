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
import java.util.ArrayDeque;                                                                   // ArrayDeque
import java.util.concurrent.locks.Condition;                                                   // Condition
import us.gov.socket.datagram;                                                                 // datagram
import java.util.HashMap;                                                                      // HashMap
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import java.util.Queue;                                                                        // Queue
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import java.util.ArrayList;                                               // ReentrantLock

public class datagram_dispatcher_t implements datagram.dispatcher_t, Runnable {

    private static void log(final String line) {                        //--strip
       CFG.log_android("datagram_dispatcher_t: " + line);               //--strip
    }                                                                   //--strip

    public static interface handler_t {
        void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload);
    }

    public datagram_dispatcher_t(int num_workers) {
        log("Starting " + num_workers + " workers"); //--strip
        workers = new Thread[num_workers];
        for (int i = 0; i < workers.length; ++i) {
            workers[i] = new Thread(this);
            workers[i].start();
        }
        sinks.ensureCapacity(10);
    }

    //2. push datagram picked from q by a worker thread process the datagram
    @Override public void run() {
        q_lock.lock();
        log("q_lock 1"); //--strip
        try {
            while(true) {
                datagram d = q.poll();
                if (d == null) {
                    q_cv.await();
                    continue;
                }
                q_lock.unlock();
                log("q_lock 0."); //--strip
                try {
                    process(d);
                }
                catch (Exception e) {
                    log("KO 39478 Exception"); //--strip
                }
                q_lock.lock();
                log("q_lock 1."); //--strip
            }
        }
        catch (InterruptedException e) {
            log("KO 39479 Exception"); //--strip
        }
        finally {
            q_lock.unlock();
            log("q_lock 0"); //--strip
        }
    }

    handler_t find__locked(handler_t o) {                                                       //--strip
        for (handler_t i: sinks) {                                                              //--strip
            if (i == o) return i;                                                               //--strip
        }                                                                                       //--strip
        return null;                                                                            //--strip
    }                                                                                           //--strip

    public void connect_sink(handler_t s) {
        log("connect_sink " + s); //--strip
        if (s == null) {                                                     //--strip
            new Exception().printStackTrace();                               //--strip
            assert false;                                                    //--strip
            return;                                                          //--strip
        }                                                                    //--strip
        mx.lock();
        try {
            if (find__locked(s) != null) {                                  //--strip
                ko r = new ko("KO 20039 handler is already added.");        //--strip
                log(r.msg);                                                 //--strip
                new Exception().printStackTrace();                          //--strip
                assert false;                                               //--strip
            }                                                               //--strip
            sinks.add(s);
        }
        finally {
            mx.unlock();
        }
    }

    public void disconnect_sink(handler_t s) {
        log("disconnect_sink " + s); //--strip
        if (s == null) {                                                     //--strip
            new Exception().printStackTrace();                               //--strip
            assert false;                                                    //--strip
            return;                                                          //--strip
        }                                                                    //--strip
        mx.lock();
        try {
            if (find__locked(s) == null) {                                       //--strip
                ko r = new ko("KO 20029 There is no handler to delete.");        //--strip
                log(r.msg);                                                      //--strip
                new Exception().printStackTrace();                               //--strip
                assert false;                                                    //--strip
            }                                                                    //--strip
            sinks.remove(s);
        }
        finally {
            mx.unlock();
        }
    }

    //2. push datagram enters q. Called by hmi guts
    @Override public boolean dispatch(datagram d) {
        log("dispatch dgram " + d); //--strip
        q_lock.lock();
        log("q_lock 1.."); //--strip
        try {
            q.add(d);
            q_cv.signal();
        }
        finally {
            q_lock.unlock();
            log("q_lock 0.."); //--strip
        }
        return true;
    }

    //4. on push is called on every subscriptor (sinks)
    public void propagate(hash_t tid, uint16_t code, byte[] payload) {
        log("propagate tid=" + tid.encode() + " code " + code.value + " payload " + payload.length + " bytes"); //--strip
        mx.lock();
        try {
            for (handler_t s: sinks) {
                assert s != null; //--strip
                s.on_push(tid, code, payload);
            }
        }
        finally {
            mx.unlock();
        }
    }

    //3. Decodes the datagram into trade id, code m payload, and propagates it
    public void process(datagram d) {
        log("main propagate svc=" + d.service.value); //--strip
        try {
            switch(d.service.value) {
                case us.gov.protocol.relay_push: {
                    us.wallet.cli.rpc_peer_t.push_in_dst_t o_in = new us.wallet.cli.rpc_peer_t.push_in_dst_t();
                    us.gov.io.blob_reader_t reader = new us.gov.io.blob_reader_t(d);
                    ko r = reader.read(o_in);
                    if (is_ko(r)) {
                        log("KO 66985 Parse svc push"); //--strip
                        return;
                    }
                    propagate(o_in.tid, o_in.code, o_in.blob.value);
                    return;
                }
                default:
                    log("KO 79979 Unexpected service." + d.service.value); //--strip
                    return;
                }
        }
        catch(Exception e) {
            log("KO 94995 Exception " + e.getMessage()); //--strip
        }
    }

    ArrayList<handler_t> sinks = new ArrayList<handler_t>();

    Thread[] workers;
//    int nextid = 0;

    Queue<datagram> q = new ArrayDeque<datagram>();
    ReentrantLock q_lock = new ReentrantLock();
    Condition q_cv = q_lock.newCondition();

    ReentrantLock mx = new ReentrantLock();
}

