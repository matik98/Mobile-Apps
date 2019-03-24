package com.example.tictactoe


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mode = 1
    private var board = arrayOf(arrayOfNulls(5), arrayOfNulls(5),
        arrayOfNulls(5), arrayOfNulls(5), arrayOfNulls<BoardButton>(5) )
    private var bot = false

    class BoardButton (val button: Button) {
        var player: Byte = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board()
    }


    private fun board()  {
        board[0][0] = BoardButton(button11)
        board[0][1] = BoardButton(button12)
        board[0][2] = BoardButton(button13)
        board[0][3] = BoardButton(button14)
        board[0][4] = BoardButton(button15)

        board[1][0] = BoardButton(button21)
        board[1][1] = BoardButton(button22)
        board[1][2] = BoardButton(button23)
        board[1][3] = BoardButton(button24)
        board[1][4] = BoardButton(button25)

        board[2][0] = BoardButton(button31)
        board[2][1] = BoardButton(button32)
        board[2][2] = BoardButton(button33)
        board[2][3] = BoardButton(button34)
        board[2][4] = BoardButton(button35)

        board[3][0] = BoardButton(button41)
        board[3][1] = BoardButton(button42)
        board[3][2] = BoardButton(button43)
        board[3][3] = BoardButton(button44)
        board[3][4] = BoardButton(button45)

        board[4][0] = BoardButton(button51)
        board[4][1] = BoardButton(button52)
        board[4][2] = BoardButton(button53)
        board[4][3] = BoardButton(button54)
        board[4][4] = BoardButton(button55)

    }



    private fun restart() {
        for( i in board ) {
            for ( j in i) {
                j?.button?.text = resources.getStringArray(R.array.symbols)[0]
                j?.button?.isEnabled = true
                j?.player = 0
            }
        }
        mode = 1
    }

    private fun findButton(id: Int): BoardButton? {
        for( i in board ) {
            for ( j in i) {
                if(j?.button?.id == id)
                    return j
            }
        }
        return null
    }

    private fun isWinner(): Boolean {
        for( i in board ) {
            val player = i[0]?.player
            if(player != 0.toByte() && player == i[1]?.player && player == i[2]?.player
                && player == i[3]?.player && player == i[4]?.player) {
                return true
            }
        }
        for (i in 0..4) {
            val player = board[0][i]?.player
            if(player != 0.toByte() && player == board[1][i]?.player && player == board[2][i]?.player &&
                player == board[3][i]?.player && player == board[4][i]?.player) {
                return true
            }
        }
        if (board[0][0]?.player != 0.toByte() && board[0][0]?.player == board[1][1]?.player
            && board[0][0]?.player == board[2][2]?.player && board[0][0]?.player == board[3][3]?.player
            && board[0][0]?.player == board[4][4]?.player) {
            return true
        } else if (board[4][0]?.player != 0.toByte() && board[4][0]?.player == board[3][1]?.player
            && board[4][0]?.player == board[2][2]?.player && board[4][0]?.player == board[1][3]?.player
            && board[4][0]?.player == board[0][4]?.player) {
            return true
        }
        return false
    }

    private fun won() {
        if(bot){
            if (mode == 1) {
                textView3.text = getString(R.string.u_won)
            } else {
                textView3.text = getString(R.string.bot_won)
            }
        } else {
            if (mode == 1) {
                textView3.text = getString(R.string.x_won)
            } else {
                textView3.text = getString(R.string.o_won)
            }
        }

        for( i in board ) {
            for ( j in i) {
                j?.button?.isEnabled = false
            }
        }
    }

    private fun botMove() {
        for( i in board ) {
            for ( j in i) {
                if(j?.player == 0.toByte()) {
                    j.button.isEnabled = false
                    j.player = 2
                    j.button.text = resources.getStringArray(R.array.symbols)[2]
                    return
                }
            }
        }
    }

    private fun printujPlansze() {
        for (i in board) {
            Log.d("board", "${i[0]?.player} ${i[1]?.player} ${i[2]?.player} ${i[3]?.player} ${i[4]?.player}")
        }
        Log.d("board"," ")
    }

    fun restartButton(view: View) {
        restart()
    }



    fun swichClick(view: View) {
        bot = !bot
        restart()

        if(!bot) {
            textView3.text = getString(R.string.x_turn)
        } else {
            textView3.text = getString(R.string.bot_game)
        }

    }


    fun buttonClick(view: View) {
        findViewById<Button>(view.id).text = resources.getStringArray(R.array.symbols)[mode]
        findViewById<Button>(view.id).isEnabled = false
        findButton(view.id)?.player = mode.toByte()

        //printujPlansze()

        if(isWinner()) {
            won()
        } else {
            mode = (mode) % 2 + 1
            if (bot) {
                botMove()
                if(isWinner()) {
                    won()
                }
                mode = (mode) % 2 + 1
            } else {
                if (mode == 1) {
                    textView3.text = getString(R.string.x_turn)
                } else {
                    textView3.text = getString(R.string.o_turn)
                }
            }
        }
    }
}
