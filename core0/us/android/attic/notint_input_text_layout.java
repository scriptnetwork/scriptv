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
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import android.content.res.ColorStateList;

public class notint_input_text_layout extends TextInputLayout {

    public notint_input_text_layout(Context context) {
        super(context);
    }

    public notint_input_text_layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public notint_input_text_layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public void setEndIconTintList(ColorStateList tintList) {
        // Avoid tinting the end icon drawable
        super.setEndIconTintList(null);
    }

}
