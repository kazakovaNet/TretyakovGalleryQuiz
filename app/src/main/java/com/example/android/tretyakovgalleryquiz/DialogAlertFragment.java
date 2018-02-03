package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogAlertFragment extends DialogFragment {
    private final String CALL_TAG = "mCallTag";
    private final String BUTTON_TEXT_TAG = "mButtonText";
    private final String TITLE_TAG = "mTitle";
    private final String ICON_TAG = "mIcon";
    private final String MESSAGE_TAG = "mMessage";
    private final String QUESTION_CLASS = String.valueOf(QuestionFragment.class);
    private final String RESULT_CLASS = String.valueOf(ResultFragment.class);
    private final String MAIN_CLASS = String.valueOf(MainActivity.class);
    private String mCallTag;
    private int mTitle = R.string.right;
    private int mIcon;
    private String mMessage;
    private String mButtonText;
    private QuestionFragment.onQuestionFragmentInteractionListener mQuestionListener;
    private ResultFragment.OnResultFragmentInteractionListener mResultListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(TITLE_TAG)) {
            mTitle = savedInstanceState.getInt(TITLE_TAG);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(ICON_TAG)) {
            mIcon = savedInstanceState.getInt(ICON_TAG);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(MESSAGE_TAG)) {
            mMessage = savedInstanceState.getString(MESSAGE_TAG);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(CALL_TAG)) {
            mCallTag = savedInstanceState.getString(CALL_TAG);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(BUTTON_TEXT_TAG)) {
            mButtonText = savedInstanceState.getString(BUTTON_TEXT_TAG);
        }

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);

        TextView textView = v.findViewById(R.id.result_text_view);
        textView.setText(mMessage);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(mTitle)
                .setIcon(mIcon)
                .setCancelable(true)
                .setPositiveButton(mButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCallTag.equals(QUESTION_CLASS)) {
                            mQuestionListener.onQuestionDialogClose();
                        } else if (mCallTag.equals(RESULT_CLASS)) {
                            mResultListener.onExitButtonClicked();
                        } else if (mCallTag.equals(MAIN_CLASS)){
                            mResultListener.onExitButtonClicked();
                        }

                        dialog.cancel();
                    }
                })
                .create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof QuestionFragment.onQuestionFragmentInteractionListener) {
            mQuestionListener = (QuestionFragment.onQuestionFragmentInteractionListener) activity;
        }

        if (activity instanceof QuestionFragment.onQuestionFragmentInteractionListener) {
            mResultListener = (ResultFragment.OnResultFragmentInteractionListener) activity;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(TITLE_TAG, mTitle);
        outState.putInt(ICON_TAG, mIcon);
        outState.putString(MESSAGE_TAG, mMessage);
        outState.putString(CALL_TAG, mCallTag);
        outState.putString(BUTTON_TEXT_TAG, mButtonText);
    }

    public void setData(int title, String message, int icon, String callTag, String buttonText) {
        mTitle = title;
        mMessage = message;
        mIcon = icon;
        mCallTag = callTag;
        mButtonText = buttonText;
    }
}
