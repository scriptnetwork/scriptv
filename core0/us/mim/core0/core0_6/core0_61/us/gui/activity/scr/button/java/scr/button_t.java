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
import android.util.AttributeSet;                                                              // AttributeSet
import android.content.Context;                                                                // Context
import android.widget.ImageButton;                                                             // ImageButton
import android.view.KeyEvent;                                                                  // KeyEvent
import android.widget.LinearLayout;                                                            // LinearLayout
import android.view.MotionEvent;                                                               // MotionEvent
import androidx.annotation.StyleableRes;                                                       // StyleableRes
import android.widget.TextView;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import android.content.res.TypedArray;                                                         // TypedArray
import android.view.View;                                                                      // View
import android.animation.ObjectAnimator;
import android.animation.AnimatorInflater;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import us.ko;
import androidx.core.content.ContextCompat;                                                    // ContextCompat
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Gravity;
import us.cash.R;
import android.graphics.drawable.BitmapDrawable;

public class button_t extends ImageButton implements View.OnClickListener, dbg_mim_t {

    private static void log(final String line) {               //--strip
        us.cash.CFG.log_scr("button_t: " + line);              //--strip
    }                                                          //--strip

    @Override public String mim_vertex_path() {
        return "core0/core0_6/core0_61/us/gui/activity/scr/button";
    }

    public button_t(Context context, final int imgid, OnClickListener listener_) {
        super(context);
        log("constructor"); //--strip
        listener = listener_;
        if (layout == null) {
            layout = us.cash.app.a.le.layout_params_wrap;
            //new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //layout.gravity = Gravity.CENTER | Gravity.CENTER_VERTICAL;
//            layout.setMargins(us.cash.app.a.le.border_button, us.cash.app.a.le.border_button, us.cash.app.a.le.border_button, us.cash.app.a.le.border_button); //left, top, right, bottom
            //layout.setMargins(us.cash.app.a.le.border_button, 0, us.cash.app.a.le.border_button, 0); //left, top, right, bottom
        }
        setLayoutParams(layout);
//        setPadding(0, 0, 0, 0);
        setPadding(1, 1, 1, 1);
        setBackgroundResource(android.R.color.transparent);
        if (listener != null) {
            setClickable(true);
        }
        setImageResource(us.cash.app.a.le.resolve_resid(imgid));
        check_animator();
    }

    public button_t(Context context, LinearLayout.LayoutParams layout_, final BitmapDrawable img, OnClickListener listener_) {
        super(context);
        log("constructor"); //--strip
        listener = listener_;
        setLayoutParams(layout_);
        setBackgroundResource(android.R.color.transparent);
        if (listener != null) {
            setClickable(true);
        }
        setImageDrawable(img);
        check_animator();
    }

    public button_t(Context context, LinearLayout.LayoutParams layout_, final int imgid, OnClickListener listener_) {
        super(context);
        log("constructor"); //--strip
        listener = listener_;
        setLayoutParams(layout_);
        setBackgroundResource(android.R.color.transparent);
        if (listener != null) {
            setClickable(true);
        }
        setImageResource(us.cash.app.a.le.resolve_resid(imgid));
        check_animator();
    }

    void check_animator() {
        if (animator != null) {
            return;
        }
        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.anim.onclick);
        animator.addListener(new Animator.AnimatorListener() {

            @Override public void onAnimationStart(Animator animation) {
                log("animator_lis " + animation);  //--strip
                if (target == null) return;
                target = ((button_t)((ObjectAnimator)animation).getTarget());
            }

            @Override public void onAnimationEnd(Animator animation) {
                log("animator ended " + animation); //--strip
                if (target == null) return;
                target.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override public void onAnimationCancel(Animator animation) {
                if (target == null) return;
                target.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override public void onAnimationRepeat(Animator animation) {
            }

            button_t target;

        });
    }

    @Override public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            log("KeyEvent.ACTION_UP from KEYCODE_DPAD_CENTER or KEYCODE_ENTER"); //--strip
            call_animation();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            log("MotionEvent.ACTION_DOWN"); //--strip
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            log("MotionEvent.ACTION_MOVE"); //--strip
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            log("MotionEvent.ACTION_UP"); //--strip
            call_animation();
        }
        else {
            log("MotionEvent.ACTION_else " + event.getAction()); //--strip
        }
        return super.dispatchTouchEvent(event);
    }

    @Override public void onClick(View view) {
    }

    void call_animation() {
        log("call_animation"); //--strip
        if (listener == null) {
            log("KO 68577 No click listener for this button."); //--strip
            return;
        }
        log("scheduling animation"); //--strip
        final button_t b = this;
        post(new Runnable() {
            @Override public void run() {
                log("starting animation"); //--strip
                animator.cancel();
                button_t t = (button_t) animator.getTarget();
                if (t != null) {
                    log("restoring background of button " + t); //--strip
                    t.setBackgroundColor(Color.TRANSPARENT); //sometimes other button is abruptly stopped because of us leaving it yellow
                }
                log("animator_  set_target " + this); //--strip
                animator.setTarget(b); //--strip
                animator.start();
            }
        });
        log("calling listener soon..."); //--strip
        postDelayed(new Runnable() {
            @Override public void run() {
                log("calling listener"); //--strip
                listener.onClick(b);
            }
        }, 10);
    }

    @Override public void setOnClickListener(OnClickListener listener_) {
        log("setOnClickListener"); //--strip
        listener = listener_;
        if (listener != null) {
            setVisibility(View.VISIBLE);
        }
        else {
            setVisibility(View.GONE);
        }
    }
/*
    public void set_stock_image(int i) {
        switch (i) {
            case 2:
                setImageResource(R.drawable.ic_pay_48dp);
            break;
            case 3:
                setImageResource(R.raw.create);
            break;
            default:
                setImageResource(R.drawable.ic_send_48dp);
        }
    }
*/
    OnClickListener listener = null;
    static ObjectAnimator animator;
    static LinearLayout.LayoutParams layout;
}

