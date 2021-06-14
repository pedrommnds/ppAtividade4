package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class OreganoDecorator extends PizzaDecorator {
	
    public OreganoDecorator(PizzaComponent decorated) {
       this.decorated = decorated;
    };
    
    public void preparar() {
        decorated.preparar();
        System.out.println("Colocando o oregano");
    };
}
