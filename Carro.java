public class Carro extends Veiculo {
    private int portas;
    private boolean automatico;

    public Carro(int id, String marca, String modelo, int ano, double precoCompra, double precoVenda,
                 int portas, boolean automatico, Tamanho tamanho) {
        super(id, marca, modelo, ano, precoCompra, precoVenda, tamanho);
        this.portas = portas;
        this.automatico = automatico;
    }

    @Override
    public String getTipo() { return "Carro"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Carro | Portas: %d | Automático: %s",
                portas, automatico ? "Sim" : "Não");
    }
}
