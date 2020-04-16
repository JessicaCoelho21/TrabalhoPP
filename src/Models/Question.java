/*
 * Nome: Jéssica Beatriz Silva Coelho
 * Número: 8170180
 * Turma: T3
 *
 * Nome: Vânia Sofia Fonseca Lemos
 * Número: 8170310
 * Turma: T2
 */

package Models;

import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;
import java.util.Objects;

/**
 * Esta classe implementa todos os métodos definidos na interface presente 
 * em interfaces.models.IQuestion
 */
public abstract class Question implements interfaces.models.IQuestion {
    private String title;
    private String question_description;
    private QuestionMetadata question_metadata = new QuestionMetadata();
    private boolean done = false;
    private float mark = 0;
    private float score = 0;

    /**
     * Método construtor
     */
    public Question() {
        //empty constructor
    }

    /**
     * Método construtor
     * @param title título da questão
     * @param question_description descrição da questão
     * @param mark marca da questão
     * @param score pontuação da questão
     */
    public Question(String title, String question_description, float mark, float score) {
        this.title = title;
        this.question_description = question_description;
        this.mark = mark;
        this.score = score;
    }

    /**
     * Método de acesso getter para obter o título da pergunta
     * @return o título da pergunta
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Método de acesso setter para atribuir o título à pegunta
     * @param title título a atribuir
     * @throws QuestionException 
     */
    @Override
    public void setTitle(String title) throws QuestionException {
        if (title == null) {
            throw new QuestionException();
        }

        this.title = title;
    }

    /**
     * Método de acesso getter para obter a descrição da pergunta
     * @return a descrição da pergunta
     */
    @Override
    public String getQuestion_description() {
        return this.question_description;
    }

    /**
     * Método de acesso setter para atribuir a descrição da pergunta
     * @param description descrição a atribuir
     * @throws QuestionException 
     */
    @Override
    public void setQuestion_description(String description) throws QuestionException {
        if (description == null) {
            throw new QuestionException();
        }

        this.question_description = description;
    }

    /**
     * Método de acesso getter para obter os dados relacionados com metadata
     * @return question_metadata
     */
    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return this.question_metadata;
    }

    /**
     * Método de acesso setter para atribuir os dados relacionados com metadata
     * @param metadata a atribuir
     */
    @Override
    public void setQuestion_metadata(IQuestionMetadata metadata) {
        this.question_metadata = (QuestionMetadata) metadata;
    }

    /**
     * Método de acesso getter para obter a informação de que o teste está concluído
     * @return true se estiver concluído, false caso contrário
     */
    @Override
    public boolean isDone() {
        return this.done;
    }

    /**
     * Método de acesso getter para abribuir o estado de concluído ao teste
     * @param done true se estiver concluído, false caso contrário
     */
    @Override
    public void setDone(boolean done) {
        /*
        representa o tempo desde que se inicia a pergunta até esta ter a sua resposta final,
        ou seja, também conta o tempo de alteração da resposta desta pergunta
        */
        this.question_metadata.setTimestamp_finish(System.currentTimeMillis());
        this.done = done;
    }

    /**
     * Método abstrato answer, implementado posteriormente nas subclasses desta classe
     * @param user_answer resposta dada pelo utilizador
     */
    @Override
    public abstract void answer(String user_answer);

    /**
     * Método abstrato evaluateAnswer, implementado posteriormente nas subclasses desta classe
     * @return 
     */
    @Override
    public abstract boolean evaluateAnswer();

    /**
     * Método de acesso getter para obter a marca do teste
     * @return a marca do teste
     */
    @Override
    public float getMark() {
        return this.mark;
    }

    /**
     * Método de acesso setter para atribuir uma marca ao teste
     * @param mark marca a atribuir
     */
    @Override
    public void setMark(float mark) {
        this.mark = mark;
    }

    /**
     * Método de acesso getter para obter a pontuação do teste
     * @return 
     */
    public float getScore() {
        return this.score;
    }

    /**
     * Método de acesso setter para atribuir uma pontuação ao teste
     * @param score pontuação a atribuir
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Método hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.question_description);
        hash = 97 * hash + Objects.hashCode(this.question_metadata);
        hash = 97 * hash + (this.done ? 1 : 0);
        hash = 97 * hash + Float.floatToIntBits(this.mark);
        
        return hash;
    }

    /**
     * Método equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        final Question other = (Question) obj;
        
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        
        if (!Objects.equals(this.question_description, other.question_description)) {
            return false;
        }
        
        if (!Objects.equals(this.question_metadata, other.question_metadata)) {
            return false;
        }
        
        if (this.done != other.done) {
            return false;
        }
        
        if (Float.floatToIntBits(this.mark) != Float.floatToIntBits(other.mark)) {
            return false;
        }
        
        return true;
    }
}
