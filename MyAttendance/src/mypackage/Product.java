package mypackage;

public class Product 
{
	String name;
	String no;


	public Product()
	{
	 // TODO Auto-generated constructor stub
	}
	
	public Product(String no,String name)
	{
	 // TODO Auto-generated constructor stub
	 this.name = name;
	 this.no=no;
	}
public String getName() {
 return name;
}
public void setName(String name) {
 this.name = name;
}

public String getNo()
 {
	 return no;
 }

public void setNo(String no) 
	{
	 this.no = no;
	}


@Override
public String toString() 
{

 return this.no+" -  "+ this.name;
}
}
