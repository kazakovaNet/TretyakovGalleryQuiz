package com.example.android.tretyakovgalleryquiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PictureAnswerFragment.PictureAnswerListener {
    private int[] correctAnswers = new int[]{R.id.answer_1_button, R.id.answer_2_button, R.id.answer_3_button, R.id.answer_4_button, R.id.answer_1_button};
    private int step = 0;
    private Toast toast;
    public static Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();

        setNewFragment();
    }

    @SuppressLint("ShowToast")
    @Override
    public void onButtonClicked(long id, String correctAnswer) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        if (correctAnswers[step] != id) {
            builder.setTitle("Wrong")
                    .setMessage("You gave the wrong answer. Correct answer - " + correctAnswer)
                    .setIcon(R.drawable.ic_launcher_background);
        } else {
            builder.setTitle("Right")
                    .setMessage("You are absolutely right!")
                    .setIcon(R.drawable.ic_launcher_background);
        }

        builder.setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (++step < PictureQuestion.PICTURE_QUESTIONS_DATA.length) {
                            setNewFragment();
                        } else {
                            builder.setTitle("End")
                                    .setMessage("You answered all questions")
                                    .setIcon(R.drawable.ic_launcher_background).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setNewFragment() {
        PictureAnswerFragment fragment = new PictureAnswerFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        fragment.setAnswerData(step);
        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // Добавить в стек возврата
        fragmentTransaction.addToBackStack(null);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }
}
