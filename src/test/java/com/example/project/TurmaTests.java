package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;


public class TurmaTests {
    @Test
	@DisplayName("Adicionar uma nota na turma")
	void addUmaNotaNaTurma() {
		Turma turma = new Turma();
        turma.addNota(10);
		assertEquals(1, turma.getQuantidadeNotas(), "Deve existir 1 nota na turma");
	}

    @Test
	@DisplayName("Adicionar duas notas na turma")
	void addsTwoNumbers() {
		Turma turma = new Turma();
        turma.addNota(10);
        turma.addNota(10);
		assertEquals(2, turma.getQuantidadeNotas(), "Deve existir 2 notas na turma");
	}

    @ParameterizedTest(name = "Calcular a media de um conjunto de notas.")
    @MethodSource("sumProvider")
    void calculaMedia(double mediaEsperada, double[] notas) {
		Turma turma = new Turma();
        for (double nota: notas) {
            turma.addNota(nota);
        }
        double media = turma.getMediaDasNotas();
		assertEquals(mediaEsperada, media, () -> "A media das notas " + notas + " deve ser " + mediaEsperada);
	}

    public static Stream<Arguments> sumProvider() {
        return Stream.of(
            Arguments.of(
                7.5, new double[]{10, 5, 7.5, 5, 10},
                10, new double[]{10, 10},
                5, new double[]{10, 0}
                ));
    }


    @ParameterizedTest(name = "Calcular a quantidade de alunos acima da media.")
    @MethodSource("quantidadeDeAlunosAcimaDaMediaProvider")
    void quantidadeDeAlunosAcimaDaMediaProvider(int quantidadeEsperada, double[] notas) {
		Turma turma = new Turma();
        for (double nota: notas) {
            turma.addNota(nota);
        }
        double quantidadeObtida = turma.getQuantidadeNotasMeioresQueAMedia();
		assertEquals(quantidadeEsperada, quantidadeObtida, 
        () -> "Quantidade de alunos acima da media obtidos: " + quantidadeObtida +  " Quantidade esperada: " + quantidadeEsperada);
	}


    public static Stream<Arguments> quantidadeDeAlunosAcimaDaMediaProvider() {
        return Stream.of(
            Arguments.of(
                2, new double[]{10, 5, 7.5, 5, 10},
                0, new double[]{10, 10},
                1, new double[]{10, 0}
                ));
    }
}
