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
import us.wallet.*;
import us.gov.crypto.base58;
import us.gov.crypto.ec;
import us.gov.crypto.symmetric_encryption;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.GeneralSecurityException;
import us.pair;
import us.ko;

public class Main{
    public static void main(String [ ] args) throws GeneralSecurityException{
        if (args.length <= 3) {
            return;
        }
                ec.create_instance();

                PrivateKey privateKey = ec.instance.get_private_key(args[0]);
                PublicKey publicKey = ec.instance.get_public_key(args[1]);


//                byte[] priv=base58.decode(args[0]);
//                byte[] pub=base58.decode(args[1]);
                String command = args[2];

//                PrivateKey privateKey = EllipticCryptography.secp256k1.getPrivateKey(priv);
//                PublicKey publicKey = EllipticCryptography.secp256k1.getPublicKey(pub);

                symmetric_encryption se = new symmetric_encryption();
                se.init(privateKey, publicKey);

                if (command.equals("decrypt")) {

                    byte[] message = base58.decode(args[3]);

                    pair<ko, byte[]> r = se.decrypt(message);
                    byte[] decrypted = r.second;
                    String decrypted_string = new String(decrypted);
                    System.out.println(decrypted_string);
                }
                if (command.equals("encrypt")) {

                    byte[] message = args[3].getBytes();

                    pair<ko, byte[]> r = se.encrypt(message);
                    byte[] encrypted = r.second;
                    System.out.println(base58.encode(encrypted));
                }



    }



}
