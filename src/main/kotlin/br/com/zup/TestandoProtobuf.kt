package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val request = FuncionarioRq
        .newBuilder()
        .setNome("Felipe Gadelha")
        .setCpf("123.123.123-00")
        .setSalario(2000.00)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRq.Endereco.newBuilder()
                .setLogradouro("Av Paulista")
                .setCep("00000-000")
                .setComplemento("Casa 20")
                .build())
        .build()

    println(request)
    request.writeTo(FileOutputStream("funcionario-rq.bin"))

    val request2 = FuncionarioRq
        .newBuilder()
        .mergeFrom(
            FileInputStream("funcionario-rq.bin")
        )
    request2.setCargo(Cargo.GERENTE)
        .build()
    println(request2)

}