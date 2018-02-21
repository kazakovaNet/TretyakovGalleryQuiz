package com.example.android.tretyakovgalleryquiz.helper;

import android.app.Activity;
import android.app.FragmentManager;

import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.fragment.DialogAlertFragment;

public class AlertHelper {
    private final String DIALOG_ALERT = "DialogAlert";
    private final Activity mActivity;

    public AlertHelper(Activity activity) {
        mActivity = activity;
    }

    public void openAnswerDialog(boolean isAnswerTrue, String correctAnswer, String className) {
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

        questionDialog.show(mActivity.getFragmentManager(), DIALOG_ALERT);
    }
}
