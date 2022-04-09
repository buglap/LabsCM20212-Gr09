package co.edu.udea.compumovil.gr09_2022.lab1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class PersonalDataActivity : AppCompatActivity() {
    private var selectedSpinnerOption: String = ""
    private val calendar = Calendar.getInstance()
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var month = calendar.get(Calendar.MONTH)
    private var year = calendar.get(Calendar.YEAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var educationLevelSpinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.education_level,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            educationLevelSpinner.adapter = adapter
        }

        educationLevelSpinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val grades = resources.getStringArray(R.array.education_level)
                selectedSpinnerOption = grades[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val changeBirthDateButton = findViewById<Button>(R.id.change)
        val birthDateText = findViewById<TextView>(R.id.bithday)

        changeBirthDateButton.setOnClickListener {
            val datePicker = DatePickerDialog(
                    this,
                    { _: DatePicker?, datePickerYear: Int, datePickerMonth: Int, datePickerDay: Int ->
                        year = datePickerYear
                        month = datePickerMonth
                        day = datePickerDay
                        birthDateText.text = "" + datePickerDay + "/" + (datePickerMonth + 1) + "/" + datePickerYear
                    },
                    year,
                    month,
                    day
            )
            datePicker.show()
        }
    }

    fun radioButtonhandler(view: View) {}

    private fun showErrorMessage() {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
    }

    fun submitbuttonHandler(view: View) {
        val nextButton = findViewById<Button>(R.id.next)
        val namesInput = findViewById<EditText>(R.id.names)
        val lastNamesInput = findViewById<EditText>(R.id.lastName)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val birthDateText = findViewById<TextView>(R.id.bithday)
        nextButton.setOnClickListener {
            if(genderRadioGroup.checkedRadioButtonId == -1 || namesInput.text.isEmpty() || lastNamesInput.text.isEmpty() || birthDateText.text.isEmpty())
                showErrorMessage()
            else {
                val intent: Intent = Intent(this, ContactDataActivity::class.java)
                startActivity(intent)
            }
        }
    }


}