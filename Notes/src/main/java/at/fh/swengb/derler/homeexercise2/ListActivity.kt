package at.fh.swengb.derler.homeexercise2

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.note_list_activity.*

 class ListActivity : AppCompatActivity() {

    private var noteList = mutableListOf<Notes>()

    private var noteAdapter = NoteAdapter()

    private lateinit var db: NotesRoomDatabase


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_list_activity)

        db = NotesRoomDatabase.getDatabase(applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter()
        recycler_view.adapter = noteAdapter
        title = "Notes"

        val dbItemz = db.noteDao.getNotes()
        noteAdapter.updateList(dbItemz)

        val dbItems = db.noteDao.getNotes()

        val namePerson = intent.getStringExtra("sharedPreferencesName")
        val agePerson = intent.getStringExtra("sharedPreferencesAge")


        noteHeader.text = "Notes for $namePerson, $agePerson"

        addNote.setOnClickListener {

            val newNote = Intent(this, AddNote::class.java)

            startActivity(newNote)

        }

    }

    fun insertNote() {
        val item = noteList.firstOrNull() ?: return
        db.noteDao.insert(item)
        val dbItems = db.noteDao.getNotes()
        noteAdapter.updateList(dbItems)

    }

     override fun onResume() {
        super.onResume()

         val dbItemz = db.noteDao.getNotes()
         noteAdapter.updateList(dbItemz)

         insertNote()


     }
}