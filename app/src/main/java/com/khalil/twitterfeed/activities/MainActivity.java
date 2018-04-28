package com.khalil.twitterfeed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.activities.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static final String KEY_SCREEN_NAME = "screenName";
    EditText editTxtScreenName;
    Button btnViewTimeline;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtScreenName = findViewById(R.id.editTxtScreenName);
        btnViewTimeline = findViewById(R.id.btnViewTimeline);
        textInputLayout = findViewById(R.id.txtInputLayout);

        btnViewTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewTimeline();
            }
        });

        editTxtScreenName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editable.toString())) {
                    return;
                }

                textInputLayout.setError(null);
            }
        });

        editTxtScreenName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    onViewTimeline();
                    return true;
                }
                return false;
            }
        });
    }

    private void onViewTimeline() {
        String screenName = editTxtScreenName.getText().toString();
        if(!isScreenNameValid(screenName)) {
            return;
        }

        navigateToTwitterTimeline(screenName);
    }

    private void navigateToTwitterTimeline(String screenName) {
        Intent intent = new Intent(this, TwitterTimelineActivity.class);
        intent.putExtra(KEY_SCREEN_NAME, screenName);
        startActivity(intent);
    }

    private boolean isScreenNameValid(String screenName) {
        if(TextUtils.isEmpty(screenName)) {
            textInputLayout.setError(getString(R.string.error_empty_screen_name));
            return false;
        }

        if(screenName.length() < 5 || screenName.length() > 15){
            textInputLayout.setError(getString(R.string.error_invalid_screen_name));
            return false;
        }

        return true;
    }

    @Override
    public View getContentView() {
        return findViewById(R.id.mainLayout);
    }
}
