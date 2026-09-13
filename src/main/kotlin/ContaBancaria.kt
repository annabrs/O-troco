import kotlin.system.exitProcess

class ContaBancaria(
    val titular: String,
    var saldo: Float = 100.5f
) {
    private val senhaCorreta = 3589

    // para validar a senha sem repetir código
    fun autenticar(): Boolean {
        print("Digite sua senha: ")
        val senha = readln().toIntOrNull()
        if (senha != senhaCorreta) {
            println("Senha incorreta!")
            return false
        }
        return true
    }

    fun verSaldo() {
        if (!autenticar()) {
            verSaldo()
            return
        }
        println("Seu saldo atual é: R$ $saldo")
    }

    fun verExtrato() {
        if (!autenticar()) {
            verExtrato()
            return
        }
        println("\n--- EXTRATO ---")
        println("- Padaria Pão Quentinho: R$ 15,00")
        println("- Supermercado: R$ 45,50")
        println("+ Depósito recebido: R$ 100,00")
        println("- Farmácia: R$ 20,00")
        println("----------------")
    }

    fun fazerSaque() {
        if (!autenticar()) {
            fazerSaque()
            return
        }

        print("Qual o valor para saque? ")
        val saque = readln().toFloatOrNull()

        if (saque == null || saque <= 0 || saque > saldo) {
            println("Operação não autorizada")
        } else {
            saldo -= saque
            println("Saque realizado com sucesso!")
            println("Seu saldo atual é: R$ $saldo")
        }
    }

    fun fazerDeposito() {
        print("Qual o valor para depósito? ")
        val deposito = readln().toFloatOrNull()

        if (deposito == null || deposito <= 0) {
            println("Operação não autorizada")
        } else {
            saldo += deposito
            println("Depósito realizado com sucesso!")
            println("Seu saldo atual é: R$ $saldo")
        }
    }

    fun fazerTransferencia() {
        if (!autenticar()) {
            fazerTransferencia()
            return
        }

        print("Informe o número da conta destino (apenas números): ")
        val contaInput = readln()

        if (contaInput.isEmpty() || !contaInput.all { it.isDigit() }) {
            println("Operação não autorizada")
            return
        }

        print("Qual o valor da transferência? ")
        val valor = readln().toFloatOrNull()

        if (valor == null || valor <= 0 || valor > saldo) {
            println("Operação não autorizada")
        } else {
            saldo -= valor
            println("Transferência para a conta $contaInput realizada com sucesso!")
            println("Seu saldo atual é: R$ $saldo")
        }
    }
}

// Objeto da conta que será inicializado no main
var minhaConta: ContaBancaria = TODO()//nao entendi 100% dessa parte sempre dava erro

fun main() {
    print("Qual é o seu nome? ")
    val nome = readln()

    // Criando a instância da classe ContaBancaria
    minhaConta = ContaBancaria(titular = nome)

    println("Olá ${minhaConta.titular} é um prazer ter você por aqui!")
    inicio()
}

fun inicio() {
    println("\nEscolha uma opção:")
    println("1 - Ver saldo")
    println("2 - Ver extrato")
    println("3 - Fazer saque")
    println("4 - Fazer depósito")
    println("5 - Fazer transferência")
    println("6 - Sair")

    val escolha = readln().toIntOrNull()

    when (escolha) {
        1 -> {
            minhaConta.verSaldo()
            inicio()
        }
        2 -> {
            minhaConta.verExtrato()
            inicio()
        }
        3 -> {
            minhaConta.fazerSaque()
            inicio()
        }
        4 -> {
            minhaConta.fazerDeposito()
            inicio()
        }
        5 -> {
            minhaConta.fazerTransferencia()
            inicio()
        }
        6 -> sair()
        else -> erro()
    }
}

fun erro() {
    println("Por favor, informe um número entre 1 a 6.")
    inicio()
}

fun sair() {
    print("Você deseja sair? (S/N) ")
    val confirma = readln().uppercase()

    when (confirma) {
        "S" -> {
            println("${minhaConta.titular}, foi um prazer ter você por aqui!")
            exitProcess(0)
        }
        "N" -> inicio()
        else -> sair()
    }
}