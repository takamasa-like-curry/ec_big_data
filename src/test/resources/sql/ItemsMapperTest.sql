--itemsテーブル作成
drop table if exists items;
create table items (
    id serial primary KEY
    ,item_id integer not null 
    ,name character varying(255) not null
    ,condition integer not null
    ,category_id integer not null
    ,brand character varying(255) 
    ,price numeric not null
    ,shipping integer not null
    ,description text not null 
    ,registered_date_time timestamp not null
    ,registered_name text not null
    ,updated_date_time timestamp not null
    ,updated_name text not null
);

--テストデータ挿入
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,'item1',1,3,'test company',10,1,'item1 is good','2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,'item2',2,4,'test company',20,1,'item2 is good','2000-01-02 12:00:00','tester','2000-01-02 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (3,'item3',3,5,'test company',30,1,'item3 is good','2000-01-03 12:00:00','tester','2000-01-03 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (4,'item4',1,6,'test company',40,1,'item4 is good','2000-01-04 12:00:00','tester','2000-01-04 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (5,'item5',2,7,'test company',50,1,'item5 is good','2000-01-05 12:00:00','tester','2000-01-05 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (6,'item6',3,8,'test company',60,1,'item6 is good','2000-01-06 12:00:00','tester','2000-01-06 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (7,'item7',1,9,'test company',70,1,'item7 is good','2000-01-07 12:00:00','tester','2000-01-07 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (8,'item8',2,10,'test company',80,1,'item8 is good','2000-01-08 12:00:00','tester','2000-01-08 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (9,'item9',3,11,'test company',90,1,'item9 is good','2000-01-09 12:00:00','tester','2000-01-09 12:00:00','tester');
INSERT INTO items (item_id,name,condition,category_id,brand,price,shipping,description,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (10,'item10',1,12,'test company',100,1,'item10 is good','2000-01-10 12:00:00','tester','2000-01-10 12:00:00','tester');


--category_tree_pathsテーブル作成
drop table if exists category_tree_paths cascade;
create table category_tree_paths (
    ancestor_id integer not null
    ,descendant_id integer not null 
    ,registered_date_time timestamp not null
    ,registered_name text not null
    ,updated_date_time timestamp not null
    ,updated_name text not null
);
--テストデータ挿入
--テストデータ挿入
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,1,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,2,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,2,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,3,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,3,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (3,3,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,4,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,4,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (4,4,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,5,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,5,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (5,5,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,6,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,6,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (6,6,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,7,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,7,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (7,7,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,8,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,8,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (8,8,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,9,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,9,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (9,9,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,10,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,10,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (10,10,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,11,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,11,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (11,11,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (1,12,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (2,12,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');
INSERT INTO category_tree_paths (ancestor_id,descendant_id,registered_date_time,registered_name,updated_date_time,updated_name)
VALUES (12,12,'2000-01-01 12:00:00','tester','2000-01-01 12:00:00','tester');

