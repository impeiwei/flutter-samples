package dev.flutter.multipleflutters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.editing.TextInputPlugin

/**
 * This is an Activity that displays one instance of Flutter.
 *
 * EngineBindings is used to bridge communication between the Flutter instance and the DataModel.
 */
class SingleFlutterActivity : FlutterActivity(), EngineBindingsDelegate {
    private val engineBindings: EngineBindings by lazy {
        EngineBindings(activity = this, delegate = this, entrypoint = "main")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        engineBindings.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        engineBindings.detach()
    }

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return engineBindings.engine
    }

    override fun onNext() {
        val flutterIntent = Intent(this, MainActivity::class.java)
        startActivity(flutterIntent)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e("MFA", "onWindowFocusChanged: $hasFocus")
//        if (hasFocus) {
//            restartIme()
//        }
    }

//    private fun restartIme() {
//        val view = window.decorView.findViewById<View>(FLUTTER_VIEW_ID) as FlutterView
//        try {
//            val field = view.javaClass.getDeclaredField("textInputPlugin")
//            field.isAccessible = true
//            val plugin = field[view] as TextInputPlugin
//            view.requestFocus()
//            plugin.inputMethodManager.restartInput(view)
//        } catch (e: NoSuchFieldException) {
//            e.printStackTrace()
//        }
//    }
}
