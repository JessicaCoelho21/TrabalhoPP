/*
 * Nome: Jéssica Beatriz Silva Coelho
 * Número: 8170180
 * Turma: T3
 *
 * Nome: Vânia Sofia Fonseca Lemos
 * Número: 8170310
 * Turma: T2
 */

package trabalhorecursopp;

import interfaces.exceptions.TestException;
import views.TestWindow;
import controller.CompletedTest;
import controller.Test;
import interfaces.controller.ITest;

public class TrabalhoRecursoPP {

    public static void main(String[] args) throws TestException {
        
        System.out.println("O teste está a iniciar...\n");
        
        //Abre o ficheiro JSON
        ITest  demoTest = new Test();
        //TestMultipleChoice demoTest = new TestMultipleChoice();
        
        demoTest.loadFromJSONFile("content/teste_A.json");
        
        //Abre a interface gráfica
        TestWindow t = new TestWindow();
        t.startTest(demoTest);
        
        CompletedTest save = new CompletedTest();
    }
}
