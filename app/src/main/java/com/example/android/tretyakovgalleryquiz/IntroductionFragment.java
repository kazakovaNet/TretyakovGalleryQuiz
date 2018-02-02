package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment {
    private EditText mNameEditText;
    private CheckBox mScoringCheckBox;
    private Context mParentContext;
    private onIntroductionFragmentInteractionListener mListener;
    private boolean isScoring;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    interface onIntroductionFragmentInteractionListener {
        void onIntroductionFragmentInteraction(String name, boolean isScoring);
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
    public void onDestroy() {
        super.onDestroy();

        // TODO добавить сохранение введенных имени и галочки
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

        mScoringCheckBox = view.findViewById(R.id.scoring_check_box);
        mScoringCheckBox.setOnClickListener(new View.OnClickListener() {
            // Установка / снятие флажка Подсчитывать результаты
            @Override
            public void onClick(View v) {
                isScoring = ((CheckBox) v).isChecked();
            }
        });

        Button startButton = view.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            // Нажатие на кнопку Старт
            @Override
            public void onClick(View v) {
                String name = String.valueOf(mNameEditText.getText());

                // TODO добавить валидацию имени
                if (name.equals("")) {
                    Toast.makeText(mParentContext, "Укажите свое имя", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Сообщить слушателю о действиях пользователя
                if (mListener != null) {
                    mListener.onIntroductionFragmentInteraction(name, isScoring);
                }
            }
        });
    }
}
