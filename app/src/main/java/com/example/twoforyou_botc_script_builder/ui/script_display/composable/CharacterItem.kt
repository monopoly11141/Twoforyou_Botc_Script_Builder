package com.example.twoforyou_botc_script_builder.ui.script_display.composable

import AutoSizeText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.getEnglishName
import com.example.twoforyou_botc_script_builder.data.model.getKoreanName

@Composable
fun CharacterItem(
    character: Character,
    modifier : Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(0.25f)
                .padding(start = 2.dp, end = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = "캐릭터 이미지",
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                )

                AutoSizeText(
                    text = character.getKoreanName()
                )
                AutoSizeText(
                    text = character.getEnglishName()
                )


            }
        }


        Row(
            modifier = Modifier
                .weight(0.75f)
        ) {

            AutoSizeText(
                text = character.ability,
                maxLines = 5
            )

        }

    }
}