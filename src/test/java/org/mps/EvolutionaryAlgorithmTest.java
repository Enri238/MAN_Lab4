// Eduardo Ariza Abad y Enrique Ibáñez Rico

package org.mps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.crossover.TwoPointCrossover;
import org.mps.mutation.GaussianMutation;
import org.mps.selection.TournamentSelection;

public class EvolutionaryAlgorithmTest {

    EvolutionaryAlgorithm ea;

    @Test
    @DisplayName("Evolutionary Algorithm Constructor throws EvolutionaryAlgorithmException if an operand is null")
    public void Constructor_NullOperand_ThrowsEAException() {
        // ((Arrange + Act) <- Constructor) + Assert
        assertThrows(EvolutionaryAlgorithmException.class, 
                    () -> new EvolutionaryAlgorithm(null, 
                                                    null, 
                                                    null));
        assertThrows(EvolutionaryAlgorithmException.class, 
                    () -> new EvolutionaryAlgorithm(new TournamentSelection(1), 
                                                    null, 
                                                    null));
        assertThrows(EvolutionaryAlgorithmException.class, 
                    () -> new EvolutionaryAlgorithm(new TournamentSelection(1), 
                                                    new GaussianMutation(), 
                                                    null));
    }

    @Test
    @DisplayName("Evolutionary Algorithm Constructor throws EvolutionaryAlgorithmException if tournament size is negative or below 0")
    public void Constructor_NegativeTournamentSize_ThrowsEAException() {
        // ((Arrange + Act) <- Constructor) + Assert
        assertThrows(EvolutionaryAlgorithmException.class, 
                    () -> new EvolutionaryAlgorithm(new TournamentSelection(-1), 
                                                    new GaussianMutation(), 
                                                    new TwoPointCrossover()));
    }

    @Test
    @DisplayName("Constructor instantiates with new EvolutionaryAlgorithm()")
    public void Constructor_ValidOperands_InitialisesCorrectly() throws EvolutionaryAlgorithmException {
        new EvolutionaryAlgorithm(new TournamentSelection(1), 
                                  new GaussianMutation(), 
                                  new TwoPointCrossover());
    }

    @Nested
    @DisplayName("Method optimize() of a correctly initialised constructor")
    class Optimization {
        
        @BeforeEach
        void createNewEvolutionaryAlgorithm() throws EvolutionaryAlgorithmException {
            // Arrange
            ea = new EvolutionaryAlgorithm(new TournamentSelection(3), 
                                           new GaussianMutation(0.5, 0.5), 
                                           new TwoPointCrossover());
        }

        @Test
        @DisplayName("optimize() throws EvolutionaryAlgorithmException if population is null")
        public void Optimize_NullPopulation_ThrowsEAException() {
            // Act + Assert
            assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(null));
        }

        @Test
        @DisplayName("optimize() throws EvolutionaryAlgorithmException if population is negative or below 0")
        public void Optimize_InvalidPopulation_ThrowsEAException() {
            // Arrange
            int [][] population = {};
            
            // Act + Assert
            assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
        }

        @Test
        @DisplayName("optimize() throws EvolutionaryAlgorithmException if population[0] is null")
        public void Optimize_NullPopulation0_ThrowsEAException() {
            // Arrange
            int [][] population = {null};
            
            // Act + Assert
            assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
        }

        @Test
        @DisplayName("optimize() throws EvolutionaryAlgorithmException if population has an odd length")
        public void Optimize_OddLengthPopulation_ThrowsEAException() {
            // Arrange
            int[][] population = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

            // Act + Assert
            assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
        }

        @Test
        @DisplayName("With correct operands, an optimization process is performed correctly")
        public void Optimize_ValidOperands_ReturnsNewPopulation() throws EvolutionaryAlgorithmException {
            // Arrange
            int[][] population = {{1, 2, 3, 4}, {5, 6, 7, 8}, {2, 3, 8, 1}, {3, 6, 8, 9}, {4, 5, 6, 7}, {8, 9, 10, 11}};

            // Act
            int[][] newPopulation = ea.optimize(population);
            int expectedLength = population.length;

            // Assert
            assertEquals(expectedLength, newPopulation.length);
        }
    }

}
