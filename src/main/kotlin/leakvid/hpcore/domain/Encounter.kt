package leakvid.hpcore.domain

import leakvid.hpcore.domain.enumtypes.DamageType
import leakvid.hpcore.domain.enumtypes.ResistanceType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Encounter(
        @Id val name: String,
        val challengeRating: Double,
        val armorClass: Int,
        val hitPoints: Int,
        val strength: Int,
        val dexterity: Int,
        val constitution: Int,
        val intelligence: Int,
        val wisdom: Int,
        val charisma: Int,
        val moveSpeed: Int,
        val resistances: Map<DamageType, ResistanceType>,
        val skills: List<Skill>,
        val features: List<Feature>,
        val actions: List<Action>)