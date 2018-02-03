package com.example.android.tretyakovgalleryquiz;


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


/**
 * A simple {@link Fragment} subclass.
 */

public class QuestionFragment extends Fragment implements View.OnClickListener {
    private onQuestionFragmentInteractionListener mListener;
    private Question mQuestion;
    private Context mParentContext;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
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
        // Назначение изображения
        ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
        pictureImageView.setImageResource(mQuestion.getPictureId());

        // Назначение текста вопроса
        TextView questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(mParentContext.getString(mQuestion.getQuestion()));

        // Получение ссылок на радио-кнопки и назначение слушателя клика
        RadioGroup answerRadioGroup = view.findViewById(R.id.answer_radio_group);
        answerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onClick(checkedId);
            }
        });

        // Назначение текста радио-кнопкам
        String[] answers = mParentContext.getResources().getStringArray(mQuestion.getAnswersArrayId());

        RadioButton answer1RadioButton = view.findViewById(R.id.answer_1_radio_button);
        answer1RadioButton.setText(answers[0]);
        RadioButton answer2RadioButton = view.findViewById(R.id.answer_2_radio_button);
        answer2RadioButton.setText(answers[1]);
        RadioButton answer3RadioButton = view.findViewById(R.id.answer_3_radio_button);
        answer3RadioButton.setText(answers[2]);
        RadioButton answer4RadioButton = view.findViewById(R.id.answer_4_radio_button);
        answer4RadioButton.setText(answers[3]);
    }

    private void onClick(int checkedId) {
        if (mListener != null) {
            // Сообщить слушателю о том, что на одной из кнопок был сделан щелчок
            mListener.onQuestionFragmentInteraction(checkedId);
        }
    }

    // Вызывается при присоединении фрагмента к активности
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof onQuestionFragmentInteractionListener) {
            mListener = (onQuestionFragmentInteractionListener) activity;
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

    // Определяются данные для отображения
    public void setAnswerData(Question question) {
        mQuestion = question;
    }

    public void onClick(View v) {
        if (mListener != null) {
            // Сообщить слушателю о том, что на одной из кнопок был сделан щелчок
            mListener.onQuestionFragmentInteraction(v.getId());
        }
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
    interface onQuestionFragmentInteractionListener {
        void onQuestionFragmentInteraction(int id);

        void onQuestionDialogClose();
    }
}
