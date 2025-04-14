// Eduardo Ariza Abad y Enrique Ibáñez Rico

package org.mps;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.mutation.GaussianMutation;

public class GaussianMutationTest {

    GaussianMutation gm;

    @BeforeEach
    void createNewGaussianMutation()  {
        // Arrange
        gm = new GaussianMutation();
    }
    
    @Test
    @DisplayName("mutate() throws EvolutionaryAlgorithmException if individual is null")
    public void Mutate_NullIndividual_ThrowsEAException() {
        // Arrange
        int[] individual = null;

        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> gm.mutate(individual));
    }

    @Test
    @DisplayName("mutate() throws EvolutionaryAlgorithmException if individual is null")
    public void Mutate_IndividualLengthIs0_ThrowsEAException() {
        // Arrange
        int[] individual = {};


        // Act + Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> gm.mutate(individual));
    }

    @Test
    @DisplayName("mutate() does not mutate the individual if mutation rate is 0")
    public void Mutate_MutationRateIs0_IndividualNotMutated() throws EvolutionaryAlgorithmException {
        // Arrange
        int[] individual = {1, 2, 3, 4};

        // Act
        int[] mutatedIndividual = gm.mutate(individual);

        // Assert
        assertTrue(Arrays.equals(individual, mutatedIndividual));
    }
 
}
