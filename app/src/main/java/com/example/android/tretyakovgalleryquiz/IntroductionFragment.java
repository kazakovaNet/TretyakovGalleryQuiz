package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment {
    private final String TAG = "IntroductionFragment";
    private EditText mNameEditText;
    private CheckBox mScoringCheckBox;
    private Context mParentContext;
    private onIntroductionFragmentInteractionListener mListener;
    private boolean isScoring;
    private String mName;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    interface onIntroductionFragmentInteractionListener {
        void onIntroductionFragmentSendData(String name, boolean isScoring);

        void onIntroductionFragmentInteraction(String name, boolean isScoring);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Сохранение введенных пользователем имени и галочки при смене ориентации
        if (mListener != null) {
            mListener.onIntroductionFragmentInteraction(String.valueOf(mNameEditText.getText()), mScoringCheckBox.isChecked());
            mNameEditText.setText("");
            mNameEditText.setHint("");
        }

        Log.d(TAG, "onPause");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

    // Вызывается при присоединении фрагмента к активности
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d(TAG, "onAttach");

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

        Log.d(TAG, "onStart");

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

                // TODO добавить валидацию имени
                if (mName.equals("")) {
                    Toast.makeText(mParentContext, "Укажите свое имя", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Сообщить слушателю о действиях пользователя
                if (mListener != null) {
                    mListener.onIntroductionFragmentSendData(mName, isScoring);
                }
            }
        });
    }

    public void setIntroductionData(String name, boolean isScoring) {
        if (!name.equals("")) {
            this.mName = name;
        }
        this.isScoring = isScoring;
    }
}
