package br.com.statelab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
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

    fun startAnimation() {
        Log.d(TAG, "AppProcesses: startAnimation")
    }

    fun stopAnimation() {
        Log.d(TAG, "AppProcesses: stopAnimation")
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

    fun restartLongRunningTasks() {
        Log.d(TAG, "AppProcesses: restartLongRunningTasks")
    }
    fun stopLongRunningTasks() {
        Log.d(TAG, "AppProcesses: stopLongRunningTasks")
    }
    fun setUpLayout() {
        Log.d(TAG, "AppProcesses: setUpLayout")
    }
}
class MainActivity : AppCompatActivity(), OnButtonClickedListener {

    private val myAppProcesses by lazy {
        AppProcesses()
    }
    private lateinit var binding: ActivityMainBinding

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
            BottomSheetDialogFragment().show(supportFragmentManager, "confirme_bottom_sheet_fragment")

            // Intent explícita
//            val intent = Intent(this, Details::class.java)
//            startActivity(intent)
        }



        myAppProcesses.setUpLayout()
        myAppProcesses.createTemporaryCacheFiles()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    private fun compartilharApp() {
        // Intent implícita
        // startActivity(Intent(ACTION_VIEW, Uri.parse("https://google.com")))

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
        // Sincronização de dados do servidor
        myAppProcesses.startLongRunningTasks()

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        // ..
        myAppProcesses.stratMusic()

        // ..

        myAppProcesses.startAnimation()

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")

        // TODO: Para tarefas que estão em segundo plano

        myAppProcesses.stopMusic()
        myAppProcesses.stopAnimation()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

        myAppProcesses.stopLongRunningTasks()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")

        myAppProcesses.restartLongRunningTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        myAppProcesses.deleteTemporaryCacheFiles()
    }

    override fun onButtonToSheetClick() {
        val intent = Intent(this, Details::class.java)
        startActivity(intent)
    }
}