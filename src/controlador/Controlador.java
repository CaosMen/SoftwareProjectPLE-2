package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import laboratorio.*;
import orientacao.*;
import publicacao.*;
import projeto.*;
import usuario.*;
import leitor.*;
import usuario.colaborador.*;

public class Controlador {
    /* Exibição Menu */

    public void menuPrint(Administrador administrador) {
        System.out.println("Bem vindo " + administrador.getNome() + "!\n");

        System.out.println("Escolha uma das opções abaixo:\n\n"
                         + "Digite (1) para adicionar um Colaborador\n"
                         + "Digite (2) para criar um Projeto\n"
                         + "Digite (3) para alocar um colaborador a um Projeto\n"
                         + "Digite (4) para alterar o status de um Projeto\n"
                         + "Digite (5) para adicionar uma Publicação\n"
                         + "Digite (6) para adicionar uma Orientação\n"
                         + "Digite (7) para fazer a consulta de um Colaborador\n"
                         + "Digite (8) para fazer a consulta de um Projeto\n"
                         + "Digite (9) para gerar o relatório do Laboratório\n"
                         + "Digite (10) para listar todos os Colaboradores\n"
                         + "Digite (0) para Sair!\n");
    }

    /* Criação do Administrador e Laboratório */

    public Administrador cadastroAdministrador(Scanner reader, Leitor leitor) {
        System.out.println("Bem vindo Administrador!\n");

        String nome = leitor.stringReader(reader, "Digite o seu nome: ");
        String email = leitor.regexValidatorReader(reader, "Digite o seu e-mail: ", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        String login = leitor.stringReader(reader, "Digite o seu login: ");
        String senha = leitor.stringReader(reader, "Digite a sua senha: ");

        return new Administrador(nome, email, login, senha);
    }

    public Laboratorio cadastroLaboratorio(Scanner reader, Administrador administrador, Leitor leitor) {
        System.out.println("Bem vindo " + administrador.getNome() + "!\n");

        String nome = leitor.stringReader(reader, "Digite o nome do Laboratório: ");

        return new Laboratorio(nome, administrador);
    }

    public void mostrarLaboratorio(Laboratorio laboratorio) {
        System.out.println("Relatório de produção acadêmica do Laboratório:\n");

        System.out.println("Número de colaboradores: " + laboratorio.getColaboradores().size());
        System.out.println("Número de projeto em elaboração: " + this.getProjetosByStatus("Em elaboração", laboratorio));
        System.out.println("Número de projeto em andamento: " + this.getProjetosByStatus("Em andamento", laboratorio));
        System.out.println("Número de projeto concluídos: " + this.getProjetosByStatus("Concluído", laboratorio));
        System.out.println("Número total de projeto: " + laboratorio.getProjetos().size());
        System.out.println("Número total de Publicações: " + this.getAllPublicacoes(laboratorio).size());
        System.out.println("Número total de Orientação: " + this.getAllOrientacoes(laboratorio).size());
        System.out.println("Número total de Produções Acadêmicas: " + (this.getAllPublicacoes(laboratorio).size() + this.getAllOrientacoes(laboratorio).size()));
    }

    /* Colaborador */

    public Colaborador criarColaborador(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Criação de Colaborador!\n");

        String nome = leitor.stringReader(reader, "Digite o nome do Colaborador: ");

        if (laboratorio.procurarColaborador(nome) == null) {
            String email = leitor.regexValidatorReader(reader, "Digite o e-mail do Colaborador: ", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
            System.out.println("Escolha um tipo de Colaborador:\n");

            System.out.println("Digite (1) para o tipo Aluno de Graduação\n"
                             + "Digite (2) para o tipo Aluno de Mestrado\n"
                             + "Digite (3) para o tipo Aluno de Doutorado\n"
                             + "Digite (4) para o tipo Professor\n"
                             + "Digite (5) para o tipo Pesquisador\n");

            int tipo = leitor.optionReader(reader, "Colaborador tipo: ", 1, 5);

            Colaborador novoColaborador;

            if (tipo == 4) {
                novoColaborador = new Professor(nome, email);
            } else if (tipo == 5) {
                novoColaborador = new Pesquisador(nome, email);
            } else {
                novoColaborador = new Aluno(nome, email, tipo - 1);
            }

            System.out.println("\nColaborador Criado!");

            return novoColaborador;
        } else {
            System.out.println("\nJá existe um colaborador com este nome!");

            return null;
        }
    }

    public Colaborador buscarColaborador(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Consulta de Colaborador!\n");

        String nome = leitor.stringReader(reader, "Digite o nome do Colaborador: ");

        Colaborador colaborador = laboratorio.procurarColaborador(nome);

        if (colaborador != null) {
            return colaborador;
        } else {
            System.out.println("\nNão existe um colaborador com este nome!");

            return null;
        }
    }

    public void alocarColaborador(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Alocação de Colaborador!\n");

        String titulo = leitor.stringReader(reader, "Digite o título do projeto: ");

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            if (projeto.getStatus().equals("Em elaboração")) {
                String nome = leitor.stringReader(reader, "Digite o nome do Colaborador: ");

                Colaborador colaborador = laboratorio.procurarColaborador(nome);

                if (colaborador != null) {
                    addColaborador(projeto, colaborador);
                } else {
                    System.out.println("\nNão existe um colaborador com este nome!");
                }
            } else {
                System.out.println("\nO projeto não está 'Em elaboração' (A alocação deve ser permitida apenas quando o projeto estiver 'Em elaboração')");
            }
        } else {
            System.out.println("\nNão existe um projeto com este título!");
        }
    }

    /* Colaborador Funções */

    public void addColaborador(Projeto projeto, Colaborador colaborador) {
        projeto.addParticipante(colaborador);
        colaborador.addProjeto(projeto);

        System.out.println("\nColaborador alocado!");
    }

    public void mostrarColaboradores(Laboratorio laboratorio) {
        System.out.println("Lista de Colaboradores:\n");

        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();

        if (!colaboradores.isEmpty()) {
            for (int i = 0; i < colaboradores.size(); i++) {
                Colaborador colaborador = colaboradores.get(i);

                String tipo = "Colaborador";

                if (colaborador instanceof Aluno) {
                    Aluno aluno = (Aluno)colaborador;

                    tipo = aluno.getTipo();
                } else if (colaborador instanceof Pesquisador) {
                    tipo = "Pesquisador";
                } else if (colaborador instanceof Professor) {
                    tipo = "Professor";
                }
                
                System.out.println("    [" + (i + 1) + "] Nome: " + colaborador.getNome() + ", E-mail: " + colaborador.getEmail() + ", Tipo de colaborador: " + tipo);
            }
        } else {
            System.out.println("Sem colaboradores!");
        }
    }

    /* Projeto */

    public Projeto buscarProjeto(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Consulta de Projeto!\n");

        String titulo = leitor.stringReader(reader, "Digite o título do Projeto: ");

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            return projeto;
        } else {
            System.out.println("\nNão existe um projeto com este título!");

            return null;
        }
    }

