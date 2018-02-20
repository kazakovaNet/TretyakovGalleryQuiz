package com.example.android.tretyakovgalleryquiz.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.tretyakovgalleryquiz.model.QuestionWithRadioButton;
import com.example.android.tretyakovgalleryquiz.R;


/**
 * Класс фрагмента вопроса типа "с радио кнопкой".
 * Активити, содержащие данный фрагмент должны реализовывать интерфейс
 * onQuestionWithRadioButtonFragmentInteractionListener для обработки
 * взаимодействия пользователя с фрагментом
 */
public class QuestionWithRadioButtonFragment extends Fragment {
    private onQuestionWithRadioButtonFragmentInteractionListener mListener;
    private Context mParentContext;
    private QuestionWithRadioButton mQuestionWithRadioButton;

    public QuestionWithRadioButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_whith_radio_button, container, false);
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
    private void initializeFragment(final View view) {
        // Назначение изображения
        ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
        pictureImageView.setImageResource(mQuestionWithRadioButton.getPictureId());

        // Назначение текста вопроса
        TextView questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(mParentContext.getString(mQuestionWithRadioButton.getQuestion()));

        // Получение ссылок на радио-кнопки и назначение слушателя клика
        RadioGroup answerRadioGroup = view.findViewById(R.id.answer_radio_group);
        answerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mListener != null) {
                    String selectedAnswer = ((RadioButton) view.findViewById(checkedId))
                            .getText().toString();

                    // Сообщить слушателю о том, что на одной из кнопок был сделан щелчок
                    mListener.onQuestionWithRadioButtonFragmentInteraction(selectedAnswer);
                }
            }
        });

        // Назначение текста радио-кнопкам
        String[] answers = mQuestionWithRadioButton.getAnswersArray();

        RadioButton answer1RadioButton = view.findViewById(R.id.answer_1_radio_button);
        answer1RadioButton.setText(answers[0]);
        RadioButton answer2RadioButton = view.findViewById(R.id.answer_2_radio_button);
        answer2RadioButton.setText(answers[1]);
        RadioButton answer3RadioButton = view.findViewById(R.id.answer_3_radio_button);
        answer3RadioButton.setText(answers[2]);
        RadioButton answer4RadioButton = view.findViewById(R.id.answer_4_radio_button);
        answer4RadioButton.setText(answers[3]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof onQuestionWithRadioButtonFragmentInteractionListener) {
            mListener = (onQuestionWithRadioButtonFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement onQuestionWithRadioButtonFragmentInteractionListener");
        }

        mParentContext = activity;
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
     * @param questionWithRadioButton отображаемый объект вопроса
     */
    public void initFragment(QuestionWithRadioButton questionWithRadioButton) {
        mQuestionWithRadioButton = questionWithRadioButton;
    }

    /**
     * Этот интерфейс должен реализовываться активностью, которая содержит этот фрагмент
     * для возможности взаимодействия с этим фрагментом
     */
    public interface onQuestionWithRadioButtonFragmentInteractionListener {
        void onQuestionWithRadioButtonFragmentInteraction(String selectAnswer);

        void onQuestionDialogClose();
    }
}
