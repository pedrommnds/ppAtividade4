package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class PresuntoDecorator extends PizzaDecorator {
	
    public PresuntoDecorator(PizzaComponent decorated) {
        this.decorated = decorated;
    };
    
    public void preparar() {
        decorated.preparar();
        System.out.println("Colocando o presunto");
    };
    
	@Override
	public String toString() {
		return "Presunto";
	};
}
