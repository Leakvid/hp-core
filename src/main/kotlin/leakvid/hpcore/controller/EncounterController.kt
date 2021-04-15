package leakvid.hpcore.controller

import leakvid.hpcore.domain.Action
import leakvid.hpcore.domain.Encounter
import leakvid.hpcore.domain.Feature
import leakvid.hpcore.domain.Skill
import leakvid.hpcore.domain.enumtypes.DamageType
import leakvid.hpcore.domain.enumtypes.ResistanceType
import leakvid.hpcore.services.IEncounterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/encounter")
class EncounterController(val service: IEncounterService) {
    @GetMapping
    fun getAll(): ResponseEntity<List<Encounter>> {
        val list = service.getAll()

        return if (list == null) {
            ResponseEntity.notFound().build();
        }else{
            ResponseEntity.ok(list)
        }
    }

    @GetMapping("/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<Encounter> {
        val encounter = service.get(name)

        return if (encounter == null) {
            ResponseEntity.notFound().build();
        }else{
            ResponseEntity.ok(encounter)
        }
    }

    @PostMapping
    fun insert(@RequestBody encounter: Encounter): ResponseEntity<Encounter>{
        service.merge(encounter)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/test")
    fun get() {
        service.merge(Encounter(
                "Dire Wolf",
                1.0,
                14,
                37,
                17,
                15,
                15,
                3,
                12,
                7,
                50,
                mapOf<DamageType, ResistanceType>(),
                listOf<Skill>(),
                listOf<Feature>(),
                listOf<Action>())
        )

        service.merge(Encounter(
                "Owlbear",
                3.0,
                13,
                59,
                20,
                12,
                17,
                3,
                12,
                7,
                40,
                mapOf<DamageType, ResistanceType>(),
                listOf<Skill>(),
                listOf<Feature>(),
                listOf<Action>())
        )

        service.merge(Encounter(
                "Gelatinous Cube",
                5.0,
                6,
                210,
                14,
                3,
                20,
                1,
                6,
                1,
                15,
                mapOf<DamageType, ResistanceType>(),
                listOf<Skill>(),
                listOf<Feature>(),
                listOf<Action>())
        )
    }
}