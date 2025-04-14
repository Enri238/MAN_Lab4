package org.mps;

import org.mps.crossover.CrossoverOperator;
import org.mps.mutation.MutationOperator;
import org.mps.selection.SelectionOperator;

/**
 * La clase EvolutionaryAlgorithm representa un algoritmo evolutivo básico que
 * se utiliza para resolver problemas de optimización.
 * Este algoritmo se basa en el proceso de evolución biológica y sigue una serie
 * de pasos para mejorar progresivamente una población de soluciones candidatas.
 *
 * El proceso de optimización se realiza en varias etapas:
 * 1. Selección: Se seleccionan las soluciones más aptas para ser utilizadas
 * como progenitores en la próxima generación. Esto se realiza mediante
 * operadores de selección como la selección de torneo, etc.
 * 2. Cruce: Se aplican operadores de cruce a los progenitores seleccionados
 * para generar una nueva población de descendientes. Esto implica la
 * combinación de características de dos o más soluciones candidatas para
 * producir nuevas soluciones.
 * 3. Mutación: Ocasionalmente, se aplican operadores de mutación a los
 * descendientes generados para introducir variabilidad en la población y evitar
 * la convergencia prematura hacia un óptimo local.
 * 4. Reemplazo: Los descendientes reemplazan a una parte de la población
 * anterior.
 *
 * La clase EvolutionaryAlgorithm proporciona una implementación básica de este
 * proceso de optimización, permitiendo la personalización mediante el uso de
 * diferentes operadores de selección, cruce y mutación.
 */
public class EvolutionaryAlgorithm {
    private SelectionOperator selectionOperator;
    private MutationOperator mutationOperator;
    private CrossoverOperator crossoverOperator;

    public EvolutionaryAlgorithm(SelectionOperator selectionOperator, MutationOperator mutationOperator,
            CrossoverOperator crossoverOperator) throws EvolutionaryAlgorithmException {
        if (selectionOperator == null || mutationOperator == null || crossoverOperator == null) { // a
            throw new EvolutionaryAlgorithmException("Argumentos nulos"); // b
        }
        this.selectionOperator = selectionOperator; // c los 3
        this.mutationOperator = mutationOperator;
        this.crossoverOperator = crossoverOperator;
    }


    public int[][] optimize(int[][] population) throws EvolutionaryAlgorithmException {
        
        // Añadidas últimas 2 condiciones
        if (population != null && population.length  > 0 && population[0] != null && population.length % 2 == 0) { // d
            // Creamos una nueva población para los descendientes
            int[][] offspringPopulation = new int[population.length][population[0].length]; // e

            // Aplicamos operadores de selección y cruce para generar descendientes
            for (int i = 0; i < population.length; i += 2) { // g
                // Seleccionamos dos individuos de la población actual
                int[] parent1 = selectionOperator.select(population[i]); // h
                int[] parent2 = selectionOperator.select(population[i + 1]); // i
               
                // Aplicamos el operador de cruce para generar dos descendientes
                int[][] offspring = crossoverOperator.crossover(parent1, parent2); // j
                // k
                offspringPopulation[i] = offspring[0];
                offspringPopulation[i + 1] = offspring[1];
            }

            // Aplicamos operador de mutación a los descendientes
            for (int i = 0; i < offspringPopulation.length; i++) { // l
                offspringPopulation[i] = mutationOperator.mutate(offspringPopulation[i]); // m
            }

            // Reemplazo
            for (int i = 0; i < population.length; i++) { // n
                if (better(offspringPopulation[i], population[i])) { // o
                    population[i] = offspringPopulation[i]; // p
                }
            }
        } else {
            throw new EvolutionaryAlgorithmException("Poblacion no valida"); // f
        }
        return population; // q
    }

    /*
     * Método que calcula que población tiene mejor calidad o fitness, que en este
     * caso se ha establecido
     * como el que tiene menor suma de sus elementos
     */
    private boolean better(int[] population1, int[] population2) {
        int suma1 = 0;
        int suma2 = 0;
        if (population1 != null && population2 != null && population1.length == population2.length) {
            for (int i = 0; i < population1.length; i++) {
                suma1 += population1[i];
                suma2 += population2[i];
            }
        }
        return suma1 < suma2;
    }

}
