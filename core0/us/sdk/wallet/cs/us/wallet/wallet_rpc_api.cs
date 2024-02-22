using System;
using System.IO;
using System.Text;
using us.gov.crypto;
using us.gov.socket;
using static us.gov.cash.tx;
using static us.gov.socket.client;

namespace us.wallet
{
    public class wallet_rpc_api : wallet_api
    {
        public wallet_rpc_api(rpc_api endpoint_)
        {
            endpoint = endpoint_;
        }

        String to_string(bool b)
        {
            return b ? "1" : "0";
        }

        String to_string(long b)
        {
            return b.ToString();
        }

        String to_string(tx_make_p2pkh_input i)
        {
            return i.to_string();
        }

        String to_string(sigcode_t i)
        {
            return i.ToString();
        }

        String to_string(byte[] b)
        {
            return base58.encode(b);
        }

//---------------------------------------------------------------------generated by make, do not edit
//content of file: ../../../api/apitool_generated__functions_wallet_cs_rpc-impl
//------------------generated by apitool- do not edit
public void ping(Stream a0) {
    endpoint.ask_string(protocol.wallet_ping, a0);
}

public void balance(bool a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_balance, o, a1);
}

public void list(bool a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_list, o, a1);
}

public void new_address(Stream a0) {
    endpoint.ask_string(protocol.wallet_new_address, a0);
}

public void add_address(byte[] a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_add_address, o, a1);
}

public void transfer(byte[] a0, long a1, byte[] a2, Stream a3) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    o += to_string(a2) + ' ';
    endpoint.ask_string(protocol.wallet_transfer, o, a3);
}

public void transfer_from(byte[] a0, byte[] a1, long a2, byte[] a3, Stream a4) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    o += to_string(a2) + ' ';
    o += to_string(a3) + ' ';
    endpoint.ask_string(protocol.wallet_transfer_from, o, a4);
}

public void tx_charge_pay(byte[] a0, long a1, byte[] a2, long a3, byte[] a4, Stream a5) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    o += to_string(a2) + ' ';
    o += to_string(a3) + ' ';
    o += to_string(a4) + ' ';
    endpoint.ask_string(protocol.wallet_tx_charge_pay, o, a5);
}

public void tx_pay(string a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_tx_pay, o, a1);
}

public void tx_new(Stream a0) {
    endpoint.ask_string(protocol.wallet_tx_new, a0);
}

public void tx_add_section(string a0, byte[] a1, Stream a2) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    endpoint.ask_string(protocol.wallet_tx_add_section, o, a2);
}

public void tx_add_input(string a0, int a1, byte[] a2, long a3, Stream a4) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    o += to_string(a2) + ' ';
    o += to_string(a3) + ' ';
    endpoint.ask_string(protocol.wallet_tx_add_input, o, a4);
}

public void tx_add_output(string a0, int a1, byte[] a2, long a3, Stream a4) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    o += to_string(a2) + ' ';
    o += to_string(a3) + ' ';
    endpoint.ask_string(protocol.wallet_tx_add_output, o, a4);
}

public void tx_make_p2pkh(tx_make_p2pkh_input a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_tx_make_p2pkh, o, a1);
}

public void tx_sign(string a0, string a1, Stream a2) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    endpoint.ask_string(protocol.wallet_tx_sign, o, a2);
}

public void tx_send(string a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_tx_send, o, a1);
}

public void tx_decode(string a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_tx_decode, o, a1);
}

public void tx_check(string a0, Stream a1) {
    String o;
    o += to_string(a0) + ' ';
    endpoint.ask_string(protocol.wallet_tx_check, o, a1);
}

public void set_supply(byte[] a0, long a1, Stream a2) {
    String o;
    o += to_string(a0) + ' ';
    o += to_string(a1) + ' ';
    endpoint.ask_string(protocol.wallet_set_supply, o, a2);
}

//-/----------------generated by apitool- do not edit

//-/-------------------------------------------------------------------generated by make, do not edit

        public void store_kv(byte[] addr, String key, String value, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_store_kv, addr, key + ' ' + value);
            proc_ans(endpoint.sendrecv(d), os);
        }

        
        public void rm_kv(byte[] addr, String key, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_rm_kv, addr, key);
            proc_ans(endpoint.sendrecv(d), os);
        }

        
        public void rm_file(byte[] addr, byte[] filename, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_rm_file, addr, filename);
            proc_ans(endpoint.sendrecv(d), os);
        }

        
        public void search(byte[] addr, String keywords, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_search, addr, keywords);
            proc_ans(endpoint.sendrecv(d), os);
        }

        
        public void store_file(byte[] addr, byte[] content, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_store_file, addr, content);
            proc_ans(endpoint.sendrecv(d), os);
        }

        
        public void file(byte[] addr, Stream os)
        {
            datagram d = new datagram((ushort)protocol.wallet_file, addr);
            pair<String, datagram> r = endpoint.sendrecv(d);
            if (r.first != null)
            {
                try
                {                    
                    os.Write(Encoding.UTF8.GetBytes(r.first), 0, Encoding.UTF8.GetBytes(r.first).Length);
                }
                catch (IOException e)
                {
                    try
                    {                                                
                        os.Write(Encoding.UTF8.GetBytes(e.Message), 0, Encoding.UTF8.GetBytes(e.Message).Length);
                    }
                    catch (Exception)
                    {
                    }
                }
            }
            else
            {
                try
                {                                        
                    os.Write(r.second.parse_bin(), 0, r.second.parse_bin().Length);
                }
                catch (IOException e)
                {
                    try
                    {
                        os.Write(Encoding.UTF8.GetBytes(e.Message), 0, Encoding.UTF8.GetBytes(e.Message).Length);
                    }
                    catch (Exception)
                    {
                    }
                }
            }

        }


        void proc_ans(pair<String, datagram> r, Stream os)
        {
            if (r.first != null)
            {
                try
                {
                    os.Write(Encoding.UTF8.GetBytes(r.first), 0, Encoding.UTF8.GetBytes(r.first).Length);
                }
                catch (IOException e)
                {
                    try
                    {
                        os.Write(Encoding.UTF8.GetBytes(e.Message), 0, Encoding.UTF8.GetBytes(e.Message).Length);
                    }
                    catch (Exception)
                    {
                    }
                }
            }
            else
            {
                try
                {
                    os.Write(Encoding.UTF8.GetBytes(r.second.parse_string()), 0, r.second.parse_string().Length);                    
                }
                catch (IOException e)
                {
                    try
                    {
                        os.Write(Encoding.UTF8.GetBytes(e.Message), 0, Encoding.UTF8.GetBytes(e.Message).Length);
                    }
                    catch (Exception)
                    {
                    }
                }
            }
        }


        rpc_api endpoint;
    }
}
