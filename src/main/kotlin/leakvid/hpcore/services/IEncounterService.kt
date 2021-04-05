package leakvid.hpcore.services

import leakvid.hpcore.domain.Encounter

interface IEncounterService {
    fun get(name: String) : Encounter?
    fun getAll() : List<Encounter>?
    fun merge(encounter: Encounter)
}