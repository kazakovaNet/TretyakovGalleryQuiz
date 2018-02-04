package com.example.android.tretyakovgalleryquiz;

class QuestionWithEditText extends Question{
    private int mQuestionId;
    private int mPictureImageId;
    private String mCorrectAnswerId;

    QuestionWithEditText(int questionId, int pictureImageId, String correctAnswerId) {
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

    String getCorrectAnswer() {
        return mCorrectAnswerId;
    }
}
