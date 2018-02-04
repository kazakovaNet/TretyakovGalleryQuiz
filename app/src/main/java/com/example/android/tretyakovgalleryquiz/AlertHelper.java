package com.example.android.tretyakovgalleryquiz;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.RadioButton;

class AlertHelper {
    private final String DIALOG_ALERT = "DialogAlert";
    private Activity mActivity;
    private FragmentManager mFragmentManager;

    AlertHelper(Activity activity) {
        mActivity = activity;
    }

    void openQuestionDialog(int correctAnswer, int selectAnswer) {
        mFragmentManager = mActivity.getFragmentManager();

        DialogAlertFragment questionDialog = new DialogAlertFragment();

        String message;
        if (correctAnswer != selectAnswer) {
            message = mActivity.getString(R.string.wrong_answer_text)
                    + ((RadioButton) mActivity.findViewById(correctAnswer))
                    .getText();

            questionDialog.setData(
                    R.string.wrong,
                    message,
                    R.drawable.wrong_icon,
                    String.valueOf(QuestionFragment.class),
                    mActivity.getString(R.string.next));
        } else {
            message = mActivity.getString(R.string.right_answer_text);

            questionDialog.setData(
                    R.string.right, message,
                    R.drawable.right_icon,
                    String.valueOf(QuestionFragment.class),
                    mActivity.getString(R.string.next));
        }

        questionDialog.show(mFragmentManager, DIALOG_ALERT);
    }

    void openQuitDialog() {
        mFragmentManager = mActivity.getFragmentManager();
        DialogAlertFragment quitDialog = new DialogAlertFragment();

        quitDialog.setData(
                R.string.quit,
                mActivity.getString(R.string.are_you_sure),
                R.drawable.end_icon,
                String.valueOf(mActivity.getClass()),
                mActivity.getString(R.string.exit));

        quitDialog.show(mFragmentManager, DIALOG_ALERT);
    }
}
