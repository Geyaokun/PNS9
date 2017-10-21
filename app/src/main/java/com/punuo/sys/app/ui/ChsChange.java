package com.punuo.sys.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.punuo.sys.app.R;
import com.punuo.sys.app.adapter.ClusterAdapter;
import com.punuo.sys.app.groupvoice.GroupInfo;
import com.punuo.sys.app.groupvoice.GroupKeepAlive;
import com.punuo.sys.app.groupvoice.GroupUdpThread;
import com.punuo.sys.app.model.Cluster;
import com.punuo.sys.app.sip.BodyFactory;
import com.punuo.sys.app.sip.SipInfo;
import com.punuo.sys.app.sip.SipMessageFactory;
import com.punuo.sys.app.sip.SipUser;
import com.punuo.sys.app.tools.MyToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.punuo.sys.app.sip.SipInfo.cacheClusters;
import static com.punuo.sys.app.sip.SipInfo.clusters;
import static com.punuo.sys.app.sip.SipInfo.serverIp;
import static com.punuo.sys.app.sip.SipInfo.sipUser;

/**
 * Author chzjy
 * Date 2016/12/19.
 * 集群呼叫频道更换
 */
public class ChsChange extends Activity implements SipUser.ClusterNotifyListener {
    @Bind(R.id.btnCall)
    ImageButton btnCall;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;
    @Bind(R.id.btn6)
    Button btn6;
    ListView clusterList;
    int i = 1;
    int j = 1;


    public ClusterAdapter clusterAdapter;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        GroupInfo.rtpAudio.changeParticipant(serverIp, GroupInfo.port);
                        GroupInfo.groupUdpThread = new GroupUdpThread(serverIp, GroupInfo.port);
                        GroupInfo.groupUdpThread.startThread();
                        GroupInfo.groupKeepAlive = new GroupKeepAlive();
                        GroupInfo.groupKeepAlive.startThread();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                title.setText("频道更换(当前:频道" + (GroupInfo.port % 7000 + 1) + ")");
                                MyToast.show(ChsChange.this, "频道切换至" + (GroupInfo.port % 7000 + 1), Toast.LENGTH_SHORT);
                            }
                        });
                    }
                }
            }.start();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chschange);
        sipUser.setClusterNotifyListener(this);
        ButterKnife.bind(this);
        clusterList = (ListView) findViewById(R.id.cluster_list);
        title.setText("频道更换(当前:频道" + (GroupInfo.port % 7000 + 1) + ")");
        clusterAdapter = new ClusterAdapter(ChsChange.this);
        clusterList.setAdapter(clusterAdapter);
        org.zoolu.sip.message.Message query_channel = SipMessageFactory.createSubscribeRequest(
                SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createClusterGroupQueryBody(GroupInfo.port % 7000 + 1));
        SipInfo.sipUser.sendMessage(query_channel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        GroupInfo.groupUdpThread.stopThread();
        GroupInfo.groupKeepAlive.stopThread();
        switch (view.getId()) {
            case R.id.btn1:
                GroupInfo.port = 7000;
                org.zoolu.sip.message.Message notify_channel_1 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "1"));
                SipInfo.sipUser.sendMessage(notify_channel_1);
                i = 1;
                break;
            case R.id.btn2:
                GroupInfo.port = 7001;
                org.zoolu.sip.message.Message notify_channel_2 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "2"));
                SipInfo.sipUser.sendMessage(notify_channel_2);
                i = 2;
                break;
            case R.id.btn3:
                GroupInfo.port = 7002;
                org.zoolu.sip.message.Message notify_channel_3 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "3"));
                SipInfo.sipUser.sendMessage(notify_channel_3);
                i = 3;
                break;
            case R.id.btn4:
                GroupInfo.port = 7003;
                org.zoolu.sip.message.Message notify_channel_4 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "4"));
                SipInfo.sipUser.sendMessage(notify_channel_4);
                i = 4;
                break;
            case R.id.btn5:
                GroupInfo.port = 7004;
                org.zoolu.sip.message.Message notify_channel_5 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "5"));
                SipInfo.sipUser.sendMessage(notify_channel_5);
                i = 5;
                break;
            case R.id.btn6:
                GroupInfo.port = 7005;
                org.zoolu.sip.message.Message notify_channel_6 = SipMessageFactory.createNotifyRequest(
                        SipInfo.sipUser, SipInfo.user_to, SipInfo.user_from, BodyFactory.createChangeDevClusterGroupBody(SipInfo.userId, i, "6"));
                SipInfo.sipUser.sendMessage(notify_channel_6);
                i = 6;
                break;
        }
        handler.sendEmptyMessage(0x1111);
    }

    @Override
    public void onNotify() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!SipInfo.cacheClusters.isEmpty()) {
                    clusterAdapter.appendData(SipInfo.cacheClusters);
                }
            }
        });

    }
}
