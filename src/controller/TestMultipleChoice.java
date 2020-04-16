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
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import Models.QuestionMultipleChoice;

public class TestMultipleChoice extends Test{
    /**
     * Método construtor
     */
    public TestMultipleChoice() {
        super();
    }

    /**
     * Método que adiciona uma questão de escolha múltipla
     * Este método é um override o método de adicionar questão addQuestion, presente na classe Test
     * 
     * Isoladamente, esta classe não adiciona mais do que uma questão de escolha múltipla
     * 
     * @param q questão a adicionar
     * @return 
     * @throws TestException 
     */
    @Override
    public boolean addQuestion(IQuestion q) throws TestException {
        //Lança exceção caso a questão seja nula
        if (q == null){
            throw new TestException();
        }
        
        int pos = 0;

        while (pos < this.questions.length ){ 
            if (this.questions[pos] == null && q instanceof QuestionMultipleChoice){
                //Insere o objeto quando encontrar uma posição vazia
                this.questions[pos] = (Question) q;
                return true;
            }
            
            pos++;   
        }

        return false;
    }
}
