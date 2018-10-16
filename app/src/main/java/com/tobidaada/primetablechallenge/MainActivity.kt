package com.tobidaada.primetablechallenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tobidaada.primetablechallenge.utils.PrimeNumberGenerator
import com.tobidaada.primetablechallenge.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mButton: Button
    private lateinit var mTextView: TextView
    private lateinit var mEditTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton = btn
        mEditTextView = et
        mTextView = tv

        mButton.setOnClickListener { onPrimeNumberLengthEntered() }
    }

    private fun onPrimeNumberLengthEntered() {
        var isOk = true

        if (mEditTextView.text.isEmpty()) {
            this.toast("Edit Text Cannot be empty")
            isOk = false
        }

        if (isOk) {
            val number = mEditTextView.text.toString().toInt()
            mTextView.text = ""
            generatePrimeNumbers(number)
        }
    }

    private fun generatePrimeNumbers(number: Int) {
        val primeNumberList = PrimeNumberGenerator.generatePrimeNumbers(number)

        for (primeNumber in primeNumberList) {
            mTextView.append("$primeNumber")
        }

        mTextView.append("\n")

        for (cell in primeNumberList.indices) {
            mTextView.append("$cell")
        }
    }

    private fun onRefreshLayoutSelected() {
        mTextView.text = ""
        mEditTextView.setText("")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            R.id.menu_clear -> {
                onRefreshLayoutSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
