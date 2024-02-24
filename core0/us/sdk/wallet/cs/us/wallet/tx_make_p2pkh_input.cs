using System;
using static us.gov.cash.tx;

namespace us.wallet
{
    public class tx_make_p2pkh_input
    {
        public tx_make_p2pkh_input(String rcpt_addr0, long amount0, /*String token0, sigcode_t sigcode_inputs0, sigcode_t sigcode_outputs0, */ bool sendover0)
        {
            src_addr = "";
            rcpt_addr = rcpt_addr0;
            amount = amount0;
            sendover = sendover0;
            if (amount < 1) amount = 0;
            //if (fee < 1) fee = 0;
            //sigcode_inputs = sigcode_inputs0;
            //sigcode_outputs = sigcode_outputs0;            
        }

        String src_addr;
        String rcpt_addr;
        long amount;
        //String token;
        //sigcode_t sigcode_inputs;
        //sigcode_t sigcode_outputs;
        bool sendover;

        public bool check()
        {
            //return fee > 0 && amount > 0 && !rcpt_addr.Equals(String.Empty);
            return amount > 0 && !rcpt_addr.Equals(String.Empty);
        }

        public String to_string()
        {
            return "11111111111111111111 " + rcpt_addr + " " + amount + " "/* + token + " " + (short)sigcode_inputs + " " + (short)sigcode_outputs + " "*/ + (sendover ? "1" : "0");
        }
    }
}
