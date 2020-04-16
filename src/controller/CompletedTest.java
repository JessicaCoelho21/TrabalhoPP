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

import Interfaces.ICompletedTests;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

public class CompletedTest implements ICompletedTests{
    private ITest[] tests;
    private static int numberTests = 0;
    private final int DEFAULT = 50;

    /**
     * Método construtor que atribui ao array um tamanho inicial por defeito
     */
    public CompletedTest() {
        this.tests = new ITest[this.DEFAULT];
    }

    /**
     * Método de acesso getter para obter o array de todos os testes existentes
     * @return array de testes
     */
    public ITest[] getTests() {
        return tests;
    }

    /**
     * Método de acesso setter para atribuir testes ao array
     * @param tests a atribuir
     */
    public void setTests(ITest[] tests) {
        this.tests = tests;
    }

    /**
     * Método de acesso getter para obter o número total de testes
     * @return número total de testes
     */
    public static int getNumberTests() {
        return numberTests;
    }

    /**
     * Método de acesso setter para atribuir um número total de testes
     * @param numberTests número de testes a atribuir
     */
    public static void setNumberTests(int numberTests) {
        CompletedTest.numberTests = numberTests;
    }
    
     /**
     * Método para adicionar um teste ao array
     * @param t questão a adicionar
     * @return true se a questão for adicionada com sucesso, false caso contrário
     * @throws TestException 
     */
    public boolean addTest(ITest t) throws TestException {
        if (t == null) { // Lança uma exceção se o objeto for nulo
            throw new TestException();
        }

        if (this.numberTests == this.tests.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //É atribuída a questão passada por parâmetro ao array na posição numberObjects, que é incrementado
        this.tests[numberTests] = t;
        numberTests++;

        return true;
    }
   
    /**
     * Método para obter um teste do array a partir da sua posição
     * @param pos posição onde o teste se encontra
     * @return o teste na posição passada por parâmetro
     * @throws TestException 
     */
    public ITest getTest(int pos) throws TestException {
       try {
            if (pos < 0 || pos > this.numberTests) { 
                //Caso a posição não exista, lança exceção
                throw new ArrayIndexOutOfBoundsException();
            }
        } 
       
       catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        return this.tests[pos];
    }
     
    /**
     * Método para remover um teste a partir da posição onde este se encontra no array
     * @param i posição do teste no array
     * @return true se for removido com sucesso, false caso contrário
     */
    public boolean removeTest(int i) {
        if (i < CompletedTest.numberTests && i >= 0 && this.tests[i] != null) {
            for (int j = i; j < CompletedTest.numberTests - 1; j++) {
                this.tests[j] = this.tests[j + 1];
            }

            this.tests[CompletedTest.numberTests - 1] = null;

            if (this.tests[CompletedTest.numberTests - 1] == null) {
                CompletedTest.numberTests--;
                return true;
            } 
            
            else {
                return false;
            }
        }
        
        return false;
    }
    
    /**
     * Método que remove um teste do array dado o teste a remover
     * @param t teste a remover
     * @return true em caso de sucesso, falso caso contrário
     */
    public boolean removeTest(ITest t) {

        for (int i = 0; i < CompletedTest.numberTests - 1; i++) {
            //Se o teste existir no array
            if (this.tests[i].equals(t)) {
                this.tests[i] = this.tests[i + 1];
            }
        }

        this.tests[CompletedTest.numberTests - 1] = null;

        if (this.tests[CompletedTest.numberTests - 1] == null) {
            CompletedTest.numberTests--;
            return true;
        } 
        
        else {
            return false;
        }
    }
    
    /**
     * Método que retorna o número total de testes completos existentes no array
     * @return número de testes completos
     */
    public int numberTests() {
        //Number of objects = posições ocupadas do array
        return CompletedTest.numberTests;
    }
    
    /**
     * Os seguintes métodos organizam os testes consoante as respostas certas ou erradas
     */
    @Override
    public void sort() {
        ITest[] tmp = new ITest[this.numberTests()];

        for (int i = 0; i < this.numberTests(); i++) {
            tmp[i] = (ITest) this.tests[i];
        }

        this.quickSort(tmp, 0, this.numberTests() - 1);
        System.arraycopy(tmp, 0, this.tests, 0, this.numberTests());
    }
    
    private void quickSort(ITest[] tests, int min, int max) {
        int indexOfPartition;

        if (max - min > 0) {
            indexOfPartition = findPartition(tests, min, max);
            quickSort(tests, min, indexOfPartition - 1);
            quickSort(tests, indexOfPartition + 1, max);
        }
    }

    private int findPartition(ITest[] tests, int min, int max) {
        int left, right;
        ITest temp, partitionelement;
        int middle = (min + max) / 2;

        partitionelement = tests[middle];
        left = min;
        right = max;
        
        while (left < right) {
            while ((this.compareTo(tests[left], partitionelement)) < 0) {
                left++;
            }
            
            while ((this.compareTo(tests[right], partitionelement)) > 0) {
                right--;
            }

            if (left < right) {
                temp = tests[left];
                tests[left] = tests[right];
                tests[right] = temp;
            }
        }

        temp = tests[min];
        tests[min] = tests[right];
        tests[right] = temp;
        
        return right;
    }

    /**
     * Método que, tendo 2 testes (left e right), compara-os e vê qual deles tem mais respostas certas
     * @param left teste a comparar
     * @param right teste a comparar
     * @return 1 caso o teste left tenha mais respostas certas que o right
     *         0 caso ambos os testes tenham o mesmo número de respostas
     *         -1 caso o teste right tenha mais respostas certas que o left
     */
    @Override
    public int compareTo(ITest left, ITest right) {

        if (left.getTestStatistics().correctAnswer() > right.getTestStatistics().correctAnswer()) {
            return 1;
        }
        
        if (left.getTestStatistics().correctAnswer() == right.getTestStatistics().correctAnswer()) {
            return 0;
        }
        
        return -1;
    }
}
