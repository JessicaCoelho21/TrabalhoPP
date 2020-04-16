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

/**
 * Esta classe implementa todos os métodos da interface fornecida, presente em
 * interfaces.models.IQuestionMetadata
 */
public class QuestionMetadata implements interfaces.models.IQuestionMetadata{
    private long questionstartt, questionfinisht;

    /**
     * Método construtor
     */
    public QuestionMetadata() {
        //empty constructor
    }

    /**
     * Método construtor
     * @param questionstartt tempo de início da questão
     * @param questionfinisht tempo de fim da questão
     */
    public QuestionMetadata(long questionstartt, long questionfinisht) {
        this.questionstartt = questionstartt;
        this.questionfinisht = questionfinisht;
    }

    /**
     * Método de acesso getter para obter o tempo de início da questão
     * @return o tempo de início
     */
    @Override
    public long getTimestamp_start() {
        return questionstartt;
    }

    /**
     * Método de acesso setter para atribuir o tempo de início da questão
     * @param l tempo de início a atribuir
     */
    @Override
    public void setTimestamp_start(long l) {
        this.questionstartt = l;
    }

    /**
     * Método de acesso getter para obter o tempo de fim da questão
     * @return tempo de fim da questão
     */
    @Override
    public long getTimestamp_finish() {
        return questionfinisht;
    }

    /**
     * Método de acesso setter para atribuir o tempo do fim da questão
     * @param l tempo final a atribuir
     */
    @Override
    public void setTimestamp_finish(long l) {
        this.questionfinisht = l;
    }

    /**
     * Método que converte o tempo em segundos, considerando que o tempo inserido está em milissegundos
     * @return cálculo do tempo de resposta à pergunta, segundos
     */
    public long getDoneTimeSeconds() {
        return (questionfinisht - questionstartt) / 1000;
    }

    /**
     * Método que converte o tempo em milissegundos, considerando que o tempo inserido está em segundos
     * @return cálculo do tempo de resposta à pergunta, milissegundos
     */
    public long getDoneTimeMilliseconds() {
        return questionfinisht - questionstartt;
    }
}