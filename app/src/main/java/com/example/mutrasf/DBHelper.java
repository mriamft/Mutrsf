package com.example.mutrasf;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

// Mutraf DataBase
public class DBHelper extends SQLiteOpenHelper {

    // FoodTruck table and its columns
    public static final String TABLE_FOODTRUCK = "FoodTruck_Table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOODTRUCK_NAME = "FOODTRUCK_Name";
    public static final String COLUMN_FOODTRUCK_CATEGORY = "FOODTRUCK_Category";
    public static final String COLUMN_FOODTRUCK_DESCRIPTION= "FOODTRUCK_Decsribtion";
    //public static final String COLUMN_FOODTRUCK_PHOTO = "img";
    public static final String COLUMN_FOODTRUCK_PRICE = "FOODTRUCK_Price";
    public static final String COLUMN_CONTACT_PHONE = "Contact_phone";


    // PriceContactimg table and its columns -To have good performance when retrieving into PriceContactimg Page
    public static final String TABLE_WISHLIST = "Wishlist";
    public static final String COLUMN_WISHLIST_ID = "ID";
    public static final String COLUMN_FOODTRUCK_ID = "FoodTruckId"; // referring to the FoodTruck Id in FOODTRUCK table to know which food truck is in PriceContactimg
    public static final String COLUMN_FOODWISHLIST_NAME = "FoodTruckName";
    public static final String COLUMN_FOODWISHLIST_PHOTO = "FoodTruckPhoto";


    // User table and its columns
    public static final String TABLE_USER = "User";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_PHONE_NUMBER = "PhoneNumber";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";


    // Reservation table and its columns
    public static final String TABLE_RESERVATION = "Reservation";
    public static final String COLUMN_RESERVEDID = "rentid";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_TRUCK_ID = "TruckId"; // referring to the FoodTruck Id in FOODTRUCK table to know which food truck is reserved
    public static final String COLUMN_USERID = "UID"; // referring to the User Id in User table to know this reservation for who


    public DBHelper(@Nullable Context context) {
        super(context, "Mutraf.db", null, 1);
    }


