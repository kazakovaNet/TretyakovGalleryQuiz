package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

        // Получение ссылок на кнопки и назначение слушателей клика
        Button answer1Button = view.findViewById(R.id.answer_1_button);
        answer1Button.setOnClickListener(this);
        Button answer2Button = view.findViewById(R.id.answer_2_button);
        answer2Button.setOnClickListener(this);
        Button answer3Button = view.findViewById(R.id.answer_3_button);
        answer3Button.setOnClickListener(this);
        Button answer4Button = view.findViewById(R.id.answer_4_button);
        answer4Button.setOnClickListener(this);

        // Назначение текста кнопкам
        String[] answers = mParentContext.getResources().getStringArray(mQuestion.getAnswersArrayId());
        answer1Button.setText(answers[0]);
        answer2Button.setText(answers[1]);
        answer3Button.setText(answers[2]);
        answer4Button.setText(answers[3]);
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
    }
}
