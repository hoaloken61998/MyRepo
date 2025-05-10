package com.example.k22411c_firstdegree;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import androidx.core.os.LocaleListCompat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến để quản lý các ô nhớ của các view
    // Kiểu dữ liệu + tên biến
    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;

    public static final String LANGUAGE_VIETNAMESE = "vi";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_SPANISH = "es";
    public static final String LANGUAGE_FRENCH = "fr";

    private int currentLanguageIndex = 0; // Keep track of the current language
    private final String[] languages = {LANGUAGE_VIETNAMESE, LANGUAGE_ENGLISH, LANGUAGE_SPANISH, LANGUAGE_FRENCH}; // Languages to cycle through
    private Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); //Có nhiệm vụ nạp file giao diện lên bộ nhớ, biến thành hướng đối tượng, mọi control phải dc truy xuất sau dòng này
        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get a reference to the Spinner
        languageSpinner = findViewById(R.id.languageSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected language code
                String selectedLanguage = languages[position];
                // Change the app's locale
                setAppLocale(MainActivity.this, selectedLanguage);

                // Show message
                String nameLanguage = "";
                switch (selectedLanguage){
                    case LANGUAGE_VIETNAMESE:
                        nameLanguage = getResources().getString(R.string.title_vietnamese);
                        break;
                    case LANGUAGE_ENGLISH:
                        nameLanguage = getResources().getString(R.string.title_english);
                        break;
                    case LANGUAGE_SPANISH:
                        nameLanguage = getResources().getString(R.string.title_spanish);
                        break;
                    case LANGUAGE_FRENCH:
                        nameLanguage = getResources().getString(R.string.title_french);
                        break;
                }

                Toast.makeText(MainActivity.this, "Language Change to " + nameLanguage, Toast.LENGTH_SHORT).show();

                // update views
                updateView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing if nothing is selected (shouldn't happen in a Spinner)
            }
        });
        updateView();
    }

    private void addViews() {
        edtCoefficientA = findViewById(R.id.edtCoefficientA);
        edtCoefficientB = findViewById(R.id.edtCoefficientB);
        txtResult = findViewById(R.id.txtResult);

    }

    private void setAppLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration(context.getResources().getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.createConfigurationContext(config);
        } else {
            config.setLocale(locale);
            context.createConfigurationContext(config);
        }
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private void updateView() {
        String selectedLanguage = languages[languageSpinner.getSelectedItemPosition()];
        Context context = this;
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = new Locale(selectedLanguage);
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration);
        } else {
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }

        // Update text views
        TextView textCoefficientA = findViewById(R.id.textView2);
        textCoefficientA.setText(getResources().getText(R.string.title_coefficient_a));

        TextView textCoefficientB = findViewById(R.id.textView3);
        textCoefficientB.setText(getResources().getText(R.string.title_coefficient_b));

        TextView textResultText = findViewById(R.id.textView7);
        textResultText.setText(getResources().getText(R.string.title_Result));

        TextView textCoefficientAText = findViewById(R.id.textView2);
        textCoefficientAText.setText(getResources().getText(R.string.title_coefficient_a));

        TextView textCoefficientBText = findViewById(R.id.textView3);
        textCoefficientBText.setText(getResources().getText(R.string.title_coefficient_b));

        Button btnSolution = findViewById(R.id.btnSolution);
        btnSolution.setText(getResources().getText(R.string.title_solution));

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setText(getResources().getText(R.string.title_Next));

        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setText(getResources().getText(R.string.title_Exit));

        // Update the result (txtResult)
        if (txtResult.getText().toString().equals(getResources().getString(R.string.title_infinity))) {
            txtResult.setText(getResources().getText(R.string.title_infinity));
        } else if (txtResult.getText().toString().equals(getResources().getString(R.string.title_no_solution))) {
            txtResult.setText(getResources().getText(R.string.title_no_solution));
        } else if (txtResult.getText().toString().startsWith("x=")) {
            // Keep the numeric part and just update "x=" to the correct language
            String x = txtResult.getText().toString().substring(2);
            txtResult.setText("x=" + x);
        } else {
            // default value: make it empty
            txtResult.setText("");
        }
    }


    @SuppressLint("SetTextI18n")
    public void do_solution(View view) {
        //Lấy hệ số a trên giao diện
        String hsa = edtCoefficientA.getText().toString();
        double a = Double.parseDouble(hsa);
        //Lấy hệ số b trên giao diện
        double b = Double.parseDouble(edtCoefficientB.getText().toString());

        if (a==0 & b==0)
        {
            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if (a==0 & b!=0)
        {
            txtResult.setText(getResources().getText(R.string.title_no_solution));
        }
        else {
            //Tính nghiệm
            double x = -b / a;
            txtResult.setText("x=" + x);
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        //Di chuyển con trỏ nhập liệu và hệ số A
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}