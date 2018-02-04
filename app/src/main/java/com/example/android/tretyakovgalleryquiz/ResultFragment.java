package com.example.android.tretyakovgalleryquiz;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnResultFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ResultFragment extends Fragment {
    private final String ON_SCREEN = "onScreen";
    private final String ON_EMAIL = "onEmail";
    private OnResultFragmentInteractionListener mListener;
    private Context mParentContext;
    private EditText mEmailEditText;
    private String mTypeResultShow = ON_EMAIL;
    private String mEmail;
    private String mName;
    private boolean isScoring;
    private int mScore;
    private int mCountOfQuestion;

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

    private void initializeFragment(View view) {
        TextView showResultTextView = view.findViewById(R.id.show_result_text_view);

        // Назначение имени пользователя
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(mParentContext.getString(R.string.thank_you_for_attention, mName));

        mEmailEditText = view.findViewById(R.id.email_edit_text);

        RadioGroup showResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
        showResultRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        }
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mTypeResultShow) {
                    case ON_SCREEN:
                        // Отображение текста с результатом викторины
                        showResultDialog();
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
        // Если не выставлен чекбокс подсчета очков, отображается кнопка выхода
        if (!isScoring) {
            exitButton.setVisibility(View.VISIBLE);
        }
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сообщить слушателю о действиях пользователя
                if (mListener != null) {
                    mListener.onExitButtonClicked();
                }
            }
        });

        // Скрытие / отображение блока способа отображения результатов и кнопки выхода
        // в зависимости от установленного checkbox на приветственном экране
        if (isScoring) {
            showResultTextView.setVisibility(View.VISIBLE);
            showResultRadioGroup.setVisibility(View.VISIBLE);
        } else {
            showResultTextView.setVisibility(View.GONE);
            showResultRadioGroup.setVisibility(View.GONE);
            exitButton.setVisibility(View.VISIBLE);
        }
    }

    public void initResultFragment(String name, boolean isScoring, int score, int countOfQuestion) {
        this.isScoring = isScoring;
        this.mName = name;
        this.mScore = score;
        this.mCountOfQuestion = countOfQuestion;
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

    private void showResultDialog() {
        AlertHelper alertHelper = new AlertHelper((Activity) mParentContext);
        alertHelper.openResultDialog(mScore, mCountOfQuestion);
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnResultFragmentInteractionListener {
        void onResultFragmentInteraction(String email);

        void onExitButtonClicked();
    }
}
