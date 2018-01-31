package com.example.android.tretyakovgalleryquiz;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnResultFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ResultFragment extends Fragment {
    private TextView mShowResultTextView;
    private EditText mEmailEditText;
    private RadioGroup mShowResultRadioGroup;
    private Button mResultButton;
    private boolean mOnScreen = true;
    private boolean isScoring;
    private String mEmail;
    private String TAG = "ResultFragment";
    private OnResultFragmentInteractionListener mListener;
    private Context mParentContext;
    private String mName;
    private TextView mNameTextView;
    private TextView mResultTextView;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
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
        // Назначение имени пользователя
        mNameTextView = view.findViewById(R.id.name_text_view);
        mNameTextView.setText(mName);

        mEmailEditText = view.findViewById(R.id.email_edit_text);
        mShowResultTextView = view.findViewById(R.id.show_result_text_view);

        mResultTextView = view.findViewById(R.id.result_on_screen_text_view);

        mShowResultRadioGroup = view.findViewById(R.id.show_result_radio_group);
        mShowResultRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.on_screen_radio_button:
                        // Отображение текста с результатом викторины
                        mResultTextView.setVisibility(View.VISIBLE);
                        // Скрытие поля для ввода e-mail
                        mEmailEditText.setVisibility(View.GONE);

                        break;
                    case R.id.on_email_radio_button:
                        // Скрытие текста с результатом викторины
                        mResultTextView.setVisibility(View.GONE);
                        // Отображение поля для ввода e-mail
                        mEmailEditText.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });
        mShowResultRadioGroup.clearCheck();

        mResultButton = view.findViewById(R.id.result_button);
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение выбранного варианта отображения результатов
                int checkedRadioButtonId = mShowResultRadioGroup.getCheckedRadioButtonId();

                if (checkedRadioButtonId == R.id.on_screen_radio_button){
                    // Отображение текста с результатом викторины
                    mResultTextView.setVisibility(View.VISIBLE);
                }

                if (mEmailEditText.getVisibility() == View.VISIBLE) {
                    // Сохранение введенного адреса электронной почты,
                    // если выбран вариант отправки результата на почту
                    mEmail = String.valueOf(mEmailEditText.getText());

                    // TODO добавить валидацию адреса почты
                    if (mEmail.equals("")) {
                        Toast.makeText(mParentContext, "Укажите адрес электронной почты", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    mEmail = "";
                }

                // Сообщить слушателю о действиях пользователя
                if (mListener != null) {
                    mListener.onResultFragmentInteraction(mEmail);
                }
            }
        });

        // Скрытие / отображение блока способа отображения результатов
        // в зависимости от установленного checkbox на приветственном экране
        int visible;
        if (isScoring) {
            visible = View.VISIBLE;
        } else {
            visible = View.GONE;
        }

        mShowResultTextView.setVisibility(visible);
        mShowResultRadioGroup.setVisibility(visible);
    }

    public void setResultData(String name, boolean isScoring) {
        this.isScoring = isScoring;
        this.mName = name;

        Log.d(TAG, String.valueOf(isScoring));
        Log.d(TAG, name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnResultFragmentInteractionListener) {
            mListener = (OnResultFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnResultFragmentInteractionListener");
        }

        mParentContext = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnResultFragmentInteractionListener {
        // TODO: Update argument type and name
        void onResultFragmentInteraction(String email);
    }
}
