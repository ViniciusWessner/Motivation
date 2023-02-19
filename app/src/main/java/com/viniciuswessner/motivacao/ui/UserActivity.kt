package com.viniciuswessner.motivacao.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.viniciuswessner.motivacao.infra.MotivationConstants
import com.viniciuswessner.motivacao.R
import com.viniciuswessner.motivacao.infra.SecurityPreferences
import com.viniciuswessner.motivacao.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //remove action bar
        supportActionBar?.hide()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.setOnClickListener(this)

        // verificando se ja possui o nome
        verifyUserName()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            //Toast.makeText(applicationContext, "Clicou no botao de salvar", Toast.LENGTH_SHORT).show()
            handleSave()
        }
    }

    private fun verifyUserName(){
        val nameuser = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (nameuser != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave(){
        val name = binding.editName.text.toString()
        if (name != ""){

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(applicationContext, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }

}