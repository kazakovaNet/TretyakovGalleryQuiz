package com.example.android.tretyakovgalleryquiz.model;

public class QuestionWithEditText extends Question {


    private final String mCorrectAnswer;

    public QuestionWithEditText(int questionId, int pictureImageId, String correctAnswer) {
        super(questionId, pictureImageId);

        this.mCorrectAnswer = correctAnswer;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public boolean checkAnswer(String enteredAnswer) {
        return this.mCorrectAnswer.equals(enteredAnswer);
    }
}
