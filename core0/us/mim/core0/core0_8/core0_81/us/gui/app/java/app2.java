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
//MIM  ******************************************************************************
//MIM  EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
//MIM  The first version of this file was produced by MIM. You can edit it afterwards.
//MIM
//MIM  Source:
//MIM    mim file: core0/core0_8/core0_81/us/gui/app/mim.h
//MIM  kickoff code hash: 3x2po66xeWmW9MhQmPaDhqfcHe4K (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;

import android.content.Intent;
import android.os.Handler;

public class app2 extends app1 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app2: " + line);          //--strip
    }                                                                         //--strip

    public app2() {
        super();
    }

    protected void add_flags_main_menu_task_launch(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    public void go_certs() {
        assert_ui_thread(); //--strip
        log("menu certs"); //--strip
        Intent intent = new Intent(getApplicationContext(), certs__conf.class);
        add_flags_main_menu_task_launch(intent);
        main.startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.fairluckico) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        go_certs();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }

    void test_cert() {                           //--strip
        go_certs();
    }                                            //--strip

    @Override public void run_test_seqs() {      //--strip
        super.run_test_seqs();                   //--strip
//        test_cert();                             //--strip
    }

}

