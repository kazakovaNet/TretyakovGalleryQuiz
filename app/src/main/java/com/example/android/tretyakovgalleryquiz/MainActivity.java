package com.example.android.tretyakovgalleryquiz;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements PictureAnswerFragment.PictureAnswerListener, IntroductionFragment.IntroductionListener {
    private int[] correctAnswers = new int[]{R.id.answer_1_button, R.id.answer_2_button, R.id.answer_3_button, R.id.answer_4_button, R.id.answer_1_button};
    private int step = 0;
    public static Resources resources;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        resources = getResources();

        if (savedInstanceState != null && savedInstanceState.containsKey("step")) {
            step = savedInstanceState.getInt("step");
        }

        setIntroductionFragment();
    }

    @Override
    public void onButtonClicked(long id, String correctAnswer) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        if (correctAnswers[step] != id) {
            builder.setTitle(R.string.wrong)
                    .setMessage(getString(R.string.wrong_answer_text) + " " + correctAnswer)
                    .setIcon(R.drawable.wrong_icon);
        } else {
            builder.setTitle(R.string.right)
                    .setMessage(R.string.right_answer_text)
                    .setIcon(R.drawable.right_icon);
        }

        builder.setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (++step < PictureQuestion.PICTURE_QUESTIONS_DATA.length) {
                            setPictureAnswerFragment();
                        } else {
                            builder.setTitle(R.string.end)
                                    .setMessage(R.string.end_text)
                                    .setIcon(R.drawable.end_icon).setNegativeButton("OK", new DialogInterface.OnClickListener() {
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

    private void setPictureAnswerFragment() {
        setTitleQuestion(step);

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

    private void setIntroductionFragment() {
        IntroductionFragment introductionFragment = new IntroductionFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, introductionFragment);
        // Добавить в стек возврата
        fragmentTransaction.addToBackStack(null);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    private void setTitleQuestion(int step) {
        setTitle("Question " + ++step + "/" + PictureQuestion.PICTURE_QUESTIONS_DATA.length);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("step", step);
    }

    @Override
    public void onIntroductionClicked(String name) {
        this.name = name;
    }
}
