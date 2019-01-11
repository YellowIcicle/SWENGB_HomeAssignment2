package at.fh.swengb.derler.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface NotesDao {

    @Insert
    fun insert(note: Notes)

    @Query("SELECT * from Notes")
    fun getNotes(): List<Notes>

}