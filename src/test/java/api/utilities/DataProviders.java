package api.utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

	//@Test
	@DataProvider(name="Data")
	public String[][] getData() throws IOException
	{
		String path="D:\\SeleniumPrograms\\PetStoreAutomation\\TestData11\\Test11.xlsx";
		XLUtility x=new XLUtility(path);
		int r=x.getRowCount("Sheet1");
		int c=x.getCellCount("Sheet1", 1);
		
		System.out.println(r+"  "+c);
		String[][] s=new String[r-1][c];
		
		for(int i=1;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				s[i-1][j]=x.getCellData("Sheet1", i, j);
			}
		}
		
	return s;
		
	}
	
	@DataProvider(name="username")
	public String[] getUserName() throws IOException
	{
		String path="D:\\SeleniumPrograms\\PetStoreAutomation\\TestData11\\Test11.xlsx";
		XLUtility x=new XLUtility(path);
		int r=x.getRowCount("Sheet1");
		
		String[] s=new String[r-1];
		
		for(int i=1;i<r;i++)
		{
			s[i-1]=x.getCellData("Sheet1", i, 1);
			System.out.println(s[i-1]);		
			}
		
		return s;
	}
}


