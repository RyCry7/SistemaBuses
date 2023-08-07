/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author Richely
 */
public class Horario implements Comparable<Horario> {
    private String parada;
    private Time horaSumada;

    public Horario(String parada, Time horaSumada) {
        this.parada = parada;
        this.horaSumada = horaSumada;
    }

    public String getParada() {
        return parada;
    }

    public Time getHoraSumada() {
        return horaSumada;
    }

    @Override
    public int compareTo(Horario otroHorario) {
        if (this.horaSumada == null && otroHorario.getHoraSumada() == null) {
            return 0;
        } else if (this.horaSumada == null) {
            return -1;
        } else if (otroHorario.getHoraSumada() == null) {
            return 1;
        } else {
            return this.horaSumada.compareTo(otroHorario.getHoraSumada());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(parada, horaSumada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Horario horario = (Horario) obj;
        return Objects.equals(parada, horario.parada) &&
                Objects.equals(horaSumada, horario.horaSumada);
    }
}