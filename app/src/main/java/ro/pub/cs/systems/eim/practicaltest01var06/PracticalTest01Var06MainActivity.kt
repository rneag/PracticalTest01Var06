package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val playButton: Button by lazy { findViewById(R.id.play) }
    val randomButton: Button by lazy { findViewById(R.id.random) }
    val number1: EditText by lazy { findViewById(R.id.number1) }
    val number2: EditText by lazy { findViewById(R.id.number2) }
    val number3: EditText by lazy { findViewById(R.id.number3) }
    val checkbox1: CheckBox by lazy { findViewById(R.id.checkbox1) }
    val checkbox2: CheckBox by lazy { findViewById(R.id.checkbox2) }
    val checkbox3: CheckBox by lazy { findViewById(R.id.checkbox3) }

    private fun play() {
        Toast.makeText(applicationContext, "Numerele sunt: ${number1.text} ${number2.text} ${number3.text}", Toast.LENGTH_SHORT).show()

        var checkbox_count: Int = 0

        if (checkbox1.isChecked)
            checkbox_count++

        if (checkbox2.isChecked)
            checkbox_count++

        if (checkbox3.isChecked)
            checkbox_count++

        val intent = Intent(this, PracticalTest01Var06SecondaryActivity::class.java).apply {
            putExtra("NUMBER1", number1.text.toString())
            putExtra("NUMBER2", number2.text.toString())
            putExtra("NUMBER3", number3.text.toString())
            putExtra("CHECKBOX_COUNT", checkbox_count)
        }

        startActivityForResult(intent, 1)
    }

    private fun randomize() {
        var number: Int = 0

        if (!checkbox1.isChecked) {
            number = (0..3).random()
            if (number == 0) {
                number1.setText("*")
            } else {
                number1.setText(number.toString())
            }
        }

        if (!checkbox2.isChecked) {
            number = (0..3).random()
            if (number == 0) {
                number2.setText("*")
            } else {
                number2.setText(number.toString())
            }
        }

        if (!checkbox3.isChecked) {
            number = (0..3).random()
            if (number == 0) {
                number3.setText("*")
            } else {
                number3.setText(number.toString())
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.play -> play()
            R.id.random -> randomize()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playButton.setOnClickListener(this)
        randomButton.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            Toast.makeText(applicationContext, "Score: $resultCode", Toast.LENGTH_SHORT).show()
        }
    }
}