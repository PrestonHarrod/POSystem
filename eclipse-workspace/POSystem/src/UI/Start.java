package UI;
import javax.swing.JFrame;

import PD.Store;
import TEST.StoreTest;

public class Start {

	public static void main(String[] args) {
		
		StoreTest test = new StoreTest();
		test.itemTest();
		test.cashierTest();
		test.registerTest();
		test.SessionTest();
		
		
//		Store myStore = new Store();
//		myStore.loadStore();
//		POSJFrame things = new POSJFrame(myStore);
//		things.open(myStore);
		//System.out.print(myStore.toString());
		/*
		JFrame jFrame = new POSJFrame(myStore);
		jFrame.pack();
		jFrame.setVisible(true);
		*/
		
	}

}
