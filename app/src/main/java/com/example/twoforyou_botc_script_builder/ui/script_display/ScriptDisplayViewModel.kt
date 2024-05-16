package com.example.twoforyou_botc_script_builder.ui.script_display

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.domain.script_display.ScriptDisplayRepository
import com.example.twoforyou_botc_script_builder.ui.script_list.ScriptListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.inject.Inject


@HiltViewModel
class ScriptDisplayViewModel @Inject constructor(
    private val repository: ScriptDisplayRepository,
    private val application: Application,
) : ViewModel() {

    private lateinit var script: Script

    private val _state = MutableStateFlow(ScriptListUiState())
    val state = combine(
        repository.displayingScript,
        _state
    ) { array ->
        ScriptListUiState(
            displayingScript = array[0] as Script,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    suspend fun getScriptById(id: Int): Script {

        script = repository.getScriptById(id)

        repository.updateScript(script)

        _state.update {
            it.copy(
                displayingScript = script
            )
        }

        return script
    }

    fun saveImageUrlToLocalMachine(name: String, urlString: String, context: Context) : Bitmap {
        var bitmap: Bitmap? = null
        val executorService = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())


        bitmap = loadImage(urlString, context)
        handler.post {
            bitmap?.let {
                saveImageToStorage(it, name, context)
            }
        }
        return bitmap!!
    }


    // Function to establish connection and load image
    private fun loadImage(urlString: String, context: Context): Bitmap? {
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        val url = URL(urlString)
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
            return bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }

    private fun saveImageToStorage(bitmap: Bitmap, name: String, context: Context) {
        val filename = "${name}.jpg"
        var outputStream: OutputStream? = null

        val fileDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(fileDirectory, filename)
        outputStream = FileOutputStream(image)

        outputStream.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }
}


