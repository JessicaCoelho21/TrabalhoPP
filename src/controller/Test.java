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

import Models.QuestionMultipleChoice;
import Models.QuestionNumeric;
import Models.QuestionYesNo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import interfaces.controller.IScoreStrategy;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import java.io.*;

/**
 * Esta classe implementa todos os métodos definidos na interface presente em
 * interfaces.controller.ITest
 */
public class Test implements interfaces.controller.ITest {

    private int numberObjects = 0;
    protected IQuestion[] questions;
    private final int DEFAULT_SIZE = 50;
    private ITestStatistics statistics;
    private IScoreStrategy scoreStrategy;

    /**
     * Método contrutor que atribui ao array um tamanho inicial por defeito
     */
    public Test() {
        this.questions = new IQuestion[this.DEFAULT_SIZE];
        this.statistics = new TestStatistics();
        this.scoreStrategy = new ScoreStrategy();
    }

    /**
     * Método para adicionar uma questão ao array
     * @param q questão a adicionar
     * @return true se a questão for adicionada com sucesso, false caso contrário
     * @throws TestException
     */
    @Override
    public boolean addQuestion(IQuestion q) throws TestException {
        //Lança uma exceção se o objeto for nulo
        if (q == null) { 
            throw new TestException();
        }

        //Se o array estiver cheio, lança exceção
        if (this.numberObjects == this.questions.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //É atribuída a questão passada por parâmetro ao array na posição numberObjects, que é incrementado
        this.questions[numberObjects] = q;
        numberObjects++;

        return true;
    }

    /**
     * Método para obter uma questão existente no array
     * @param pos posição onde se encontra a questão
     * @return a questão na posição passada por parâmetro
     * @throws TestException caso não exista nenhuma questão nessa posição
     */
    @Override
    public IQuestion getQuestion(int pos) throws TestException {
        try {
            //se a posição passada por parâmetro não existe no array, lança exceção
            if (pos < 0 || pos > this.numberObjects) {
                throw new ArrayIndexOutOfBoundsException();
            }
        } 
        
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        return this.questions[pos];
    }

    /**
     * Método que remove uma questão dada a posição onde esta se encontra no array
     * @param i posição da questão a remover
     * @return true se a questão foi removida com sucesso, false caso contrário
     */
    @Override
    public boolean removeQuestion(int i) {
        //Se i for menor que o número de objetos e a questão dessa posição não seja nula
        if (i < this.numberObjects && i >= 0 && this.questions[i] != null) {
            //O array é percorrido e todos os elementos em j tomam o valor de j + 1
            for (int j = i; j < this.numberObjects - 1; j++) {
                this.questions[j] = this.questions[j + 1];
            }

            //O último elemento do array toma o valor de nulo
            this.questions[this.numberObjects - 1] = null;

            //O número de elementos é decrementado, caso o último elemento do array seja nulo
            if (this.questions[this.numberObjects - 1] == null) {
                this.numberObjects--;
                return true;
            } 
            
            else {
                return false;
            }
        }

        return false;
    }

    /**
     * Método que remove uma questão, dada a questão
     * @param q questão a remover
     * @return true se a frase foi removida com sucesso, false caso contrário
     */
    @Override
    public boolean removeQuestion(IQuestion q) {
        for (int i = 0; i < this.numberObjects - 1; i++) {
            //Se o equals retornar verdadeiro, o conteúdo da posição i vai tomar o valor de i + 1
            if (this.questions[i].equals(q)) {
                this.questions[i] = this.questions[i + 1];
            }
        }

        //O último elemento do array toma o valor de nulo
        this.questions[this.numberObjects - 1] = null;

        //O número de elementos é decrementado, caso o último elemento do array seja nulo
        if (this.questions[this.numberObjects - 1] == null) {
            this.numberObjects--;
            return true;
        } 
        
        else {
            return false;
        }
    }

    /**
     * Método que retorna o número de questões existentes no array
     * @return o número de questões existentes no array
     */
    @Override
    public int numberQuestions() {
        //Number of objects = posições ocupadas do array
        return numberObjects;
    }

    /**
     * Método que verifica se todas as perguntas estão respondidas
     * @return true em caso de sucesso
     */
    @Override
    public boolean isComplete() {
        for (int i = 0; i < this.numberObjects; i++) {
            if (!this.questions[i].isDone()) {
                return false;
            }
        }
        
        ITestStatistics stats = new TestStatistics(this);
        this.statistics = stats;

        return true;
    }

    /**
     * Método que obtém as estatísticas
     * @return statistics
     */
    @Override
    public ITestStatistics getTestStatistics() {
        return this.statistics;
    }

    /**
     * Método de acesso getter para obter as estatísticas
     * @return statistics
     */
    public ITestStatistics getStatistics() {
        return statistics;
    }

    /**
     * Método de acesso setter para atribuir uma estatística
     * @param statistics estatística a atribuir
     */
    public void setStatistics(ITestStatistics statistics) {
        this.statistics = statistics;
    }
    
    /**
     * Método que carrega o ficheiro JSON
     * @param string
     * @return true em caso de sucesso
     * @throws TestException
     */
    @Override
    public boolean loadFromJSONFile(String string) throws TestException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(string));
            JsonStreamParser p = new JsonStreamParser(reader);
            JsonArray arr = (JsonArray) p.next();
            int arr_size = arr.size();

