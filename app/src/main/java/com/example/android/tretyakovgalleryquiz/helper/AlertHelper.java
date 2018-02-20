package com.example.android.tretyakovgalleryquiz.helper;

import android.app.Activity;
import android.app.FragmentManager;

import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.fragment.DialogAlertFragment;
import com.example.android.tretyakovgalleryquiz.fragment.ResultFragment;

public class AlertHelper {
    private final String DIALOG_ALERT = "DialogAlert";
    private final Activity mActivity;
    private FragmentManager mFragmentManager;

    public AlertHelper(Activity activity) {
        mActivity = activity;
    }

    public void openQuitDialog() {
        mFragmentManager = mActivity.getFragmentManager();
        DialogAlertFragment quitDialog = new DialogAlertFragment();

        quitDialog.initDialogAlertFragment(
                R.string.quit,
                mActivity.getString(R.string.are_you_sure),
                R.drawable.end_icon,
                String.valueOf(mActivity.getClass()),
                mActivity.getString(R.string.exit));

        quitDialog.show(mFragmentManager, DIALOG_ALERT);
    }

    public void openResultDialog(int score, int countOfQuestion) {
        mFragmentManager = mActivity.getFragmentManager();
        DialogAlertFragment resultDialog = new DialogAlertFragment();

        resultDialog.initDialogAlertFragment(
                R.string.your_result,
                mActivity.getString(R.string.text_result_on_screen, score, countOfQuestion),
                R.drawable.right_icon,
                String.valueOf(ResultFragment.class),
                mActivity.getString(R.string.exit));

        resultDialog.show(mFragmentManager, DIALOG_ALERT);
    }

    public void openAnswerDialog(boolean isAnswerTrue, String correctAnswer, String className) {
        mFragmentManager = mActivity.getFragmentManager();

        DialogAlertFragment questionDialog = new DialogAlertFragment();
        String message;

        if (!isAnswerTrue) {
            message = mActivity.getString(R.string.wrong_answer_text) + correctAnswer;

            questionDialog.initDialogAlertFragment(
                    R.string.wrong,
                    message,
                    R.drawable.wrong_icon,
                    className,
                    mActivity.getString(R.string.next));
        } else {
            message = mActivity.getString(R.string.right_answer_text);

            questionDialog.initDialogAlertFragment(
                    R.string.right,
                    message,
                    R.drawable.right_icon,
                    className,
                    mActivity.getString(R.string.next));
        }

        questionDialog.show(mFragmentManager, DIALOG_ALERT);
    }
}