    // Create tables and insert Food trucks into the food trucks table
    @Override
    public void onCreate(SQLiteDatabase DB) {
        // To create Users table
        String createUsersTableQuery = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PHONE_NUMBER + " INTEGER,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_NAME + " TEXT"
                + ")";
        DB.execSQL(createUsersTableQuery);

        // To create FoodTruck table
        String createFoodTruckTableQuery = "CREATE TABLE " + TABLE_FOODTRUCK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOODTRUCK_NAME + " TEXT,"
                + COLUMN_FOODTRUCK_CATEGORY + " TEXT,"
                + COLUMN_FOODTRUCK_DESCRIPTION + " TEXT,"
                //+ COLUMN_FOODTRUCK_PHOTO + " BLOB,"
                +COLUMN_FOODTRUCK_PRICE +" FLOAT,"
                + COLUMN_CONTACT_PHONE + " INTEGER"
                + ")";
        DB.execSQL(createFoodTruckTableQuery);

        ContentValues values = new ContentValues(); // to store column-value pairs for database operations
        // First Food Truck

        values.put(COLUMN_FOODTRUCK_NAME, "churros house");
        values.put(COLUMN_FOODTRUCK_CATEGORY, "Coffee");
        values.put(COLUMN_FOODTRUCK_DESCRIPTION, "Premium churros, handcrafted and made to order, Vegan friendly");
        //Get the photo path
        //String photoFilePath = "res/drawable/logo1.png";
        // Convert the photo to a array of bytes
        //byte[] photoData = getPhotoData(photoFilePath);
        //values.put(COLUMN_FOODTRUCK_PHOTO, photoData);
        values.put(COLUMN_FOODTRUCK_PRICE, 1000);
        values.put(COLUMN_CONTACT_PHONE, 530826614);
        DB.insert(TABLE_FOODTRUCK, null, values);

        // Second Food Truck
        values.clear();
        values.put(COLUMN_FOODTRUCK_NAME, "Dominos");
        values.put(COLUMN_FOODTRUCK_CATEGORY, "Pizza");
        values.put(COLUMN_FOODTRUCK_DESCRIPTION, "The largest pizza company in the world!");
        //Get the photo path
        //String photoFilePath2 = "res/drawable/dominos.jpg";
        // Convert the photo to a array of bytes
        //byte[] photoData2 = getPhotoData(photoFilePath2);
        //values.put(COLUMN_FOODTRUCK_PHOTO, photoData2);
        values.put(COLUMN_FOODTRUCK_PRICE, 1350);
        values.put(COLUMN_CONTACT_PHONE, 506533752);
        DB.insert(TABLE_FOODTRUCK, null, values);

        // Third Food Truck
        values.clear();
        values.put(COLUMN_FOODTRUCK_NAME, "Shawermer");
        values.put(COLUMN_FOODTRUCK_CATEGORY, "Sandwich");
        values.put(COLUMN_FOODTRUCK_DESCRIPTION, "Middle Eastern quick-service restaurant chain specializing in Shawarma");
        //Get the photo path
        //String photoFilePath3 = "res/drawable/shawermer.jpeg";
        // Convert the photo to a array of bytes
        //byte[] photoData3 = getPhotoData(photoFilePath3);
        //values.put(COLUMN_FOODTRUCK_PHOTO, photoData3);
        values.put(COLUMN_FOODTRUCK_PRICE, 2000);
        values.put(COLUMN_CONTACT_PHONE, 555419841);
        DB.insert(TABLE_FOODTRUCK, null, values);

        // Fourth Food Truck
        values.clear();
        values.put(COLUMN_FOODTRUCK_NAME, "Rose INC");
        values.put(COLUMN_FOODTRUCK_CATEGORY, "Coffee");
        values.put(COLUMN_FOODTRUCK_DESCRIPTION, "The package of ROSE INC offers: Classic Black Coffee, Creamy Cappuccino, Rich Mocha, Iced Coffee Delights, Seasonal Specials.");
        //Get the photo path
        //String photoFilePath4 = "res/drawable/roseinc.jpeg";
        // Convert the photo to a array of bytes
        //byte[] photoData4 = getPhotoData(photoFilePath4);
        //values.put(COLUMN_FOODTRUCK_PHOTO, photoData4);
        values.put(COLUMN_FOODTRUCK_PRICE, 2500);
        values.put(COLUMN_CONTACT_PHONE, 530984476);
        DB.insert(TABLE_FOODTRUCK, null, values);

        // Fifth Food Truck
        values.clear();
        values.put(COLUMN_FOODTRUCK_NAME, "maestroPizza");
        values.put(COLUMN_FOODTRUCK_CATEGORY, "Pizza");
        values.put(COLUMN_FOODTRUCK_DESCRIPTION, "Best Saudi resturant serving pizza!");
        //Get the photo path
        //String photoFilePath5 = "res/drawable/maestropizza.jpeg";
        // Convert the photo to a array of bytes
        //byte[] photoData5 = getPhotoData(photoFilePath5);
        //values.put(COLUMN_FOODTRUCK_PHOTO, photoData5);
        values.put(COLUMN_FOODTRUCK_PRICE, 990);
        values.put(COLUMN_CONTACT_PHONE, 552340975);
        DB.insert(TABLE_FOODTRUCK, null, values);

        // To create PriceContactimg table
        String createWishlistTableQuery = "CREATE TABLE " + TABLE_WISHLIST + "("
                + COLUMN_WISHLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOODTRUCK_ID + " INTEGER,"
                + COLUMN_FOODWISHLIST_NAME + " TEXT,"
                + COLUMN_FOODWISHLIST_PHOTO + " BLOB,"
                + "FOREIGN KEY(" + COLUMN_FOODTRUCK_ID + ") REFERENCES " + TABLE_FOODTRUCK + "(" + COLUMN_ID + ")"
                + ")";
        DB.execSQL(createWishlistTableQuery);

        // To create Reservation table
        String createReservationTableQuery = "CREATE TABLE " + TABLE_RESERVATION + "("
                + COLUMN_RESERVEDID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TIME + " TIME,"
                + COLUMN_DATE + " DATE,"
                + COLUMN_TRUCK_ID + " INTEGER,"
                + COLUMN_USERID + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_TRUCK_ID + ") REFERENCES " + TABLE_FOODTRUCK + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_USERID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_PHONE_NUMBER + ")"
                + ")";
        DB.execSQL(createReservationTableQuery);
    }

