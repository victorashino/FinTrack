package com.example.fintrack.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityCreateCategoryBinding

class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            val nameCategoryFragment = NameCategoryFragment.newInstance()

            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, nameCategoryFragment)
                setReorderingAllowed(true)
            }
            binding.icBack.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
        } // TODO(Ashino) // fragment cor/icone muda a acao do botao voltar

    }
}