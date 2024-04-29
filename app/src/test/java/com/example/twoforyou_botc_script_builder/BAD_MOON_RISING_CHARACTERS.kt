package com.example.twoforyou_botc_script_builder

import com.example.twoforyou_botc_script_builder.domain.enum.Character_Type
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.example.twoforyou_botc_script_builder.data.model.Character

class BAD_MOON_RISING_CHARACTERS {

    companion object {
        val badMoonRisingCharacterList = listOf(
            Character(
                name = "할머니Grandmother",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[첫 밤] 당신은 선팀 플레이어 1명과 그 캐릭터를 알고 게임을 시작합니다. 악마가 해당 플레이어를 살해하면, 당신 역시 사망합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/2/26/Icon_grandmother.png",
            ),

            Character(
                name = "항해사Sailor",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "당신은 죽지 않습니다. [매일 밤] 살아있는 플레이어 1명을 선택합니다: 당신과 해당 플레이어 중 1명은 황혼까지 취합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/16/Icon_sailor.png",
            ),

            Character(
                name = "객실_청소부Chambermaid",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤] 본인을 제외한 살아있는 플레이어 2명을 선택합니다: 오늘 밤 자신의 능력 때문에 깨어난 해당 플레이어들의 숫자를 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/8/87/Icon_chambermaid.png",
            ),

            Character(
                name = "여관주인Innkeeper",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 플레이어 2명을 선택합니다: 해당 플레이어들은 오늘 밤 죽지 않지만, 해당 플레이어 중 1명은 황혼까지 취합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/0/0c/Icon_innkeeper.png",
            ),

            Character(
                name = "도박꾼Gambler",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 플레이어 1명과 캐릭터 1개를 선택합니다: 해당 플레이어가 해당 캐릭터가 아니라면, 당신은 사망합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/f/fd/Icon_gambler.png",
            ),

            Character(
                name = "퇴마사Exorcist",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 밤*] 어제와 다른 플레이어 1명을 선택합니다: 악마를 선택했다면, 악마가 당신의 정체를 알게 됩니다. 이 경우, 악마는 오늘 밤 살해하지 못하며 깨어나지 않습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/c/c2/Icon_exorcist.png",
            ),

            Character(
                name = "험담꾼Gossip",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[매일 낮] 공개적으로, 험담꾼이라고 선언하고 소문을 1개 퍼트립니다. 해당 소문이 사실이었다면, 오늘 밤 플레이어 1명이 죽습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/c/c7/Icon_gossip.png",
            ),

            Character(
                name = "신하Courtier",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[게임당 1번] 밤에 캐릭터 1개를 선택합니다: 해당 캐릭터의 플레이어는 (존재한다면) 3번의 밤과 3번의 낮 동안 취합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/e/e0/Icon_courtier.png",
            ),

            Character(
                name = "교수Professor",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "[게임당 1번] 밤에* 사망한 플레이어 1명을 선택합니다: 해당 플레이어가 마을주민이라면, 부활시킵니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/6/65/Icon_professor.png",
            ),

            Character(
                name = "바보Fool",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "당신은 처음 사망할 때 죽지 않습니다. 능력이 사용됐더라도 이야기꾼은 바보에게 알려주지 않습니다.\n",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d9/Icon_fool.png",
            ),

            Character(
                name = "평화주의자Pacifist",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "선팀 플레이어가 처형되면 사망하지 않을 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/5/5d/Icon_pacifist.png",
            ),

            Character(
                name = "찻집_여인Tea_Lady",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "당신 양쪽으로 가장 인접한 살아있는 플레이어 2명 모두 선팀일 경우, 해당 플레이어들은 죽지 않습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/16/Icon_tealady.png",
            ),

            Character(
                name = "음악가Minstrel",
                characterType = Character_Type.마을주민_TOWNSFOLK,
                ability = "하수인이 처형으로 사망하면, 음악가를 제외한 (여행자 제외) 모든 플레이어는 내일 황혼까지 취합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/2/24/Icon_minstrel.png",
            ),

            Character(
                name = "폭력배Goon",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "[매일 밤] 자신의 능력으로 폭력배를 선택한 1번째 플레이어는 그 즉시 다음 황혼까지 취합니다. 당신은 해당 플레이어의 팀이 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/6/6f/Icon_goon.png",
            ),

