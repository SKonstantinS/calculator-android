package com.sks.calculator2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sks.calculator2.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickHandler() //установил обработчики
    }

    private fun setTextFields(str: String){

        if(binding.resultText.text.isNotEmpty()){
            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }

        binding.mathOperation.append(str)
    }

    private fun setOnClickHandler(){
        binding.btn0.setOnClickListener{setTextFields("0")}
        binding.btn1.setOnClickListener{setTextFields("1")}
        binding.btn2.setOnClickListener{setTextFields("2")}
        binding.btn3.setOnClickListener{setTextFields("3")}
        binding.btn4.setOnClickListener{setTextFields("4")}
        binding.btn5.setOnClickListener{setTextFields("5")}
        binding.btn6.setOnClickListener{setTextFields("6")}
        binding.btn7.setOnClickListener{setTextFields("7")}
        binding.btn8.setOnClickListener{setTextFields("8")}
        binding.btn9.setOnClickListener{setTextFields("9")}
        binding.minusBtn.setOnClickListener{setTextFields("-")}
        binding.plusBtn.setOnClickListener{setTextFields("+")}
        binding.devBtn.setOnClickListener{setTextFields("/")}
        binding.multBtn.setOnClickListener{setTextFields("*")}
        binding.dotBtn.setOnClickListener{setTextFields(".")}
        binding.bracketBeginBtn.setOnClickListener{setTextFields("(")}
        binding.bracketEndBtn.setOnClickListener{setTextFields(")")}
        binding.acBtn.setOnClickListener{
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }
        binding.backBtn.setOnClickListener{
            val str = binding.mathOperation.text.toString()

            if (str.isNotEmpty())
                binding.mathOperation.text = str.substring(0, str.length - 1)//удалить последний эл-т
            binding.resultText.text = ""
        }
        binding.equalBtn.setOnClickListener{
            try {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val rez = ex.evaluate()

                val longRez = rez.toLong()

                if(rez == longRez.toDouble())
                    binding.resultText.text = longRez.toString()
                else
                    binding.resultText.text = rez.toString()

            }catch (e:Exception){
                Log.d("Error", "Message text: ${e.message}")
            }
        }
    }
}