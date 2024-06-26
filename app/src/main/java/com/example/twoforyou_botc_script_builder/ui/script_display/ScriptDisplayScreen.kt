package com.example.twoforyou_botc_script_builder.ui.script_display

import AutoSizeText
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.data.model.getEnglishName
import com.example.twoforyou_botc_script_builder.data.model.getKoreanName
import com.example.twoforyou_botc_script_builder.data.model.helper.Character_Type
import com.example.twoforyou_botc_script_builder.ui.script_display.composable.CharacterItem
import com.example.twoforyou_botc_script_builder.ui.theme.Demon_Color
import com.example.twoforyou_botc_script_builder.ui.theme.Fabled_Color
import com.example.twoforyou_botc_script_builder.ui.theme.Minion_Color
import com.example.twoforyou_botc_script_builder.ui.theme.Outsider_Color
import com.example.twoforyou_botc_script_builder.ui.theme.Townsfolk_Color
import java.io.File
import java.io.FileOutputStream


@Composable
fun ScriptDisplayScreen(
    navController: NavController,
    scriptId: Int,
    viewModel: ScriptDisplayViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()

    var script by remember { mutableStateOf(Script()) }

    var isGeneratePdf by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        script = viewModel.getScriptById(scriptId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {

            items(script.charactersObjectList) { character ->
                CharacterItem(
                    character = character
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Black
                )
            }
            items(script.fabledCharacterList) { fabledCharacter ->
                FabledCharacterItem(
                    character = fabledCharacter
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Black
                )
            }
        }

        FloatingActionButton(
            onClick = {
                isGeneratePdf = true
            },
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth(),

            ) {
            AutoSizeText(
                text = "pdf파일로 저장하기",
            )
        }
    }

    if (isGeneratePdf) {
        generatePdf(script, context, viewModel)

        isGeneratePdf = false
    }


}

