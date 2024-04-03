package DM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import PD.*;

public class GetData {
	
	public static void loadData(Store store) {
	
	String fileName = "src/StoreData_v2019.csv";
	String line = null;
	String[] token;
	String dataRowClass;
	
	try {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		Session session = new Session();
		Sale sale = new Sale();
		
		while ((line = bufferedReader.readLine()) != null)
		{
			token = line.split(",");
			dataRowClass = token[0];
			boolean saleMade = false;
			
			if (dataRowClass.equals("Store"))
			{
				store.setName(token[1]);
			}
			else if (dataRowClass.equals("TaxCategory"))
			{
				TaxCategory taxCategory = new TaxCategory(token[1]);
				BigDecimal y = new BigDecimal(token[2]);
				LocalDate x = LocalDate.parse(token[3], DateTimeFormatter.ofPattern("M/d/yy"));
				TaxRate taxRate = new TaxRate(x, y);
				
				taxCategory.addTaxRate(taxRate);
				store.addTaxCategory(taxCategory);
			}
			else if (dataRowClass.equals("Cashier"))
			{
				Person person = new Person(token[2], token[4], token[5], token[6], token[7], token[8], token[3]);
				Cashier cashier = new Cashier(token[1], person, token[9]);
				
				store.addCashier(cashier);
			}
			else if (dataRowClass.equals("Item"))
			{
				Item item = new Item(token[1], token[3]);
				item.setTaxCategory(store.findTaxCategoryByName(token[4]));
				UPC upc = new UPC(token[2]);
				Price price = new Price(token[5], token[6]);
				PromoPrice promoPrice = new PromoPrice();
				if (token.length == 10)
				{
					promoPrice = new PromoPrice(token[7], token[8], token[9]);
				}
				item.addPrice(price);
				item.addPrice(promoPrice);
				item.addUPC(upc);
				upc.setItem(item);
				store.addItem(item);
				store.addUPC(upc);
			}
			else if (dataRowClass.equals("Register"))
			{
				Register register = new Register(token[1]);
				
				store.addRegister(register);
			}
			else if (dataRowClass.equals("Session"))
			{
				session = new Session(store.findCashierForNumber(token[1]), store.findRegisterByNumber(token[2]));
				Cashier x = store.findCashierForNumber(token[1]);
				x.addSession(session);
				store.addSession(session);
			}
			else if (dataRowClass.equals("Sale"))
			{
				sale = new Sale(token[1]);
				saleMade = true;
			}
			else if (dataRowClass.equals("SaleLineItem"))
			{
				SaleLineItem sli = new SaleLineItem(sale, store.findItemForNumber(token[1]), Integer.parseInt(token[2]));
				sale.addSaleLineItem(sli);
			}
			else if (dataRowClass.equals("Payment"))
			{
				if (token[1].equals("Cash")) {
					BigDecimal x = new BigDecimal(token[3]);
					Cash cash = new Cash(token[2], new BigDecimal(token[3]));
					session.getSales().get(session.getSales().size() - 1).addPayment(cash);
				} 
				else if (token[1].equals("Credit"))
				{
					Credit credit = new Credit(token[4], token[5], token[6], new BigDecimal(token[2]));
					session.getSales().get(session.getSales().size() - 1).addPayment(credit);
				}
				else if (token[1].equals("Check"))
				{
					Check check = new Check(token[2], token[6], token[4], token[5]);
					session.getSales().get(session.getSales().size() - 1).addPayment(check);				}
			}
			if (saleMade)
			{
				session.addSale(sale);	
			}
			}
				bufferedReader.close();
		}
		

	catch(FileNotFoundException ex) 
    {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) 
    {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");  
    }
	}
}
