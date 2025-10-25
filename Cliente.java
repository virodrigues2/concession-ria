public class Cliente {
    private int id;
    private String nome;
    private int idade;
    private Biotipo biotipo;

    public Cliente(int id, String nome, int idade, Biotipo biotipo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.biotipo = biotipo;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Biotipo getBiotipo() { return biotipo; }

    @Override
    public String toString() {
        return String.format("ID:%d | %s | %d anos | Biotipo: %s", id, nome, idade, biotipo);
    }
}
