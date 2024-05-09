package com.example.twoforyou_botc_script_builder.ui.script_display

import AutoSizeText
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.ui.script_display.composable.CharacterItem
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URL


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
        createPdf(script, context)

        isGeneratePdf = false
    }


}
//
//@Composable
//fun GeneratePdf(
//    script: Script,
//    context: Context,
//    viewModel: ScriptDisplayViewModel = hiltViewModel()
//) {
//
//
//
//
//
//
//    //A4 Size
//    val pageWidth = 595
//    val pageHeight = 842
//    val startingYValue = 20f
//    val characterNameXValue = 20f
//    val characterImageXValue = 300f
//
//    val pdfDocument = PdfDocument()
//    val pdfDocumentPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
//    val pdfDocumentPage = pdfDocument.startPage(pdfDocumentPageInfo)
//
//    val canvas = pdfDocumentPage.canvas
//
//    val titlePaint = Paint()
//    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
//    titlePaint.textSize = 24F
//    titlePaint.setColor(Color.Black.toArgb())
//    titlePaint.textAlign = Paint.Align.CENTER
//    val titleText = "${script.scriptGeneralInfo?.name} by ${script.scriptGeneralInfo?.author}"
//
//    canvas.drawText(
//        titleText,
//        pageWidth / 2f,
//        startingYValue,
//        titlePaint
//    )
//
//    var currentYPosition = 20f
//    val incrementX = (pageHeight - startingYValue) / (script.charactersObjectList.size + 2)
//
//    for (character in script.charactersObjectList) {
//        currentYPosition += incrementX
//
//        val characterTextPaint = Paint()
//        characterTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
//        characterTextPaint.textSize = 14F
//        when (character.characterType) {
//            Character_Type.마을주민_TOWNSFOLK -> {
//                characterTextPaint.color = Color.Blue.toArgb()
//            }
//
//            Character_Type.외부인_OUTSIDER -> {
//                characterTextPaint.color = SkyBlue.toArgb()
//            }
//
//            Character_Type.하수인_MINION -> {
//                characterTextPaint.color = Pink.toArgb()
//            }
//
//            Character_Type.악마_DEMON -> {
//                characterTextPaint.color = Color.Red.toArgb()
//            }
//        }
//
//
//        val characterText = "${character.getKoreanName()}${character.getEnglishName()}"
//        canvas.drawText(
//            characterText,
//            characterNameXValue,
//            currentYPosition,
//            characterTextPaint
//        )
//
//        val characterImagePaint = Paint()
//
//    }
//
//
//    pdfDocument.finishPage(pdfDocumentPage)
//
//
//    val pdfFile =
//        File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath,
//            childForPdfFile
//        )
//
//    try {
//        pdfDocument.writeTo(FileOutputStream(pdfFile))
//
//        Toast.makeText(context, "pdf파일 만들기 성공", Toast.LENGTH_SHORT).show()
//    } catch (e: Exception) {
//        e.printStackTrace()
//
//        Toast.makeText(context, "pdf파일 만들기 실패", Toast.LENGTH_SHORT)
//            .show()
//    }
//
//    pdfDocument.close()
//
//}
//
//fun drawableToBitmap(drawable: Drawable): Bitmap? {
//    if (drawable is BitmapDrawable) {
//        return drawable.bitmap
//    }
//    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
//    val canvas = Canvas(bitmap)
//    drawable.setBounds(0, 0, canvas.width, canvas.height)
//    drawable.draw(canvas)
//    return bitmap
//}

private fun createPdf(script: Script, context: Context) {
    val childForPdfFile = "${
        script.scriptGeneralInfo?.name
            ?.replace(" ", "_")
            ?.replace(":", ".")
            ?.replace("\t", "")
            ?.replace("\n", "")
    }.pdf"

    val outputFile = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath,
        childForPdfFile)
    val pdfWriter = PdfWriter(outputFile)
    val pdfDocument = PdfDocument(pdfWriter)
    val document = Document(pdfDocument, PageSize.A4)
    pdfDocument.addNewPage()

    document.add(
        Paragraph("Hello World")
    )
    val url = URL(script.charactersObjectList[0].imageUrl)
    val connection = url.openConnection()
    val stream = connection.getInputStream()
    val bitmap = BitmapFactory.decodeStream(stream)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    val image =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    val image2 = ImageDataFactory.create(script.charactersObjectList[0].imageUrl)
    val imag2e= Image(image2)
    document.add(imag2e)

    Toast.makeText(context, "pdf파일 만들기 성공", Toast.LENGTH_SHORT).show()

    pdfDocument.close()

}