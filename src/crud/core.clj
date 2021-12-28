(ns crud.core
  (:use

    [crud.connection.connection]),
  (:require
    [next.jdbc.sql :as query]))


(query/insert! ds :fruit {:name "Watermelon" :appearance "rosy" :cost 32})

(query/update! ds :fruit {:name "Apple" :appearance "rosy" :cost 24}  { :name "Orange"})

(query/delete! ds :fruit { :cost 24})