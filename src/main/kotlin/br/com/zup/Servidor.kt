package br.com.zup

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {
    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndPoint())
        .build()

    server.start()
    server.awaitTermination()
}

class FuncionarioEndPoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {

    override fun cadastrar(request: FuncionarioRq?, responseObserver: StreamObserver<FuncionarioRs>?) {

        println(request!!)

        var nome: String? = request?.nome
        if (!request.hasField(FuncionarioRq.getDescriptor().findFieldByName("nome"))){
            nome = "[???]"
        }
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()

        val criadoEm = Timestamp.newBuilder()
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()
        val response = FuncionarioRs
            .newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}