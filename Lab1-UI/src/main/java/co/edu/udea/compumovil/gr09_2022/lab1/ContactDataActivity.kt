package co.edu.udea.compumovil.gr09_2022.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class ContactDataActivity : AppCompatActivity() {

    private val countries = Locale.getAvailableLocales().map { it.displayCountry }.filter { it.trim().isNotBlank() }.distinct().sorted()

    private val cities = Locale.getAvailableLocales().map { it.displayCountry }.filter { it.trim().isNotBlank() }.distinct().sorted()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

    }

    private fun showErrorMessage() {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
    }

    fun submitbuttonHandler(view: View) {
        val nextButton = findViewById<Button>(R.id.nextContact)
        val phone = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val address = findViewById<EditText>(R.id.address)
        val country = findViewById<AutoCompleteTextView>(R.id.country)
        val city = findViewById<AutoCompleteTextView>(R.id.city)

        val countryAdapter = ArrayAdapter(this,
                R.layout.autocomplete_item, R.id.autoCompleteItem, countries
        )
        val cityAdapter = ArrayAdapter(this,
                R.layout.autocomplete_item, R.id.autoCompleteItem, cities
        )
        country.setAdapter(countryAdapter)
        city.setAdapter(cityAdapter)
        if(phone.text.isEmpty() && address.text.isEmpty() && email.text.isEmpty()  && country.text.isEmpty()  && city.text.isEmpty())
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
        else {
            Toast.makeText(this, "succeed", Toast.LENGTH_LONG).show()
        }
    }
}