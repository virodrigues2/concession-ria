public class VendaInvalidaException extends Exception {

    // Construtor sem parâmetros
    public VendaInvalidaException() {
        super("Venda inválida.");
    }

    // Construtor com mensagem personalizada
    public VendaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
