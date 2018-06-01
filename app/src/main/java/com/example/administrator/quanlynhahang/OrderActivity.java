package com.example.administrator.quanlynhahang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class OrderActivity extends AppCompatActivity {

    Button btnRung,btnCance,btnGuiTinHieu;
    Vibrator v;
    AlertDialog.Builder alertDialogBuilder;
    Dialog dialog;
    String s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnRung = findViewById(R.id.btnRung);
        btnCance = findViewById(R.id.btnCancel);
        btnGuiTinHieu = findViewById(R.id.btnGui);
        s = "android gui tin hieu";
        btnGuiTinHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSocket.emit("android", s);
            }
        });
        btnRung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rung(1);
                showAlertDialog();
            }
        });
        btnCance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rung(0);
            }
        });
        mSocket.connect();
        mSocket.on("sendandroid", onNewMessage);



    }


    public  void Rung(int i){
        if (i == 1){
            v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            //deprecated in API 26
            v.vibrate(10000);
        }
        else if( i == 0)
        {
            v.cancel();
        }
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phan Trong");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, "Không thoát được", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                v.cancel();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.50.100.179:3000");
        } catch (URISyntaxException e) {}
    }
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Rung(1);
                    showAlertDialog();
                }
            });
        }
    };


}