            for (int i = 0; i < arr_size; i++) {
                JsonElement arrayElement = arr.get(i);
                JsonObject obj = arrayElement.getAsJsonObject();

                if (obj.has("type") && obj.has("question")) {
                    //Conversões do tipo
                    String tempType = obj.get("type").getAsString();
                    JsonObject questionObj = obj.get("question").getAsJsonObject();

                    //Conversões do tipo
                    String title = questionObj.get("title").getAsString();
                    float score = questionObj.get("score").getAsFloat();
                    float mark = questionObj.get("mark").getAsFloat();
                    String description = questionObj.get("question_description").getAsString();
                    String correctAnswer = questionObj.get("correct_answer").getAsString();

                    //Carrega as questões de escolha múltipla
                    if (tempType.equals("MultipleChoice")) {
                        JsonArray options = questionObj.get("options").getAsJsonArray();
                        String[] opt = new String[options.size()];

                        for (int j = 0; j < options.size(); j++) {
                            opt[j] = options.get(j).getAsString();
                        }

                        IQuestion question = new QuestionMultipleChoice(opt, correctAnswer, title, description, mark, score);
                        this.addQuestion(question);
                    }

                    //São carregadas as questões numéricas
                    if (tempType.equals("Numeric")) {
                        IQuestion question = new QuestionNumeric(questionObj.get("correct_answer").getAsDouble(), title, description, mark, score);
                        this.addQuestion(question);
                    }

                    //São carregadas as questões de sim ou não
                    if (tempType.equals("YesNo")) {
                        IQuestion question = new QuestionYesNo(correctAnswer, title, description, mark, score);
                        this.addQuestion(question);
                    }
                }
            }
        } 
        
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        } 
        
        finally {
                try {
                    reader.close();
                } 
                
                catch (IOException ex) {
                    System.out.println(ex);
                }
            }

        return true;
    }

    /**
     * Método que guarda os resultados do teste de um ficheiro .txt
     * @return true em caso de sucesso, false caso contrário
     * @throws TestException
     */
    @Override
    public boolean saveTestResults() throws TestException {
        String path = "C:\\Projetos\\Java\\TrabalhoRecursoPP\\data.txt";
        
        /**
         * Será criado um ficheiro .txt caso este não exista, caso exista, a nova informação vai ser acrescentada
         * ao final do ficheiro
         */
        
        //Instância para escrita do ficheiro
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) { 
            System.out.println("\n\n FICHEIRO ESCRITO\n\nLocal de destino: " + path);
            //É imprimido no ficheiro o conteúdo do método toString
            String test = this.toString();

            // Se não existirem questões, lança a exceção
            if (test.isEmpty()) { 
                throw new TestException();
            }

            writer.append(test);
            writer.flush();
            writer.close();
        } 
        
        // Se não for prossível criar o ficheiro, retorna falso
        catch (IOException ex) { 
            System.err.println("ERROR in file\n - path: [" + path + "]");
            return false;
        }
        
        return true;
    }

    /**
     * Método toString
     * @return resultados do teste em formato String
     */
    @Override
    public String toString() {
        // Se não existirem questões, retorna uma string vazia
        StringBuilder builder = new StringBuilder().append("**********TESTE**********\n")
                .append("\nNúmero de Questões: \n").append(this.numberQuestions());

        int corrNum = this.statistics.correctAnswer(), incorrNum = this.statistics.incorrectAnswer();
        
        builder.append("\n*****Estatísticas do Teste*****\n");
        builder.append("\n\tNúmero de Respostas Corretas: ").append(corrNum)
                .append("\n\tPercentagem de Respostas Corretas: ")
                .append(this.statistics.correctAnswerPecentage()).append(" %");

        if (corrNum != 0) {
            builder.append("\n\tRespostas Corretas: ");
            IQuestion[] ca = this.statistics.correctAnswers();

            for (IQuestion q : ca) {
                builder.append("\n\t> ").append(q.getTitle());
            }
        }

        builder.append("\n\tNúmero de Respostas Incorretas: ").append(incorrNum)
                .append("\n\tPercentagem de Respostas Incorretas: ")
                .append(this.statistics.incorrectAnswerPecentage()).append(" %");

        if (incorrNum != 0) {
            builder.append("\n\tRespostas Incorretas: ");
            
            IQuestion[] ia = this.statistics.incorrectAnswers();

            for (IQuestion q : ia) {
                builder.append("\n\t> ").append(q.getTitle());
            }
        }

        builder.append("\n\tMédia de Tempo por Resposta: ").append(this.statistics.meanTimePerAnswer())
                .append("\n\tDesvio Padrão: ").append(this.statistics.standardDeviationTimePerAnsewer());

        return builder.toString();
    }

    /**
     * Método de acesso getter para obter o scoreStrategy
     * @return scoreStrategy
     */
    @Override
    public IScoreStrategy getScoreStrategy() {
        return this.scoreStrategy;
    }

    /**
     * Método de acesso setter para atribuir um scoreStrategy
     * @param iss scoreStrategy a atribuir
     */
    @Override
    public void setScoreStrategy(IScoreStrategy iss) {
        this.scoreStrategy = iss;
    }

    /**
     * Método que calcula a pontuação do array de questões
     * @return pontuação do array
     */
    @Override
    public String calculateScore() {
        //questions = iqs passado por parâmetro em ScoreStrategy
        IScoreStrategy is= new ScoreStrategy(this);
        this.setScoreStrategy(is);
        return scoreStrategy.CalculateScore(questions);
    }

    /**
     * Método de acesso getter para obter questões
     * @return questions
     */
    public IQuestion[] getQuestions() { 
        return questions;
    }

    /**
     * Método de acesso setter para atribuir questões
     * @param questions questões a atribuir
     */
    public void setQuestions(IQuestion[] questions) {
        this.questions = questions;
    }
}
