package com.example.apk2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 2, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {

    public abstract PhoneDao elementDao();

    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PhoneRoomDatabase.class,
                                    "phone-database")
                            .addCallback(roomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // insert sample data into database during creation
            databaseWriteExecutor.execute(() -> {
                PhoneDao dao = INSTANCE.elementDao();

                Phone phone1 = new Phone("LG", "G7", "10.0", "example.com");
                Phone phone2 = new Phone("Samsung", "A21", "11.0", "example.com");

                dao.insert(phone1);
                dao.insert(phone2);
            });
        }

//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//            // insert sample data into database during creation
//            databaseWriteExecutor.execute(() -> {
//                PhoneDao dao = INSTANCE.elementDao();
//
//                Phone phone1 = new Phone("LG", "G7", "10.0", "example.com");
//                Phone phone2 = new Phone("Samsung", "A21", "11.0", "example.com");
//
//                dao.insert(phone1);
//                dao.insert(phone2);
//            });
//        }
    };
}