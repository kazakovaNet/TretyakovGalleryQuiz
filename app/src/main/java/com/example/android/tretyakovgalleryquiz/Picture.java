package com.example.android.tretyakovgalleryquiz;

import java.util.Arrays;

public class Picture {
    private String question;
    private int pictureImage;
    private String[] answers;
    private int correctAnswer;

    public Picture(String question, int pictureImage, String[] answers, int correctAnswer) {
        this.question = question;
        this.pictureImage = pictureImage;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public static final Picture[] pictures = {
            new Picture("Question 1", R.drawable.pic_1, new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 0),
            new Picture("Question 2", R.drawable.pic_1, new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 1),
            new Picture("Question 3", R.drawable.pic_1, new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 2),
            new Picture("Question 4", R.drawable.pic_1, new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 3),
            new Picture("Question 5", R.drawable.pic_1, new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 0)
    };

    public String getQuestion() {
        return question;
    }

    public int getPictureId() {
        return pictureImage;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "question='" + question + '\'' +
                ", R.drawable.pic_1='" + pictureImage + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
