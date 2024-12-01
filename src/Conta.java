/**
 * @author jennifer
 *
 */
public class Conta {
    private int numero;
    private double saldo;
    private double limite;
    private double[] extrato;
    private int operacoes;

    public Conta(int numero, double saldoInicial) {
    this.numero = numero;
    this.saldo = saldoInicial;
    this.limite = 100;
    this.extrato = new double[10];
    this.operacoes = 0;
    }

    
    public int getNumero() {
        return numero;
    }
    
    public double getSaldo() {
        return saldo + limite;
    }

    
    public double getLimite() {
        return limite;
    }
    
    public boolean sacar(double valor) {
        if (valor <= 0 || valor > getSaldo()) {
            return false;
        }

        registrar(-valor);

        if (valor <= saldo) {
            saldo -= valor;
        } else {
            double resto = valor - saldo;
            saldo = 0;
            limite -= resto;
        }
        return true;
    }

   
    public boolean depositar(double valor) {
        if(valor <= 0) {
            return false;
        }
        registrar(valor);

        double limite2 = 100 - limite;
        if(valor >= limite2) {
            limite = 100;
            saldo += (valor - limite2);
        } else {
            limite += valor;
        }
        return true;
    }

    
    public boolean transferir(Conta destino, double valor) {
        if(valor <= 0 || !sacar(valor)) {
            return false;
        }
        destino.depositar(valor);
        return true;
    }


    public double[] verExtrato() {
        double[] extratoNovo = new double[operacoes];
        for (int i = 0; i < operacoes; i++) {
            extratoNovo[i] = extrato[i];
        }
        return extratoNovo;
    } 


    private void registrar(double valor) {
        if (operacoes < extrato.length) {
            extrato[operacoes++] = valor;
        }
    }

    @Override
    public String toString() {
        return "Conta{numero=" + numero + ", saldo=" + saldo + ", limite=" + limite + "}";
    }
}