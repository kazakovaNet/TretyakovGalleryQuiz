package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment implements View.OnClickListener {
    EditText nameEditText;
    EditText emailEditText;
    CheckBox scoringCheckBox;
    TextView showResultTextView;
    RadioGroup showResultRadioGroup;
    IntroductionListener listener;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    interface IntroductionListener {
        void onIntroductionClicked(String name);
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

        this.listener = (IntroductionListener) activity;
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            nameEditText = view.findViewById(R.id.name_edit_text);
            emailEditText = view.findViewById(R.id.email_edit_text);
            scoringCheckBox = view.findViewById(R.id.scoring_check_box);
            scoringCheckBox.setOnClickListener(this);
            showResultTextView = view.findViewById(R.id.show_result_text_view);
            showResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
        }
    }

    @Override
    public void onClick(View v) {
        String name = "";

        switch (v.getId()) {
            case R.id.scoring_check_box:
                name = String.valueOf(nameEditText.getText());
                boolean scoring = scoringCheckBox.isChecked();

//                Log.d("IntroductionFragment", name + "\n" + String.valueOf(scoring));

                if (scoring) {
                    showResultTextView.setVisibility(View.VISIBLE);
                    showResultRadioGroup.setVisibility(View.VISIBLE);
                }
                break;
        }

        if (listener != null) {
            // Сообщить слушателю о том, что на одной из кнопок был сделан щелчок
            listener.onIntroductionClicked(name);
        }
    }
}
