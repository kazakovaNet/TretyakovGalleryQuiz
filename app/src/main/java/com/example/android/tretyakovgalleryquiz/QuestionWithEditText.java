package com.example.android.tretyakovgalleryquiz;

class QuestionWithEditText extends Question{
    private int mQuestionId;
    private int mPictureImageId;
    private int mCorrectAnswerId;

    QuestionWithEditText(int questionId, int pictureImageId, int correctAnswerId) {
        this.mQuestionId = questionId;
        this.mPictureImageId = pictureImageId;
        this.mCorrectAnswerId = correctAnswerId;
    }

    int getQuestion() {
        return mQuestionId;
    }

    int getPictureId() {
        return mPictureImageId;
    }

    int getCorrectAnswer() {
        return mCorrectAnswerId;
    }
}
