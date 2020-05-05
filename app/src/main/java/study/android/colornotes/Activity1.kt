package study.android.colornotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity1.*


class Activity1 : AppCompatActivity() {

    var colors = arrayOf(0xFFFFFFA0, 0xFFFFA0FF, 0xFFA0FFFF)
    var sheetNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        sheetNumber = getIntent().getIntExtra("sheetNumber", 0)
        next.setOnClickListener() {
            if (sheetNumber < colors.size - 1) { //запускаем свою копию
                val intent = Intent(this,this::class.java)
                //закладываем в intent номер следующего листа
                intent.putExtra("sheetNumber", sheetNumber + 1)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, R.string.last_page,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // открываем редактор изменений
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        // записываем данные "ключ"-"значение"
        prefs.putString("note" + sheetNumber, text.editableText.toString())
        // подтверждаем сделанные изменения (сохраняем)
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        //устанавливаем цвет из массива цветов
        sheet.setBackgroundColor(colors[sheetNumber].toInt())
        // читаем по ключу
        val savedState =
            getPreferences(Context.MODE_PRIVATE).getString("note"+sheetNumber.toString(), null)
        // если есть сохраненная заметка - восстанавливаем
        if (savedState != null) {
            text.setText(savedState)
        }
    }


}