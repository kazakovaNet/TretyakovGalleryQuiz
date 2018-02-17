package com.example.android.tretyakovgalleryquiz.model;

public class QuestionWithRadioButton extends Question {

    private int mAnswersArrayId;

    public QuestionWithRadioButton(int questionId, int pictureImageId, int answersArrayId, int correctAnswerId) {
        super(questionId, pictureImageId, correctAnswerId);

        this.mAnswersArrayId = answersArrayId;
    }

    public int getAnswersArrayId() {
        return mAnswersArrayId;
    }
}
