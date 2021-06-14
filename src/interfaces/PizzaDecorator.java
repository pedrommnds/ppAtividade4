package interfaces;

public abstract class PizzaDecorator implements PizzaComponent {
	
    public final void setDecorated(PizzaComponent decorated) {
        this.decorated = decorated;        
    };
    protected PizzaComponent decorated;
}
