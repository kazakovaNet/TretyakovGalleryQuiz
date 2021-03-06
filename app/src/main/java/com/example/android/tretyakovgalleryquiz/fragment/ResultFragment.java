package com.example.android.tretyakovgalleryquiz.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.activity.MainActivity;
import com.example.android.tretyakovgalleryquiz.helper.AlertHelper;


/**
 * Класс результирующего фрагмента.
 * Активити, содержащие данный фрагмент должны реализовывать интерфейс
 * OnResultFragmentInteractionListener для обработки взаимодействия пользователя с фрагментом
 */
public class ResultFragment extends Fragment {
    private final String ON_SCREEN = "onScreen";
    private final String ON_EMAIL = "onEmail";
    private OnResultFragmentInteractionListener mListener;
    private Activity mParentContext;
    private EditText mEmailEditText;
    private String mTypeResultShow = ON_EMAIL;
    private String mEmail;
    private String mName;
    private boolean isScoring;
    private int mScore;
    private int mCountOfQuestion;
    private RadioGroup mShowResultRadioGroup;
    private int mCheckedTypeResultShow = R.id.on_email_radio_button;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            initializeFragment(view);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        // Сохранение введенного адреса электронной почты
        if (mListener != null) {
            mListener.onResultFragmentPause(String.valueOf(mEmailEditText.getText()), mShowResultRadioGroup.getCheckedRadioButtonId());
        }
    }

    /**
     * Метод инициализирует данные во фрагменте
     *
     * @param view объект представления фрагмента
     */
    private void initializeFragment(View view) {
        TextView showResultTextView = view.findViewById(R.id.show_result_text_view);

        // Назначение имени пользователя
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(mParentContext.getString(R.string.thank_you_for_attention, mName));

        mEmailEditText = view.findViewById(R.id.email_edit_text);
        if (mEmail != null && !mEmail.equals("")) {
            mEmailEditText.setText(mEmail);
        }

        mShowResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
        if (mShowResultRadioGroup != null && mCheckedTypeResultShow != 0) {
            RadioButton selectRadioButton = view.findViewById(mCheckedTypeResultShow);
            selectRadioButton.setChecked(true);
        }
        mShowResultRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.on_screen_radio_button:
                        // Флаг отображения на экране
                        mTypeResultShow = ON_SCREEN;

                        // Скрытие поля для ввода e-mail
                        mEmailEditText.setVisibility(View.GONE);

                        break;
                    case R.id.on_email_radio_button:
                        // Флаг отправки на почту
                        mTypeResultShow = ON_EMAIL;

                        // Отображение поля для ввода e-mail
                        mEmailEditText.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });

        Button resultButton = view.findViewById(R.id.result_button);
        // Если выставлен чекбокс подсчета очков, отображается кнопка отображения результатов
        if (isScoring) {
            resultButton.setVisibility(View.VISIBLE);
            if (mCheckedTypeResultShow == R.id.on_email_radio_button) {
                mEmailEditText.setVisibility(View.VISIBLE);
            }
        } else {
            mEmailEditText.setVisibility(View.GONE);
        }

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mTypeResultShow) {
                    case ON_SCREEN:
                        // Отображение текста с результатом викторины
                        showResult();
                        break;
                    case ON_EMAIL:
                        // Сохранение введенного адреса электронной почты,
                        // если выбран вариант отправки результата на почту
                        mEmail = String.valueOf(mEmailEditText.getText());

                        if (!isValidEmail(mEmail)) {
                            Toast.makeText(mParentContext, mParentContext.getString(R.string.enter_valid_email), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Сообщить слушателю о действиях пользователя
                        if (mListener != null) {
                            mListener.onResultFragmentInteraction(mEmail);
                        }
                        break;
                }
            }
        });

        Button exitButton = view.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParentContext.finish();
            }
        });

        // Скрытие / отображение блока способа отображения результатов и кнопки выхода
        // в зависимости от установленного checkbox на приветственном экране
        if (isScoring) {
            showResultTextView.setVisibility(View.VISIBLE);
            mShowResultRadioGroup.setVisibility(View.VISIBLE);
        } else {
            showResultTextView.setVisibility(View.GONE);
            mShowResultRadioGroup.setVisibility(View.GONE);
        }
    }

    public void initFragment(String name, boolean isScoring, int score, int countOfQuestion, String email, int checkedTypeResultShow) {
        this.isScoring = isScoring;
        this.mName = name;
        this.mScore = score;
        this.mCountOfQuestion = countOfQuestion;
        this.mEmail = email;
        if (checkedTypeResultShow == 0) {
            this.mCheckedTypeResultShow = R.id.on_email_radio_button;
        } else {
            this.mCheckedTypeResultShow = checkedTypeResultShow;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnResultFragmentInteractionListener) {
            mListener = (OnResultFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnResultFragmentInteractionListener");
        }

        mParentContext = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
        mParentContext = null;
    }

    private void showResult() {
        Toast.makeText(mParentContext, mParentContext.getString(
                R.string.text_result_on_screen, mScore, mCountOfQuestion),
                Toast.LENGTH_LONG).show();
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Этот интерфейс должен реализовываться активностью, которая содержит этот фрагмент
     * для возможности взаимодействия с этим фрагментом
     */
    public interface OnResultFragmentInteractionListener {
        void onResultFragmentInteraction(String email);

        void onResultFragmentPause(String email, int checkedRadioButtonId);
    }
}
