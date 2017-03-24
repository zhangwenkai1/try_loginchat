package com.example.administrator.try_loginchat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/3/24.
 */

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;


    class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("警告！");
            builder.setCancelable(false);
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();//销毁所有活动
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
