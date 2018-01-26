package com.example.android.tretyakovgalleryquiz;


import android.app.Fragment;
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

public class PictureAnswerFragment extends Fragment {
    private int pictureId;

    public PictureAnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_answer, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            Picture picture = Picture.pictures[pictureId];
            String[] answers = picture.getAnswers();
            String correctAnswer = answers[picture.getCorrectAnswer()];

            ImageView pictureImageView = view.findViewById(R.id.picture_image_view);
            TextView questionTextView = view.findViewById(R.id.question_text_view);
            Button answer1Button = view.findViewById(R.id.answer_1_button);
            Button answer2Button = view.findViewById(R.id.answer_2_button);
            Button answer3Button = view.findViewById(R.id.answer_3_button);
            Button answer4Button = view.findViewById(R.id.answer_4_button);

            pictureImageView.setImageResource(picture.getPictureId());
            questionTextView.setText(picture.getQuestion());
            answer1Button.setText(answers[0]);
            answer2Button.setText(answers[1]);
            answer3Button.setText(answers[2]);
            answer4Button.setText(answers[3]);
        }

    }

    public void setPicture(int pictureId) {
        this.pictureId = pictureId;
    }
}
