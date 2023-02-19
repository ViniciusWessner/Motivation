package com.viniciuswessner.motivacao.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.viniciuswessner.motivacao.infra.MotivationConstants
import com.viniciuswessner.motivacao.R
import com.viniciuswessner.motivacao.data.Mock
import com.viniciuswessner.motivacao.data.Phrase
import com.viniciuswessner.motivacao.infra.SecurityPreferences
import com.viniciuswessner.motivacao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // esconder barra navegacao
        supportActionBar?.hide()

        //eventos
        setContentView(binding.root)

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextFrase()

        binding.buttonNewFrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_frase){
            handleNextFrase()
        }else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(view.id)
        }

    }

    private fun handleFilter(id: Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

   private fun handleUserName(){
        val nameuser = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.userName.text = "Olá, $nameuser"
    }

    private fun handleNextFrase(){
        binding.textMotivacao.text = Mock().getPhrase(categoryId)
    }
}