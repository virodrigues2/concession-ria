public class Caminhao extends Veiculo {
    private double capacidadeCarga; // em toneladas
    private int eixos;

    public Caminhao(int id, String marca, String modelo, int ano, double precoCompra, double precoVenda,
                    double capacidadeCarga, int eixos, Tamanho tamanho) {
        super(id, marca, modelo, ano, precoCompra, precoVenda, tamanho);
        this.capacidadeCarga = capacidadeCarga;
        this.eixos = eixos;
    }

    @Override
    public String getTipo() { return "Caminhão"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Caminhão | Carga: %.1f t | Eixos: %d",
                capacidadeCarga, eixos);
    }
}
