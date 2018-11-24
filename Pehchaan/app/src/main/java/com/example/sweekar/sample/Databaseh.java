package com.example.sweekar.sample;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 15/4/18.
 */

public class Databaseh extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    Context context;
    // Database Name
    private static final String DATABASE_NAME = "Hawker";
    // Transaction table name
    private  static final String KEY_Transaction_Table="transactiontable";
    private static final String KEY_Time="timestamp";
    private static final String Key_Transaction_id="transaction_id";
    //private static final String KEY_HAWKER_ID="hawker_id";
    private static final String Key_Customer_id="customer_id";
    private static final String Key_Total_Price="total_price";


    //Product Table
    private static final String KEY_Transaction_Product_Table="transactionproducttable";
    //private static final String Key_Transaction_id="transaction_id";
    private static final String KEY_Product_Name="productname";

    //Hawker Table
    private static final String KEY_Hawker_Table="hawkertable";
    private static final String KEY_HAWKER_ID="hawker_id";
    private static final String Key_Name="hawkername";
    private static final String KEY_location="location";
    private static final String KEY_number="phonenumber";
    private static final String KEY_Gender="gender";
    private static final String KEY_Experience="experience";
    private static final String KEY_Estimated_Total_Customers="total_estimated_customers";
    private static final String KEY_Estimated_Repeat_Customers="total_estimated_repeat_customers";
    private static final String KEY_IS_Verified="IS_Verified";

    //Customer Table
