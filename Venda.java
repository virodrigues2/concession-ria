import java.time.LocalDate;
import java.time.LocalDate;

public class Venda {
    private Veiculo veiculo;
    private Cliente cliente;
    private LocalDate data;
    private double precoVenda;

    public Venda(Veiculo veiculo, Cliente cliente, LocalDate data, double precoVenda) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.data = data;
        this.precoVenda = precoVenda;
    }

    public double getLucro() {
        return precoVenda - veiculo.getPrecoCompra();
    }

    @Override
    public String toString() {
        return String.format("Venda - Data: %s | Veículo: [%s] | Cliente: %s | Preço Venda: R$%.2f",
                data.toString(), veiculo.toString(), cliente.getNome(), precoVenda);
    }
}
