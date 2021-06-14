import interfaces.PizzaComponent;

public class PizzaSimples implements PizzaComponent {
    @Override
    public void preparar() {
        System.out.println("Preparando massa + molho + queijo");
    };
}
	