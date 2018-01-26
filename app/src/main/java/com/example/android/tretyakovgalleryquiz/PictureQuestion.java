package com.example.android.tretyakovgalleryquiz;

import java.util.Arrays;

public class PictureQuestion {
    private String question;
    private int pictureImage;
    private String[] answers;
    private int correctAnswer;

    public PictureQuestion(String question, int pictureImage, String[] answers, int correctAnswer) {
        this.question = question;
        this.pictureImage = pictureImage;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public static final PictureQuestion[] PICTURE_QUESTIONS_DATA = {
            new PictureQuestion(
                    "This is a picture of Nikolai Shilder \"Temptation\". What was it significant for Tretyakov?",
                    R.drawable.pic_1,
                    new String[]{
                            "She was the first in the collection",
                            "To get it Tretyakov went to India",
                            "It was subjected to censorship",
                            "It depicts the princess Sophia"},
                    0),
            new PictureQuestion(
                    "Before you, Vasily Surikov's famous painting \"The Morning of the Streltsy Execution\". In the depiction of events, the author made a historic mistake. Name it.",
                    R.drawable.pic_2,
                    new String[]{
                            "The painting was supposed to be Ivan the Terrible, not Peter I",
                            "Events took place not on the Red, but on the Bolotnaya Square",
                            "Convicts were not allowed to say goodbye to their relatives",
                            "Sagittarians were sentenced to exile, and not to be executed"},
                    1),
            new PictureQuestion(
                    "Once an illiterate shepherd boy in the field met a monk and complained to him that he was not given a letter. The monk took the prosphore from the chest, handed it to the boy, and offered to eat it. Having eaten the prosphora, the shepherd took possession of the letter, and the monk, having predicted his extraordinary destiny, disappeared. This meeting changed the boy's whole life. What happened to this cowherd boy?",
                    R.drawable.pic_3,
                    new String[]{
                            "This young man is Peresvet, a participant of the Kulikovo battle",
                            "Bartholomew - the name given at the baptism to Mikhail Lomonosov",
                            "The boy Bartholomew is the future Reverend Sergius of Radonezh",
                            "It was Bartolomeo Rastrelli, a Russian architect of Italian origin"},
                    3)
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

    public String getCorrectAnswer() {
        return answers[correctAnswer];
    }

    @Override
    public String toString() {
        return "PictureQuestion{" +
                "question='" + question + '\'' +
                ", R.drawable.pic_1='" + pictureImage + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
