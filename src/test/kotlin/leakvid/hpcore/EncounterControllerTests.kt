package leakvid.hpcore

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import leakvid.hpcore.controller.EncounterController
import leakvid.hpcore.domain.Action
import leakvid.hpcore.domain.Encounter
import leakvid.hpcore.domain.Feature
import leakvid.hpcore.domain.Skill
import leakvid.hpcore.domain.enumtypes.DamageType
import leakvid.hpcore.domain.enumtypes.ResistanceType
import leakvid.hpcore.services.IEncounterService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(EncounterController::class)
@AutoConfigureMockMvc(addFilters = false)
class EncounterControllerTests {

    @MockkBean
    private lateinit var service: IEncounterService

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val name = "Cat"
    private val imageRoute = "/encounter"
    private val encounter = Encounter(
        "Cat",
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

    @Test
    fun `when no encounter exists, return not found`() {
        every { service.getAll() } returns null

        mockMvc.perform(
            MockMvcRequestBuilders.get("$imageRoute/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn()

        verify { service.getAll() }
    }

    @Test
    fun `when encounters exists, return ok`() {
        every { service.getAll() } returns listOf(encounter)

        mockMvc.perform(
            MockMvcRequestBuilders.get("$imageRoute/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        verify { service.getAll() }
    }

    @Test
    fun `when specific encounter does not exists, return not found`() {
        every { service.get(name) } returns null

        mockMvc.perform(
            MockMvcRequestBuilders.get("$imageRoute/$name")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn()

        verify { service.get(name) }
    }


    @Test
    fun `when specific encounter exists, return ok`() {
        every { service.get(name) } returns encounter

        mockMvc.perform(
            MockMvcRequestBuilders.get("$imageRoute/$name")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        verify { service.get(name) }
    }

    @Test
    fun `when encounter is merged, return ok`() {
        every { service.merge(encounter) } just Runs

        mockMvc.perform(
            MockMvcRequestBuilders.post(imageRoute)
                .content(ObjectMapper().writeValueAsString(encounter))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        verify { service.merge(encounter) }
    }
}