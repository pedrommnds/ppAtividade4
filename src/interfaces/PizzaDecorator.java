package interfaces;

public abstract class PizzaDecorator implements PizzaComponent {
	
    public final void setDecorated(PizzaComponent decorated) {
        this.decorated = decorated;        
    };
    public abstract String toString();
    protected PizzaComponent decorated;
}
