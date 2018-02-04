package com.example.android.tretyakovgalleryquiz;

class QuestionWithRadioButton extends Question{
    private int mQuestionId;
    private int mPictureImageId;
    private int mAnswersArrayId;
    private int mCorrectAnswerId;

    QuestionWithRadioButton(int questionId, int pictureImageId, int answersArrayId, int correctAnswerId) {
        this.mQuestionId = questionId;
        this.mPictureImageId = pictureImageId;
        this.mAnswersArrayId = answersArrayId;
        this.mCorrectAnswerId = correctAnswerId;
    }

    int getQuestion() {
        return mQuestionId;
    }

    int getPictureId() {
        return mPictureImageId;
    }

    int getAnswersArrayId() {
        return mAnswersArrayId;
    }

    int getCorrectAnswerId() {
        return mCorrectAnswerId;
    }
}