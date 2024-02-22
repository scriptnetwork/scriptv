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
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import us.cash.R;
import us.cash.CFG;

public class ##classname##__widgets extends listview_activity__widgets {

    private static void log(final String s) {                   //--strip
        CFG.log_scr("##classname##__widgets: " + s);            //--strip
    }                                                           //--strip

    public static class menu__modal_t extends menu_t {

        public menu__modal_t(Context ctx) {
            super(ctx, new menu_header_t(ctx, ##resiconmenu150##, "##menutitle1##", "##menutitle2##", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    //us.cash.app.a.toast(us.CFG.COPYRIGHT_LINE);
                }
            }));
        }

        @Override public menuspec_t create_spec() {
            menuspec_t spec = new menuspec_t(); {
                group_t g0 = new group_t(); {
                    g0.add(R.raw.bnew, "item1");
                    g0.add(R.raw.log, "item2");
                }
                group_t g1 = new group_t(); {
                    g1.add(R.raw.exit, "Exit activity");
                    g1.add(R.raw.burger, "Close menu");
                }
                spec.add(g0);
                spec.add(g1);
            }
            return spec;
        }

        public static menu__modal_t get_instance(Context ctx) {
            if (instance != null) return instance;
            instance = new menu__modal_t(ctx);
            return instance;
        }

        static menu__modal_t instance;
    }
    
    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        canvas_t canvas = new canvas_t(ctx, 10, 1); {
            /*
            text_view_t text_view = new text_view_t(ctx, 0); {
                text_view.setText("Connections");
                text_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                
                //int padding = us.cash.app.a.le.dp2px(150);
                //text_view.setPadding(padding, 0, padding, 0);
            }
            */
            list = new ##classname##__list_view_t(ctx); {
            }
            //canvas.addView(text_view);
            canvas.addView(list);
        }
        return canvas;
    }

}

