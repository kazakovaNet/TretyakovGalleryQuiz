package com.example.android.tretyakovgalleryquiz.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.tretyakovgalleryquiz.R;

/**
 * Класс приветственного фрагмента.
 * Активити, содержащие данный фрагмент должны реализовывать интерфейс
 * onIntroductionFragmentInteractionListener для обработки
 * взаимодействия пользователя с фрагментом
 */
public class IntroductionFragment extends Fragment {
    public static final String IS_SCORING = "isScoring";
    public final String M_NAME = "mName";
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

        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

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

    /**
     * Метод инициализирует данные во фрагменте
     *
     * @param view объект представления фрагмента
     */
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

    /**
     * Метод устанавливает данные для фрагмента (после его приостановки)
     *
     * @param name      введенное пользователем имя
     * @param isScoring выбранное пользователем состояние виджета CheckBox, отвечающего за посчет очков
     */
    public void initIntroductionFragment(String name, boolean isScoring) {
        if (!name.equals("")) {
            this.mName = name;
        }

        this.isScoring = isScoring;
    }

    /**
     * Этот интерфейс должен реализовываться активностью, которая содержит этот фрагмент
     * для возможности взаимодействия с этим фрагментом
     */
    public interface onIntroductionFragmentInteractionListener {
        void onIntroductionFragmentInteraction(String name, boolean isScoring);

        void onIntroductionFragmentPause(String name, boolean isScoring);
    }
}
