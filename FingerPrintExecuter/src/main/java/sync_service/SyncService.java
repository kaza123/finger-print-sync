/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync_service;

import controller.MsSQLController;
import controller.MySQLController;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.TFingerPrint;
import org.apache.log4j.Logger;

/**
 *
 * @author 'Kasun Chamara'
 */
public class SyncService {

    private ScheduledExecutorService scheduledExecutorService;

    private final MySQLController mySQLController;
    private final MsSQLController msSQLController;

    private static SyncService instance;
    private static final Logger LOGGER = Logger.getLogger(SyncService.class);

    public static SyncService getInstance() throws SQLException {
        if (instance == null) {
            instance = new SyncService();
        }

        return instance;
    }

    private SyncService() throws SQLException {
        this.mySQLController = new MySQLController();
        this.msSQLController = new MsSQLController();
    }

    public void start() throws ConcurrentModificationException {
        if (scheduledExecutorService != null) {
            throw new ConcurrentModificationException("Sync process already running");
        }

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(getSyncRunnable(), 0, 5, TimeUnit.SECONDS);
        LOGGER.info("Synchronization service started successfully");
    }

    public void stop() throws ConcurrentModificationException {
        if (scheduledExecutorService == null) {
            throw new ConcurrentModificationException("Sync process not started");
        }

        scheduledExecutorService.shutdown();
        scheduledExecutorService = null;
        LOGGER.info("Synchronization service stopped successfully");
    }

    private Runnable getSyncRunnable() {
        Runnable syncRunnable = () -> {
            try {
                LOGGER.info("Start synchronization");
                Integer maxMysql = mySQLController.getMaxNo();

                ArrayList<TFingerPrint> fingerPrintData = msSQLController.getFingerPrintList(maxMysql);

                if (fingerPrintData.size() > 0) {
                    Integer saveIndex = mySQLController.saveFingerPrint(fingerPrintData);
                    if (saveIndex > 0) {
                        String dateTime = new SimpleDateFormat("yyyy-MM-dd  hh-mm-ss a").format(new Date());
                        LOGGER.info(fingerPrintData.size() + "  Data Synchronized success @ " + dateTime);

                    }
                } else {
                    LOGGER.info("Synchronization Empty");
                }
            } catch (SQLException ex) {
                LOGGER.fatal("Synchronization failed", ex);
            }
        };
        return syncRunnable;
    }
}
