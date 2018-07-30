package ysaak.geotoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.Locale;

public class MainActivity extends Activity {
    private LinearLayout wordValuePanel = null;
    private LinearLayout wordDigitalRootPanel = null;
    private TextView wordValueField = null;
    private TextView wordValueOneDigitField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText wordInputField = findViewById(R.id.word_input_field);
        wordValueField = findViewById(R.id.word_value_field);
        wordValueOneDigitField = findViewById(R.id.word_digital_root);

        wordValuePanel = findViewById(R.id.word_value_panel);
        wordDigitalRootPanel = findViewById(R.id.word_digital_root_panel);

        wordInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {/**/}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {/**/}

            @Override
            public void afterTextChanged(Editable editable) {
                calculateAndDisplayWordValue(editable.toString());
            }
        });

        calculateAndDisplayWordValue("");
    }

    private void calculateAndDisplayWordValue(String word) {
        if (word == null || word.trim().isEmpty()) {
            wordValuePanel.setVisibility(View.INVISIBLE);
            wordDigitalRootPanel.setVisibility(View.INVISIBLE);
            wordValueField.setText("");
            wordValueOneDigitField.setText("");
            return;
        }

        int value = 0;

        String loweredWord = stripAccents(word.trim().toLowerCase());

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (char c : loweredWord.toCharArray()) {
            if (!first) {
                sb.append(" + ");
            }

            if (c >= 'a' && c <= 'z') {
                int cVal = (int) c - 'a' + 1;
                value += cVal;
                sb.append(cVal);
            }
            else {
                sb.append("0");
            }

            first = false;
        }

        sb.append(" = ").append(value);

        wordValueField.setText(sb.toString());

        int digitalRoot = value % 9;
        wordValueOneDigitField.setText(String.format(Locale.getDefault(), "%d = %d", value, digitalRoot));
        wordValuePanel.setVisibility(View.VISIBLE);
        wordDigitalRootPanel.setVisibility(View.VISIBLE);
    }

    private static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}
