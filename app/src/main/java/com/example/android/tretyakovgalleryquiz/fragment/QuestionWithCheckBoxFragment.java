package com.example.android.tretyakovgalleryquiz.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.model.QuestionWithCheckBox;

import java.util.ArrayList;


/**
 * Класс фрагмента вопроса типа "с чекбоксом".
 * Активити, содержащие данный фрагмент должны реализовывать интерфейс
 * onQuestionWithCheckBoxFragmentInteractionListener для обработки
 * взаимодействия пользователя с фрагментом
 */
public class QuestionWithCheckBoxFragment extends Fragment implements View.OnClickListener {
    private onQuestionWithCheckBoxFragmentInteractionListener mListener;
    private Context mParentContext;
    private QuestionWithCheckBox mQuestionWithCheckBox;
    private ArrayList<String> mEnteredAnswersArray;

    public QuestionWithCheckBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_whith_checkbox, container, false);
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
        // Инициализация массива введенных ответов
        mEnteredAnswersArray = new ArrayList<>();

        // Назначение изображения
        ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
        pictureImageView.setImageResource(mQuestionWithCheckBox.getPictureId());

        // Назначение текста вопроса
        TextView questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(mParentContext.getString(mQuestionWithCheckBox.getQuestion()));

        // Назначение текста checkbox'ам
        String[] answers = mParentContext.getResources().getStringArray(mQuestionWithCheckBox.getAnswersArrayId());

        CheckBox answer1CheckBox = view.findViewById(R.id.answer_checkbox_1);
        CheckBox answer2CheckBox = view.findViewById(R.id.answer_checkbox_2);
        CheckBox answer3CheckBox = view.findViewById(R.id.answer_checkbox_3);
        CheckBox answer4CheckBox = view.findViewById(R.id.answer_checkbox_4);

        answer1CheckBox.setText(answers[0]);
        answer2CheckBox.setText(answers[1]);
        answer3CheckBox.setText(answers[2]);
        answer4CheckBox.setText(answers[3]);

        answer1CheckBox.setOnClickListener(this);
        answer2CheckBox.setOnClickListener(this);
        answer3CheckBox.setOnClickListener(this);
        answer4CheckBox.setOnClickListener(this);

        // Назначение действия кнопке
        Button checkButton = view.findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Сообщить слушателю о том, что на кнопке был сделан щелчок
                    mListener.onQuestionWithCheckboxFragmentInteraction(mEnteredAnswersArray);
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof onQuestionWithCheckBoxFragmentInteractionListener) {
            mListener = (onQuestionWithCheckBoxFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement onQuestionWithCheckBoxFragmentInteractionListener");
        }

        mParentContext = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
        mParentContext = null;
    }

    @Override
    public void onClick(View v) {
        String selectedAnswer = ((CheckBox) v).getText().toString();

        if (mEnteredAnswersArray.contains(selectedAnswer)) {
            mEnteredAnswersArray.remove(selectedAnswer);
        } else {
            mEnteredAnswersArray.add(selectedAnswer);
        }
    }

    /**
     * Метод устанавливает данные для фрагмента (после его приостановки)
     *
     * @param questionWithRadioButton отображаемый объект вопроса
     */
    public void initFragment(QuestionWithCheckBox questionWithRadioButton) {
        mQuestionWithCheckBox = questionWithRadioButton;
    }

    /**
     * Этот интерфейс должен реализовываться активностью, которая содержит этот фрагмент
     * для возможности взаимодействия с этим фрагментом
     */
    public interface onQuestionWithCheckBoxFragmentInteractionListener {
        void onQuestionWithCheckboxFragmentInteraction(ArrayList<String> id);

        void onQuestionDialogClose();
    }
}
