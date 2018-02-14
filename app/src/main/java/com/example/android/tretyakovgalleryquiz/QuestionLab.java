package com.example.android.tretyakovgalleryquiz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

class QuestionLab {
    private static QuestionLab questionLab;
    private List<Question> questions;

    private QuestionLab(Context context) {
        questions = new ArrayList<>();

        questions.add(new QuestionWithRadioButton(
                R.string.question_1,
                R.drawable.pic_1,
                R.array.answers_question_1,
                R.id.answer_1_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_2,
                R.drawable.pic_2,
                R.array.answers_question_2,
                R.id.answer_2_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_3,
                R.drawable.pic_3,
                R.array.answers_question_3,
                R.id.answer_3_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_4,
                R.drawable.pic_4,
                R.array.answers_question_4,
                R.id.answer_1_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_5,
                R.drawable.pic_5,
                R.array.answers_question_5,
                R.id.answer_3_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_6,
                R.drawable.pic_6,
                R.array.answers_question_6,
                R.id.answer_4_radio_button));
        questions.add(new QuestionWithRadioButton(
                R.string.question_7,
                R.drawable.pic_7,
                R.array.answers_question_7,
                R.id.answer_4_radio_button));
        questions.add(new QuestionWithEditText(
                R.string.question_8,
                R.drawable.pic_8,
                R.string.question_8_answer
        ));
    }

    static QuestionLab get(Context context) {
        if (questionLab == null) {
            questionLab = new QuestionLab(context);
        }

        return questionLab;
    }

    List<Question> getQuestions() {
        return questions;
    }

    Question getQuestion(int position) {
        return questions.get(position);
    }
}
