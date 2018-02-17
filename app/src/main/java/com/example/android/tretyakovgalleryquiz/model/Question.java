package com.example.android.tretyakovgalleryquiz.model;

public class Question {
    protected int mQuestionId;
    protected int mPictureImageId;
    protected int mCorrectAnswerId;

    public Question(int questionId, int pictureImageId, int correctAnswerId) {
        this.mQuestionId = questionId;
        this.mPictureImageId = pictureImageId;
        this.mCorrectAnswerId = correctAnswerId;
    }

    public int getQuestion() {
        return mQuestionId;
    }

    public int getPictureId() {
        return mPictureImageId;
    }

    public int getCorrectAnswerId() {
        return mCorrectAnswerId;
    }
}