    //To handle necessary changes to the database structure
    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODTRUCK);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(DB);
    }

    // Add reservation for specific user
    public boolean addReservation(String time, String date, int foodTruckId, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TRUCK_ID, foodTruckId);
        values.put(COLUMN_USER_ID, userID);

        long result = db.insert(TABLE_RESERVATION, null, values);


        db.close();
        // Check if the query has been executed successfully
        return result != -1 ;
    }

    // Delete reservation for specific user
    public boolean deleteReservation(int reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_RESERVATION, COLUMN_RESERVEDID + "=?", new String[]{String.valueOf(reservationId)});

        db.close();
        // Check if the query has been executed successfully
        return rowsDeleted > 0 ;
    }

    // For sign up
    public boolean insertData(String phoneNumber, String password, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_PHONE_NUMBER + " = ?", new String[]{phoneNumber});
        int Exists = cursor.getCount();

        // Check if if the phone number is exist, then the user cannot signup
        if(Exists > 0){
            db.close();
            return false;
        }
        else {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PHONE_NUMBER, phoneNumber);
            values.put(COLUMN_PASSWORD, password);
            values.put(COLUMN_NAME, name);

            long result = db.insert(TABLE_USER, null, values);
            db.close();
            // Return true if the insertion was successful
            return result != -1;
        }

    }


    // When log in, to check matching password with phone number
    public boolean checkPassword(String phoneNumber, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_PHONE_NUMBER + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{phoneNumber, password});
        int Match = cursor.getCount();
        cursor.close();
        db.close();

        // Return true if it is matching correctly
        return (Match > 0) ;
    }

    // edit reservation
    public boolean editReservation(int reservationId, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);

        int rowsUpdated = db.update(TABLE_RESERVATION, values, COLUMN_RESERVEDID + "=?", new String[]{String.valueOf(reservationId)});
        db.close();
        // check if the updating was successful or not
        return rowsUpdated > 0;
    }

    // Add Food truck to wishlist table
    public boolean addToWishlist(int foodTruckId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // To get the foodtruck name and photo depends on the foodtruck id
        //Cursor cursor = db.query(TABLE_FOODTRUCK, new String[]{COLUMN_FOODTRUCK_NAME, COLUMN_FOODTRUCK_PHOTO}, COLUMN_ID + "=?", new String[]{String.valueOf(foodTruckId)}, null, null, null);
        Cursor cursor = db.query(TABLE_FOODTRUCK, new String[]{COLUMN_FOODTRUCK_NAME}, COLUMN_ID + "=?", new String[]{String.valueOf(foodTruckId)}, null, null, null);
        if (cursor.moveToFirst()) {
            String foodTruckName = cursor.getString(2);
            byte[] foodTruckPhoto = cursor.getBlob(3);
            ContentValues values = new ContentValues();
            values.put(COLUMN_FOODTRUCK_ID, foodTruckId);
            values.put(COLUMN_FOODWISHLIST_NAME, foodTruckName);
            values.put(COLUMN_FOODWISHLIST_PHOTO, foodTruckPhoto);

            long result = db.insert(TABLE_WISHLIST, null, values);
            db.close();
            // check if the insertion was successful or not
            return result != -1;
        } else {
            // Food truck not found in food truck table
            db.close();
            return false;
        }
    }

    // remove Food truck from the wishlist table
    public boolean removeFromWishlist(int foodTruckId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_WISHLIST, COLUMN_FOODTRUCK_ID + "=?", new String[]{String.valueOf(foodTruckId)});
        db.close();

        // Return true if the deletion was successful
        return rowsDeleted > 0;
    }

    // convert the photo to array byte to store it in the food truck table
    private byte[] getPhotoData(String photoFilePath) {
        byte[] photoData = null;
        // convert the photo into a Bitmap
        Bitmap photoBitmap = BitmapFactory.decodeFile(photoFilePath);
        // convert the Bitmap to a byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        photoData = stream.toByteArray();
        //return the photo as array of byte to store it in Mutraf database
        return photoData;
    }

    // Methods to retrieve the data from database
    // retrieve All foodtrucks
    public List<FoodTruckObject> getAllFoodTrucks() {
        List<FoodTruckObject> foodTrucks = new ArrayList<>();
        String queryString =  "SELECT * FROM " + TABLE_FOODTRUCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{ //index based on the foodtruck table
                int id = cursor.getInt(0); // FoodTruck id
                String name = cursor.getString(1); // FoodTruck name
                String category = cursor.getString(2); // FoodTruck category
                String description = cursor.getString(3); // FoodTruck description
                int phoneNumber = cursor.getInt(4); // FoodTruck phoneNumber
                float price = cursor.getFloat(5); // FoodTruck price
                byte[] photoBytes = cursor.getBlob(6); // FoodTruck photo as byte array
                Bitmap photo = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length); // FoodTruck photo
                FoodTruckObject foodTruck = new FoodTruckObject(id, name, category, description, phoneNumber, price, photo);
                foodTrucks.add(foodTruck);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodTrucks;
    }

    public Cursor getFoodTrucks() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FOODTRUCK , null);
        return cursor;
    }

    // retrieve All reservations
    public List<ReservationObject> getAllReservation(int userID) {
        List<ReservationObject> reservations = new ArrayList<>();
        SQLiteDatabase DB = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RESERVATION + " WHERE " + COLUMN_USERID + "=?";
        Cursor cursor = DB.rawQuery(query, new String[]{String.valueOf(userID)});
        if (cursor.moveToFirst()) {
            do { //index based on the reservation table
                int reservationId = cursor.getInt(0); // reservation Id
                String time = cursor.getString(1); // reservation time
                String date = cursor.getString(2); // reservation date
                int foodTruckId = cursor.getInt(3); // Food truck Id refers to the truck in the food truck table to know which truck is reserved
                ReservationObject reservation = new ReservationObject(reservationId, time, date, foodTruckId, userID);
                reservations.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reservations;
    }

    public Cursor getMYreservation(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RESERVATION +
                " INNER JOIN " + TABLE_FOODTRUCK +
                " ON " + TABLE_RESERVATION + "." +  COLUMN_TRUCK_ID +
                " = " + TABLE_FOODTRUCK + "." +  COLUMN_ID +
                " WHERE " + TABLE_RESERVATION + "." + COLUMN_USERID + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        return cursor;
    }

    // retrieve All foodtrucks in wishlist
    public List<WishlistObject> getAllFoodTrucksWishList() {
        List<WishlistObject> foodTrucksWishList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_WISHLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do { //index based on the reservation table
                int id = cursor.getInt(0); // id of foodtruck in wishlist table
                int foodTruckId = cursor.getInt(1); // id of foodtrucks refers to foodtruck table
                String foodTruckName = cursor.getString(2); // name of the food truck
                byte[] foodTruckPhoto = cursor.getBlob(3); // photo of the food truck
                WishlistObject foodTruck = new WishlistObject(id, foodTruckId, foodTruckName, foodTruckPhoto);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodTrucksWishList;
    }
    // retrieve user name to present it in the dashboard page
    public String GetUserName(int userID){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + "=?", new String[]{String.valueOf(userID)});
        String userName = "";
        if(cursor.moveToFirst()) {
            userName = cursor.getString(3);
        }
        cursor.close();
        return userName;
    }

    public int getUserIdFromName(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER +
                " WHERE " + COLUMN_PHONE_NUMBER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{userName});

        int userId = -1; // Default value if user ID is not found

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
        }

        cursor.close();
        return userId;
    }

    //Search my reservations
    public Cursor searchReservationByName(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_RESERVATION + " WHERE " + COLUMN_USERID + " LIKE '%" + query + "%'";
        return db.rawQuery(queryString, null);
    }


}