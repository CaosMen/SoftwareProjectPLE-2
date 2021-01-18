package projeto;

import java.util.ArrayList;
import java.util.Arrays;
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
    private int status = 0;
    private ArrayList<String> allStatus = new ArrayList<String>(Arrays.asList("Em elaboração", "Em andamento", "Concluído"));
    private ArrayList<Colaborador> participantes = new ArrayList<Colaborador>();
    private ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();

    public Projeto(String titulo, LocalDate dataInicio, LocalDate dataTermino, String agenciaFinanciadora, float valorFinanciado, String objetivo, String descricao, Colaborador professor) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;

        this.participantes.add(professor);
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
        return this.allStatus.get(this.status);
    }

    public void setStatus(String status) {
        this.status = allStatus.indexOf(status);
    }

    public boolean alterarStatusInicial() {
        if (this.status == 0) {
            boolean alunoCheck = true;

            for (int i = 0; i < this.participantes.size(); i++) {
                if (this.participantes.get(i) instanceof Aluno) {
                    Aluno aluno = (Aluno)this.participantes.get(i);

                    if (aluno.getTipo().equals("Aluno de Graduação") && aluno.getProjetosByStatus("Em andamento").size() == 2) {
                        alunoCheck = false;
                    }
                }
            }

            if (alunoCheck) {
                this.status = 1;

                return true;
            }
        }

        return false;
    }

    public boolean alterarStatusFinal() {
        if (this.status == 1 && !this.publicacoes.isEmpty()) {
            this.status = 2;

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
        StringBuilder bld = new StringBuilder("(");

        for (int i = 0; i < this.participantes.size(); i++) {
            bld.append(this.participantes.get(i).getNome());

            if (i == (this.participantes.size() - 1)) {
                bld.append(")");
            } else {
                bld.append(", ");
            }
        }

        return bld.toString();
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Título: " + this.titulo + "\n"
             + "Data de Ínicio: " + this.dataInicio.format(formatter) + "\n"
             + "Data de Termino: " + this.dataTermino.format(formatter) + "\n"
             + "Agência Financiadora: " + this.agenciaFinanciadora + "\n"
             + "Valor Financiado: R$ " + this.valorFinanciado + "\n"
             + "Objetivo: " + this.objetivo + "\n"
             + "Descrição: " + this.descricao + "\n"
             + "Status: " + this.allStatus.get(this.status) + "\n"
             + "Participantes: " + stringParticipantes();
    }
}
