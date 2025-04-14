// Eduardo Ariza Abad y Enrique Ibáñez Rico

package org.mps;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.selection.TournamentSelection;

public class TournamentSelectionTest {

    TournamentSelection ts;

    @BeforeEach
    void createNewTournamentSelection() throws EvolutionaryAlgorithmException  {
        // Arrange
        ts = new TournamentSelection(2);
    }

    @Test
    @DisplayName("select() throws EvolutionaryAlgorithmException if population is null")
    public void Select_NullPopulation_ThrowsEAException() {
        // Arrange
        int[] population = null;

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ts.select(population));
    }

    @Test
    @DisplayName("select() throws EvolutionaryAlgorithmException if population is empty")
    public void Select_EmptyPopulation_ThrowsEAException() {
        // Arrange
        int[] population = new int[0];

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ts.select(population));
    }

    @Test
    @DisplayName("select() throws EvolutionaryAlgorithmException if the population size is lower or equal than the tournament size")
    public void Select_PopulationBiggerThanTournamentSize_ThrowsEAException() {
        // Arrange
        int[] population = new int[1];

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ts.select(population));
    }

}