//unused
    /*
    private static final String KEY_Customer_Table="Customertable";
    // private static final String Key_Customer_id="customer_id";
    private static final String Key_customer_Name="customername";
    private static final String KEY_customer_location="location";
    private static final String KEY_customer_number="phonenumber";
    */

    //Referral Table
    //private static final String KEY_HAWKER_ID="hawker_id";
    // private static final String Key_Customer_id="customer_id";
    private static final String KEY_Referral_Table="referraltable";
    private static final String KEY_ReferredBy="referredby";
    private static final String KEY_ReferredTo="referredto";
    private static final String referralno="referralno";







    public Databaseh(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Referral_Table="CREATE TABLE "+ KEY_Referral_Table+"("+
                referralno +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_HAWKER_ID+" TEXT,"+
                KEY_ReferredBy+" INTEGER,"+KEY_ReferredTo+" INTEGER "+")";
        db.execSQL(CREATE_Referral_Table);

        String CREATE_Transaction_Table="CREATE TABLE "+ KEY_Transaction_Table+"("+
                Key_Transaction_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_HAWKER_ID+" TEXT,"+KEY_Time+" TEXT,"+Key_Customer_id+" INTEGER,"+
                Key_Total_Price+" REAL"+")";
        db.execSQL(CREATE_Transaction_Table);

        String CREATE_Product_Table="CREATE TABLE "+ KEY_Transaction_Product_Table+"("+
                Key_Transaction_id+" INTEGER,"+KEY_HAWKER_ID+" TEXT,"+KEY_Product_Name+" Text"+
                ")";
        db.execSQL(CREATE_Product_Table);

        String CREATE_Hawker_Table="CREATE TABLE "+ KEY_Hawker_Table+"("+
                KEY_HAWKER_ID +" TEXT,"+Key_Name+" TEXT,"+KEY_location+" TEXT,"+KEY_number+
                " TEXT,"+KEY_IS_Verified+" INTEGER DEFAULT 0,"+KEY_Gender+" TEXT,"+KEY_Experience+" TEXT,"+KEY_Estimated_Total_Customers+" INTEGER,"+KEY_Estimated_Repeat_Customers+" INTEGER"+")";
        db.execSQL(CREATE_Hawker_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + KEY_Transaction_Table);// Create tables again
        onCreate(db);
    }

    public void addreferral(Referral referral)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="INSERT INTO "+KEY_Referral_Table+"("+KEY_HAWKER_ID+","+KEY_ReferredBy+","+
                KEY_ReferredTo+")" +
                " VALUES('"+referral.hawkerid+"',"+referral.referredby+","+referral.referredto+
                ")";
        db.execSQL(sql);
    }

    public int totalsalesunderpehchaan()
    {
        String selectQuery = "SELECT sum("+Key_Total_Price+") from "+KEY_Transaction_Table ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int newpehchaancustomerinaday(String date)
    {
        String selectQuery ="Select count(distinct(e.f)) from "+KEY_Transaction_Table+"" +
                " join (Select D.B as F from ("+ "SELECT substr("+KEY_Time+",13) AS A,"+
                Key_Customer_id+" AS B from "+KEY_Transaction_Table+") AS D GROUP BY D.B Having" +
                " count(distinct(D.A))=1) as e on e.F="+KEY_Transaction_Table+"."+
                Key_Customer_id+" where substr("+KEY_Time+",13) LIKE '"+date+"'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int salesfromrepeatcustomersinaday(String date) {
        String selectQuery ="Select sum("+Key_Total_Price +")from "+KEY_Transaction_Table+
                " join (Select D.B as F from ("+ "SELECT substr("+KEY_Time+",13) AS A,"
                +Key_Customer_id+" AS B from "+KEY_Transaction_Table+") AS D GROUP BY D.B " +
                "Having count(distinct(D.A))>1) as e on e.F="+KEY_Transaction_Table+"."+
                Key_Customer_id+" where substr("+KEY_Time+",13) LIKE '"+date+"'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int repeatcustomersinaday(String date)
    {
        String selectQuery ="Select count(distinct(e.f)) from "+KEY_Transaction_Table+" " +
                "join (Select D.B as F from ("+ "SELECT substr("+KEY_Time+",13) AS A,"+
                Key_Customer_id+" AS B from "+KEY_Transaction_Table+") AS D GROUP BY D.B" +
                " Having count(distinct(D.A))>1) as e on e.F="+KEY_Transaction_Table+"."+
                Key_Customer_id+" where substr("+KEY_Time+",13) LIKE '"+date+"'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int salesofnewpehchaancustomerinaday(String date)
    {
        String selectQuery ="Select sum("+Key_Total_Price +")from "+KEY_Transaction_Table+
                " join (Select D.B as F from ("+ "SELECT substr("+KEY_Time+",13) AS A,"+
                Key_Customer_id+" AS B from "+KEY_Transaction_Table+") AS D GROUP BY D.B Having" +
                " count(distinct(D.A))=1) as e on e.F="+KEY_Transaction_Table+"."+Key_Customer_id+
                " where substr("+KEY_Time+",13) LIKE '"+date+"'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }

    public int totalpehchaancustomers()
    {
        String selectQuery = "SELECT Count(distinct("+Key_Customer_id+")) from "+
                KEY_Transaction_Table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int totalsalesinaday(String date)
    {
        String selectQuery = "SELECT sum("+Key_Total_Price+") from "+KEY_Transaction_Table+
                " where "+KEY_Time+" LIKE '%"+ date+"%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public int totaltransactionsinaday(String date)
    {
        String selectQuery = "SELECT count(*"+") from "+KEY_Transaction_Table+" where "+
                KEY_Time+" LIKE '%"+ date+"%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public List<Referral> returnreferrals()
    {
        List<Referral> referrals=new ArrayList<Referral>() ;
        String selectQuery = "SELECT * FROM " + KEY_Referral_Table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Referral referral = new Referral();
                referral.referralno =cursor.getInt(0);
                referral.hawkerid=cursor.getString(1);
                referral.referredby=cursor.getInt(2);
                referral.referredto=cursor.getInt(3);
                referrals.add(referral);
            } while (cursor.moveToNext());
        }
        return referrals;
    }
    public void addTransaction(Transactionbackup transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="INSERT INTO "+KEY_Transaction_Table+"("+KEY_HAWKER_ID+","+KEY_Time+","+
                Key_Customer_id+","+Key_Total_Price+") VALUES('"+transaction.hawker_id+
                "','"+transaction.time+"',"+transaction.customer_id+","+
                transaction.totalprice+")";

        db.execSQL(sql);
    }
    public List<Transaction> getTransactions() {
        List<Transaction> transactionsList = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + KEY_Transaction_Table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.transaction_id =cursor.getInt(0);
                transaction.hawker_id=cursor.getString(1);
                transaction.time=cursor.getString(2);
                transaction.customer_id=cursor.getInt(3);
                transaction.totalprice=cursor.getFloat(4);
                transactionsList.add(transaction);
            } while (cursor.moveToNext());
        }
        return transactionsList;
    }
/*needs to be updated according to new hawker table

  public List<combineddatatobepushed> returningcombinedtable()
    {
        List<combineddatatobepushed> temp=new ArrayList<combineddatatobepushed>();
        String selectQuery = "SELECT "+KEY_Hawker_Table+"."+KEY_HAWKER_ID+" ,"+
                KEY_Hawker_Table+"."+Key_Name+" ,"+KEY_Hawker_Table+"."+KEY_location+
                " ,"+KEY_Hawker_Table+"."+KEY_number+",b."+KEY_Time+",b."+
                Key_Transaction_id+",b."+Key_Customer_id+",b."+Key_Total_Price+",b."+
                KEY_Product_Name+" FROM "+KEY_Hawker_Table+" JOIN " +
                "(Select * from "+KEY_Transaction_Product_Table+" join "+
                KEY_Transaction_Table+" on "+KEY_Transaction_Product_Table+"."+
                KEY_HAWKER_ID+"="+KEY_Transaction_Table+"."+KEY_HAWKER_ID+" AND "+
                KEY_Transaction_Product_Table+"."+Key_Transaction_id+"="+
                KEY_Transaction_Table+"."+Key_Transaction_id + ") as b on "+
                KEY_Hawker_Table+"."+KEY_HAWKER_ID+"=b."+KEY_HAWKER_ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                combineddatatobepushed Combined=new combineddatatobepushed();
                Combined.hawkerid =cursor.getString(0);
                Combined.hawkername=cursor.getString(1);
                Combined.area=cursor.getString(2);
                Combined.phonenumber=cursor.getString(3);
                Combined.time=cursor.getString(4);
                Combined.transaction_id=cursor.getInt(5);
                Combined.customer_id=cursor.getInt(6);
                Combined.totalprice=cursor.getFloat(7);
                Combined.productname=cursor.getString(8);
                temp.add(Combined);

            } while (cursor.moveToNext());
        }
        return temp;
    }
    */
    public void addProductintoProductTable(TransactionProducts temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="INSERT INTO "+KEY_Transaction_Product_Table+"("+Key_Transaction_id+","+
                KEY_HAWKER_ID+","+KEY_Product_Name+") VALUES("+temp.transactionid+",'"+
                temp.hawkerid+"','"+temp.productname+"')";
        db.execSQL(sql);
    }
    public List<TransactionProducts> getTransactionProducts() {
        List<TransactionProducts> transactionProductsList = new ArrayList<TransactionProducts>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + KEY_Transaction_Product_Table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TransactionProducts temp = new TransactionProducts();
                temp.transactionid =cursor.getInt(0);
                temp.hawkerid=cursor.getString(1);
                temp.productname=cursor.getString(2);
                transactionProductsList.add(temp);
            } while (cursor.moveToNext());
        }
        return transactionProductsList;
    }
    public int findTransaction(String time)
    {
        String query="Select * from " +KEY_Transaction_Table+" where "+KEY_Time+" LIKE '"+
                time+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return -1;
    }
    public void addHawker(Hawker hawker) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="INSERT INTO "+KEY_Hawker_Table+"("+KEY_HAWKER_ID+","+Key_Name+","+
                KEY_location+","+KEY_number+","+KEY_IS_Verified+","+KEY_Gender+
        ","+KEY_Experience+","+KEY_Estimated_Total_Customers+","+KEY_Estimated_Repeat_Customers+") VALUES('"+hawker.hawkerid+"','"+
                hawker.hawkername+"','"+hawker.area+"','"+hawker.phonenumber+"',"+hawker.isverified+",'"+hawker.gender+"','"+hawker.experiece+"',"+hawker.totalexpectedcustomers+","+hawker.repeatexpectedcustomers+")";
        db.execSQL(sql);
    }
  /*
  no longer needed
  public Hawker findHawker(String id)
    {
        String query="Select * from " +KEY_Hawker_Table+" where "+KEY_HAWKER_ID+
                " LIKE '"+id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
               return new Hawker(id,cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8));
            } while (cursor.moveToNext());
        }
        return null;
    }
    */
    public Hawker findHawker()
    {
        String query="Select * from " +KEY_Hawker_Table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return new Hawker(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8));
            } while (cursor.moveToNext());
        }
        return null;
    }
