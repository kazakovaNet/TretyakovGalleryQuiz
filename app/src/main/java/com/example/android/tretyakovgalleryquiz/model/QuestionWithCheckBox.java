package com.example.android.tretyakovgalleryquiz.model;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionWithCheckBox extends Question {

    private final int mAnswersArrayId;
    private final ArrayList<String> mCorrectAnswersArray;

    public QuestionWithCheckBox(int questionId, int pictureImageId,
                                int answersArrayId, ArrayList<String> correctAnswersArray) {
        super(questionId, pictureImageId);

        this.mAnswersArrayId = answersArrayId;
        this.mCorrectAnswersArray = correctAnswersArray;
    }

    public int getAnswersArrayId() {
        return mAnswersArrayId;
    }

    public String getCorrectAnswers() {
        StringBuilder result = new StringBuilder();

        for (String item : mCorrectAnswersArray) {
            result.append(item).append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    public boolean checkAnswer(ArrayList<String> mEnteredAnswersArray) {
        Collections.sort(mCorrectAnswersArray);
        Collections.sort(mEnteredAnswersArray);

        return mCorrectAnswersArray.equals(mEnteredAnswersArray);
    }
}
