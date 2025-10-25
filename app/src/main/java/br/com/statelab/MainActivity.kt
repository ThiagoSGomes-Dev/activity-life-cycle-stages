package br.com.statelab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.statelab.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class AppProcesses {
    fun stratMusic() {
        Log.d(TAG, "AppProcesses: startMusic")
    }
    fun stopMusic() {
        Log.d(TAG, "AppProcesses: stopMusic")
    }
    fun createTemporaryCacheFiles() {
        Log.d(TAG, "AppProcesses: createTemporaryCacheFiles")
    }
    fun deleteTemporaryCacheFiles() {
        Log.d(TAG, "AppProcesses: deleteTemporaryCacheFiles")
    }
    fun startLongRunningTasks() {
        Log.d(TAG, "AppProcesses: startLongRunningTasks")
    }
    fun stopLongRunningTasks() {
        Log.d(TAG, "AppProcesses: stopLongRunningTasks")
    }
    fun setUpLayout() {
        Log.d(TAG, "AppProcesses: setUpLayout")
    }
}
class MainActivity : AppCompatActivity() {

    private val myAppProcesses by lazy {
        AppProcesses()
    }
    private lateinit var binding: ActivityMainBinding

    private val URL_PARA_COMPARTILHAR = "https://www.google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCrate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    compartilharApp()
                    true
                }
                else -> false
            }
        }

        binding.btnClick.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    private fun compartilharApp() {
        val text = "Confira meu app"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }

        val chooser = Intent.createChooser(intent, "Compartilhar via")
        startActivity(chooser)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}