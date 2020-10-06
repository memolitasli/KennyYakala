package com.example.kennyyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
int skor = 0;
TextView tv_skor;
TextView tv_zaman;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

  @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_skor = (TextView)findViewById(R.id.tvt_skor_id);
        tv_zaman = (TextView)findViewById(R.id.tv_sayac);

        img1 =  (ImageView)findViewById(R.id.imageView1);
       img2 = (ImageView)findViewById(R.id.imageView2);
       img3 = (ImageView)findViewById(R.id.imageView3);
       img4 = (ImageView)findViewById(R.id.imageView4);
       img5 = (ImageView)findViewById(R.id.imageView5);
       img6 = (ImageView)findViewById(R.id.imageView6);
       img7 = (ImageView)findViewById(R.id.imageView7);
       img8 = (ImageView)findViewById(R.id.imageView8);
       img9 = (ImageView)findViewById(R.id.imageView9);

        imageArray = new ImageView[]{
          img1,img2,img3,img4,img5,img6,img7,img8,img9
        };
        hideImages();


        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long l) {
                  tv_zaman.setText("Kalan Sure : " + l/1000);
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Oyun Bitti");
                alert.setMessage("Yeniden Başlamak ister misin ? ");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // oyunu baştan başlat
                        Intent intent = getIntent();
                        finish(); // güncel aktivite bitti
                        startActivity(intent); // aynı aktiviteyi baştan başlattım
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Oyun Bitti",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

  }

  public void hideImages(){
    handler = new Handler();
    runnable = new Runnable() {
        @Override
        public void run() {
            for(ImageView image: imageArray){
                image.setVisibility(View.INVISIBLE);
            }

            Random rnd = new Random();
            int i = rnd.nextInt(9);
            imageArray[i].setVisibility(View.VISIBLE);
            handler.postDelayed(this,500);
        }
    };
    handler.post(runnable);
  }

    //bir buttona veya image gibi nesneye onclick atarken view parametresini vermem gerekiyor
public void kennyTiklama(View view){
skor++;
tv_skor.setText("Skor :" + skor);

}
}