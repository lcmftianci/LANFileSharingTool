package com.example.shinelon.flowerviewpager.MsgSendGet;

import android.app.Activity;
import android.media.AudioDeviceCallback;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.shinelon.flowerviewpager.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Shinelon on 2017/10/26.
 */

public class MsgActivity extends Activity {

    private Button client;
    private Button send;
    private Socket socket;
    private DataOutputStream writer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_activity);

        client = (Button)findViewById(R.id.Client);
        send = (Button)findViewById(R.id.Send);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try{
                            socket = new Socket("192.168.31.122", 8090);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if(socket == null){
                            try{
                                socket = new Socket("192.168.31.122", 8090);
                            }catch (UnknownHostException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }

                        if(writer == null){
                            try{
                                writer = new DataOutputStream(socket.getOutputStream());
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }

                        try{
                            writer.writeUTF("client 数据" + socket);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
