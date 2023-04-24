package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao.PlaceDao;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao.ProfileDao;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao.UserDataDao;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.UserDataEntity;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;

@Database(entities = {PlaceEntity.class, ProfileEntity.class, UserDataEntity.class}, version = 6)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PlaceDao placeDao();

    public abstract ProfileDao profileDao();

    public abstract UserDataDao userDataDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {

                    INSTANCE = buildDatabase(context);

//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                                    AppDataBase.class, "app_database")
//                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public static AppDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "app_database")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        databaseWriteExecutor.execute(() -> {
                            synchronized (AppDataBase.class) {
                                for (int i = 0; i < 10; i++) {
                                    getDataBase(context).profileDao().addProfile(new ProfileEntity("profile_" + String.valueOf(i), 1, i));
                                    getDataBase(context).placeDao().addPlace(new PlaceEntity("default_" + String.valueOf(i), 1, i));
                                }
                                getDataBase(context).userDataDao().addProfile(new UserDataEntity());
                            }
                        });
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        databaseWriteExecutor.execute(()->{
                            synchronized (AppDataBase.class){
                                getDataBase(context).userDataDao().addProfile(new UserDataEntity());
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration() // Очищает базу данных при изменении. Заменить на миграцию, если будет необходимость.
                .build();

    }
}

//    private static RoomDatabase.Callback creatingFillDataBase = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            for (int i = 1; i < 15; i++){
//                synchronized (AppDataBase.class) {
//                    if (INSTANCE == null) {
//                        INSTANCE.profileDao().addProfile(new ProfileEntity("profile_" + String.valueOf(i), 1, i));
//                        INSTANCE.placeDao().addPlace(new PlaceEntity("default_" + String.valueOf(i), 1, i));
//                    }
//                }
//
//            }
//        }
//    };

//            Room.databaseBuilder(context.applicationContext,
//    DataDatabase::class.java, "Sample.db")
//            // prepopulate the database after onCreate was called
//            .addCallback(object : Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            // insert the data on the IO Thread
//            ioThread {
//                getInstance(context).dataDao().insertData(PREPOPULATE_DATA)
//            }
//        }
//    })
//            .build()

//    private void RoomDatabase.Callback builDataBase = Room.databaseBuilder();

