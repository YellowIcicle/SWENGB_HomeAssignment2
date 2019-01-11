package at.fh.swengb.derler.homeexercise2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Notes"

        db = NotesRoomDatabase.getDatabase(this)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val textNameView = findViewById<View>(R.id.textName) as EditText
        val textAgeView = findViewById<View>(R.id.textAge) as EditText


            saveButton.setOnClickListener {


                val name = sharedPreferences.edit().putString("Name", textNameView.text.toString()).apply()
                val age = sharedPreferences.edit().putInt("Age", textAgeView.text.toString().toInt()).apply()


                val nameString = sharedPreferences.getString("Name", "")
                Log.v("Name", nameString)

                val ageString = sharedPreferences.getInt("Age", 0)
                Log.v("Age", ageString.toString())

                val intent = Intent(this, ListActivity::class.java)

                intent.putExtra("sharedPreferencesName", nameString)
                intent.putExtra("sharedPreferencesAge", ageString.toString())


                startActivity(intent)

        }
    }
}


