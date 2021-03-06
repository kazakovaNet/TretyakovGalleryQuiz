package com.example.android.tretyakovgalleryquiz.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tretyakovgalleryquiz.model.QuestionWithEditText;
import com.example.android.tretyakovgalleryquiz.R;


/**
 * Класс фрагмента вопроса типа "с радио кнопкой".
 * Активити, содержащие данный фрагмент должны реализовывать интерфейс
 * onQuestionWithEditTextFragmentInteractionListener для обработки
 * взаимодействия пользователя с фрагментом
 */
public class QuestionWithEditTextFragment extends Fragment {
    private onQuestionWithEditTextFragmentInteractionListener mListener;
    private Context mParentContext;
    private QuestionWithEditText mQuestionWithEditText;
    private EditText mQuestionEditText;
    private String mEnterAnswer;

    public QuestionWithEditTextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_whith_edit_text, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            initializeFragment(view);
        }
    }

    /**
     * Метод инициализирует данные во фрагменте
     *
     * @param view объект представления фрагмента
     */
    private void initializeFragment(View view) {
        // Назначение изображения
        ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
        pictureImageView.setImageResource(mQuestionWithEditText.getPictureId());

        // Назначение текста вопроса
        TextView questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(mParentContext.getString(mQuestionWithEditText.getQuestion()));

        // Получение ссылки на поле ввода текста
        mQuestionEditText = view.findViewById(R.id.question_edit_text);
        if (mEnterAnswer != null && !mEnterAnswer.equals("")) {
            mQuestionEditText.setText(mEnterAnswer);
        }

        Button questionButton = view.findViewById(R.id.question_button);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredAnswer = String.valueOf(mQuestionEditText.getText());

                if (enteredAnswer.equals("")) {
                    Toast.makeText(
                            mParentContext,
                            mParentContext.getString(R.string.enter_your_answer),
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (mListener != null) {
                    // Сообщить слушателю о том, что на кнопке был сделан щелчок
                    mListener.onQuestionWithEditTextFragmentInteraction(enteredAnswer);
                }
            }
        });
    }

    // Вызывается при присоединении фрагмента к активности
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof onQuestionWithEditTextFragmentInteractionListener) {
            mListener = (onQuestionWithEditTextFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnResultFragmentInteractionListener");
        }

        mParentContext = activity;
    }

    @Override
    public void onPause() {
        super.onPause();

        // Сохранение введенных пользователем имени и галочки при смене ориентации
        if (mListener != null) {
            mListener.onQuestionWithEditTextFragmentPause(String.valueOf(mQuestionEditText.getText()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
        mParentContext = null;
    }

    /**
     * Метод устанавливает данные для фрагмента (после его приостановки)
     *
     * @param questionWithEditText отображаемый объект вопроса
     * @param answer               введенный пользователем ответ
     */
    public void initFragment(QuestionWithEditText questionWithEditText, String answer) {
        mQuestionWithEditText = questionWithEditText;
        mEnterAnswer = answer;
    }

    /**
     * Этот интерфейс должен реализовываться активностью, которая содержит этот фрагмент
     * для возможности взаимодействия с этим фрагментом
     */
    public interface onQuestionWithEditTextFragmentInteractionListener {
        void onQuestionWithEditTextFragmentInteraction(String answer);

        void onQuestionDialogClose();

        void onQuestionWithEditTextFragmentPause(String ennterAnswer);
    }
}
