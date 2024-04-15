package com.example.twoforyou_botc_script_builder

import com.example.twoforyou_botc_script_builder.BAD_MOON_RISING_CHARACTERS.Companion.badMoonRisingCharacterList
import com.example.twoforyou_botc_script_builder.TROUBLE_BREWING_CHARACTERS.Companion.troubleBrewingCharacterList
import com.example.twoforyou_botc_script_builder.domain.enum.Character_Type
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.example.twoforyou_botc_script_builder.domain.model.Character

class SECTS_AND_VIOLETS_CHARACTERS {

    companion object {
        val sectsAndVioletsCharacterList = listOf(
            Character(
                name = "시계제작자Clockmaker",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[첫 밤] 당신은 악마로부터 가장 가까운 하수인이 악마와 몇 칸 떨어져 있는지 알고 게임을 시작합니다. (인접한다면 1칸)",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/3/3d/Icon_clockmaker.png",
            ),

            Character(
                name = "몽상가Dreamer",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤] 본인을 제외한 (여행자 제외) 플레이어 1명을 선택합니다: 이야기꾼이 당신에게 선팀 캐릭터 1개와 악팀 캐릭터 1개를 보여줍니다. 둘 중 하나가 해당 플레이어의 캐릭터입니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/f/f2/Icon_dreamer.png",
            ),

            Character(
                name = "뱀부리미Snake_Charmer",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤] 본인을 제외한 살아있는 플레이어 2명을 선택합니다: 오늘 밤 자신의 능력 때문에 깨어난 해당 플레이어들의 숫자를 알게 됩니다.[매일 밤] 살아있는 플레이어 1명을 선택합니다: 악마를 선택했다면, 당신과 그 악마는 서로 캐릭터와 팀을 교환합니다. 그다음 새로운 뱀부리미가 영원히 중독됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/0/08/Icon_snakecharmer.png",
            ),

