using System.Net.Sockets;

namespace us.gov.socket
{
    public class peer_t : client
    {
        public peer_t() : base()
        {
        }

        public peer_t(Socket sock): base(sock)
        {            
        }

        public bool is_slow()
        {
            return false;
        }

        public bool ping()
        {
            return send3(new datagram((ushort)protocol.gov_socket_ping, "ping")) == null;
        }

        public void process_pong()
        {
        }

        public virtual bool process_work(datagram d)
        {
            if (d.service.Equals(protocol.gov_socket_ping))
            {
                send3(new datagram((ushort)protocol.gov_socket_pong, "pong"));
            }
            else if (d.service.Equals(protocol.gov_socket_pong))
            {
                process_pong();                
            }
            else {
                return false;
            }
            return true;
        }

    }
}
