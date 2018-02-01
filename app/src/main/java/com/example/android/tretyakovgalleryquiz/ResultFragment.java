package com.example.android.tretyakovgalleryquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private TextView mShowResultTextView;
    private EditText mEmailEditText;
    private RadioGroup mShowResultRadioGroup;
    private Button mResultButton;
    private String mTypeResultShow = ON_SCREEN;
    private boolean isScoring;
    private String mEmail;
    private String TAG = "ResultFragment";
    private OnResultFragmentInteractionListener mListener;
    private Context mParentContext;
    private String mName;
    private TextView mNameTextView;
    private TextView mResultTextView;
    private Button mExitButton;
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
        // Назначение имени пользователя
        mNameTextView = view.findViewById(R.id.name_text_view);
        mNameTextView.setText(mName);

        mEmailEditText = view.findViewById(R.id.email_edit_text);
        mShowResultTextView = view.findViewById(R.id.show_result_text_view);

        mResultTextView = view.findViewById(R.id.result_on_screen_text_view);

        mShowResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
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
        mShowResultRadioGroup.clearCheck();

        mResultButton = view.findViewById(R.id.result_button);
        // Если выставлен чекбокс подсчета очков, отображается кнопка отображения результатов
        if (isScoring) {
            mResultButton.setVisibility(View.VISIBLE);
        }
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mTypeResultShow) {
                    case ON_SCREEN:
                        // Отображение текста с результатом викторины
                        showResultAlert();
                        break;
                    case ON_EMAIL:
                        // Сохранение введенного адреса электронной почты,
                        // если выбран вариант отправки результата на почту
                        mEmail = String.valueOf(mEmailEditText.getText());

                        // TODO добавить валидацию адреса почты
                        if (mEmail.equals("")) {
                            Toast.makeText(mParentContext, mParentContext.getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
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

        mExitButton = view.findViewById(R.id.exit_button);
        // Если не выставлен чекбокс подсчета очков, отображается кнопка выхода
        if (!isScoring) {
            mExitButton.setVisibility(View.VISIBLE);
        }
        mExitButton.setOnClickListener(new View.OnClickListener() {
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
            mShowResultTextView.setVisibility(View.VISIBLE);
            mShowResultRadioGroup.setVisibility(View.VISIBLE);
        } else {
            mShowResultTextView.setVisibility(View.GONE);
            mShowResultRadioGroup.setVisibility(View.GONE);
            mExitButton.setVisibility(View.VISIBLE);
        }
    }

    public void setResultData(String name, boolean isScoring, int score, int countOfQuestion) {
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
    }

    private void showResultAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mParentContext);

        builder.setTitle(R.string.your_result)
                .setMessage(mParentContext.getString(R.string.text_result_on_screen, mScore, mCountOfQuestion))
                .setIcon(R.drawable.right_icon)
                .setCancelable(false)
                .setNegativeButton(mParentContext.getString(R.string.exit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        // Завершить работу приложения
                        if (mListener != null) {
                            mListener.onExitButtonClicked();
                        }
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        // TODO: Update argument type and name
        void onResultFragmentInteraction(String email);

        void onExitButtonClicked();
    }
}