            Character(
                name = "수학자Mathematician",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤] 당신은 다른 캐릭터의 능력 때문에 자신의 능력이 오작동한 플레이어의 수를 알게 됩니다. (그날 새벽부터 지금까지). 수학자는 수학자의 능력 오작동은 감지하지 못합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/f/f1/Icon_mathematician.png",
            ),

            Character(
                name = "꽃_파는_소녀Flowergirl",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 당신은 오늘 낮에 악마가 처형 투표 중에 투표했는지 (해당 투표의 결과와 상관없이) 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/a/ac/Icon_flowergirl.png",
            ),

            Character(
                name = "포고_관리인Town_Crier",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 당신은 오늘 낮에 하수인이 처형 투표의 지목을 시작했는지를 (해당 투표의 결과와 상관없이) 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/e/ef/Icon_towncrier.png",
            ),

            Character(
                name = "예언자Oracle",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 당신은 사망한 플레이어 중 몇 명이 악팀인지 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/b/bb/Icon_oracle.png",
            ),

            Character(
                name = "학자Savant",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 낮] 이야기꾼을 사적으로 방문하여 2가지 정보를 얻습니다. 그 중 하나는 진실이고, 하나는 거짓입니다. 학자는 해당 효과를 사용하지 않아도 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d5/Icon_savant.png",
            ),

            Character(
                name = "저글러Juggler",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[첫 낮] 공개적으로, 저글러를 선언하고 5번까지 특정 플레이어와 특정 캐릭터를 선언할 수 있습니다. (예: 철수 - 저글러) 그날 밤, 당신은 그 중 몇 번을 올바르게 예측했는지 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/4/42/Icon_juggler.png",
            ),

            Character(
                name = "예술가Artist",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[게임당 1번] 낮에, 이야기꾼에게 ‘예’ / ‘아니요’로 대답할 수 있는 질문 1개를 사적으로 물어봅니다. 이야기꾼은 질문에 대해 솔직하게 ‘예’ / ‘아니요’ / ‘잘 모르겠다’ 중의 하나로 대답합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/1a/Icon_artist.png",
            ),

            Character(
                name = "재봉사Seamstress",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[게임당 1번] 밤에, 자신을 제외한 플레이어 2명을 선택합니다: 해당 플레이어들이 같은 팀인지 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/5/53/Icon_seamstress.png",
            ),

            Character(
                name = "철학자Philosopher",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[게임당 1번] 밤에, 선팀 캐릭터 1명을 선택합니다: 해당 능력을 지속적으로 얻습니다. 이미 존재하는 캐릭터면, 그 캐릭터의 플레이어는 지속적으로 취합니다. 철학자가 죽거나 취함/중독이 될 경우, 취한 그 캐릭터는 그동안 취함에서 풀립니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/5/5d/Icon_philosopher.png",
            ),

            Character(
                name = "현자Sage",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "악마가 당신을 살해하면, 이야기꾼이 당신을 깨워서 플레이어 2명을 지목합니다: 2명 중 1명은 당신을 죽인 악마입니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/a/a0/Icon_sage.png",
            ),

            Character(
                name = "연인Sweetheart",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신이 사망할 때, 플레이어 1명이 이제부터 취합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/6/6a/Icon_sweetheart.png",
            ),

            Character(
                name = "돌연변이Mutant",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신이 외부인이라는 사실에 미쳐있다면, 이야기꾼은 언제든지(밤이든 낮이든) 당신을 처형시킬 수 있습니다. 그날 낮에 처형이 있었더라도 저녁때 당신의 처형은 일어날 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/2/2e/Icon_mutant.png",
            ),

            Character(
                name = "얼뜨기Klutz",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신이 죽었다는 사실을 알았을 때, 즉시 공개적으로, 얼뜨기라고 선언하고 살아있는 플레이어 1명을 선택해야 합니다 (선택 전 논의가 가능합니다): 해당 플레이어가 악팀이라면, 당신의 팀이 패배합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/b/bc/Icon_klutz.png",
            ),

            Character(
                name = "이발사Barber",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신이 오늘 낮 또는 밤에 사망했다면: 악마 1명은 (다른 악마를 제외한) 플레이어 2명을 선택해 서로의 캐릭터를 바꿀 수 있습니다. 서로의 팀은 바뀌지 않습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/16/Icon_barber.png",
            ),

            Character(
                name = "마녀Witch",
                characterType = Character_Type.하수인_MINION,
                ability = "[매일 밤] 플레이어 1명을 선택합니다: 해당 플레이어가 처형 투표의 지목을 시작했다면, 해당 플레이어는 죽습니다. (투표는 진행됩니다.) 살아있는 플레이어가 3명이라면 이 능력은 사라집니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/7/7b/Icon_witch.png",
            ),

            Character(
                name = "두뇌세뇌꾼Cerenovus",
                characterType = Character_Type.하수인_MINION,
                ability = "[매일 밤] 플레이어 1명과 선팀 캐릭터 1개를 선택합니다(해당 플레이어는 해당 캐릭터를 알게 됩니다.): 해당 플레이어가 내일 해당 캐릭터라는 것에 미쳐있지 않으면, 이야기꾼은 해당 플레이어를 처형시킬 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/4/45/Icon_cerenovus.png",
            ),

            Character(
                name = "곰보_할멈Pit-Hag",
                characterType = Character_Type.하수인_MINION,
                ability = "[매일 밤*] 플레이어 1명과 캐릭터 1개를 선택합니다: 그 캐릭터가 이번 게임에 존재하지 않을 경우, 해당 플레이어는 해당 캐릭터로 변경됩니다. 팀은 바뀌지 않습니다. 이렇게 악마가 만들어졌다면, 오늘 밤 모든 죽음은 (누가 죽는 지와 몇 명이 죽는지) 이야기꾼이 결정합니다. 이 죽음(들)은 곰보 할멈에 의한 죽음으로 처리됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/6/6b/Icon_pithag.png",
            ),

            Character(
                name = "사악한_쌍둥이Evil_Twin",
                characterType = Character_Type.하수인_MINION,
                ability = "[첫 밤] 상대 팀 플레이어 중 1명은 쌍둥이로 지정되며, 서로는 서로 쌍둥이인 것과 서로의 캐릭터를 알고 게임을 시작합니다. 선팀 쌍둥이가 처형되면, 즉시 악팀이 승리합니다. 쌍둥이 둘 다 살아있다면, 선팀은 승리할 수 없으며 게임이 계속됩니다. 선팀 쌍둥이와 악팀 쌍둥이만 살아있는 경우, 악팀이 승리합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/f/f4/Icon_eviltwin.png",
            ),

            Character(
                name = "노_다시No_Dashii",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 1명을 선택합니다: 해당 플레이어는 사망합니다. 당신 양쪽으로 가장 인접한 마을주민은 지속적으로 중독됩니다. 노 다시의 중독 효과는 플레이어들의 캐릭터가 변경됨으로써 대상이 바뀔 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/e/eb/Icon_nodashii.png",
            ),

            Character(
                name = "보르톡스Vortox",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 1명을 선택합니다: 해당 플레이어는 사망합니다. 마을주민이 얻는 정보는 항상 거짓입니다. 매일 낮, 아무도 처형되지 않았다면, 악팀이 승리합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/8/89/Icon_vortox.png",
            ),

            Character(
                name = "비고모티스Vigomortis",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 1명을 선택합니다: 해당 플레이어는 사망합니다. 비고모티스가 하수인을 살해하면, 해당 하수인은 능력을 잃지 않은 채 사망하고, 살해당한 하수인과 가장 가까이 인접한 마을주민 하나가 중독됩니다. [-1 외부인, +1 마을주민]",
                isFormatChangingRole = true,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/1a/Icon_vigormortis.png",
            ),

            Character(
                name = "팽_구Fang_Gu",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 1명을 선택합니다: 해당 플레이어는 사망합니다. [게임당 1번] 이렇게 외부인을 살해하려고 하면, 1번만, 해당 외부인이 악팀 팽 구가 되고, 당신이 대신 사망합니다. [+1 외부인, -1 마을주민]",
                isFormatChangingRole = true,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/0/0e/Icon_fanggu.png",
            ),
        )

        fun addSectsAndVioletsCharactersToDatabase() {
            val database = Firebase.database
            val databaseReference = database.getReference()
            for (character in sectsAndVioletsCharacterList) {
                databaseReference.child("SectsAndViolets").child(character.characterType.toString()).child(character.name).setValue(character)
            }

        }
    }

}