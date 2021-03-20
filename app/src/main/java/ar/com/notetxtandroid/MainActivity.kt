package ar.com.notetxtandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notes = findViewById<EditText>(R.id.et_note)
        val buttonSave = findViewById<Button>(R.id.btn_save_notes)

        if (fileList().contains("androidNotas.txt")) {
            try {
                val file = InputStreamReader(openFileInput("androidNotas.txt"))
                val br = BufferedReader(file)
                var line = br.readLine()
                val todo = StringBuilder()
                while (line != null) {
                    todo.append(line + "\n")
                    line = br.readLine()
                }
                br.close()
                file.close()
                notes.setText(todo)
            } catch (e: IOException) {
                Toast.makeText(this, e.printStackTrace().toString(), Toast.LENGTH_SHORT).show()
            }
        }

        buttonSave.setOnClickListener {
            try {
                val file = OutputStreamWriter(openFileOutput("androidNotas.txt", MODE_PRIVATE))
                file.write(notes.text.toString())
                file.flush()
                file.close()
            } catch (e: IOException) {
            }
            Toast.makeText(this, "Your notes has been saved!", Toast.LENGTH_SHORT).show()
        }
    }
}