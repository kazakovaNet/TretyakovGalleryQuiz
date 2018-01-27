package com.example.android.tretyakovgalleryquiz;

import android.content.res.Resources;

class PictureQuestion {
    private String question;
    private int pictureImage;
    private String[] answers;
    private int correctAnswer;
    private static Resources resources = new MainActivity().getResources();

    private PictureQuestion(String question, int pictureImage, String[] answers, int correctAnswer) {
        this.question = question;
        this.pictureImage = pictureImage;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    static final PictureQuestion[] PICTURE_QUESTIONS_DATA = {
            new PictureQuestion(
                    resources.getString(R.string.question_1),
                    R.drawable.pic_1,
                    resources.getStringArray(R.array.answers_question_1),
                    0),
            new PictureQuestion(
                    resources.getString(R.string.question_2),
                    R.drawable.pic_2,
                    resources.getStringArray(R.array.answers_question_2),
                    1),
            new PictureQuestion(
                    resources.getString(R.string.question_3),
                    R.drawable.pic_3,
                    resources.getStringArray(R.array.answers_question_3),
                    2)
    };

    String getQuestion() {
        return question;
    }

    int getPictureId() {
        return pictureImage;
    }

    String[] getAnswers() {
        return answers;
    }

    String getCorrectAnswer() {
        return answers[correctAnswer];
    }
}