fun generatePdf(
    script: Script,
    context: Context,
    viewModel: ScriptDisplayViewModel
) {
    //A4 Size
    val pageWidth = 595
    val pageHeight = 842
    val startingYValue = 20f

    val pdfDocument = PdfDocument()
    val pdfDocumentPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val pdfDocumentPage = pdfDocument.startPage(pdfDocumentPageInfo)

    val canvas = pdfDocumentPage.canvas

    val titlePaint = Paint()
    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
    titlePaint.textSize = 18F
    titlePaint.setColor(Color.Black.toArgb())
    titlePaint.textAlign = Paint.Align.CENTER
    val titleText = "${script.scriptGeneralInfo?.name} by ${script.scriptGeneralInfo?.author}"

    canvas.drawText(
        titleText,
        pageWidth / 2f,
        startingYValue,
        titlePaint
    )
    val characterTypePaint = Paint()
    val characterTypeColorArray = intArrayOf(Townsfolk_Color.toArgb(), Outsider_Color.toArgb(), Minion_Color.toArgb(), Demon_Color.toArgb())
    for(i in characterTypeColorArray.indices) {
        characterTypePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        characterTypePaint.textSize = 8F
        characterTypePaint.setColor(characterTypeColorArray[i])
        val characterTypeString = Character_Type.entries[i].toString()
        // {0, 1, 2, 3}
        if(i % 2 == 0) { //0, 2
            canvas.drawText(
                characterTypeString.replace("_", ""),
                5f + (i / 2) * 80,
                10f,
                characterTypePaint
            )
        }else { //1, 3
            canvas.drawText(
                characterTypeString.replace("_", ""),
                5f + (i / 2) * 80,
                10f + 10f,
                characterTypePaint
            )
        }

    }
    characterTypePaint.color = Color.Black.toArgb()

    canvas.drawText(
        "* = 첫 밤 제외",
        pageWidth - 45f,
        10f,
        characterTypePaint
    )

    characterTypePaint.color = Fabled_Color.toArgb()

    canvas.drawText(
        "우화Fabled",
        pageWidth - 40f,
        10f + 10f,
        characterTypePaint
    )

    var currentYPosition = 10f
    val incrementY = (pageHeight - startingYValue) / (script.charactersObjectList.size + script.fabledCharacterList.size + 1)

    for (character in script.charactersObjectList) {

        val characterTextPaint = Paint()
        characterTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val characterTextSize = 12f
        characterTextPaint.textSize = characterTextSize
        val characterNameXValue = 50f

        val characterAbilityTextPaint = Paint()
        characterAbilityTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val characterAbilityTextSize = 9f
        characterAbilityTextPaint.textSize = characterAbilityTextSize
        val characterAbilityTextXValue = 160f

        when (character.characterType) {
            Character_Type.마을주민_TOWNSFOLK -> {
                characterTextPaint.color = Townsfolk_Color.toArgb()
                characterAbilityTextPaint.color = Townsfolk_Color.toArgb()
            }

            Character_Type.외부인_OUTSIDER -> {
                characterTextPaint.color = Outsider_Color.toArgb()
                characterAbilityTextPaint.color = Outsider_Color.toArgb()
            }

            Character_Type.하수인_MINION -> {
                characterTextPaint.color = Minion_Color.toArgb()
                characterAbilityTextPaint.color = Minion_Color.toArgb()
            }

            Character_Type.악마_DEMON -> {
                characterTextPaint.color = Demon_Color.toArgb()
                characterAbilityTextPaint.color = Demon_Color.toArgb()
            }
        }
        canvas.drawLine(
            0f,
            currentYPosition + 2 * (characterAbilityTextSize + 1),
            pageWidth.toFloat(),
            currentYPosition + 2 * (characterAbilityTextSize + 1) + 1,
            characterTextPaint
        )

        currentYPosition += incrementY

        val characterKoreanText = "${character.getKoreanName()}"
        val characterEnglishText = "${character.getEnglishName()}"
        canvas.drawText(
            characterKoreanText,
            characterNameXValue,
            currentYPosition,
            characterTextPaint
        )

        canvas.drawText(
            characterEnglishText,
            characterNameXValue,
            currentYPosition + characterTextSize.toInt(),
            characterTextPaint
        )

        val characterAbilityText = character.ability
        var currentPosition = currentYPosition
        val characterAbilityTextSplitArray = characterAbilityText.split("\\n")
        for (characterAbilitySplitString in characterAbilityTextSplitArray) {
            canvas.drawText(
                characterAbilitySplitString,
                characterAbilityTextXValue,
                currentPosition,
                characterAbilityTextPaint
            )
            currentPosition += characterAbilityTextSize + 1
        }

        val characterImagePaint = Paint()
        characterImagePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val characterImageXValue = 10f
        val characterImageSize = 40
        val bitmap =
            viewModel.saveImageUrlToLocalMachine(character.name, character.imageUrl, context)
        val mutableBitmap =
            Bitmap.createScaledBitmap(bitmap, characterImageSize, characterImageSize, true)

        canvas.drawBitmap(
            mutableBitmap,
            characterImageXValue,
            currentYPosition - (characterImageSize / 2),
            characterImagePaint
        )

    }

    for(fabledCharacter in script.fabledCharacterList) {


        val characterTextPaint = Paint()
        characterTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val characterTextSize = 12f
        characterTextPaint.textSize = characterTextSize
        characterTextPaint.color = Fabled_Color.toArgb()
        val characterNameXValue = 50f

        val characterAbilityTextPaint = Paint()
        characterAbilityTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val characterAbilityTextSize = 9f
        characterAbilityTextPaint.color = Fabled_Color.toArgb()
        characterAbilityTextPaint.textSize = characterAbilityTextSize
        val characterAbilityTextXValue = 160f

        canvas.drawLine(
            0f,
            currentYPosition + 2 * (characterAbilityTextSize + 1),
            pageWidth.toFloat(),
            currentYPosition + 2 * (characterAbilityTextSize + 1) + 1,
            characterTextPaint
        )

        currentYPosition += incrementY

        val characterKoreanText = "${fabledCharacter.getKoreanName()}"
        val characterEnglishText = "${fabledCharacter.getEnglishName()}"
        canvas.drawText(
            characterKoreanText,
            characterNameXValue,
            currentYPosition,
            characterTextPaint
        )

        canvas.drawText(
            characterEnglishText,
            characterNameXValue,
            currentYPosition + characterTextSize.toInt(),
            characterTextPaint
        )


        val characterAbilityText = fabledCharacter.ability
        var currentPosition = currentYPosition
        val characterAbilityTextSplitArray = characterAbilityText.split("\\n")
        for (characterAbilitySplitString in characterAbilityTextSplitArray) {
            canvas.drawText(
                characterAbilitySplitString,
                characterAbilityTextXValue,
                currentPosition,
                characterAbilityTextPaint
            )
            currentPosition += characterAbilityTextSize + 1
        }

        val fabledCharacterImagePaint = Paint()
        fabledCharacterImagePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
        val fabledCharacterImageXValue = 10f
        val fabledCharacterImageSize = 40

        Log.d(TAG, "generatePdf: name: ${fabledCharacter.name}, imageUrl: ${fabledCharacter.imageUrl}")

        val bitmap =
            viewModel.saveImageUrlToLocalMachine(fabledCharacter.name, fabledCharacter.imageUrl, context)
        val mutableBitmap =
            Bitmap.createScaledBitmap(bitmap, fabledCharacterImageSize, fabledCharacterImageSize, true)

        canvas.drawBitmap(
            mutableBitmap,
            fabledCharacterImageXValue,
            currentYPosition - (fabledCharacterImageSize / 2),
            fabledCharacterImagePaint
        )
    }

    pdfDocument.finishPage(pdfDocumentPage)

    val childForPdfFile = "${
        script.scriptGeneralInfo?.name
            ?.replace(" ", "_")
            ?.replace(":", ".")
            ?.replace("\t", "")
            ?.replace("\n", "")
    }.pdf"
    val pdfFile =
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath,
            childForPdfFile
        )

    try {
        pdfDocument.writeTo(FileOutputStream(pdfFile))

        Toast.makeText(context, "pdf파일 만들기 성공", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "generatePdf: Successfully made pdf")
    } catch (e: Exception) {
        e.printStackTrace()

        Toast.makeText(context, "pdf파일 만들기 실패", Toast.LENGTH_SHORT)
            .show()
    }

    pdfDocument.close()

}

