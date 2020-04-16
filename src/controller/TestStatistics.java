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

import Models.Question;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;


/**
 * Esta classe impletemeta todos os métodos da interface fornecida, presente em
 * interfaces.controller.ITestStatistics
 */
public class TestStatistics implements interfaces.controller.ITestStatistics {
    private ITest test;

    /**
     * Método construtor
     */
    public TestStatistics() {
        //Empty constructor
    }

    /**
     * Método contrutor que recebe um teste como parâmetro
     * @param test 
     */
    public TestStatistics(ITest test) {
        this.test = test;
    }

    /**
     * Método que calcula o tempo médio de cada resposta
     * @return a média de tempo por cada resposta
     */
    @Override
    public double meanTimePerAnswer() {
        double totalTimeSum = 0, qTime, mean = 0;

        //O array é percurrido, e é calculada a diferença entre o tempo de fim e o tempo de início
        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                mean += (this.test.getQuestion(i).getQuestion_metadata().getTimestamp_finish() - 
                        this.test.getQuestion(i).getQuestion_metadata().getTimestamp_start());
            } 
            
            catch (Exception e) {
                System.out.println(e);
            }
        }

        //é retornada a divisão entre as subtrações de todos os tempos e o número total de questões
        return mean / this.test.numberQuestions();
    }

    /**
     * Método que calcula o desvio padrão dos tempos de resposta
     * @return o desvio padrão
     */
    @Override
    public double standardDeviationTimePerAnsewer() {
        //Obtém a média dos tempos
        double mean = this.meanTimePerAnswer(); 
        double variance = 0, deviation = 0;

        //O array é percorrido, e é calculada a diferença entre o tempo final, o tempo inicial e a média, e esse resultado ao quadrado
        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                 variance += Math.pow(this.test.getQuestion(i).getQuestion_metadata().getTimestamp_finish() 
                         - this.test.getQuestion(i).getQuestion_metadata().getTimestamp_start() - this.meanTimePerAnswer(), 2);
            } 
            
            catch (Exception e) {
                System.out.println(e);
            }
            
            //O resultado anteriormente obtido é dividido pelo número total de questões - 1, e depois é feita a sua raiz quadrada
            deviation = variance / (this.test.numberQuestions() - 1);
            deviation = Math.sqrt(deviation);
        }
        
        return deviation;
    }

    /**
     * Método que calcula a percentagem de respostas corretas de um teste
     * @return percentagem de respostas corretas
     */
    @Override
    public double correctAnswerPecentage() {
        return (this.correctAnswer() * 100) / this.test.numberQuestions();
    }

    /**
     * Método que calcula a percentagem de respostas incorretas de um teste
     * @return  percentagem de respostas incorretas
     */
    @Override
    public double incorrectAnswerPecentage() {
        return (this.incorrectAnswer() * 100) / this.test.numberQuestions();
    }

    /**
     * Método que conta o número de respostas corretas dadas num teste
     * @return o número de respostas corretas
     */
    @Override
    public int correctAnswer() {
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
     * @return o número de respostas incorretas
     */
    @Override
    public int incorrectAnswer() {
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

    /**
     * Método que guarda as respostas erradas dadas num array de respostas
     * @return array de respostas erradas
     */
    @Override
    public IQuestion[] incorrectAnswers() {
        //recebe o número de respostas incorretas
        int j = this.incorrectAnswer();
        //Instancia um array com o tamanho do número das respostas erradas
        IQuestion[] result = new Question[j];
        int k = 0;
        
        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                //Se a resposta estiver errada, a resposta na posição i é guardada no array
                if (this.test.getQuestion(i).evaluateAnswer() == false) {
                    result[k] = this.test.getQuestion(i);
                    k++;
                }
            } 
            
            catch (TestException ex) {
                System.out.println(ex);
            }
        }

        return result;
    }

    /**
     * Método que guarda as respostas corretas dadas num array de respostas
     * @return array de respostas certas
     */
    @Override
    public IQuestion[] correctAnswers() {
        // Buscar o número de respostas corretas
        int j = this.correctAnswer(); 

        // Instanciar uma estrutura com o tamanho necessário
        IQuestion[] result = new Question[j];
        int k = 0;
        
        for (int i = 0; i < this.test.numberQuestions(); i++) {
            try {
                //Se a resposta estiver correta, a resposta é guardada no array de respostas
                if (this.test.getQuestion(i).evaluateAnswer() == true) {
                    result[k] = this.test.getQuestion(i);
                    k++;
                }
            } 
            
            catch (TestException ex) {
                System.out.println(ex);
            }
        }
       
        return result;
    }
}