    public Projeto criarProjeto(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Criação de Projeto!\n");

        String titulo = leitor.stringReader(reader, "Digite o título do Projeto: ");

        if (laboratorio.procurarProjeto(titulo) == null) {
            LocalDate dataInicio = leitor.dateReader(reader, "Digite a Data de Ínicio do Projeto (Formato: dd/MM/yyyy): ");

            LocalDate dataTermino = leitor.dateReader(reader, "Digite a Data de Termino do Projeto (Formato: dd/MM/yyyy): ");

            String agenciaFinanciadora = leitor.stringReader(reader, "Digite o nome da Agência Financiadora do Projeto: ");

            float valorFinanciado = Float.parseFloat(leitor.regexValidatorReader(reader, "Digite o valor financiado do Projeto em R$: ", "\\d+([,.]\\d+)?(;|$)"));

            String objetivo = leitor.stringReader(reader, "Digite o objetivo do Projeto: ");

            String descricao = leitor.stringReader(reader, "Digite a descrição do Projeto: ");

            boolean professorCreate = true;

            Colaborador professor = null;

            while (professorCreate) {
                String nomeProfessor = leitor.stringReader(reader, "Informe o nome do professor (Você deve alocar ao menos um professor ao novo projeto): ");

                Colaborador professorBusca = laboratorio.procurarColaborador(nomeProfessor);

                if (professorBusca != null) {
                    if (professorBusca instanceof Professor) {
                        boolean addOption = leitor.stringBoolReader(reader, "\nJá existe um professor cadastrado com esse nome, deseja adicioná-lo?");

                        if (addOption) {
                            professor = professorBusca;

                            professorCreate = false;
                        } else {
                            System.out.println("");
                        }
                    } else {
                        System.out.print("\nJá existe um colaborador com este nome (informe outro nome)\n\n");
                    }
                } else {
                    String emailProfessor = leitor.regexValidatorReader(reader, "Informe o e-mail do professor: ", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

                    professor = new Professor(nomeProfessor, emailProfessor);

                    laboratorio.addColaborador(professor);

                    professorCreate = false;
                }
            }

            Projeto novoProjeto = new Projeto(titulo, dataInicio, dataTermino, agenciaFinanciadora, valorFinanciado, objetivo, descricao, professor);

            professor.addProjeto(novoProjeto);

            System.out.println("\nProjeto Criado!");

            return novoProjeto;
        } else {
            System.out.println("\nJá existe um projeto com este título!");

            return null;
        }
    }

    public void alterarStatus(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Alterador de status!\n");

        String titulo = leitor.stringReader(reader, "Digite o título do Projeto: ");

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            if (projeto.getStatus().equals("Em elaboração")) {
                boolean statusOption = leitor.stringBoolReader(reader, "\nVocê deseja alterar o status 'Em elaboração' para 'Em andamento'?");

                if (statusOption) {
                    if (projeto.alterarStatus()) {
                        System.out.println("\nStatus alterado com sucesso!");
                    } else {
                        System.out.println("\nStatus não alterado!\n\nOs seguintes 'alunos de gradução' estão em dois projetos com o status 'Em andamento': ");
                        ArrayList<Colaborador> colaboradores = projeto.getParticipantes();
                        ArrayList<Aluno> alunosRemover = new ArrayList<Aluno>();

                        for (int i = 0; i < colaboradores.size(); i++) {
                            if (colaboradores.get(i) instanceof Aluno) {
                                Aluno aluno = (Aluno)colaboradores.get(i);
            
                                if (aluno.getTipo().equals("Aluno de Graduação") && aluno.getProjetosByStatus("Em andamento").size() == 2) {
                                    System.out.println("    [" + (i + 1) + "] Nome: " + aluno.getNome() + ", E-mail: " + aluno.getEmail());
                                    alunosRemover.add(aluno);
                                }
                            }
                        }

                        boolean deleteOption = leitor.stringBoolReader(reader, "\nDeseja remover estes alunos do projeto?");

                        if (deleteOption) {
                            for (int i = 0; i < alunosRemover.size(); i++) {
                                alunosRemover.get(i).removeProjeto(projeto);
                                projeto.removeParticipante(alunosRemover.get(i));
                            }

                            System.out.println("\nTente alterar novamente!");
                        }
                    }
                } else {
                    System.out.println("\nStatus não alterado!");
                }
            } else if (projeto.getStatus().equals("Em andamento")) {
                boolean statusOption = leitor.stringBoolReader(reader, "\nVocê deseja alterar o status 'Em andamento' para 'Concluído'?");

                if (statusOption) {
                    if (projeto.alterarStatus()) {
                        System.out.println("\nStatus alterado com sucesso!");
                    } else {
                        System.out.println("\nStatus não alterado! (Não existem publicações associadas ao projeto)");
                    }
                }
            } else {
                System.out.println("\nO projeto já foi concluído!");
            }
        } else {
            System.out.println("\nNão existe um projeto com este título!");
        }
    }

