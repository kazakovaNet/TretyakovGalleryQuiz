package com.example.android.tretyakovgalleryquiz.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.android.tretyakovgalleryquiz.fragment.QuestionWithCheckBoxFragment;
import com.example.android.tretyakovgalleryquiz.fragment.QuizFragment;
import com.example.android.tretyakovgalleryquiz.helper.AlertHelper;
import com.example.android.tretyakovgalleryquiz.fragment.IntroductionFragment;
import com.example.android.tretyakovgalleryquiz.model.Question;
import com.example.android.tretyakovgalleryquiz.model.QuestionWithCheckBox;
import com.example.android.tretyakovgalleryquiz.model.QuestionWithEditText;
import com.example.android.tretyakovgalleryquiz.fragment.QuestionWithEditTextFragment;
import com.example.android.tretyakovgalleryquiz.model.QuestionWithRadioButton;
import com.example.android.tretyakovgalleryquiz.fragment.QuestionWithRadioButtonFragment;
import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.fragment.ResultFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener,
        QuestionWithEditTextFragment.onQuestionWithEditTextFragmentInteractionListener,
        QuestionWithCheckBoxFragment.onQuestionWithCheckBoxFragmentInteractionListener,
        IntroductionFragment.onIntroductionFragmentInteractionListener,
        ResultFragment.OnResultFragmentInteractionListener {
    private final String TAG = "MainActivity";
    private String mName;
    private String mEmail;
    private String mEnterAnswer;
    private boolean isScoring;
    private int mScore;
    private int mCurrentStep = -2;

    private int mCheckedTypeResultShow;
    private final ArrayList<Question> mQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Формирование массива вопросов
        initQuestions();

        // Восстановление сохраненного состояния
        restoreInstanceState(savedInstanceState);

        // Отображение фрагмента
        setFragment();
    }

    /**
     * Метод восстанавливает значения переменных из сохраненного состояния
     *
     * @param savedInstanceState объект Bundle с сохраненными данными
     */
    private void restoreInstanceState(Bundle savedInstanceState) {
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

        // Восстановление введенного в EditText значения
        if (savedInstanceState != null && savedInstanceState.containsKey("mEnterAnswer")) {
            mEnterAnswer = savedInstanceState.getString("mEnterAnswer");
        }

        // Восстановление введенной в EditText электронной почты
        if (savedInstanceState != null && savedInstanceState.containsKey("mEmail")) {
            mEmail = savedInstanceState.getString("mEmail");
        }

        // Восстановление введенной в EditText электронной почты
        if (savedInstanceState != null && savedInstanceState.containsKey("mCheckedTypeResultShow")) {
            mCheckedTypeResultShow = savedInstanceState.getInt("mCheckedTypeResultShow");
        }
    }

    /**
     * Метод формирует массив вопросов
     */
    private void initQuestions() {
        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_1,
                R.drawable.pic_1,
                getResources().getStringArray(R.array.answers_question_1),
                0));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_2,
                R.drawable.pic_2,
                getResources().getStringArray(R.array.answers_question_2),
                1));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_3,
                R.drawable.pic_3,
                getResources().getStringArray(R.array.answers_question_3),
                2));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_4,
                R.drawable.pic_4,
                getResources().getStringArray(R.array.answers_question_4),
                3));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_5,
                R.drawable.pic_5,
                getResources().getStringArray(R.array.answers_question_5),
                2));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_6,
                R.drawable.pic_6,
                getResources().getStringArray(R.array.answers_question_6),
                3));

        mQuestions.add(new QuestionWithRadioButton(
                R.string.question_7,
                R.drawable.pic_7,
                getResources().getStringArray(R.array.answers_question_7),
                3));

        mQuestions.add(new QuestionWithEditText(
                R.string.question_8,
                R.drawable.pic_8,
                getString(R.string.question_8_answer)));

        mQuestions.add(new QuestionWithCheckBox(
                R.string.question_9,
                R.drawable.pic_8,
                R.array.answers_question_9,
                new ArrayList<String>(Arrays.asList(getResources()
                        .getStringArray(R.array.correct_answers_question_9)))));
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
        // Сохранение введенного в EditText значения
        outState.putString("mEnterAnswer", mEnterAnswer);
        // Сохранение введенной в EditText электронной почты
        outState.putString("mEmail", mEmail);
        // Сохранение выбранного вида отображения результатов
        outState.putInt("mCheckedTypeResultShow", mCheckedTypeResultShow);
    }

    /**
     * Метод создает, инициализирует и выводит фрагмент
     */
    private void setFragment() {
        ++mCurrentStep;

        if (mCurrentStep == -1) {
            IntroductionFragment fragment = new IntroductionFragment();

            fragment.initFragment(mName, isScoring);

            startFragment(fragment);
        } else if (mCurrentStep == mQuestions.size()) {
            // Сброс заголовка окна
            resetTitle();

            ResultFragment fragment = new ResultFragment();

            fragment.initFragment(mName, isScoring, mScore, mQuestions.size(), mEmail,
                    mCheckedTypeResultShow);

            startFragment(fragment);
        } else {
            // Установка счетчика вопросов в заголовок окна
            setTitleQuestion(mCurrentStep);

            Question question = mQuestions.get(mCurrentStep);

            if (question instanceof QuestionWithRadioButton) {
                QuestionWithRadioButtonFragment fragment = new QuestionWithRadioButtonFragment();

                fragment.initFragment((QuestionWithRadioButton) question);

                startFragment(fragment);
            } else if (question instanceof QuestionWithEditText) {
                QuestionWithEditTextFragment fragment = new QuestionWithEditTextFragment();

                fragment.initFragment((QuestionWithEditText) question, mEnterAnswer);

                startFragment(fragment);
            } else if (question instanceof QuestionWithCheckBox) {
                QuestionWithCheckBoxFragment fragment = new QuestionWithCheckBoxFragment();

                fragment.initFragment((QuestionWithCheckBox) question);

                startFragment(fragment);
            }
        }
    }

    private void startFragment(Fragment fragment) {
        // начало транзакции фрагмента
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // Включить анимацию растворения и появления фрагментов
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Закрепить транзакцию
        fragmentTransaction.commit();
    }

    /**
     * Метод реагирует на взаимодействие с фрагментом вопроса типа "с радио кнопкой"
     *
     * @param selectAnswer идентификатор выбранного пользователем виджета RadioButton
     */
    @Override
    public void onQuestionWithRadioButtonFragmentInteraction(String selectAnswer) {
        QuestionWithRadioButton questionWithRadioButton =
                (QuestionWithRadioButton) mQuestions.get(mCurrentStep);

        // Проверка, правильно ли ответил пользователь
        boolean isAnswerTrue = questionWithRadioButton.checkAnswer(selectAnswer);

        // Открытие диалога с результатом проверки
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openAnswerDialog(
                isAnswerTrue, questionWithRadioButton.getCorrectAnswer(),
                String.valueOf(QuestionWithRadioButtonFragment.class));

        // Увеличение количества набранных очков, если ответ верен и установлен чекбокс подсчета результата
        if (isScoring && isAnswerTrue) {
            mScore++;
        }
    }

    /**
     * Метод реагирует на взаимодействие с фрагментом вопроса типа "с полем для ввода текста"
     *
     * @param enterAnswer введенный пользователем ответ
     */
    @Override
    public void onQuestionWithEditTextFragmentInteraction(String enterAnswer) {
        QuestionWithEditText questionWithEditText =
                (QuestionWithEditText) mQuestions.get(mCurrentStep);

        // Проверка, правильно ли ответил пользователь
        boolean isAnswerTrue = questionWithEditText.checkAnswer(enterAnswer);

        // Открытие диалога с результатом проверки
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openAnswerDialog(
                isAnswerTrue, questionWithEditText.getmCorrectAnswer(),
                String.valueOf(QuestionWithEditTextFragment.class));

        // Увеличение количества набранных очков, если ответ верен и установлен чекбокс подсчета результата
        if (isScoring && isAnswerTrue) {
            mScore++;
        }
    }

    /**
     * Метод реагирует на взаимодействие с приветственным фрагментом
     *
     * @param name      введенное пользователем имя
     * @param isScoring выбранное пользователем состояние виджета CheckBox, отвечающего за посчет очков
     */
    @Override
    public void onIntroductionFragmentInteraction(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;

        // Отображение фрагмента
        setFragment();
    }

    /**
     * Метод реагирует на взаимодействие с фрагментом вопроса типа "с чекбоксом"
     *
     * @param enteredAnswers выбранные пользователем варианты ответа
     */
    @Override
    public void onQuestionWithCheckboxFragmentInteraction(ArrayList<String> enteredAnswers) {
        QuestionWithCheckBox questionWithCheckBox =
                (QuestionWithCheckBox) mQuestions.get(mCurrentStep);

        // Проверка, правильно ли ответил пользователь
        boolean isAnswerTrue = questionWithCheckBox.checkAnswer(enteredAnswers);

        // Открытие диалога с результатом проверки
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openAnswerDialog(
                isAnswerTrue, questionWithCheckBox.getCorrectAnswers(),
                String.valueOf(QuestionWithCheckBox.class));

        // Увеличение количества набранных очков, если ответ верен и установлен чекбокс подсчета результата
        if (isScoring && isAnswerTrue) {
            mScore++;
        }
    }

    /**
     * Метод реагирует на взаимодействие с результирующим фрагментом
     *
     * @param email введенный пользователем адрес электронной почты
     */
    @Override
    public void onResultFragmentInteraction(String email) {
        sentResultOnEmail(email);
    }

    /**
     * Метод реагирует на приостановку приветственного фрагмента, сохраняет введенные пользователем данные
     *
     * @param name      введенное пользователем имя
     * @param isScoring выбранное пользователем состояние виджета CheckBox, отвечающего за посчет очков
     */
    @Override
    public void onIntroductionFragmentPause(String name, boolean isScoring) {
        this.mName = name;
        this.isScoring = isScoring;
    }

    /**
     * Метод реагирует на приостановку фрагмента вопроса типа "с полем для ввода текста",
     * сохраняет введенные пользователем данные
     *
     * @param enterAnswer введенный пользователем ответ
     */
    @Override
    public void onQuestionWithEditTextFragmentPause(String enterAnswer) {
        this.mEnterAnswer = enterAnswer;
    }

    /**
     * Метод реагирует на приостановку результирующего фрагмента,
     * сохраняет введенные пользователем данные
     *
     * @param email                 введенный пользователем адрес электронной почты
     * @param checkedTypeResultShow выбранное пользователем состояние виджета RadioButton,
     *                              отвечающего за тип отображения результатов викторины
     */
    @Override
    public void onResultFragmentPause(String email, int checkedTypeResultShow) {
        this.mEmail = email;
        this.mCheckedTypeResultShow = checkedTypeResultShow;
    }

    /**
     * Метод реагирует на закрытие диалога с результатом ответа на вопрос
     */
    @Override
    public void onQuestionDialogClose() {
        // Отображение фрагмента
        setFragment();
    }

    /**
     * Метод реагирует на нажатие кнопки выхода из программы
     */
    @Override
    public void onExitButtonClicked() {
        finish();
    }

    /**
     * Метод реагирует на нажатие аппаратной кнопки назад
     */
    @Override
    public void onBackPressed() {
        AlertHelper alertHelper = new AlertHelper(MainActivity.this);
        alertHelper.openQuitDialog();
    }

    /**
     * Метод устанавливает в заголовок программы название викторины
     */
    private void resetTitle() {
        setTitle(getString(R.string.app_name));
    }

    /**
     * Метод устанавливает в заголовок программы текущий вопрос / общее количество вопросов
     */
    private void setTitleQuestion(int step) {
        setTitle(getString(R.string.title, ++step, mQuestions.size()));
    }

    /**
     * Метод отправляет результат викторы на электронную почту
     *
     * @param email введенный пользователем адрес электронной почты
     */
    private void sentResultOnEmail(String email) {
        String resultSummary = createResultSummary();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SENDTO);
        // only email apps should handle this
        sendIntent.setData(Uri.parse("mailto:"));
        // добавляем текст для передачи
        sendIntent.putExtra(Intent.EXTRA_TEXT, resultSummary);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.result_email_subject));
        // запускаем активити
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    /**
     * Метод формирует резюме прохождения викторины
     *
     * @return строка с результатом викторины
     */
    private String createResultSummary() {
        return getString(R.string.text_result_on_screen, mScore, mQuestions.size());
    }

    /**
     * Метод реагирует на клик вне виджета EditText и скрывает клавиатуру
     *
     * @param ev событие клика
     * @return результат назначения обработчика
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {

                view.clearFocus();
                hideKeyboard();
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * Метод скрывает клавиатуру
     */
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
