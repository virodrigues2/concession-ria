public class Moto extends Veiculo {
    private int cilindradas;

    public Moto(int id, String marca, String modelo, int ano, double precoCompra, double precoVenda,
                int cilindradas, Tamanho tamanho) {
        super(id, marca, modelo, ano, precoCompra, precoVenda, tamanho);
        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() { return "Moto"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Moto | %dcc", cilindradas);
    }
}
