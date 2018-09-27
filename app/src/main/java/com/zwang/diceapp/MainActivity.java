package com.zwang.diceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Random rand = new Random();
    private static final int[] dicts =
            {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView diceImage1 = (ImageView)findViewById(R.id.dice_1);
        final ImageView diceImage2 = (ImageView)findViewById(R.id.dice_2);
        diceImage1.setImageResource(dicts[0]);
        diceImage2.setImageResource(dicts[0]);

        final Button roll = (Button)findViewById(R.id.dice_button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ImageView diceImage1 = (ImageView)findViewById(R.id.dice_1);
                final ImageView diceImage2 = (ImageView)findViewById(R.id.dice_2);
                diceImage1.setImageResource(dicts[0]);
                diceImage2.setImageResource(dicts[0]);

                Toast.makeText(MainActivity.this, R.string.dice_button_popup, Toast.LENGTH_SHORT).show();

                roll.setClickable(false);

                try {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final int dice_1 = rand.nextInt(6);
                                    final int dice_2 = rand.nextInt(6);

                                    final ImageView diceImage1 = (ImageView)findViewById(R.id.dice_1);
                                    final ImageView diceImage2 = (ImageView)findViewById(R.id.dice_2);
                                    diceImage1.setImageResource(dicts[dice_1]);
                                    diceImage2.setImageResource(dicts[dice_2]);

                                    final int total = dice_1 + dice_2 + 2;
                                    final String text = "The number is " + total;
                                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

                                    roll.setClickable(true);
                                }
                            });
                        }
                    });
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
