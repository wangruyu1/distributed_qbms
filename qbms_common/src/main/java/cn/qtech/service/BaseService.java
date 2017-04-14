package cn.qtech.service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/23-16:54
 */
public interface BaseService<T> {
    public List<T> queryAll();

    public List<T> queryByUser(String userId);

    public boolean insert(T t);

    public boolean delete(String id);

    public boolean update(T t);
}
