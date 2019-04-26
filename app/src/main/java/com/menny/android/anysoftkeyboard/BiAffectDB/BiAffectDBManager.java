package com.menny.android.anysoftkeyboard.BiAffectDB;

import android.content.Context;

import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.AccelerometerData;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.DeviceData;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.KeyData;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.SessionData;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.TouchData;

public class BiAffectDBManager implements BiAffectDBInterface.TouchDataInterface, BiAffectDBInterface.SessionDataInterface, BiAffectDBInterface.KeyTypeDataInterface,BiAffectDBInterface.DeviceDataInterface, BiAffectDBInterface.AccelerometerDataInterface{
    /**
     * Created by Sreetama Banerjee on 4/22/2019.
     * reason : to allow all components of project to get appcontext
     */
    Context mcontext = AnyApplication.getAppContext();

    private BiAffectDB DBINSTANCE;

    private static BiAffectDBManager MngrInstance = null;

    public static synchronized BiAffectDBManager getInstance() {
            if (MngrInstance == null)
                MngrInstance = new BiAffectDBManager();
            return MngrInstance;
        }

    //can put static if need be
    private BiAffectDBManager() {
        DBINSTANCE=BiAffectDB.getDatabase(mcontext);

    }

    @Override
    public void insertTouchTypeData(long eventDowntime,long eventTime,int eventAction,float pressure,float x, float y,float major_Axis,float minor_axis){
        TouchData TouchDataObj =new TouchData();
        TouchDataObj.eventDownTime=eventDowntime;
        TouchDataObj.eventAction=eventAction;
        TouchDataObj.pressure=pressure;
        TouchDataObj.x_cord=x;
        TouchDataObj.y_cord=y;
        TouchDataObj.major_axis=major_Axis;
        TouchDataObj.major_axis=minor_axis;

        DBINSTANCE.TouchDataDao().insertOnlySingleTouchMetrics(TouchDataObj);
    }
//    @Override
//    public void insertTouchTypeEntryBatch(TouchData[] multi_entry){
//        DBINSTANCE.TouchDataDao().insertMultipleTouchMetrics(multi_entry);
//    }

    @Override
    public TouchData[] fetchTouchDataRows(long keyId,int motioneventtype){
        return DBINSTANCE.TouchDataDao().fetchTouchData(keyId,motioneventtype);
    }
    @Override
    public  void insertSessionStartTime (long startTime){
        SessionData sessionDataObj =new SessionData();
       // DBINSTANCE.SessionDataDao().insertSessionStartTime(time);
    }

    @Override
    public void updateSessionEndTime(long startTime,long endTime){
        //DBINSTANCE.SessionDataDao().insertSessionStartTime(data);
    }

    @Override
    public void insertKeyTypeData (long keyId,int keytypecode,float x,float y, float width, float height){
        //DBINSTANCE.KeyDataDAO().insertOnlySingleKeyMetrics(single_entry);
    }

//    @Override
//    public void insertMultipleKeyMetrics (KeyData[] keyDataList){
//        DBINSTANCE.KeyDataDAO().insertMultipleKeyMetrics(keyDataList);
//    }

    @Override
    public int updateRadiusofTouch(KeyData data){
        return DBINSTANCE.KeyDataDAO().updateRadiusofTouch(data);
    }

    @Override
    public void insertDeviceData (DeviceData single_entry){
        DBINSTANCE.DeviceDataDAO().insertDeviceData(single_entry);
    }

    @Override
    public int fetchDeviceId(){
        return DBINSTANCE.DeviceDataDAO().fetchDeviceId();
    }

    @Override
    public void insertOnlySingleAccelerometerEntry (AccelerometerData single_entry){
        DBINSTANCE.AccelDataDAO().insertOnlySingleAccelerometerEntry(single_entry);
    }

    @Override
    public void insertMultipleAccelerometerEntry (AccelerometerData[] DataList){
        DBINSTANCE.AccelDataDAO().insertMultipleAccelerometerEntry(DataList);
    }
}
