package at.fh.swengb.derler.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.util.Log

@Entity(tableName = "Notes")
class Notes(val title: String, val content: String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    }