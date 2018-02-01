package com.example.android.tretyakovgalleryquiz;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements QuestionFragment.onQuestionFragmentInteractionListener, IntroductionFragment.onIntroductionFragmentInteractionListener, ResultFragment.OnResultFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    private int mCurrentStep = 0;
    private String mName;
    private String mEmail;
    private Question mCurrentQuestion;
    private boolean isScoring;

    private Question[] mQuestions = {
            new Question(
                    R.string.question_1,
                    R.drawable.pic_1,
                    R.array.answers_question_1,
                    R.id.answer_1_button)/*,
            new Question(
                    R.string.question_2,
                    R.drawable.pic_2,
                    R.array.answers_question_2,
                    R.id.answer_2_button),
            new Question(
                    R.string.question_3,
                    R.drawable.pic_3,
                    R.array.answers_question_3,
                    R.id.answer_3_button)*/
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey("mCurrentStep")) {
            mCurrentStep = savedInstanceState.getInt("mCurrentStep");
        }

        // В зависимости от шага отображается приветственный фрагмент /
        // фрагмент с вопросом / результирующий фрагмент
        if (mCurrentStep == 0) {
            setIntroductionFragment();
        } else if (mCurrentStep == mQuestions.length) {
            setResultFragment();
        } else {
            setQuestionFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mCurrentStep", mCurrentStep);
    }

    private void setQuestionFragment() {
        setTitleQuestion(mCurrentStep);

        QuestionFragment fragment = new QuestionFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        mCurrentQuestion = mQuestions[mCurrentStep];

        fragment.setAnswerData(mCurrentQuestion);
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

    private void setResultFragment() {
        resetTitle();

        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        resultFragment.setResultData(mName, isScoring);

        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, resultFragment);
        // Добавить в стек возврата
        fragmentTransaction.addToBackStack(null);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    @Override
    public void onQuestionFragmentInteraction(int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        int correctAnswer = mCurrentQuestion.getCorrectAnswerId();

        if (correctAnswer != id) {
            builder.setTitle(R.string.wrong)
                    .setMessage(getString(R.string.wrong_answer_text) + ((Button) findViewById(correctAnswer)).getText())
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
                        if (++mCurrentStep < mQuestions.length) {
                            setQuestionFragment();
                        } else {
                            // Если вопрос последний, отображается результирующий фрагмент
                            setResultFragment();
                        }

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onIntroductionFragmentInteraction(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;

        setQuestionFragment();
    }

    @Override
    public void onResultFragmentInteraction(String email) {
        this.mEmail = email;

        Log.d(TAG, email);
    }

    @Override
    public void onExitButtonClicked() {
        finish();
    }

    private void setTitleQuestion(int step) {
        setTitle("Question " + ++step + "/" + mQuestions.length);
    }

    private void resetTitle() {
        setTitle(getString(R.string.app_name));
    }
}
