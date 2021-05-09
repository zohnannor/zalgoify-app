package com.zohnannor.zalgoify

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ZalgoifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        val readonly = intent.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)

        if (!readonly && text != null) {
            val outgoingIntent = Intent()
            outgoingIntent.putExtra(Intent.EXTRA_PROCESS_TEXT, zalgoifyText(text))
            setResult(Activity.RESULT_OK, outgoingIntent)
            Toast.makeText(this, "Here you go!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Text cannot be modified", Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    private fun zalgoifyText(text: CharSequence): String? {
        var s = ""
        for (c in text.toString()) {
            s += c
            for (i in 0..20) {
                s += (0x0300..0x036F).random().toChar()
            }
        }
        return s
    }
}