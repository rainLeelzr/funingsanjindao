package com.funing.commonfn.manager;

import com.funing.commonfn.manager.operate.CanDoOperate;
import com.funing.commonfn.manager.picker.PersonalCardInfoPicker;
import com.funing.commonfn.manager.scanTask.BaseScanTask;
import com.funing.commonfn.model.User;
import com.funing.commonfn.model.mahjong.Mahjong;
import com.funing.commonfn.model.mahjong.MahjongGameData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 操作扫描管理器，
 * * 依次扫描scanTasks中的具体任务，得出所有玩家可以有的操作列表
 */
public abstract class AbstractManager {

    protected static final Logger log = LoggerFactory.getLogger(AbstractManager.class);

    /**
     * 已注册的扫描任务
     */
    protected List<Class<? extends BaseScanTask>> scanTasks;

    protected PersonalCardInfoPicker personalCardInfoPicker;

    protected abstract void setPersonalCardInfoPicker();

    public ArrayList<CanDoOperate> scan(MahjongGameData mahjongGameData, Mahjong putOutMahjong, User user) throws
            IllegalAccessException,
            InstantiationException {
        ArrayList<CanDoOperate> canDoOperates = new ArrayList<>();
        for (Class<? extends BaseScanTask> scanTask : scanTasks) {
            BaseScanTask task = scanTask.newInstance();
            task.setMahjongGameData(mahjongGameData);
            task.setSpecifiedMahjong(putOutMahjong);
            task.setUser(user);
            task.setCanOperates(canDoOperates);
            task.setPersonalCardInfoPicker(personalCardInfoPicker);
            task.scan();
        }
        Iterator<CanDoOperate> it = canDoOperates.iterator();
        while (it.hasNext()) {
            CanDoOperate next = it.next();
            if (next.getOperates().size() == 0) {
                it.remove();
            }
        }
        Collections.sort(canDoOperates);
        log.debug("最终扫描结果：{}", canDoOperates);
        return canDoOperates;
    }


}
