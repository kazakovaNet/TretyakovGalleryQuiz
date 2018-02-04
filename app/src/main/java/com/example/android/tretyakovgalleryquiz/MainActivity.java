package com.example.android.tretyakovgalleryquiz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements QuestionFragment.onQuestionFragmentInteractionListener, IntroductionFragment.onIntroductionFragmentInteractionListener, ResultFragment.OnResultFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    private Question mCurrentQuestion;
    private String mName;
    private boolean isScoring;
    private int mScore;
    private int mCurrentStep = -1;

    private Question[] mQuestions = {
            new Question(
                    R.string.question_1,
                    R.drawable.pic_1,
                    R.array.answers_question_1,
                    R.id.answer_1_radio_button),
            new Question(
                    R.string.question_2,
                    R.drawable.pic_2,
                    R.array.answers_question_2,
                    R.id.answer_2_radio_button)/*,
            new Question(
                    R.string.question_3,
                    R.drawable.pic_3,
                    R.array.answers_question_3,
                    R.id.answer_3_radio_button),
            new Question(
                    R.string.question_4,
                    R.drawable.pic_4,
                    R.array.answers_question_4,
                    R.id.answer_1_radio_button),
            new Question(
                    R.string.question_5,
                    R.drawable.pic_5,
                    R.array.answers_question_5,
                    R.id.answer_3_radio_button),
            new Question(
                    R.string.question_6,
                    R.drawable.pic_6,
                    R.array.answers_question_6,
                    R.id.answer_4_radio_button),
            new Question(
                    R.string.question_7,
                    R.drawable.pic_7,
                    R.array.answers_question_7,
                    R.id.answer_4_radio_button)*/
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Восстанавление значения текущего шага
        if (savedInstanceState != null && savedInstanceState.containsKey("mCurrentStep")) {
            mCurrentStep = savedInstanceState.getInt("mCurrentStep");
        }

        // Восстанавление количества набранных очков
        if (savedInstanceState != null && savedInstanceState.containsKey("mScore")) {
            mScore = savedInstanceState.getInt("mScore");
        }

        // Восстановление имени
        if (savedInstanceState != null && savedInstanceState.containsKey("mName")) {
            mName = savedInstanceState.getString("mName");
        }

        // Восстановление галочки подсчета результатов
        if (savedInstanceState != null && savedInstanceState.containsKey("isScoring")) {
            isScoring = savedInstanceState.getBoolean("isScoring");
        }

        // В зависимости от шага отображается приветственный фрагмент /
        // фрагмент с вопросом / результирующий фрагмент
        if (mCurrentStep == -1) {
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

        // Сохранение значения текущего шага
        outState.putInt("mCurrentStep", mCurrentStep);
        // Сохранение количества набранных очков
        outState.putInt("mScore", mScore);
        // Сохранение имени
        outState.putString("mName", mName);
        // Сохранение галочки подсчета результатов
        outState.putBoolean("isScoring", isScoring);
    }

    private void setQuestionFragment() {
        setTitleQuestion(mCurrentStep);

        QuestionFragment fragment = new QuestionFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        mCurrentQuestion = mQuestions[mCurrentStep];

        fragment.initQuestionFragment(mCurrentQuestion);
        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    private void setIntroductionFragment() {
        IntroductionFragment introductionFragment = new IntroductionFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        // Передаем имя и состояние галочки
        if (mName != null) {
            introductionFragment.initIntroductionFragment(mName, isScoring);
        }

        // Добавить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, introductionFragment);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    private void setResultFragment() {
        resetTitle();

        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        resultFragment.initResultFragment(mName, isScoring, mScore, mQuestions.length);

        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, resultFragment);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    @Override
    public void onQuestionFragmentInteraction(int selectAnswer) {
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openQuestionDialog(mCurrentQuestion.getCorrectAnswerId(), selectAnswer);

        // Увеличение количества набранных очков, если установлен чекбокс подсчета результата
        if (isScoring) {
            mScore++;
        }
    }

    @Override
    public void onQuestionDialogClose() {
        if (++mCurrentStep < mQuestions.length) {
            setQuestionFragment();
        } else {
            // Если вопрос последний, отображается результирующий фрагмент
            setResultFragment();
        }
    }

    @Override
    public void onIntroductionFragmentInteraction(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;

        mCurrentStep++;

        setQuestionFragment();
    }

    @Override
    public void onIntroductionFragmentPause(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;
    }

    @Override
    public void onResultFragmentInteraction(String email) {
        sentResultOnEmail(email);
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

    public void sentResultOnEmail(String email) {
        String resultSummary = createResultSummary();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SENDTO);
        // only email apps should handle this
        sendIntent.setData(Uri.parse("mailto:"));
        // добавляем текст для передачи
        sendIntent.putExtra(Intent.EXTRA_TEXT, resultSummary);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.result_email_subject, mName));
        // запускаем активити
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    private String createResultSummary() {
        return getString(R.string.text_result_on_screen, mScore, mQuestions.length);
    }

    @Override
    public void onBackPressed() {
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openQuitDialog();
    }
}
