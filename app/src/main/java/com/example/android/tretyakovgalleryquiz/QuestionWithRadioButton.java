package com.example.android.tretyakovgalleryquiz;

class QuestionWithRadioButton extends Question {

    private int mAnswersArrayId;

    QuestionWithRadioButton(int questionId, int pictureImageId, int answersArrayId, int correctAnswerId) {
        super(questionId, pictureImageId, correctAnswerId);

        this.mAnswersArrayId = answersArrayId;
    }

    int getAnswersArrayId() {
        return mAnswersArrayId;
    }
}
