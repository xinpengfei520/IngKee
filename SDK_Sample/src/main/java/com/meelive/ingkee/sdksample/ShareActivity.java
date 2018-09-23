package com.meelive.ingkee.sdksample;

import android.app.Activity;
import android.os.Bundle;

public class ShareActivity extends Activity {

    public static final String SHARE_EXTRA = "share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
    }
}
