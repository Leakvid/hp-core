package leakvid.hpcore.repositories

import leakvid.hpcore.domain.Encounter
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EncounterRepository : MongoRepository<Encounter, Long> {
    fun findByName(name : String) : Encounter
}