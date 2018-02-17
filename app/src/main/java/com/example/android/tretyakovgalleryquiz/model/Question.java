package com.example.android.tretyakovgalleryquiz.model;

public class Question {
    private final int mQuestionId;
    private final int mPictureImageId;
    private final int mCorrectAnswerId;

    Question(int questionId, int pictureImageId, int correctAnswerId) {
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
