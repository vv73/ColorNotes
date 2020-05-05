package study.android.colornotes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity1.*

class Activity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        next.setOnClickListener() {
            //Переходим на следующую активность
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        // открываем редактор изменений
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        // записываем данные "ключ"-"значение"
        prefs.putString("note2", text.editableText.toString())
        // подтверждаем сделанные изменения (сохраняем)
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        // читаем по ключу
        val savedState =
            getPreferences(Context.MODE_PRIVATE).getString("note2", null)
        // если есть сохраненная заметка - восстанавливаем
        if (savedState != null) {
            text.setText(savedState)
        }
    }
}

