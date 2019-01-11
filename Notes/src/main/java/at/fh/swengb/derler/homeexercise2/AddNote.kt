package at.fh.swengb.derler.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.add_note.*

class AddNote : AppCompatActivity() {

    private val noteList = mutableListOf<Notes>()

    private var noteAdapter = NoteAdapter()

    private lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note)
        db = NotesRoomDatabase.getDatabase(this)
        title = "Notes"


    }

    fun addNote(view: View) {

        val title = noteTitle.text.toString()
        val content = noteContent.text.toString()

        val note = Notes(title, content)
        noteList.add(note)

        val item = noteList.firstOrNull() ?: return
        db.noteDao.insert(item)
        val dbItems = db.noteDao.getNotes()
        noteAdapter.updateList(dbItems)

        finish()


    }
}

