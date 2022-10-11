package com.example.bmical

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var height:EditText
    private lateinit var weight:EditText
    private lateinit var sp:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    //var weight = findViewById<EditText>(R.id.weight)
    //var height = findViewById<EditText>(R.id.height1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weight = findViewById(R.id.weight)
        height = findViewById(R.id.height1)
        var b=findViewById<Button>(R.id.button1)
        var box=findViewById<CardView>(R.id.card2)
        box.visibility= INVISIBLE
        b.setOnClickListener(){
            sp=getSharedPreferences("my_sp", MODE_PRIVATE)
            editor=sp.edit()
            var h1= height.text.toString()
            var w1=weight.text.toString()
            if (w1=="" || h1=="")
            {
                Toast.makeText(this@MainActivity,"enter data properly",Toast.LENGTH_SHORT).show()
            }
            else
            {
            box.visibility= VISIBLE
            var h= height.text.toString().toFloat()
            var w=weight.text.toString().toFloat()
            h=h/(3.281F)
            var bmi=w/(h*h)
            height.text.clear()
            weight.text.clear()
            var m1=findViewById<TextView>(R.id.message1)
            m1.text="BMI = $bmi"
            var m2 = findViewById<TextView>(R.id.message2)
            if (bmi<18.5)
            {
                m2.setTextColor(Color.parseColor("#FF00FF"))
                m2.text="UNDER-WEIGHT"

            }
            else if(bmi>24.9)
            {
                m2.setTextColor(Color.parseColor("#FF0000"))
                m2.text="OVER-WEIGHT"
            }
            else
            {
                m2.setTextColor(Color.parseColor("#008000"))
                m2.text="NORMAL"
            }}

        }


    }

    override fun onPause() {
        super.onPause()
        var w=weight.text.toString()
        var h=height.text.toString()
        editor.apply{
            putString("w1",w)
            putString("h1",h)
            commit()
        }
    }

  /*  override fun onResume() {
        super.onResume()
        var w2 = sp.getString("w1","")
        var h2=sp.getString("h1","")
        weight.setText(w2)
        height.setText(h2)
    }*/
}