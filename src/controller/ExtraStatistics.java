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

import Interfaces.IExtraStatistics;
import Models.Question;
import Models.QuestionMultipleChoice;
import Models.QuestionNumeric;
import Models.QuestionYesNo;
import interfaces.controller.ITest;

public class ExtraStatistics implements IExtraStatistics{
    private final Question[] testQuestions;
    private int counternum, counteryesno, countermulti;
    private final Question[] multiquestion;
    private final Question[] yesnoquestion;
    private final Question[] numericquestion;
    private ITest test;

    /**
     * Método construtor com a inicialização de variáveis - separa os tipos de questão em 3 arrays diferentes
     * @param qlist array com as questões para análise estatística
     */
    public ExtraStatistics(Question[] qlist) {
        this.testQuestions = qlist;
        this.counternum = 0;
        this.countermulti = 0;
        this.counteryesno = 0;

        //Percorre o array, verifica se a questão pertence a cada um dos 3 tipos, e incrementa o contador
        for (int i = 0; i < qlist.length; i++) {
            if (qlist[i] instanceof QuestionMultipleChoice){
                this.countermulti++;
            }
                
            if (qlist[i] instanceof QuestionNumeric){
                this.counternum++;
            }

            if (qlist[i] instanceof QuestionYesNo){
                this.counteryesno++;
            }
        }

        //Intância de cada um dos tipos de questões
        this.multiquestion = new Question[this.countermulti];
        this.yesnoquestion = new Question[this.counteryesno];
        this.numericquestion = new Question[this.counternum];

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        /**
         * Percorre o array, verifica se a questão pertence a cada um dos tipos, e atribui a questão
         * ao array do respetivo tipo, incrementando o contador
         */
        for (int i = 0; i < qlist.length; i++) {
            if (qlist[i] instanceof QuestionMultipleChoice) {
                this.multiquestion[c1] = qlist[i];
                c1++;
            }

            if (qlist[i] instanceof QuestionNumeric) {
                this.numericquestion[c2] = qlist[i];
                c2++;
            }

            if (qlist[i] instanceof QuestionYesNo) {
                this.numericquestion[c3] = qlist[i];
                c3++;
            }
        }
    }

    /**
     * Método que calcura a percentagem de questões numéricas corretas
     * @return percentagem de respostas corretas
     */
    @Override
    public double percentagemRespostasNumericasCertas() {
        double result = ((double) this.correctAnswer(this.numericquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /**
     * Método que calcula a percentagem de questões numéricas erradas
     * @return percentagem de respostas erradas
     */
    @Override
    public double percentagemRespostasNumericasErradas() {
        double result = ((double) this.incorrectAnswer(this.numericquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /** 
     * Método que calcula a percentagem de questões sim ou não corretas
     * @return percentagem de respostas corretas
     */
    @Override
    public double percentagemRespostasSimNaoCertas() {
        double result = ((double) this.correctAnswer(this.yesnoquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /** 
     * Método que calcula a percentagem de questões sim ou não erradas
     * @return percentagem de respostas erradas
     */
    @Override
    public double percentagemRespostasSimNaosErradas() {
        double result = ((double) this.incorrectAnswer(this.yesnoquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /**
     * Método que calcula a percentagem de questões de escolha múltipla corretas
     * @return percentagem de respostas corretas
     */
    @Override
    public double percentagemRespostasMultiplasCertas() {
        double result = ((double) this.correctAnswer(this.multiquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /**
     * Método que calcula a percentagem de questões de escolha múltipla erradas
     * @return percentagem de respostas erradas
     */
    @Override
    public double percentagemRespostasMultiplasErradas() {
        double result = ((double) this.incorrectAnswer(this.multiquestion) / (double) this.testQuestions.length) * 100;
        return result;
    }

    /**
     * Método que conta o número de respostas corretas dadas num teste
     * @return o número de respostas corretas
     * @param questions
     */
    @Override
    public int correctAnswer(Question[] questions) {
        int count = 0;

        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                //Se a questão estiver correta, incrementa o contador
                if (this.test.getQuestion(i).evaluateAnswer() == true) {
                    count++;
                }
            } 
            
            catch (Exception e) {
                System.out.println(e);
            }
        }

        return count;
    }

    /**
     * Método que conta o número de respostas erradas dadas num teste
     * @param questions
     * @return o número de respostas incorretas
     */
    @Override
    public int incorrectAnswer(Question[] questions) {
        int count = 0;

        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                //Se a resposta estiver errada, o contador é incrementado
                if (this.test.getQuestion(i).evaluateAnswer() == false) {
                    count++;
                }
            } 
            
            catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return count;
    }
}
