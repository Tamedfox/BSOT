package com.cf.bsot.common.utils;

import com.cf.bsot.model.dto.NavMenuDTO;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.ResolverUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author cf
 * @version 1.0
 * @date 2021/1/29 21:34
 */
public class TreeUtils {

    /**
     * 默认转换为树结构
     *
     * @param collection
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, @NotNull Class<T> clazz) {
        return toTree(collection, null, null, null, clazz);
    }

    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, String id, String parentId, String children, @NotNull Class<T> clazz) {
        try {
            if (collection == null || collection.isEmpty()) {
                return null;
            }
            if (StringUtils.isEmpty(id)) {
                id = "id";
            }
            if (StringUtils.isEmpty(parentId)) {
                parentId = "parentId";
            }
            if (StringUtils.isEmpty(children)) {
                children = "children";
            }

            //初始化根节点集合
            Collection<T> roots;
            if (collection.getClass().isAssignableFrom(Set.class)) {
                roots = new HashSet<>();
            } else {
                roots = new ArrayList<>();
            }

            //获取id字段，从当前对象或其父类
            Field idField;
            try {
                idField = clazz.getDeclaredField(id);
            } catch (NoSuchFieldException e) {
                idField = clazz.getSuperclass().getDeclaredField(id);
            }

            //获取parentId字段，从当前对象或其父类
            Field parentField;
            try {
                parentField = clazz.getDeclaredField(parentId);
            } catch (NoSuchFieldException e) {
                parentField = clazz.getSuperclass().getDeclaredField(parentId);
            }

            //获取 children 字段，从当前或其父类
            Field chidrenField;
            try {
                chidrenField = clazz.getDeclaredField(children);
            } catch (NoSuchFieldException e) {
                chidrenField = clazz.getSuperclass().getDeclaredField(children);
            }

            //设置为可访问
            idField.setAccessible(true);
            parentField.setAccessible(true);
            chidrenField.setAccessible(true);

            //查找所有根节点
            for (T c : collection) {
                Object cParentId = parentField.get(c);
                if (isRootNode(cParentId)) {
                    roots.add(c);
                }
            }

            //从目标集合移除所有根节点
            collection.removeAll(roots);

            // 遍历根节点，依次添加子节点
            for (T root : roots) {
                addChild(root, collection, idField, parentField, chidrenField);
            }

            //关闭可访问
            idField.setAccessible(false);
            parentField.setAccessible(false);
            chidrenField.setAccessible(false);

            return roots;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 为目标节点添加孩子节点
     *
     * @param node         目标节点
     * @param collection   目标集合
     * @param idField      ID字段
     * @param parentField  父节点ID字段
     * @param childrenField 子节点字段
     * @param <T>
     */
    private static <T> void addChild(T node, Collection<T> collection, Field idField, Field parentField, Field childrenField) throws IllegalAccessException {
        Object id = idField.get(node);
        Collection<T> children = (Collection<T>) childrenField.get(node);
        // 如果子节点的集合为null，初始化孩子集合
        if (children == null) {
            if (collection.getClass().isAssignableFrom(Set.class)){
                children = new HashSet<>();
            } else {
                children = new ArrayList<>();
            }
        }

        for (T t : collection) {
            Object tParentId = parentField.get(t);
            if(id.equals(tParentId)){
                //将当前节点添加到目标节点的孩子节点
                children.add(t);
                childrenField.set(node,children);
                //递归增加孩子节点
                addChild(t, collection, idField, parentField, childrenField);
            }
        }
    }

    /**
     * 判断是否是根节点, 判断方式为: 父节点编号为空或为 0, 则认为是根节点.
     *
     * @param parentId 父节点编号
     * @return 判断是否为根节点
     */
    private static boolean isRootNode(Object parentId) {
        boolean flag = false;
        if (parentId == null) {
            flag = true;
        } else if (parentId instanceof String && (StringUtils.isEmpty((String) parentId) || "0".equals(parentId))) {
            flag = true;
        } else if (parentId instanceof Integer && Integer.valueOf(0).equals(parentId)) {
            flag = true;
        } else if (parentId instanceof Long && Long.valueOf("0").equals(parentId)){
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        ArrayList<TestMenu> menuList = new ArrayList<>();
        menuList.add(new TestMenu(1L, 0L, "节点1"));
        menuList.add(new TestMenu(2L, 0L, "节点2"));
        menuList.add(new TestMenu(3L, 1L, "节点1.1"));
        menuList.add(new TestMenu(4L, 1L, "节点1.2"));
        menuList.add(new TestMenu(5L, 3L, "节点1.1.1"));
        Collection<TestMenu> testMenus = TreeUtils.toTree(menuList, "id", "parentId", "children", TestMenu.class);
        System.out.println(testMenus.toString());
    }

}

class TestMenu{
    private Long id;
    private Long parentId;
    private String name;
    private List<TestMenu>  children;

    public TestMenu(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TestMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TestMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TestMenu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
