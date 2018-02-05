package com.example.android.tretyakovgalleryquiz;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class IntroductionFragment extends Fragment {
    private onIntroductionFragmentInteractionListener mListener;
    private Context mParentContext;
    private EditText mNameEditText;
    private CheckBox mScoringCheckBox;
    private String mName;
    private boolean isScoring;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();

        // Сохранение введенных пользователем имени и галочки при смене ориентации
        if (mListener != null) {
            mListener.onIntroductionFragmentPause(String.valueOf(mNameEditText.getText()), mScoringCheckBox.isChecked());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

    // Вызывается при присоединении фрагмента к активности
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof onIntroductionFragmentInteractionListener) {
            mListener = (onIntroductionFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement onIntroductionFragmentInteractionListener");
        }

        this.mParentContext = activity;
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            initializeFragment(view);
        }
    }

    private void initializeFragment(View view) {
        mNameEditText = view.findViewById(R.id.name_edit_text);
        if (mName != null && !mName.equals("")) {
            mNameEditText.setText(mName);
        }

        mScoringCheckBox = view.findViewById(R.id.scoring_check_box);
        mScoringCheckBox.setOnClickListener(new View.OnClickListener() {
            // Установка / снятие флажка Подсчитывать результаты
            @Override
            public void onClick(View v) {
                isScoring = ((CheckBox) v).isChecked();
            }
        });
        mScoringCheckBox.setChecked(isScoring);

        Button startButton = view.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            // Нажатие на кнопку Старт
            @Override
            public void onClick(View v) {
                mName = String.valueOf(mNameEditText.getText());

                if (mName.trim().equals("")) {
                    Toast.makeText(mParentContext, mParentContext.getString(R.string.enter_your_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Сообщить слушателю о действиях пользователя
                if (mListener != null) {
                    mListener.onIntroductionFragmentInteraction(mName, isScoring);
                }
            }
        });
    }

    public void initIntroductionFragment(String name, boolean isScoring) {
        if (!name.equals("")) {
            this.mName = name;
        }

        this.isScoring = isScoring;
    }

    interface onIntroductionFragmentInteractionListener {
        void onIntroductionFragmentInteraction(String name, boolean isScoring);

        void onIntroductionFragmentPause(String name, boolean isScoring);
    }
}
