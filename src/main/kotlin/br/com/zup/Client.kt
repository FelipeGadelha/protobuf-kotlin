package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()
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

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)
    println(response)

}