package com.example.android.tretyakovgalleryquiz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener, QuestionWithEditTextFragment.onQuestionWithEditTextFragmentInteractionListener, IntroductionFragment.onIntroductionFragmentInteractionListener, ResultFragment.OnResultFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    private QuestionWithRadioButton mCurrentQuestionWithRadioButton;
    private String mName;
    private boolean isScoring;
    private int mScore;
    private int mCurrentStep = -1;

    private Question[] mQuestions = {
            new QuestionWithRadioButton(
                    R.string.question_1,
                    R.drawable.pic_1,
                    R.array.answers_question_1,
                    R.id.answer_1_radio_button),
            /*new QuestionWithRadioButton(
                    R.string.question_2,
                    R.drawable.pic_2,
                    R.array.answers_question_2,
                    R.id.answer_2_radio_button),
            new QuestionWithRadioButton(
                    R.string.question_3,
                    R.drawable.pic_3,
                    R.array.answers_question_3,
                    R.id.answer_3_radio_button),
            new QuestionWithRadioButton(
                    R.string.question_4,
                    R.drawable.pic_4,
                    R.array.answers_question_4,
                    R.id.answer_1_radio_button),
            new QuestionWithRadioButton(
                    R.string.question_5,
                    R.drawable.pic_5,
                    R.array.answers_question_5,
                    R.id.answer_3_radio_button),
            new QuestionWithRadioButton(
                    R.string.question_6,
                    R.drawable.pic_6,
                    R.array.answers_question_6,
                    R.id.answer_4_radio_button),
            new QuestionWithRadioButton(
                    R.string.question_7,
                    R.drawable.pic_7,
                    R.array.answers_question_7,
                    R.id.answer_4_radio_button)*/
            new QuestionWithEditText(
                    R.string.question_8,
                    R.drawable.pic_7,
                    "123"
            )
    };
    private QuestionWithEditText mCurrentQuestionWithEditText;

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
        } else if (mCurrentStep == 1) {
            setQuestionWithEditTextFragment();
        } else {
            setQuestionWithRadioButtonFragment();
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

    private void setQuestionWithRadioButtonFragment() {
        setTitleQuestion(mCurrentStep);

        QuestionWithRadioButtonFragment fragment = new QuestionWithRadioButtonFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        mCurrentQuestionWithRadioButton = (QuestionWithRadioButton) mQuestions[mCurrentStep];

        fragment.initQuestionWhithRadioButtonFragment(mCurrentQuestionWithRadioButton);
        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    private void setQuestionWithEditTextFragment() {
        setTitleQuestion(mCurrentStep);

        QuestionWithEditTextFragment fragment = new QuestionWithEditTextFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction(); // начало транзакции фрагмента

        mCurrentQuestionWithEditText = (QuestionWithEditText) mQuestions[mCurrentStep];

        fragment.initQuestionWithEditTextFragment(mCurrentQuestionWithEditText);
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
    public void onQuestionWithRadioButtonFragmentInteraction(int selectAnswer) {
        int correctAnswerId = mCurrentQuestionWithRadioButton.getCorrectAnswerId();

        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openQuestionWithRadioButtonDialog(correctAnswerId, selectAnswer);

        // Увеличение количества набранных очков, если ответ верен и установлен чекбокс подсчета результата
        if (isScoring && correctAnswerId == selectAnswer) {
            mScore++;
        }
    }

    @Override
    public void onQuestionWithEditTextFragmentInteraction(String enterAnswer) {
        String correctAnswer = mCurrentQuestionWithEditText.getCorrectAnswer();

        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openQuestionWithEditTextDialog(correctAnswer, enterAnswer);

        // Увеличение количества набранных очков, если ответ верен и установлен чекбокс подсчета результата
        if (isScoring && correctAnswer.equals(enterAnswer)) {
            mScore++;
        }
    }

    @Override
    public void onQuestionDialogClose() {
        ++mCurrentStep;

        if (mCurrentStep == 1) {
            setQuestionWithEditTextFragment();
        } else if (mCurrentStep == mQuestions.length) {
            // Если вопрос последний, отображается результирующий фрагмент
            setResultFragment();
        } else {
            setQuestionWithRadioButtonFragment();
        }
    }

    @Override
    public void onIntroductionFragmentInteraction(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;

        mCurrentStep++;

        setQuestionWithRadioButtonFragment();
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
        setTitle("QuestionWithRadioButton " + ++step + "/" + mQuestions.length);
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
