/*
 * Nome: Jéssica Beatriz Silva Coelho
 * Número: 8170180
 * Turma: T3
 *
 * Nome: Vânia Sofia Fonseca Lemos
 * Número: 8170310
 * Turma: T2
 */

package controller;

import interfaces.controller.ITest;
import interfaces.models.IQuestion;

/**
 * Esta classe implementa todos os métodos da interface fornecida, presente em
 * interfaces.models.IScoreStrategy
 */
public class ScoreStrategy implements interfaces.controller.IScoreStrategy {
    private ITest test;

    /**
     * Método construtor
     */
    public ScoreStrategy() {
        //empty constructor
    }
    
    /**
     * Método construtor
     * @param test
     */
    public ScoreStrategy(ITest test) {
        this.test = test;
    }

    /**
     * Método que apresenta a percentagem de respostas corretas de um dado teste
     * @param iqs array de perguntas
     * @return 
     */
    @Override
    public String CalculateScore(IQuestion[] iqs) {    
        return String.valueOf(this.test.getTestStatistics().correctAnswerPecentage()) + " %";
    }
}
