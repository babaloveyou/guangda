package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xuemei on 2016/3/9.
 * 跑马灯效果
 */
public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet atts) {
        super(context,atts);
    }

    public MarqueeTextView(Context context, AttributeSet atts, int style) {
        super(context,atts,style);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
