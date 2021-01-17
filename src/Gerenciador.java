import java.util.Scanner;
import java.io.IOException;

import controlador.*;
import laboratorio.*;
import projeto.*;
import usuario.*;
import leitor.*;
import usuario.colaborador.*;

public class Gerenciador {
    public static void clearconsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void waitEnter(Scanner reader) {
        System.out.print("\nPressione Enter para continuar...");

        reader.nextLine();
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        Controlador controlador = new Controlador();
        Leitor leitor = new Leitor();

        boolean loop = true;

        clearconsole();
        Administrador administrador = controlador.cadastroAdministrador(reader, leitor);

        clearconsole();
        Laboratorio laboratorio = controlador.cadastroLaboratorio(reader, administrador, leitor);

        while(loop) {
            clearconsole();

            controlador.menuPrint(administrador);

            int option = leitor.optionReader(reader, "Digite a opção: ", 0, 10);

            clearconsole();
            switch (option) {
                case 1:
                    Colaborador novoColaborador = controlador.criarColaborador(reader, laboratorio, leitor);

                    if (novoColaborador != null) {
                        laboratorio.addColaborador(novoColaborador);
                    }

                    break;
                case 2:
                    Projeto novoProjeto = controlador.criarProjeto(reader, laboratorio, leitor);

                    if (novoProjeto != null) {
                        laboratorio.addProjeto(novoProjeto);
                    }

                    break;
                case 3:
                    controlador.alocarColaborador(reader, laboratorio, leitor);

                    break;
                case 4:
                    controlador.alterarStatus(reader, laboratorio, leitor);

                    break;
                case 5:
                    controlador.addPublicacao(reader, laboratorio, leitor);

                    break;
                case 6:
                    controlador.adicionarOrientacao(reader, laboratorio, leitor);

                    break;
                case 7:
                    Colaborador colaborador = controlador.buscarColaborador(reader, laboratorio, leitor);

                    if (colaborador != null) {
                        System.out.print(colaborador.stringRelatorio());
                    }

                    break;
                case 8:
                    Projeto projeto = controlador.buscarProjeto(reader, laboratorio, leitor);

                    if (projeto != null) {
                        controlador.mostrarInformacoesProjeto(projeto);
                    }

                    break;
                case 9:
                    controlador.mostrarLaboratorio(laboratorio);

                    break;
                case 10:
                    controlador.mostrarColaboradores(laboratorio);

                    break;
                case 0:
                    loop = false;

                    break;
                default:
                    loop = false;
            }

            waitEnter(reader);
        }

        reader.close();
    }
}
