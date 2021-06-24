package am.mtgtrade.app.util

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizerOptions

object TextRecognition {

    fun readTextFromImage(context: Context, _fieldToUpdate: MutableLiveData<String>) {
        if (TakenPhoto.uri != null) {
            val inputImage = InputImage.fromFilePath(context, TakenPhoto.uri)
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val result = recognizer.process(inputImage)
                .addOnSuccessListener { visionText ->
                    Log.d(ContentValues.TAG,"Wow recognised some text POG! ${visionText.text}")

                    try {
                        _fieldToUpdate.value = visionText.textBlocks.first().text
                    } catch (e:NoSuchElementException) {
                        _fieldToUpdate.value = "scan again!"
                    }

                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG,"Didn't recognise text :(")
                }
        }
        else {
            Log.d(ContentValues.TAG, "No image!")
        }

    }
}