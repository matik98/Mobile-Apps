package com.example.hangman


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var words = arrayOf("")
    private var lookingWord: String = ""
    private var currWord: String = ""
    private var imageList = intArrayOf(
        R.drawable.hangman0,
        R.drawable.hangman1,
        R.drawable.hangman2,
        R.drawable.hangman3,
        R.drawable.hangman4,
        R.drawable.hangman5,
        R.drawable.hangman6,
        R.drawable.hangman7,
        R.drawable.hangman8,
        R.drawable.hangman9,
        R.drawable.hangman10,
        R.drawable.hangman11
    )
    private val buttonMap = mutableMapOf<Button, Char>()
    private var iterator = 0

    private fun hideString (str: String): String {
        var result = ""
        for (i in str) {
            result = result.plus("_ ")
        }
        return result
    }

    private fun newGame() {
        lookingWord = words.random()
        currWord = hideString(lookingWord)
        textView.text = currWord
        iterator = 0
        imageView.setImageResource(imageList[iterator])
        buttonMap.forEach {(b: Button) -> b.isEnabled = true}
    }

    private fun showString (letter: Char){
        var str = ""
        for(i in 0..(lookingWord.length - 1) ) {
            str = when(currWord[2*i] != lookingWord[i] && lookingWord[i] == letter) {
                true -> str.plus("${lookingWord[i]} ")

                false -> str.plus("${currWord[2*i]} ")
            }
        }
        currWord = str
    }

    private fun makeButtonMap() {
        buttonMap[button1] = 'a'
        buttonMap[button2] = 'b'
        buttonMap[button3] = 'c'
        buttonMap[button4] = 'd'
        buttonMap[button5] = 'e'
        buttonMap[button6] = 'f'
        buttonMap[button7] = 'g'
        buttonMap[button8] = 'h'
        buttonMap[button9] = 'i'
        buttonMap[button10] = 'j'
        buttonMap[button11] = 'k'
        buttonMap[button12] = 'l'
        buttonMap[button13] = 'm'
        buttonMap[button14] = 'n'
        buttonMap[button15] = 'o'
        buttonMap[button16] = 'p'
        buttonMap[button17] = 'q'
        buttonMap[button18] = 'r'
        buttonMap[button19] = 's'
        buttonMap[button20] = 't'
        buttonMap[button21] = 'u'
        buttonMap[button22] = 'v'
        buttonMap[button23] = 'w'
        buttonMap[button24] = 'x'
        buttonMap[button25] = 'y'
        buttonMap[button26] = 'z'
    }

    private fun containChar(str: String, char: Char): Boolean {
        for( i in str) {
            if(i == char) {
                return true
            }
        }
        return false
    }

    private fun isWin() {
        when {

            lookingWord == currWord.filterIndexed {_: Int, char: Char -> char!=' '} -> {
                buttonMap.forEach{(b: Button) -> b.isEnabled = false}
                textView.text = lookingWord
                Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show()
            }
            iterator == 11 -> {
                buttonMap.forEach{(b: Button) -> b.isEnabled = false}
                Toast.makeText(this, "You lost!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        words = resources.getStringArray(R.array.words)
        newGame()
        makeButtonMap()
    }

    fun buttonListener(view: View) {
        newGame()
    }


    fun tableListener(view: View) {
        findViewById<Button>(view.id).isEnabled = false
        when(val i = buttonMap[view]) {
            null -> Log.d("error", "BUTTON IS NOT FOUND")
            else -> when (containChar(lookingWord, i)) {
                        false -> {
                            iterator++
                            imageView.setImageResource(imageList[iterator])
                            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
                        }
                        true -> {
                            showString(i)
                            textView.text = currWord
                            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                        }

            }
        }
        isWin()
    }

}

