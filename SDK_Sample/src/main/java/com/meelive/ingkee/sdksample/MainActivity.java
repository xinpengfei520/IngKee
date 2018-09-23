package com.meelive.ingkee.sdksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meelive.ingkee.sdk.plugin.IInkeCallback;
import com.meelive.ingkee.sdk.plugin.InKeSdkPluginAPI;
import com.meelive.ingkee.sdk.plugin.entity.ShareInfo;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {

    // APP_ID 替换为你申请的APPID
    private static final String APP_ID = "1000010001";

    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InKeSdkPluginAPI.register(inkeCallback, APP_ID);

        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
    }

    // IInkeCallback 接口的实现，用于SDK通知宿主需要执行登录、支付、分享操作
    IInkeCallback inkeCallback = new IInkeCallback() {
        @Override
        public void loginTrigger() {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void payTrigger(String orderId, String callString) {
            Log.d(TAG, "pay:" + orderId + "|callString:" + callString);
            //TODO 处理支付逻辑
            //......
            //TODO 处理支付逻辑后，把orderId和支付状态传递给SDK
            InKeSdkPluginAPI.dealPay(orderId, true);
        }

        @Override
        public void shareTrigger(ShareInfo share) {
            Log.d(TAG, "share.");
            //TODO 处理分享逻辑
            //......
            //toast("shareTrigger()");
            StringBuffer shareSb = new StringBuffer();
            shareSb.append("platform:").append(share.platform);
            shareSb.append("<br/>text:").append(share.text);
            shareSb.append("<br/>content:").append(share.content);
            shareSb.append("<br/>url:").append(share.url);
            shareSb.append("<br/>picUrl:").append(share.picUrl);

            Intent intent = new Intent(MainActivity.this, ShareActivity.class);
            intent.putExtra(ShareActivity.SHARE_EXTRA, shareSb.toString());
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnter:
                InKeSdkPluginAPI.start(MainActivity.this, null, "");
                break;
            default:
                break;
        }
    }
}
