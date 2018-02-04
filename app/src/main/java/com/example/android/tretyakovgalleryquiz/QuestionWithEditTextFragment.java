package com.example.android.tretyakovgalleryquiz;


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


/**
 * A simple {@link Fragment} subclass.
 */

public class QuestionWithEditTextFragment extends Fragment {
    private onQuestionWithEditTextFragmentInteractionListener mListener;
    private Context mParentContext;
    private QuestionWithEditText mQuestionWithEditText;

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

    private void initializeFragment(View view) {
        // Назначение изображения
        ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
        pictureImageView.setImageResource(mQuestionWithEditText.getPictureId());

        // Назначение текста вопроса
        TextView questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(mParentContext.getString(mQuestionWithEditText.getQuestion()));

        // Получение ссылки на поле ввода текста
        final EditText questionEditText = view.findViewById(R.id.question_edit_text);

        Button questionButton = view.findViewById(R.id.question_button);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterAnswer = String.valueOf(questionEditText.getText());

                if (enterAnswer.equals("")) {
                    Toast.makeText(
                            mParentContext,
                            mParentContext.getString(R.string.enter_your_answer),
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (mListener != null) {
                    // Сообщить слушателю о том, что на кнопке был сделан щелчок
                    mListener.onQuestionWithEditTextFragmentInteraction(enterAnswer);
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
    public void onDetach() {
        super.onDetach();

        mListener = null;
        mParentContext = null;
    }

    // Определяются данные для отображения
    public void initQuestionWithEditTextFragment(QuestionWithEditText questionWithEditText) {
        mQuestionWithEditText = questionWithEditText;
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
    interface onQuestionWithEditTextFragmentInteractionListener {
        void onQuestionWithEditTextFragmentInteraction(String answer);

        void onQuestionDialogClose();
    }
}
