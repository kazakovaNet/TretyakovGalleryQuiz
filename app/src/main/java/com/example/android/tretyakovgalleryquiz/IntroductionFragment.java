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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment {
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private CheckBox mScoringCheckBox;
    private TextView mShowResultTextView;
    private RadioGroup mShowResultRadioGroup;
    private IntroductionListener mIntroductionListener;
    private boolean mOnScreen = true;
    private Context mParentContext;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    interface IntroductionListener {
        void onIntroductionFragmentInteraction(String name, String email);
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

        this.mIntroductionListener = (IntroductionListener) activity;
        this.mParentContext = activity;
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            mNameEditText = view.findViewById(R.id.name_edit_text);
            mEmailEditText = view.findViewById(R.id.email_edit_text);
            mShowResultTextView = view.findViewById(R.id.show_result_text_view);

            mScoringCheckBox = view.findViewById(R.id.scoring_check_box);
            mScoringCheckBox.setOnClickListener(new View.OnClickListener() {
                // Установка / снятие флажка Подсчитывать результаты
                @Override
                public void onClick(View v) {
                    int visible;

                    // Скрытие / отображение блока способа отображения результатов
                    // в зависимости от установленного checkbox
                    if (mScoringCheckBox.isChecked()) {
                        visible = View.VISIBLE;
                    } else {
                        visible = View.GONE;

                        // Скрытие поля ввода электронной почты при снятии галочки подсчета результатов
                        mEmailEditText.setVisibility(visible);
                    }

                    mShowResultTextView.setVisibility(visible);
                    mShowResultRadioGroup.setVisibility(visible);
                    mShowResultRadioGroup.clearCheck();
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

                    // Сохранение введенного адреса электронной почты,
                    // если выбран вариант отправки результата на почту
                    String email;
                    if (!mOnScreen) {
                        email = String.valueOf(mEmailEditText.getText());

                        // TODO добавить валидацию адреса почты
                        if (email.equals("")) {
                            Toast.makeText(mParentContext, "Укажите адрес электронной почты", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        email = "";
                    }

                    // Сообщить слушателю о действиях пользователя
                    if (mIntroductionListener != null) {
                        mIntroductionListener.onIntroductionFragmentInteraction(name, email);
                    }
                }
            });

            mShowResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
            mShowResultRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int visible = View.GONE;

                    switch (checkedId) {
                        case R.id.on_screen_radio_button:
                            visible = View.GONE;

                            break;
                        case R.id.on_email_radio_button:
                            visible = View.VISIBLE;

                            // Флаг отображения результатов на экране
                            mOnScreen = false;
                            break;
                    }

                    // Скрытие / отображение поля для ввода e-mail
                    mEmailEditText.setVisibility(visible);
                }
            });
        }
    }
}