            Character(
                name = "땜장이Tinker",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "이야기꾼은 당신을 (죽을 수 있다면) 언제든 죽일 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/9/98/Icon_tinker.png",
            ),

            Character(
                name = "달의_아이Moonchild",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신이 죽었다는 사실을 알았을 때, 즉시 공개적으로, 달의 아이라고 선언하고 살아있는 플레이어 1명을 선택해야 합니다 (선택 전 논의가 가능합니다): 해당 플레이어가 선팀이라면, 오늘 밤에 죽습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/d/dc/Icon_moonchild.png",
            ),

            Character(
                name = "미치광이Lunatic",
                characterType = Character_Type.외부인_OUTSIDER,
                ability = "당신은 자신을 악마라고 생각하지만 실제로는 아닙니다. 악마는 당신이 누군지 알고 있으며, 밤에 누구를 선택하는지 알게 됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/6/64/Icon_lunatic.png",
            ),

            Character(
                name = "대부Godfather",
                characterType = Character_Type.하수인_MINION,
                ability = "[첫 밤] 이번 게임에 어떤 외부인(들)이 존재하는지 알고 게임을 시작합니다. 그 중 1명이 오늘 낮에 죽었다면, 오늘 밤 플레이어 1명을 선택합니다: 해당 플레이어는 죽습니다. [마을주민 -1 외부인 +1 또는 마을주민 +1 외부인 -1]",
                isFormatChangingRole = true,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d0/Icon_godfather.png",
            ),

            Character(
                name = "악마추종자Devil's_Advocate",
                characterType = Character_Type.하수인_MINION,
                ability = "[매일 밤] 어제와 다른 살아있는 플레이어 1명을 선택합니다: 내일 낮에 해당 플레이어가 처형될 경우, 죽지 않습니다..",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/0/09/Icon_devilsadvocate.png",
            ),

            Character(
                name = "암살자Assassin",
                characterType = Character_Type.하수인_MINION,
                ability = "[게임당 1번] 밤에*, 플레이어 1명을 선택합니다: 해당 플레이어는 다른 어떤 능력으로도 막을 수 없게 사망합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/4/49/Icon_assassin.png",
            ),

            Character(
                name = "배후Mastermind",
                characterType = Character_Type.하수인_MINION,
                ability = "악마가 처형으로 사망하여 게임이 끝나려 할 때, 하루만 더 게임을 이어나갑니다. 해당 하루 동안 처형된 플레이어가 있다면, 그 플레이어의 팀이 패배합니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d5/Icon_mastermind.png",
            ),

            Character(
                name = "퓨카Pukka",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤] 플레이어 1명을 선택합니다: 해당 플레이어는 중독됩니다. 이전에 퓨카가 중독시켰던 플레이어는 이번 밤에 사망한 후에 중독에서 풀립니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/File:Icon_pukka.png",
            ),

            Character(
                name = "샤바로스Shabaloth",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 2명을 선택합니다: 해당 플레이어들은 죽습니다. 어젯밤 선택했던 현재 죽은 플레이어 1명은 되살아날 수 있습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/1f/Icon_shabaloth.png",
            ),

            Character(
                name = "포Po",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 플레이어 1명을 선택할 수 있습니다: 해당 플레이어는 죽습니다. 지난 자발적인 선택이 플레이어를 선택하지 않는 거였다면, 이번 밤 플레이어 3명을 선택합니다: 해당 플레이어들은 죽습니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/b/b2/Icon_po.png",
            ),

            Character(
                name = "좀뷸Zombuul",
                characterType = Character_Type.악마_DEMON,
                ability = "[매일 밤*] 오늘 낮에 아무도 죽지 않았다면, 플레이어 1명을 선택합니다: 해당 플레이어는 죽습니다. 당신이 처음 사망하면, 살아있지만 죽은 것으로 취급됩니다.",
                isFormatChangingRole = false,
                imageUrl = "https://wiki.bloodontheclocktower.com/images/1/15/Icon_zombuul.png",
            ),
        )

        fun addBadMoonRisingCharactersToDatabase() {
            val database = Firebase.database
            val databaseReference = database.getReference()
            for (character in badMoonRisingCharacterList) {
                databaseReference.child("BadMoonRising").child(character.characterType.toString()).child(character.name).setValue(character)
            }

        }
    }

}