package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class CreateDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.md.bean");
        schema.setDefaultJavaPackageDao("com.md.dao");

        addCacheList(schema);

        new DaoGenerator().generateAll(schema, "E:\\Android-Blog-Source\\zhihu\\app\\src\\main\\java-gen");
    }

    private static void addCacheList(Schema schema) {
        Entity cache = schema.addEntity("CacheBean");
        cache.addStringProperty("url").primaryKey().notNull();
        cache.addStringProperty("json").notNull();
    }
}
