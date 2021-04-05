package leakvid.hpcore.services.impl

import leakvid.hpcore.domain.Encounter
import leakvid.hpcore.repositories.EncounterRepository
import leakvid.hpcore.services.IEncounterService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EncounterService(val repository: EncounterRepository) : IEncounterService {
    override fun get(name: String): Encounter {
        return repository.findByName(name)
    }

    override fun getAll(): List<Encounter>? {
        return repository.findAll()
    }

    override fun merge(encounter: Encounter) {
        repository.save(encounter)
    }
}