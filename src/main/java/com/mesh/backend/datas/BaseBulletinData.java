package com.mesh.backend.datas;

import com.mesh.backend.entity.Bulletins;

/**
 * bulletin返回值
 *
 * @author xuedixuedi
 */
public class BaseBulletinData extends BaseData {
    public BulletinData bulletin;

    public BaseBulletinData(Bulletins bulletins) {
        super(true, "");
        bulletin = new BulletinData(bulletins);
    }

}
