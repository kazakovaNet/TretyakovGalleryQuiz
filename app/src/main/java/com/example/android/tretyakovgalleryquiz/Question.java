package com.example.android.tretyakovgalleryquiz;

class Question {
    protected int mQuestionId;
    protected int mPictureImageId;
    protected int mCorrectAnswerId;

    Question(int questionId, int pictureImageId, int correctAnswerId) {
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

    int getCorrectAnswerId() {
        return mCorrectAnswerId;
    }
}
