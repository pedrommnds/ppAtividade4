package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class AzeitonaDecorator extends PizzaDecorator {
	
    public AzeitonaDecorator(PizzaComponent decorated) {
       this.decorated = decorated;
    };
    
    public void preparar() {
        decorated.preparar();
        System.out.println("Colocando a azeitona");
    };
}
