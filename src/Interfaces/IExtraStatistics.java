/*
 * Nome: Jéssica Beatriz Silva Coelho
 * Número: 8170180
 * Turma: T3
 *
 * Nome: Vânia Sofia Fonseca Lemos
 * Número: 8170310
 * Turma: T2
 */

package Interfaces;

import Models.Question;

public interface IExtraStatistics {
    public double percentagemRespostasNumericasCertas();
    public double percentagemRespostasNumericasErradas();

    public double percentagemRespostasSimNaoCertas();
    public double percentagemRespostasSimNaosErradas();

    public double percentagemRespostasMultiplasCertas();
    public double percentagemRespostasMultiplasErradas();

    public int correctAnswer(Question[] question);
    public int incorrectAnswer(Question[] question);
}
