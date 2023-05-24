package com.yuanlrc.campus_market.service.admin;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.admin.DatabaseBakDao;
import com.yuanlrc.campus_market.entity.admin.DatabaseBak;
import com.yuanlrc.campus_market.util.PathUtil;
import com.yuanlrc.campus_market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 数据库备份service
 */

@Service
public class DatabaseBakService {

    @Autowired
    private OperaterLogService operaterLogService;
    @Autowired
    private DatabaseBakDao databaseBakDao;
    //@Value("${ylrc.database.backup.dir}")
    private final String backUpDir = PathUtil.newInstance().getBackUpDir();
    @Value("${ylrc.database.backup.username}")
    private String dbUsername;
    @Value("${ylrc.database.backup.password}")
    private String dbPwd;
    @Value("${ylrc.database.backup.database.name}")
    private String dbName;

    private Logger log = LoggerFactory.getLogger(DatabaseBakService.class);

    /**
     * 分页查找数据库备份记录
     *
     * @param pageBean
     * @return
     */
    public PageBean<DatabaseBak> findList(PageBean<DatabaseBak> pageBean) {
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize());
        Page<DatabaseBak> findAll = databaseBakDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 添加或修改数据库备份记录
     *
     * @param databaseBak
     * @return
     */
    public DatabaseBak save(DatabaseBak databaseBak) {
        return databaseBakDao.save(databaseBak);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public DatabaseBak find(Long id) {
        return databaseBakDao.find(id);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void delete(Long id) {
        databaseBakDao.deleteById(id);
    }

    /**
     * 备份数据库
     */
    public void backup() {
        File path = new File(backUpDir);
        if (!path.exists()) {
            path.mkdir();
        }
        try {
            String filename = dbName + "_" + StringUtil.getFormatterDate(new Date(), "yyyyMMddHHmmss") + ".sql";
            String cmd = "mysqldump -u" + dbUsername + " -p" + dbPwd + " " + dbName + " -r " + backUpDir + filename;
            Runtime.getRuntime().exec(cmd);
            DatabaseBak databaseBak = new DatabaseBak();
            databaseBak.setFilename(filename);
            databaseBak.setFilepath(backUpDir);
            save(databaseBak);
            log.info("数据库备份成功");
            operaterLogService.add("数据库成功备份，备份文件信息：" + databaseBak);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 还原数据库
     *
     * @param id
     */
    public void restore(Long id) {
        DatabaseBak databaseBak = find(id);
        if (databaseBak != null) {
            try {
                String filename = databaseBak.getFilename();
                File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
                String cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + backUpDir + filename;
                ;
                if (!file.exists()) {
                    cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + databaseBak.getFilepath() + databaseBak.getFilename();
                }
                String stmt1 = "mysqladmin -u " + dbUsername + " -p" + dbPwd + " create " + dbName;
                String[] cmds = {"cmd", "/c", cmd};
                Runtime.getRuntime().exec(stmt1);
                Process exec = Runtime.getRuntime().exec(cmds);
                log.info(StringUtil.getStringFromInputStream(exec.getErrorStream()));
                log.info("数据库还原成功");
                operaterLogService.add("数据库成功还原，还原文件信息：" + databaseBak);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 备份总数
     *
     * @return
     */
    public long total() {
        return databaseBakDao.count();
    }
}