    /* Projeto Funções */

    public int getProjetosByStatus(String status, Laboratorio laboratorio) {
        int counter = 0;

        ArrayList<Projeto> projetos = laboratorio.getProjetos();

        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getStatus().equals(status)) {
                counter++;
            }
        }

        return counter;
    }

    public void mostrarInformacoesProjeto(Projeto projeto) {
        System.out.print("\n" + projeto);

        ArrayList<Publicacao> publicacoes = projeto.getPublicacoes();

        if (!publicacoes.isEmpty()) {
            System.out.println("\n\nProdução Acadêmica:");

            for (int i = 0; i < publicacoes.size(); i++) {
                System.out.println("\n    [" + (i + 1) + "] " + publicacoes.get(i).toString().replace("\n", "\n    "));
            }
        } else {
            System.out.println("");
        }
    }

    /* Publicação */

    public void addPublicacao(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        if (!laboratorio.getColaboradores().isEmpty()) {
            Projeto projeto = null;

            System.out.println("Adicionar uma Publicação!\n");

            String titulo = leitor.stringReader(reader, "Digite o título da Publicação: ");

            String conferencia = leitor.stringReader(reader, "Digite o nome da conferência onde foi Publicada: ");

            int ano = Integer.parseInt(leitor.regexValidatorReader(reader, "Digite o ano de Publicação (Formato: yyyy): ", "^[0-9]{4}$"));

            boolean optProjeto = leitor.stringBoolReader(reader, "A publicação tem algum projeto associado?");

            if (optProjeto) {
                boolean loop = true;

                while (loop) {
                    String projetoTitulo = leitor.stringReader(reader, "Digite o título do Projeto (Para cancelar digite 'sair'): ");

                    if (!projetoTitulo.equalsIgnoreCase("sair")) {
                        projeto = laboratorio.procurarProjeto(projetoTitulo);

                        if (projeto != null) {
                            if (projeto.getStatus().equals("Em andamento")) {
                                loop = false;
                                
                                System.out.println("\nProjeto associado!");
                            } else {
                                System.out.println("\nO Projeto não está 'Em andamento'!\n");
                            }
                        } else {
                            System.out.println("\nProjeto não encontrado!\n");
                        }
                    } else {
                        loop = false;

                        projeto = null;
                    }
                }
            }

            System.out.println("\nSeleção dos autores da Publicação: \n"
                             + "\nColaboradores:");

            ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();

            for (int i = 0; i < colaboradores.size(); i++) {
                System.out.println("    [" + (i + 1) + "] Nome: " + colaboradores.get(i).getNome() + ", E-mail: " + colaboradores.get(i).getEmail());
            }

            System.out.println("");
            String list = leitor.stringReader(reader, "Digite o número de no mínimo um colaborador para associá-lo a Publicação (Formato: 1, 2, 3, ...): ");

            String[] addColaboradores = list.replaceAll("\\s+","").split(",");

            ArrayList<Colaborador> autores = new ArrayList<Colaborador>();

            for (int i = 0; i < addColaboradores.length; i++) {
                if (addColaboradores[i].matches("^[0-9]{1,9}$")) {
                    int position = Integer.parseInt(addColaboradores[i]) - 1;

                    if (position >= 0 && position < colaboradores.size()) {
                        Colaborador colaborador = colaboradores.get(position);

                        autores.add(colaborador);
                    }
                }
            }

            Publicacao novaPublicacao;
            
            if (!autores.isEmpty()) {
                if (projeto == null) {
                    novaPublicacao = new Publicacao(titulo, conferencia, ano, autores);

                    for (int i = 0; i < autores.size(); i++) {
                        autores.get(i).addPublicacao(novaPublicacao);
                    }
                } else {
                    novaPublicacao = new Publicacao(titulo, conferencia, ano, autores, projeto);

                    for (int i = 0; i < autores.size(); i++) {
                        autores.get(i).addPublicacao(novaPublicacao);
                    }

                    projeto.addPublicacao(novaPublicacao);
                }

                System.out.println("\nPublicação Adicionada!");
            } else {
                System.out.println("\nPublicação não adicionada! (Sem autores)");
            }
        } else {
            System.out.println("Adicione no mínimo um colaborador para adicionar uma Publicação!");
        }
    }

    /* Publicação Funções */

    public ArrayList<Publicacao> getAllPublicacoes(Laboratorio laboratorio) {
        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();
        ArrayList<Publicacao> publicacoesResultado = new ArrayList<Publicacao>();

        for (int i = 0; i < colaboradores.size(); i++) {
            ArrayList<Publicacao> publicacoesColaborador = colaboradores.get(i).getPublicacoes();

            for (int j = 0; j < publicacoesColaborador.size(); j++) {
                if (!publicacoesResultado.contains(publicacoesColaborador.get(j))) {
                    publicacoesResultado.add(publicacoesColaborador.get(j));
                }
            }
        }

        return publicacoesResultado;
    }

    /* Orientação */

    public void adicionarOrientacao(Scanner reader, Laboratorio laboratorio, Leitor leitor) {
        System.out.println("Adicionar uma Orientação!\n");

        String nomeOrientador = leitor.stringReader(reader, "Digite o nome do Professor (Orientador): ");

        Colaborador orientador = laboratorio.procurarColaborador(nomeOrientador);

        if (orientador != null) {
            if (orientador instanceof Professor) {
                Professor professor = (Professor)orientador;
                
                String nomeOrientando = leitor.stringReader(reader, "Digite o nome do colaborador que vai ser orientado pelo professor " + professor.getNome() + ": ");

                Colaborador orientando = laboratorio.procurarColaborador(nomeOrientando);

                if (orientando != null) {
                    if (orientando instanceof Aluno) {
                        Aluno aluno = (Aluno)orientando;

                        String titulo = leitor.stringReader(reader, "Digite o titulo da Orientação: ");

                        int anoOrientacao = Integer.parseInt(leitor.regexValidatorReader(reader, "Digite o ano de início da Orientação (Formato: yyyy): ", "^[0-9]{4}$"));

                        Orientacao orientacao = new Orientacao(titulo, anoOrientacao, professor, aluno);

                        professor.addOrientacao(orientacao);

                        System.out.println("\nOrientação Adicionada!");
                    } else {
                        System.out.println("\nO orientando deve ser um Aluno!");
                    }
                } else {
                    System.out.println("\nNão foi encontrado nenhum colaborador com este nome!");
                }
            } else {
                System.out.println("\nO colaborador não é um professor!");
            }
        } else {
            System.out.println("\nNão foi encontrado nenhum colaborador com este nome!");
        }
    }

    /* Orientação Funções */

    public ArrayList<Orientacao> getAllOrientacoes(Laboratorio laboratorio) {
        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();
        ArrayList<Orientacao> orientacoesResultado = new ArrayList<Orientacao>();

        for (int i = 0; i < colaboradores.size(); i++) {
            if (colaboradores.get(i) instanceof Professor) {
                Professor professor = (Professor)colaboradores.get(i);

                ArrayList<Orientacao> orientacoesColaborador = professor.getOrientacoes();

                for (int j = 0; j < orientacoesColaborador.size(); j++) {
                    orientacoesResultado.add(orientacoesColaborador.get(j));
                }
            }
        }

        return orientacoesResultado;
    }
}
