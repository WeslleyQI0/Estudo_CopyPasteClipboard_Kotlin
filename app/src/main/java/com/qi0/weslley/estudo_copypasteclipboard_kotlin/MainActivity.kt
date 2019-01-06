package com.qi0.weslley.estudo_copypasteclipboard_kotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        if (!clipboardManager.hasPrimaryClip()){
            btPaste.isEnabled = false
        }

        btCopy.setOnClickListener {
            var text = edtText.text.toString()

            if (!text.isBlank()){
                var clipData = ClipData.newPlainText("text", text)
                clipboardManager.primaryClip = clipData

                Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
                btPaste.isEnabled = true
                btPaste.visibility = View.VISIBLE
            }
        }

        btPaste.setOnClickListener {
            var clipeData = clipboardManager.primaryClip
            var item = clipeData.getItemAt(0)

            tvTxtCopied.text = item.text.toString()

            Toast.makeText(this, "Pasted", Toast.LENGTH_SHORT).show()
        }
    }
}
