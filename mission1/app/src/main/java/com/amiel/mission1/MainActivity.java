package com.amiel.mission1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static java.util.Arrays.*;

public class MainActivity extends AppCompatActivity {

    /*
    Player representation:
    0 - O
    1 - X
    */
    int activePlayer;
    ImageView status;
    ImageView[] gridBoxes;

    /*
    State explanation:
    -1 - Null
    1 - X
    0 - O
    */
    int[] state;

    // All winning options and appropriate win mark image
    static final int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    static final int[] winPositionsImage = {R.drawable.mark7, R.drawable.mark7, R.drawable.mark7, R.drawable.mark4, R.drawable.mark4, R.drawable.mark4, R.drawable.mark1, R.drawable.mark2};

    // Counter for turns taken
    public static int counter = 0;

    // this function will be called every time a
    // players tap in an empty box of the grid
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        status = findViewById(R.id.status);

        // If the tapped image is NULL
        if (state[tappedImage] == -1) {

            // Increase the counter
            counter++;

            // Mark this position
            state[tappedImage] = activePlayer;

            // Set transition effect
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                // Set the image of o and change status
                img.setImageResource(R.drawable.o);
                status.setImageResource(R.drawable.xplay);
            } else {
                // Set the image of x and change status
                img.setImageResource(R.drawable.x);
                status.setImageResource(R.drawable.oplay);
            }

            // Change active player
            activePlayer = 1 - activePlayer;

            img.animate().translationYBy(1000f).setDuration(300);
        }

        int flag = 0;

        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (state[winPosition[0]] == state[winPosition[1]] &&
                    state[winPosition[1]] == state[winPosition[2]] &&
                    state[winPosition[0]] != -1) {
                flag = 1;

                // Set appropriate winning status
                int winImageRes = state[winPosition[0]] == 0 ? R.drawable.owin : R.drawable.xwin;
                status.setImageResource(winImageRes);

                // Draw the appropriate winning mark
                for (int winIndex : winPosition) {
                    gridBoxes[winIndex].setBackground(gridBoxes[winIndex].getDrawable());
                    gridBoxes[winIndex].setImageResource(winPositionsImage[asList(winPositions).indexOf(winPosition)]);
                }

                // Display play again dialog
                showPlayAgainDialog();
            }
        }

        // Set the status if the match draw and display play again dialog
        if (counter == 9 && flag == 0) {
            status.setImageResource(R.drawable.nowin);
            showPlayAgainDialog();
        }
    }

    public void showPlayAgainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Add the buttons
        builder.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                restartGame();
            }
        });

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setCancelable(false);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Remove all images from boxes and restart the game
    public void restartGame() {
        activePlayer = 1;
        fill(state, -1);

        // Remove all the images from the boxes inside the grid
        for (ImageView gridBox : gridBoxes) {
            gridBox.setImageResource(0);
            gridBox.setBackgroundResource(0);
        }

        status.setImageResource(R.drawable.xplay);
        counter = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // X starts the game
        activePlayer = 1;

        // Set initial state
        state = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1};

        gridBoxes = new ImageView[] {
                (findViewById(R.id.box1)),
                (findViewById(R.id.box2)),
                (findViewById(R.id.box3)),
                (findViewById(R.id.box4)),
                (findViewById(R.id.box5)),
                (findViewById(R.id.box6)),
                (findViewById(R.id.box7)),
                (findViewById(R.id.box8)),
                (findViewById(R.id.box9))
        };
    }
}