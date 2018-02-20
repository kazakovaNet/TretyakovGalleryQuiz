package com.example.android.tretyakovgalleryquiz.model;

public class QuestionWithRadioButton extends Question {

    private final String[] mAnswersArray;


    private final String mCorrectAnswer;

    public QuestionWithRadioButton(int questionId, int pictureImageId, String[] answersArray,
                                   int correctAnswerIndex) {
        super(questionId, pictureImageId);

        this.mAnswersArray = answersArray;
        this.mCorrectAnswer = answersArray[correctAnswerIndex];
    }

    public String[] getAnswersArray() {
        return mAnswersArray;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public boolean checkAnswer(String selectAnswer) {
        return this.mCorrectAnswer.equals(selectAnswer);
    }
}
