package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var06SecondaryActivity : AppCompatActivity() {
    val okButton: Button by lazy { findViewById(R.id.okButton) }
    val gainedText: EditText by lazy { findViewById(R.id.gained_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val number1 = intent.getStringExtra("NUMBER1")
        val number2 = intent.getStringExtra("NUMBER2")
        val number3 = intent.getStringExtra("NUMBER3")
        val checkbox_count: Int = intent.getIntExtra("CHECKBOX_COUNT", 0)

        var gained: Boolean = true
        var score: Int = 0

        if (number1 != "*" && number2 != "*" && number1 != number2) {
            gained = false
        }

        if (number2 != "*" && number3 != "*" && number2 != number3) {
            gained = false
        }

        if (number1 != "*" && number3 != "*" && number1 != number3) {
            gained = false
        }

        if (gained) {
            score = if (checkbox_count == 0)
                100
            else if (checkbox_count == 1)
                50
            else if (checkbox_count == 2)
                10
            else
                0

            gainedText.visibility = View.VISIBLE
            gainedText.setText("Gained: $score")
        } else {
            gainedText.visibility = View.INVISIBLE
        }

        okButton.setOnClickListener {
            setResult(score)
            finish()
        }

    }
}