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

    private static final String DICE1 = "DICE1";
    private static final String DICE2 = "DICE2";

    private int dice1;
    private int dice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView diceImage1 = findViewById(R.id.dice_1);
        final ImageView diceImage2 = findViewById(R.id.dice_2);

        if (savedInstanceState != null) {
            dice1 = savedInstanceState.getInt(DICE1);
            dice2 = savedInstanceState.getInt(DICE2);
        }

        diceImage1.setImageResource(dicts[dice1]);
        diceImage2.setImageResource(dicts[dice2]);

        final Button roll = findViewById(R.id.dice_button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ImageView diceImage1 = findViewById(R.id.dice_1);
                final ImageView diceImage2 = findViewById(R.id.dice_2);
                diceImage1.setImageResource(dicts[0]);
                diceImage2.setImageResource(dicts[0]);

                Toast.makeText(MainActivity.this, R.string.dice_button_popup, Toast.LENGTH_SHORT).show();

                roll.setClickable(false);

                roll.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dice1 = rand.nextInt(6);
                        dice2 = rand.nextInt(6);

                        final ImageView diceImage1 = findViewById(R.id.dice_1);
                        final ImageView diceImage2 = findViewById(R.id.dice_2);
                        diceImage1.setImageResource(dicts[dice1]);
                        diceImage2.setImageResource(dicts[dice2]);

                        final int total = dice1 + dice2 + 2;
                        final String text = getString(R.string.dice_result) + " " + total;
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

                        roll.setClickable(true);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(DICE1, dice1);
        savedInstanceState.putInt(DICE2, dice2);
        super.onSaveInstanceState(savedInstanceState);
    }
}
