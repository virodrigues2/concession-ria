public abstract class Veiculo {
    protected int id;
    protected String marca;
    protected String modelo;
    protected int ano;
    protected double precoCompra;
    protected double precoVenda;
    protected Tamanho tamanho;

    public Veiculo(int id, String marca, String modelo, int ano, double precoCompra, double precoVenda, Tamanho tamanho) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.tamanho = tamanho;
    }

    public int getId() { return id; }
    public double getPrecoCompra() { return precoCompra; }
    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    public Tamanho getTamanho() { return tamanho; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("ID:%d | %s %s (%d) | Compra: R$%.2f | Venda: R$%.2f | Tamanho: %s",
                id, marca, modelo, ano, precoCompra, precoVenda, tamanho);
    }
}
