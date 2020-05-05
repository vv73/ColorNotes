package study.android.colornotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity1.*


class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)
        next.setOnClickListener() {
            //Переходим на следующую активность
            Toast.makeText(this, getString(R.string.last_page), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        // открываем редактор изменений
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        // записываем данные "ключ"-"значение"
        prefs.putString("note3", text.editableText.toString())
        // подтверждаем сделанные изменения (сохраняем)
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        // читаем по ключу
        val savedState =
            getPreferences(Context.MODE_PRIVATE).getString("note3", null)
        // если есть сохраненная заметка - восстанавливаем
        if (savedState != null) {
            text.setText(savedState)
        }
    }
}


