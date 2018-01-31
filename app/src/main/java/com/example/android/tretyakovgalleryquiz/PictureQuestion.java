package com.example.android.tretyakovgalleryquiz;

import android.content.res.Resources;

class PictureQuestion {
    private String mQuestion;
    private int mPictureImage;
    private String[] mAnswers;
    private int mCorrectAnswer;
    private static Resources sResources = MainActivity.sResources;

    private PictureQuestion(String question, int pictureImage, String[] answers, int correctAnswer) {
        this.mQuestion = question;
        this.mPictureImage = pictureImage;
        this.mAnswers = answers;
        this.mCorrectAnswer = correctAnswer;
    }

    static final PictureQuestion[] PICTURE_QUESTIONS_DATA = {
            new PictureQuestion(
                    sResources.getString(R.string.question_1),
                    R.drawable.pic_1,
                    sResources.getStringArray(R.array.answers_question_1),
                    0),
            new PictureQuestion(
                    sResources.getString(R.string.question_2),
                    R.drawable.pic_2,
                    sResources.getStringArray(R.array.answers_question_2),
                    1),
            new PictureQuestion(
                    sResources.getString(R.string.question_3),
                    R.drawable.pic_3,
                    sResources.getStringArray(R.array.answers_question_3),
                    2)
    };

    String getQuestion() {
        return mQuestion;
    }

    int getPictureId() {
        return mPictureImage;
    }

    String[] getAnswers() {
        return mAnswers;
    }

    String getCorrectAnswer() {
        return mAnswers[mCorrectAnswer];
    }
}
