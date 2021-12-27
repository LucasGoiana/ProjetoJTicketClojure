(ns crud.core
  (:require  [next.jdbc :as jdbc],
             [next.jdbc.sql :as query]))

(def db {:dbtype "mysql"
         :dbname "crud"
         :user "lucas"
         :password "123456"
        })

(def ds (jdbc/get-datasource db))
;
;(jdbc/execute! ds ["
;create table fruit (
;  id int auto_increment primary key,
;  name varchar(32),
;  appearance varchar(255),
;   cost varchar(255)
;)"])

(query/insert! ds :fruit {:name "Watermelon" :appearance "rosy" :cost 32})

(query/update! ds :fruit {:name "Apple" :appearance "rosy" :cost 24}  { :name "Orange"})

(query/delete! ds :fruit { :cost 24})