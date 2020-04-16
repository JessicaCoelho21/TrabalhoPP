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
 * Esta classe implementa todos os métodos definidos na interface fornecida, presente em
 * interfaces.models.IQuestionYesNo
 */
public class QuestionYesNo extends Question implements interfaces.models.IQuestionYesNo {
    private String correct_answer, user_answer;

    /**
     * Método Construtor
     */
    public QuestionYesNo() {
        //empty constructor
    }

    /**
     * Método construtor
     * @param correct_answer resposta correta à questão
     * @param title título da questão
     * @param question_description descrição da questão
     * @param mark marca da questão
     * @param score pontuação da questão
     */
    public QuestionYesNo(String correct_answer, String title, String question_description, float mark, float score) {
        super(title, question_description, mark, score);
        this.correct_answer = correct_answer;
    }

    /**
     * Método de acesso getter para obter a resposta correta à questão
     * @return resposta correta
     */
    @Override
    public String getCorrect_answer() {
        return this.correct_answer;
    }

    /**
     * Método de acesso setter para atribuir a resposta correta à questão
     * @param correct_answer resposta correta a ser atribuída
     */
    @Override
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    /**
     * Método de acesso getter para obter a resposta dada pelo utilizador à questão
     * @return resposta dada pelo utilizador
     */
    @Override
    public String getUser_answer() {
        return this.user_answer;
    }

    /**
     * Método de acesso setter para atribuir a resposta dada pelo utilizador à questão
     * @param user_answer resposta a atribuir
     */
    @Override
    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
        this.setDone(true);
    }

    /**
     * Método que marca a rquestão como concluída
     * @param user_answer resposta dada pelo utilizador
     */
    @Override
    public void answer(String user_answer) {
        this.setUser_answer(user_answer);
        //Marca a resposta como concluída ou não
        this.setDone(true);
    }

    /**
     * Método que verifica se a resposta dada está ou não correta
     * @return true se a resposta estiver correta, false caso contrário
     */
    @Override
    public boolean evaluateAnswer() {
        return this.correct_answer.equalsIgnoreCase(this.user_answer);
    }

    /**
     * Método toString
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("\n*****Questão Sim ou Não*****");
        
        if (super.isDone()) {
            QuestionMetadata meta = (QuestionMetadata) super.getQuestion_metadata();
            builder.append("\nQuestão Concluida!").append(": Tempo de Realização: ")
                    .append(Math.round(meta.getDoneTimeSeconds())).append(" segundos");
        }
        
        else {
            builder.append("\n{ Questão Incompleta! }");
        }
        
        builder.append("\n\tTítulo: ").append(super.getTitle())
                .append("\n\tDescrição: ").append(super.getQuestion_description());
        builder.append("\n\t-\n\tResposta Correta: ").append(this.correct_answer)
                .append("\n\tResposta Utilizador: ").append(this.user_answer).append('\n');
        
        return builder.toString();
    }
}
