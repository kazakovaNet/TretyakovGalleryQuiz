package com.example.android.tretyakovgalleryquiz.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tretyakovgalleryquiz.R;
import com.example.android.tretyakovgalleryquiz.activity.MainActivity;

public class DialogAlertFragment extends DialogFragment {
    private final String CALL_TAG = "mCallTag";
    private final String BUTTON_TEXT_TAG = "mButtonText";
    private final String TITLE_TAG = "mTitle";
    private final String ICON_TAG = "mIcon";
    private final String MESSAGE_TAG = "mMessage";
    private final String QUESTION_WITH_RADIO_BUTTON_CLASS = String.valueOf(QuestionWithRadioButtonFragment.class);
    private final String QUESTION_WITH_EDIT_TEXT_CLASS = String.valueOf(QuestionWithEditTextFragment.class);
    private final String RESULT_CLASS = String.valueOf(ResultFragment.class);
    private final String MAIN_CLASS = String.valueOf(MainActivity.class);
    private String mCallTag;
    private String mMessage;
    private String mButtonText;
    private int mTitle = R.string.right;
    private int mIcon;
    private QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener mOnQuestionWithRadioButtonFragmentInteractionListener;
    private QuestionWithEditTextFragment.onQuestionWithEditTextFragmentInteractionListener mOnQuestionWithEditTextFragmentInteractionListener;
    private ResultFragment.OnResultFragmentInteractionListener mResultListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Запрет на закрытие диалога по клику за пределами
        getDialog().setCanceledOnTouchOutside(false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getSaveInstanceState(savedInstanceState);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);

        TextView textView = v.findViewById(R.id.result_text_view);
        textView.setText(mMessage);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(mTitle)
                .setIcon(mIcon)
                .setPositiveButton(mButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCallTag.equals(QUESTION_WITH_RADIO_BUTTON_CLASS)) {
                            mOnQuestionWithRadioButtonFragmentInteractionListener.onQuestionDialogClose();
                        } else if (mCallTag.equals(QUESTION_WITH_EDIT_TEXT_CLASS)) {
                            mOnQuestionWithEditTextFragmentInteractionListener.onQuestionDialogClose();
                        }if (mCallTag.equals(RESULT_CLASS)) {
                            mResultListener.onExitButtonClicked();
                        } else if (mCallTag.equals(MAIN_CLASS)){
                            mResultListener.onExitButtonClicked();
                        }

                        dialog.cancel();
                    }
                })
                .create();
    }

    private void getSaveInstanceState(Bundle savedInstanceState) {
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
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener) {
            mOnQuestionWithRadioButtonFragmentInteractionListener = (QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener) activity;
        }

        if (activity instanceof QuestionWithEditTextFragment.onQuestionWithEditTextFragmentInteractionListener) {
            mOnQuestionWithEditTextFragmentInteractionListener = (QuestionWithEditTextFragment.onQuestionWithEditTextFragmentInteractionListener) activity;
        }

        if (activity instanceof QuestionWithRadioButtonFragment.onQuestionWithRadioButtonFragmentInteractionListener) {
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

    public void initDialogAlertFragment(int title, String message, int icon, String callTag, String buttonText) {
        mTitle = title;
        mMessage = message;
        mIcon = icon;
        mCallTag = callTag;
        mButtonText = buttonText;
    }
}
