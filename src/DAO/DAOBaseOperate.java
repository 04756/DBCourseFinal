package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAOBaseOperate <T>{

    /**
     * 插入新值
     * @param o 插入的数据
     * @return  如果插入成功，返回 {@code true};反之返回{@code false}
     * @throws SQLException
     */
    public Boolean insert(T o) throws SQLException;

    /**
     * 删除某条记录
     * @param o 删除的数据
     * @return 如果删除成功，返回 {@code true};反之返回{@code false}
     * @throws SQLException
     */
    public Boolean delete(T o) throws SQLException;

    /**
     * 更新某条记录
     * @param o 更新后的数据
     * @return 如果更新成功，返回 {@code true};反之返回{@code false}
     * @throws SQLException
     */
    public Boolean update(T o) throws SQLException;

    /**
     * 获取符合某要求的数据集合
     * @param o 获取记录的主键数据
     * @return 如果获取成功，返回数据集合;反之返回{@code NULL}
     * @throws SQLException
     */
    public T get(T o) throws SQLException;

}
