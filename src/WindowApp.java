import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import net.miginfocom.swing.MigLayout;

public class WindowApp{

JFrame frame;

	public WindowApp() throws Exception {
		initialize();
	};

	private void initialize() throws Exception {
		File currentDir = new File("./plugins");
		String[] plugins = currentDir.list();
		URL[] jars = new URL[plugins.length];
		String[] values = new String[plugins.length];
		for (int i = 0; i < plugins.length; i++) {
			try {
				jars[i] = (new File("./plugins/" + plugins[i])).toURL();
				int index = plugins[i].indexOf("Decorator");
				String subString = plugins[i].substring(0, index);
				
				values[i] = subString;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultListModel<Object> listaPlugins = new DefaultListModel<Object>();
    	DefaultListModel<Object> listaSelecao = new DefaultListModel<Object>();
    	for (String value : values) {
    		listaPlugins.addElement(value);
    	}
    	frame.getContentPane().setLayout(new MigLayout("", "[173px][66px][173px]", "[110px][2px][84px][23px]"));

    	JList<Object> list = new JList<Object>();
    	list.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
    	list.setBounds(17, 41, 174, 194);
    	list.setModel(listaPlugins);

    	JScrollPane listScroller = new JScrollPane();
    	listScroller.setViewportView(list);
    	list.setLayoutOrientation(JList.VERTICAL);
    	frame.getContentPane().add(listScroller, "cell 0 0 1 3,grow");

    	JList<Object> list_1 = new JList<Object>();
    	list_1.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
    	list_1.setBounds(262, 41, 174, 194);
    	list_1.setModel(listaSelecao);

    	JScrollPane listScroller1 = new JScrollPane();
    	listScroller1.setViewportView(list_1);
    	list_1.setLayoutOrientation(JList.VERTICAL);
    	frame.getContentPane().add(listScroller1, "cell 2 0 1 3,grow");

    	JButton vaiButton = new JButton(">>>");
    	vaiButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			list.getSelectedValuesList().stream().forEach((data) -> {
    				listaSelecao.addElement(data);
    			});
    		}
    	});
    	frame.getContentPane().add(vaiButton, "cell 1 0,growx,aligny bottom");
    
    	JButton voltaButton = new JButton("<<<");
    	frame.getContentPane().add(voltaButton, "cell 1 2,growx,aligny top");
  
    	voltaButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			list_1.getSelectedValuesList().stream().forEach((data) -> {
    				listaSelecao.removeElement(data);
    			});
    		}
    	});
  
    	JButton makeButton = new JButton("Preparar");
    	makeButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
    				preparar(jars, listaSelecao);
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    		}
    	});
    	frame.getContentPane().add(makeButton, "cell 2 3,alignx right,growy");
	};
	
	private void preparar(URL[] jars, DefaultListModel<Object> lista) throws Exception {
		URLClassLoader ucl = new URLClassLoader(jars);
		Class[] cArg = new Class[1];
		cArg[0] = PizzaComponent.class;
		
		int tamanhoLista = lista.getSize();
		Class MetaDecorator = Class.forName("decorators." + ((String) lista.getElementAt(tamanhoLista - 1)) + "Decorator",true,ucl);
		PizzaDecorator decorator = (PizzaDecorator) MetaDecorator.getDeclaredConstructor(cArg).newInstance(new PizzaSimples());
		
		int count = tamanhoLista - 2;
		do {
			Class NewMetaDecorator = Class.forName("decorators." + ((String) lista.getElementAt(count)) + "Decorator",true,ucl);
			PizzaDecorator NewDecorator = (PizzaDecorator) NewMetaDecorator.getDeclaredConstructor(cArg).newInstance(decorator);
			decorator = NewDecorator;
			count--;
		}
		while(count >= 0);
		
		decorator.preparar();
	};
}
