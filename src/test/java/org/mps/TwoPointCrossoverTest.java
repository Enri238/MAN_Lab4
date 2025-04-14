// Eduardo Ariza Abad y Enrique Ibáñez Rico

package org.mps;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.TwoPointCrossover;

public class TwoPointCrossoverTest {

    TwoPointCrossover crossover;

    @BeforeEach
    void createNewTwoPointCrossover()  {
        // Arrange
        crossover = new TwoPointCrossover();
    }

    @Test
    @DisplayName("crossover() throws EvolutionaryAlgorithmException if parent 1 is null")
    public void Crossover_NullParent1_ThrowsEAException() {
        // Arrange
        int[] parent1 = null;
        int[] parent2 = {1, 2, 3, 4};

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> crossover.crossover(parent1, parent2));
    }

    @Test
    @DisplayName("crossover() throws EvolutionaryAlgorithmException if parent 2 is null")
    public void Crossover_NullParent2_ThrowsEAException() {
        // Arrange
        int[] parent1 = {1, 2, 3, 4};
        int[] parent2 = null;

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> crossover.crossover(parent1, parent2));
    }

    @Test
    @DisplayName("crossover() throws EvolutionaryAlgorithmException if parent 1 has 1 or less population")
    public void Crossover_InsufficientPopulation_ThrowsEAException() {
        // Arrange
        int[] parent1 = {1};
        int[] parent2 = {2, 3, 4};

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> crossover.crossover(parent1, parent2));
    }

    @Test
    @DisplayName("crossover() throws EvolutionaryAlgorithmException if parents have different population")
    public void Crossover_DifferentPopulation_ThrowsEAException() {
        // Arrange
        int[] parent1 = {1, 2, 3, 4};
        int[] parent2 = {5, 6, 7};

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> crossover.crossover(parent1, parent2));
    }

}
