package com.example.quizly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class GameActivity : AppCompatActivity() {

    private var correctAns = 0
    private var wrongAns = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var millisInFuture: Long = 50000

        // when plusPoints hits 5, millisInFuture  will increase and this variable will reset
        var plusPoints = 0
        val countdownInterval: Long = 1000
        val timer=MyCounter(millisInFuture,countdownInterval)

        fun increaseTimer() {
            if (plusPoints == 5) {
                millisInFuture += 10000
                plusPoints = 0
            }
        }

        fun sum(num1: Double, num2: Double): Double {
            return num1 + num2
        }

        fun sub(num1: Double, num2: Double): Double {
            return num1 - num2
        }

        fun mul(num1: Double, num2: Double): Double {
            return num1 * num2
        }

        fun div(num1: Double, num2: Double): Double {
            return num1 / num2
        }

        fun correctPop() {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popup: View = inflater.inflate(R.layout.correct_popup, null)
            val focusable = true
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val popUpWindow = PopupWindow(popup, width, height, focusable)
            popUpWindow.showAtLocation(popup, Gravity.CENTER, 0, 0)
        }

        fun wrongPop() {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popup: View = inflater.inflate(R.layout.wrong_pop, null)
            val focusable = true
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val popUpWindow = PopupWindow(popup, width, height, focusable)
            popUpWindow.showAtLocation(popup, Gravity.CENTER, 0, 0)
        }

        fun equationGenerator() {

            var ansForFirstEquation = 0.0 // answer to the first equation
            var ansForSecEquation = 0.0 // answer to the second equation
            var firstBracket = 0.0 // when have 3 terms -- (6 + 4)/ 2
            var secBracket = 0.0 // when have 4 terms -- ((10 - 2) * 3) / 4

            val firstEquation = findViewById<TextView>(R.id.firstEquation)
            val secEquation = findViewById<TextView>(R.id.secondEquation)
            val numOfTermsForFirstEquation = (1..4).random()
            val numOfTermsForSecEquation = (1..4).random()

            // let application decide how many terms are going to use
            // for the first equation
            when (numOfTermsForFirstEquation) {
                // if there is only one term
                1 -> {
                    val num = (1..20).random()
                    firstEquation.text = num.toString()
                    ansForFirstEquation = num.toDouble()
                }

                // if there are 2 terms
                2 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    // app will select a random operator from the list
                    val operator = list.random()
                    firstEquation.text = num1.toString() + operator + num2.toString()

                    when (operator) {
                        "+" -> ansForFirstEquation = sum(num1.toDouble(), num2.toDouble())
                        "-" -> ansForFirstEquation = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else { ansForFirstEquation = mul(num1.toDouble(), num2.toDouble()) }
                        "/" -> if (num1.toDouble()%num2.toDouble() == 0.0) {
                            ansForFirstEquation = div (num1.toDouble(), num2.toDouble())
                        } else { equationGenerator() }
                        else -> println("invalid operator")
                    }
                }

                // if there are 3 terms
                3 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val num3 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    val operator1 = list.random()
                    val operator2 = list.random()

                    firstEquation.text = "(" + num1.toString() + operator1 + num2.toString() + ")" + operator2 + num3.toString()

                    when (operator1) {
                        "+" -> firstBracket = sum(num1.toDouble(), num2.toDouble())
                        "-" -> firstBracket = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else {
                            firstBracket = mul(num1.toDouble(), num2.toDouble())}
                        "/" -> if(num1.toDouble()%num2.toDouble() == 0.0) {
                            firstBracket = div(num1.toDouble(), num2.toDouble())
                        } else {equationGenerator()}
                        else -> println("invalid operator")
                    }

                    when (operator2) {
                        "+" -> if (firstBracket + num3 > 100) { equationGenerator() }
                        else {ansForFirstEquation = sum(firstBracket, num3.toDouble())}
                        "-" -> ansForFirstEquation = sub(firstBracket, num3.toDouble())
                        "*" -> if (firstBracket * num3 > 100) {equationGenerator()}
                        else {ansForFirstEquation = mul(firstBracket, num3.toDouble())}
                        "/" -> if (firstBracket % num3.toDouble() == 0.0) {
                            ansForFirstEquation = div(firstBracket, num3.toDouble())
                        } else {equationGenerator()}
                        else -> println("Invalid operator")
                    }
                }

                // if there are 4 terms
                4 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val num3 = (1..20).random()
                    val num4 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    val operator1 = list.random()
                    val operator2 = list.random()
                    val operator3 = list.random()

                    firstEquation.text = "((" + num1.toString() + operator1 + num2.toString() + "" +
                            ")" + operator2 + num3.toString() + ")" + operator3 + num4.toString()

                    when (operator1) {
                        "+" -> firstBracket = sum(num1.toDouble(), num2.toDouble())
                        "-" -> firstBracket = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else {
                            firstBracket = mul(num1.toDouble(), num2.toDouble())}
                        "/" -> if(num1.toDouble()%num2.toDouble() == 0.0) {
                            firstBracket = div(num1.toDouble(), num2.toDouble())
                        } else {equationGenerator()}
                        else -> println("invalid operator")
                    }

                    when (operator2) {
                        "+" -> if (firstBracket + num3 > 100) { equationGenerator() }
                        else {secBracket = sum(firstBracket, num3.toDouble())}
                        "-" -> secBracket = sub(firstBracket, num3.toDouble())
                        "*" -> if (firstBracket * num3 > 100) {equationGenerator()}
                        else {secBracket = mul(firstBracket, num3.toDouble())}
                        "/" -> if (firstBracket % num3.toDouble() == 0.0) {
                            secBracket = div(firstBracket, num3.toDouble())
                        } else {equationGenerator()}
                        else -> println("Invalid operator")
                    }

                    when (operator3) {
                        "+" -> if (secBracket + num4 > 100) {equationGenerator()}
                        else {ansForFirstEquation = sum(secBracket, num4.toDouble())}
                        "-" -> ansForFirstEquation = sub(secBracket, num4.toDouble())
                        "*" -> if (secBracket * num4 > 100) {equationGenerator()}
                        else {ansForFirstEquation = mul(secBracket, num4.toDouble())}
                        "/" -> if (secBracket % num4.toDouble() == 0.0) {
                            ansForFirstEquation = div(secBracket, num4.toDouble())
                        } else {equationGenerator()}
                    }
                }
                else -> println("Invalid operator")
            }

            // for the second equation
            when (numOfTermsForSecEquation) {
                // if there is only one term
                1 -> {
                    val num = (1..20).random()
                    secEquation.text = num.toString()
                    ansForSecEquation = num.toDouble()
                }

                // if there are 2 terms
                2 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    // app will select a random operator from the list
                    val operator = list.random()
                    secEquation.text = num1.toString() + operator + num2.toString()

                    when (operator) {
                        "+" -> ansForSecEquation = sum(num1.toDouble(), num2.toDouble())
                        "-" -> ansForSecEquation = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else { ansForSecEquation = mul(num1.toDouble(), num2.toDouble()) }
                        "/" -> if (num1.toDouble()%num2.toDouble() == 0.0) {
                            ansForSecEquation = div (num1.toDouble(), num2.toDouble())
                        } else { equationGenerator() }
                        else -> println("invalid operator")
                    }
                }

                // if there are 3 terms
                3 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val num3 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    val operator1 = list.random()
                    val operator2 = list.random()

                    secEquation.text = "(" + num1.toString() + operator1 + num2.toString() + ")" + operator2 + num3.toString()

                    when (operator1) {
                        "+" -> firstBracket = sum(num1.toDouble(), num2.toDouble())
                        "-" -> firstBracket = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else {
                            firstBracket = mul(num1.toDouble(), num2.toDouble())}
                        "/" -> if(num1.toDouble()%num2.toDouble() == 0.0) {
                            firstBracket = div(num1.toDouble(), num2.toDouble())
                        } else {equationGenerator()}
                        else -> println("invalid operator")
                    }

                    when (operator2) {
                        "+" -> if (firstBracket + num3 > 100) { equationGenerator() }
                        else {ansForSecEquation = sum(firstBracket, num3.toDouble())}
                        "-" -> ansForSecEquation = sub(firstBracket, num3.toDouble())
                        "*" -> if (firstBracket * num3 > 100) {equationGenerator()}
                        else {ansForSecEquation = mul(firstBracket, num3.toDouble())}
                        "/" -> if (firstBracket % num3.toDouble() == 0.0) {
                            ansForSecEquation = div(firstBracket, num3.toDouble())
                        } else {equationGenerator()}
                        else -> println("Invalid operator")
                    }
                }

                // if there are 4 terms
                4 -> {
                    val num1 = (1..20).random()
                    val num2 = (1..20).random()
                    val num3 = (1..20).random()
                    val num4 = (1..20).random()
                    val list = listOf("+", "-", "*", "/")
                    val operator1 = list.random()
                    val operator2 = list.random()
                    val operator3 = list.random()

                    secEquation.text = "((" + num1.toString() + operator1 + num2.toString() + "" +
                            ")" + operator2 + num3.toString() + ")" + operator3 + num4.toString()

                    when (operator1) {
                        "+" -> firstBracket = sum(num1.toDouble(), num2.toDouble())
                        "-" -> firstBracket = sub(num1.toDouble(), num2.toDouble())
                        "*" -> if (num1 * num2 > 100) { equationGenerator()
                        } else {
                            firstBracket = mul(num1.toDouble(), num2.toDouble())}
                        "/" -> if(num1.toDouble()%num2.toDouble() == 0.0) {
                            firstBracket = div(num1.toDouble(), num2.toDouble())
                        } else {equationGenerator()}
                        else -> println("invalid operator")
                    }

                    when (operator2) {
                        "+" -> if (firstBracket + num3 > 100) { equationGenerator() }
                        else {secBracket = sum(firstBracket, num3.toDouble())}
                        "-" -> secBracket = sub(firstBracket, num3.toDouble())
                        "*" -> if (firstBracket * num3 > 100) {equationGenerator()}
                        else {secBracket = mul(firstBracket, num3.toDouble())}
                        "/" -> if (firstBracket % num3.toDouble() == 0.0) {
                            secBracket = div(firstBracket, num3.toDouble())
                        } else {equationGenerator()}
                        else -> println("Invalid operator")
                    }

                    when (operator3) {
                        "+" -> if (secBracket + num4 > 100) {equationGenerator()}
                        else {ansForSecEquation = sum(secBracket, num4.toDouble())}
                        "-" -> ansForSecEquation = sub(secBracket, num4.toDouble())
                        "*" -> if (secBracket * num4 > 100) {equationGenerator()}
                        else {ansForSecEquation = mul(secBracket, num4.toDouble())}
                        "/" -> if (secBracket % num4.toDouble() == 0.0) {
                            ansForSecEquation = div(secBracket, num4.toDouble())
                        } else {equationGenerator()}
                    }
                }
                else -> println("Invalid operator")
            }

            // set action listeners to buttons
            val greatBtn = findViewById<Button>(R.id.greaterBtn)
            greatBtn.setOnClickListener {
                equationGenerator()
                if (ansForFirstEquation > ansForSecEquation) {
                    correctAns+=1
                    increaseTimer()
                    correctPop()
                } else if (ansForFirstEquation < ansForSecEquation) {
                    wrongAns+=1
                    wrongPop()
                } else {
                    wrongAns+=1
                    wrongPop()
                }

            }

            val lessBtn = findViewById<Button>(R.id.lessBtn)
            lessBtn.setOnClickListener {
                equationGenerator()
                if (ansForFirstEquation > ansForSecEquation) {
                    wrongAns+=1
                    wrongPop()
                } else if (ansForFirstEquation < ansForSecEquation) {
                    correctAns+=1
                    increaseTimer()
                    correctPop()
                } else {
                    wrongAns+=1
                    wrongPop()
                }
            }

            val equalBtn = findViewById<Button>(R.id.equalBtn)
            equalBtn.setOnClickListener {
                equationGenerator()
                if (ansForFirstEquation > ansForSecEquation) {
                    wrongAns+=1
                    wrongPop()
                } else if (ansForFirstEquation < ansForSecEquation) {
                    wrongAns+=1
                    wrongPop()
                } else {
                    correctAns+=1
                    increaseTimer()
                    correctPop()
                }
            }
        }

        equationGenerator()
        timer.start()


    }

    inner class MyCounter(millisInFuture:Long,countdownInterval:Long):
        CountDownTimer(millisInFuture,countdownInterval){

        override fun onFinish() {
            val finalResult = findViewById<TextView>(R.id.result)
            finalResult.text = ("Correct Answers - "+correctAns .toString() +"\nWrong Ansrwers - "+wrongAns.toString())
            val greatBtn = findViewById<Button>(R.id.greaterBtn)
            greatBtn.isEnabled = false
            val lessBtn = findViewById<Button>(R.id.lessBtn)
            lessBtn.isEnabled = false
            val equalBtn = findViewById<Button>(R.id.equalBtn)
            equalBtn.isEnabled = false


        }

        override fun onTick(millisUntilFinished: Long) {
            val countDown = findViewById<TextView>(R.id.countDown)
            countDown.text = (millisUntilFinished/1000).toString()
        }

    }
}