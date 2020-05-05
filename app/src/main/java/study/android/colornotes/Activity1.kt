package study.android.colornotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity1.*

class Activity1 : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        next.setOnClickListener() {
            //Переходим на следующую активность
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        // открываем редактор изменений
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        // записываем данные "ключ"-"значение"
        prefs.putString("note1", text.editableText.toString())
        // подтверждаем сделанные изменения (сохраняем)
        prefs.commit()
    }

    override fun onResume() {
        super.onResume()
        // читаем по ключу
        val savedState =
            getPreferences(Context.MODE_PRIVATE).getString("note1", null)
        // если есть сохраненная заметка - восстанавливаем
        if (savedState != null) {
            text.setText(savedState)
        }
    }

}