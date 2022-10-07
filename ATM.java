package com.task;

import java.util.*;
import java.io.*;
class Account implements Serializable{
protected int A_no;
protected String A_hlr;
int P_no;
int Bal;
public Account(int A_no, String A_hlr, int P_no, int Bal) {
super();
this.A_no = A_no;
this.A_hlr = A_hlr;
this.P_no = P_no;
this.Bal = Bal;
}
public int getA_no() {
return A_no;
}
public void setA_no(int A_no) {
this.A_no = A_no;
}
public String getA_hlr() {
return A_hlr;
}
public void setA_hlr(String A_hlr) {
this.A_hlr = A_hlr;
}
public int getP_no() {
return P_no;
}
public void setP_no(int P_no) {
this.P_no = P_no;
}
public int getBal() {
return Bal;
}
public void setBal(int Bal) {
this.Bal = Bal;
}
@Override
public String toString() {
return  A_no + "\t\t" + A_hlr + "\t "
+ P_no + "\t" + Bal ;
}


}
class BankDatabase implements java.io.Serializable{

static List<Account>acc=new ArrayList<>();
public BankDatabase(){
Account a=new Account(101,"Suresh",2343,25234);
Account b=new Account(102,"Ganesh",5432,34123);
Account c=new Account(103,"Magesh",7854,26100);
Account d=new Account(104,"Naresh",2345,80000);
Account e=new Account(105,"Harish",1907,103400);
acc.add(a);
acc.add(b);
acc.add(c);
acc.add(d);
acc.add(e);
}



@Override
public String toString() {
return super.toString();
}



public static void   cusdetails() {
System.out.println("Account_num\t Name\t pin \tBal");
for(Account a:acc)
{
System.out.println(a.toString());
}

}
public static void checkBal(int accnum,int pin) {
for(Account a:acc) {
if(a.A_no==accnum) {
System.out.println(a.getBal());
break;
}
}
}
public static void  withdraw(int accnumber,int amount,int pin) {
for(Account a:acc) {
if(a.A_no==accnumber) {
if(a.Bal>amount) {
a.Bal=a.Bal-amount;
System.out.println("after withdraw"+a.Bal);
}
else {
System.out.println("Insufficient Bal ");
}
}
}
}
public static boolean checkpin(int accnum,int pin) {
for(Account a:acc) {
if(a.A_no==accnum&&a.P_no==pin) {
         return true;
}
}
return false;}

public static void transfer(int accnum,int totransfer,int amount) {
int flag=0;
for(Account a:acc) {
if(a.A_no==accnum) {
if(a.Bal>amount) {
a.Bal=a.Bal-amount;
     flag=1;
}

else {
System.out.println("Insufficient Bal ");
}
}
               }
if(flag==1) {
for(Account b:acc) {
if(b.A_no==totransfer) {
b.Bal=b.Bal+amount;}
                        }
                       }
}
   public static void serial()  {
  try {
 
  FileOutputStream fil = new FileOutputStream("C:\\Users\\Anguraj\\OneDrive\\Documents\\Anguraj\\20L104.txt");
           ObjectOutputStream out = new ObjectOutputStream(fil);
           out.writeObject(acc);
           out.close();
           fil.close();
           System.out.println("\nSerialization Successful...\n");
  }
  catch(Exception e) {
  System.out.println(e);
  }
   }
  public static void deserial() throws ClassNotFoundException {
 try {
          FileInputStream fileIn = new FileInputStream("C:\\Users\\Anguraj\\OneDrive\\Documents\\Anguraj\\20L104.txt");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          System.out.println("Deserialized Data: \n" + in.readObject().toString());
          in.close();
          fileIn.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }


}


public class Demo {

public static void main(String[] args) throws ClassNotFoundException {
// TODO Auto-generated method stub
Scanner sc =new Scanner(System.in);
BankDatabase bank=new BankDatabase();
   while(true) {
 
  System.out.println("Enter 1 to load cash");
  System.out.println("Enter 2 to view customerdetails");
  System.out.println("Enter 3 to transfer or withdraw or checkBal");
  System.out.println("Enter the option:");
  int option=sc.nextInt();
  switch(option) {
   case 1:
    System.out.println("Enter the Denominations");
     System.out.println("2000 = ");
     int d1=sc.nextInt();
     System.out.println("500 = ");
     int d2=sc.nextInt();
     System.out.println("100 = ");
     int d3=sc.nextInt();
   
    break;
   case 2:
    bank.cusdetails() ;
    bank.serial();
    bank.deserial();
    break;
   case 3:
    System.out.println("1 to check Bal:");
    System.out.println("2 to Withdraw:");
    System.out.println("3 to transfer:");
    System.out.println("Enter the enquiry option:");
    int enquiry=sc.nextInt();
    System.out.println("Enter the acc num:");
    int accnum=sc.nextInt();
    System.out.println("Enter the pin:");
    int pin=sc.nextInt();
    if(bank.checkpin(accnum,pin)) {
           switch(enquiry) {
            case 1:
            System.out.println("BAL IS:");
            bank.checkBal(accnum,pin);
            bank.serial();
            bank.deserial();
            break;
            case 2:
            System.out.println("Enter the amount to withdraw:");
            int amount=sc.nextInt();
            bank.withdraw(accnum,amount,pin);
            bank.serial();
            bank.deserial();
            break;
            case 3:
            System.out.println("Enter the accont number to transfer: ");
            int totransfer=sc.nextInt();
            System.out.println("Enter the amount  to transfer: ");
            int amounttotransfer=sc.nextInt();
            bank.transfer(accnum,totransfer,amounttotransfer);
            bank.serial();
            bank.deserial();
            break;
           }
    }
    else {
    System.out.println("incorect pin");
    }
           
           break;
   case 4:
        //bank.getATMBal(accnum);
   
        break;
  		}
   	}
}
}
