package com.fancytank.ognia.muzeumwojska.details;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancytank.ognia.muzeumwojska.R;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayParagraph;

public class ParagraphView extends FrameLayout {
    ImageView image;
    TextView text;

    public ParagraphView(Context context) {
        super(context);
        init(context);
    }

    public ParagraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ParagraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setData(DisplayParagraph data) {

    }

    private void init(Context context) {
        inflate(context, R.layout.paragraph_view, this);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
    }
}
