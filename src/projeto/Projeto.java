package projeto;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import usuario.colaborador.*;
import publicacao.*;

public class Projeto {
    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String agenciaFinanciadora;
    private float valorFinanciado;
    private String objetivo;
    private String descricao;
    private String status;
    private ArrayList<Colaborador> participantes;
    private ArrayList<Publicacao> publicacoes;

    public Projeto(String titulo, LocalDate dataInicio, LocalDate dataTermino, String agenciaFinanciadora, float valorFinanciado, String objetivo, String descricao, Colaborador professor) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.status = "Em elaboração";

        this.participantes = new ArrayList<Colaborador>();
        this.participantes.add(professor);

        this.publicacoes = new ArrayList<Publicacao>();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return this.dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getAgenciaFinanciadora() {
        return this.agenciaFinanciadora;
    }

    public void setAgenciaFinanciadora(String agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
    }

    public float getValorFinanciado() {
        return this.valorFinanciado;
    }

    public void setValorFinanciado(float valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
    }

    public String getObjetivo() {
        return this.objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean alterarStatus() {
        if (this.status.equals("Em elaboração")) {
            boolean alunoCheck = true;

            for (int i = 0; i < this.participantes.size(); i++) {
                if (this.participantes.get(i) instanceof Aluno) {
                    Aluno aluno = (Aluno)this.participantes.get(i);

                    if (aluno.getTipo().equals("Aluno de Graduação")) {
                        if (aluno.getProjetosByStatus("Em andamento").size() == 2) {
                            alunoCheck = false;
                        }
                    }
                }
            }

            if (alunoCheck) {
                this.status = "Em andamento";

                return true;
            }
        } else if (this.status.equals("Em andamento") && !this.publicacoes.isEmpty()) {
            this.status = "Concluído";

            return true;
        }

        return false;
    }

    public ArrayList<Colaborador> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(ArrayList<Colaborador> participantes) {
        this.participantes = participantes;
    }

    public String stringParticipantes() {
        String participantesResult = "(";

        for (int i = 0; i < this.participantes.size(); i++) {
            if (i == (this.participantes.size() - 1)) {
                participantesResult += this.participantes.get(i).getNome() + ")";
            } else {
                participantesResult += this.participantes.get(i).getNome() + ", ";
            }
        }

        return participantesResult;
    }

    public void addParticipante(Colaborador participante) {
        if (!this.participantes.contains(participante)) {
            this.participantes.add(participante);
        }
    }

    public void removeParticipante(Colaborador participante) {
        if (this.participantes.contains(participante)) {
            this.participantes.remove(participante);
        }
    }

    public ArrayList<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public void addPublicacao(Publicacao publicacao) {
        if (!this.publicacoes.contains(publicacao)) {
            this.publicacoes.add(publicacao);
        }
    }

    public void removePublicacao(Publicacao publicacao) {
        if (this.publicacoes.contains(publicacao)) {
            this.publicacoes.remove(publicacao);
        }
    }

    public String toString(String position) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "    [" + position + "] Título: " + this.titulo + "\n"
             + "    Data de Ínicio: " + this.dataInicio.format(formatter) + "\n"
             + "    Data de Termino: " + this.dataTermino.format(formatter) + "\n"
             + "    Agência Financiadora: " + this.agenciaFinanciadora + "\n"
             + "    Valor Financiado: R$ " + this.valorFinanciado + "\n"
             + "    Objetivo: " + this.objetivo + "\n"
             + "    Descrição: " + this.descricao + "\n"
             + "    Status: " + this.status + "\n"
             + "    Participantes: " + stringParticipantes();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "    Título: " + this.titulo + "\n"
             + "    Data de Ínicio: " + this.dataInicio.format(formatter) + "\n"
             + "    Data de Termino: " + this.dataTermino.format(formatter) + "\n"
             + "    Agência Financiadora: " + this.agenciaFinanciadora + "\n"
             + "    Valor Financiado: R$ " + this.valorFinanciado + "\n"
             + "    Objetivo: " + this.objetivo + "\n"
             + "    Descrição: " + this.descricao + "\n"
             + "    Status: " + this.status + "\n"
             + "    Participantes: " + stringParticipantes();
    }
}
