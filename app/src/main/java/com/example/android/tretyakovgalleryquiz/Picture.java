package com.example.android.tretyakovgalleryquiz;

import java.util.Arrays;

public class Picture {
    private String question;
    private String path;
    private String[] answers;
    private int correctAnswer;

    public Picture(String question, String path, String[] answers, int correctAnswer) {
        this.question = question;
        this.path = path;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public static final Picture[] pictures = {
            new Picture("Question 1", "pic_1", new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 0),
            new Picture("Question 2", "pic_2", new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 1),
            new Picture("Question 3", "pic_3", new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 2),
            new Picture("Question 4", "pic_4", new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 3),
            new Picture("Question 5", "pic_5", new String[]{"Answer A", "Answer B", "Answer C", "Answer D"}, 0)
    };

    public String getQuestion() {
        return question;
    }

    public String getPath() {
        return path;
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
                ", path='" + path + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
