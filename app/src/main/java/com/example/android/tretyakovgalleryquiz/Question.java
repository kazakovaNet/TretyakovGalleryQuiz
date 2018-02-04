package com.example.android.tretyakovgalleryquiz;

class Question {
    protected int mQuestionId;
    protected int mPictureImageId;
    protected int mCorrectAnswerId;

    Question(int pictureImageId, int correctAnswerId, int questionId) {
        this.mPictureImageId = pictureImageId;
        this.mCorrectAnswerId = correctAnswerId;
        this.mQuestionId = questionId;
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
