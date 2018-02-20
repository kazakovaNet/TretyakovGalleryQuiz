package com.example.android.tretyakovgalleryquiz.model;

public class Question {
    private final int mQuestionId;
    private final int mPictureImageId;

    Question(int questionId, int pictureImageId) {
        this.mQuestionId = questionId;
        this.mPictureImageId = pictureImageId;
    }

    public int getQuestion() {
        return mQuestionId;
    }

    public int getPictureId() {
        return mPictureImageId;
    }
}
