package com.example.android.tretyakovgalleryquiz;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
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
        // Закрытие открытого тоста
        if (toast != null) {
            toast.cancel();
        }

        if (correctAnswers[step] != id) {
            toast = Toast.makeText(this, "You gave the wrong answer. Correct answer - " + correctAnswer, Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(this, "You are absolutely right!", Toast.LENGTH_SHORT);
        }

        toast.show();

        if (++step < PictureQuestion.PICTURE_QUESTIONS_DATA.length) {
            setNewFragment();
        } else {
            toast = Toast.makeText(this, "You answered all questions", Toast.LENGTH_SHORT);
            toast.show();
        }
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
