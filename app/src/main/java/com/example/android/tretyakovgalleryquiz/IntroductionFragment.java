package com.example.android.tretyakovgalleryquiz;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
public class IntroductionFragment extends Fragment implements View.OnClickListener {
    private EditText nameEditText;
    private EditText emailEditText;
    private CheckBox scoringCheckBox;
    private TextView showResultTextView;
    private RadioGroup showResultRadioGroup;
    private IntroductionListener listener;
    private boolean onScreen;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    interface IntroductionListener {
        void onIntroductionFragmentClicked(String name, String email);
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
            Button startButton = view.findViewById(R.id.start_button);
            startButton.setOnClickListener(this);
            showResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
            showResultRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int visible = View.GONE;

                    switch (checkedId) {
                        case R.id.on_screen_radio_button:
                            visible = View.GONE;

                            // Флаг отображения результатов на экране
                            onScreen = true;
                            break;
                        case R.id.on_email_radio_button:
                            visible = View.VISIBLE;
                            break;
                    }

                    // Скрытие / отображение поля для ввода e-mail
                    emailEditText.setVisibility(visible);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // Установка / снятие флажка Подсчитывать результаты
            case R.id.scoring_check_box:
                int visible;
                Log.d("IntroductionFragment", String.valueOf(emailEditText.getVisibility()));
                // Скрытие / отображение блока способа отображения результатов
                // в зависимости от установленного checkbox
                if (scoringCheckBox.isChecked()) {
                    visible = View.VISIBLE;
                } else {
                    visible = View.GONE;
                    Log.d("IntroductionFragment", String.valueOf(emailEditText.getVisibility()));

                    // Скрытие поля ввода электронной почты при снятии галочки подсчета результатов
                    emailEditText.setVisibility(visible);
                    Log.d("IntroductionFragment", String.valueOf(emailEditText.getVisibility()));

                }

                showResultTextView.setVisibility(visible);
                showResultRadioGroup.setVisibility(visible);
                showResultRadioGroup.clearCheck();

                Log.d("IntroductionFragment", String.valueOf(emailEditText.getVisibility()));
                break;
            // Нажатие на кнопку Старт
            case R.id.start_button:
                String name = String.valueOf(nameEditText.getText());

                // TODO добавить валидацию имени
                if (name.equals("")) {
                    Toast.makeText(this.getActivity(), "Укажите свое имя", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Сохранение введенного адреса электронной почты,
                // если выбран вариант отправки результата на почту
                String email;
                if (!onScreen) {
                    email = String.valueOf(emailEditText.getText());

                    // TODO добавить валидацию адреса почты
                    if (email.equals("")) {
                        Toast.makeText(this.getActivity(), "Укажите адрес электронной почты", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    email = "";
                }

                // Сообщить слушателю о действиях пользователя
                if (listener != null) {
                    listener.onIntroductionFragmentClicked(name, email);
                }

                break;
            case R.id.show_result_radio_group:
                showResultRadioGroup.getCheckedRadioButtonId();
        }
    }
}