/*no longer needed
     public List<Hawker> findhawkers()
    {
        String query="Select * from " +KEY_Hawker_Table;
        List<Hawker> hawkersList = new ArrayList<Hawker>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Hawker temp = new Hawker();
                temp.hawkerid =cursor.getString(0);
                temp.hawkername=cursor.getString(1);
                temp.area=cursor.getString(2);
                temp.phonenumber=cursor.getString(3);

                hawkersList.add(temp);
            } while (cursor.moveToNext());
        }
        return hawkersList;
    }
*/

    public String gethawkerid()
    {
        String query="Select "+KEY_HAWKER_ID+" from "  +KEY_Hawker_Table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return null;
    }



    public void updateHawker(Hawker updated){
    String query="Update "+KEY_Hawker_Table+
        " SET "+Key_Name+"='"+updated.hawkername+"',"+KEY_location+ "='"+
            updated.area+"',"+KEY_number+"='"+updated.phonenumber+"',"+KEY_Gender+"='"+updated.gender+"',"+KEY_Experience+"='"+updated.experiece+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
    }
    public Float totalearnings(int customerid)
    {
        String query="Select sum("+Key_Total_Price+") from "  +
                KEY_Transaction_Table+" where "+Key_Customer_id+"="+customerid;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getFloat(0);
            } while (cursor.moveToNext());
        }
        float a=0;
        return a;

    }


    public int findfrequencyofvisit(int customerid)
    {
        String query="Select count(*)"+ " from "  +KEY_Transaction_Table+" where "+
                Key_Customer_id+"="+customerid;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
                if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    /*
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_User;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int tobereturned=cursor.getCount();
        cursor.close();

        // return count
        return tobereturned;
    }

    public int findInteraction(String time)
    {
        String query="Select * from " +TABLE_Interaction+" where "+KEY_time+" LIKE '"+time+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;

    }
    /*
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        String selectQuery = "SELECT * FROM " + TABLE_Product;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.productid =cursor.getInt(0);
                product.name =cursor.getString(1);
                product.sellingprice=cursor.getFloat(2);
                product.baseprice=cursor.getFloat(3);
                product.quantity=cursor.getFloat(4);
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }



    public List<User> getallUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_Interaction;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.id=cursor.getString(0);
                user.time=cursor.getString(1);
                user.age=cursor.getString(2);
                user.Gender=cursor.getString(3);
                user.incometypeOfCustomer=cursor.getString(4);
                user.TypeofCustomer=cursor.getString(5);
                user.InteractionTime=cursor.getString(6);
                user.ModeofTravel=cursor.getString(7);
                user.SoldOrNot=cursor.getString(8);
                user.ownbag=cursor.getString(9);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }
    public int findProduct(String name)
    {
        String query="Select * from " +TABLE_Product+" where "+KEY_name+" LIKE '"+name+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }
    public List<FinalTable> findfinalTable() {
        List<FinalTable> finalTablesList = new ArrayList<FinalTable>();
        String selectQuery = "SELECT " +TABLE_Interaction+"."+KEY_Interaction_ID+","+TABLE_Transaction+"."+KEY_Transaction_productid+","+TABLE_Transaction+"."+KEY_Transaction_price+","+TABLE_Transaction+"."+KEY_Transaction_quantity+"," +TABLE_Interaction+"."+KEY_time+"," +TABLE_Interaction+"."+KEY_age+"," +TABLE_Interaction+"."+KEY_gender+"," +TABLE_Interaction+"."+KEY_incometypeOfCustomer+"," +TABLE_Interaction+"."+KEY_TypeOfCustomer+"," +TABLE_Interaction+"."+KEY_SoldOrNot+"," +TABLE_Interaction+"."+KEY_ModeofTravel+"," +TABLE_Interaction+"."+KEY_ownbag+","+TABLE_Transaction+"."+KEY_Transaction_base_price+","+TABLE_Transaction+"."+KEY_Transaction_expected_selling_price+","+TABLE_Transaction+"."+KEY_name+ " FROM " + TABLE_Interaction +" INNER JOIN "+TABLE_Transaction+" on "+TABLE_Interaction+"."+KEY_Interaction_ID+"="+TABLE_Transaction+"."+KEY_Interaction_ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FinalTable table = new FinalTable();
                table.interaction_id=cursor.getString(0);
                table.product_id=cursor.getInt(1);
                table.sellingprice=cursor.getFloat(2);
                table.quantity=cursor.getFloat(3);
                table.time=cursor.getString(4);
                table.age=cursor.getString(5);
                table.Gender=cursor.getString(6);
                table.incometypeOfCustomer=cursor.getString(7);
                table.TypeofCustomer=cursor.getString(8);
                table.SoldOrNot=cursor.getString(9);
                table.ModeofTravel=cursor.getString(10);
                table.ownbag=cursor.getString(11);
                table.baseprice=cursor.getFloat(12);
                table.expectedsellingprice=cursor.getFloat(13);
                table.name=cursor.getString(14);
                if(table.name.equalsIgnoreCase("banana"))
                {
                    table.expectedprofit = table.expectedprofit+((table.expectedsellingprice/12*table.quantity)-(table.baseprice/12*table.quantity));
                    table.actualprofit = table.actualprofit+(table.sellingprice-(table.baseprice/12*table.quantity));



                }
                else {
                    table.expectedprofit = table.expectedprofit+((table.expectedsellingprice*table.quantity)-(table.baseprice*table.quantity));
                    table.actualprofit = table.actualprofit+(table.sellingprice-(table.baseprice*table.quantity));
                }
                finalTablesList.add(table);
            } while (cursor.moveToNext());
        }
        return finalTablesList;
    }
    */
    /*
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_User, KEY_NAME + " = ?",
                new String[] { String.valueOf(user.getName()) });
        db.close();
    }*/
/*
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_time, user.time);
        values.put(KEY_gender, user.Gender);
        values.put(KEY_incometypeOfCustomer, user.incometypeOfCustomer);
        values.put(KEY_TypeOfCustomer, user.TypeofCustomer);
        values.put(KEY_InteractionTime, user.InteractionTime);
        values.put(KEY_ModeofTravel, user.ModeofTravel);
        values.put(KEY_SoldOrNot, user.SoldOrNot);
        values.put(KEY_ownbag, user.ownbag);
        db.insert(TABLE_Interaction, null, values);
        db.close(); // Closing database connection
    }*/
}
