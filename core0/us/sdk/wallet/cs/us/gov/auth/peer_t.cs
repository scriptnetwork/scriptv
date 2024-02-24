using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace us.gov.auth
{
    public class peer_t : us.gov.id.peer_t
    {
        public peer_t(Socket sock) : base(sock)
        {            
        }

        public enum Stage_t
        {
            denied = 0,
            authorized = 1,
            num_stages = 2
        };
    
        public override void verification_completed()
        {
            if (!verification_is_fine())
            {
                return;
            }
            if (!authorize(pubkey))
            {
                return;
            }
            stage = Stage_t.authorized;
        }

        public virtual bool authorize(byte[] pub)
        {
            return false;
        }

        public Stage_t stage = Stage_t.denied;
    }
}
