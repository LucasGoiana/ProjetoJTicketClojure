(ns crud.connection.connection
  (:require [next.jdbc :as jdbc],[config.core :refer [env]])
  (:gen-class))

(def db-type (:db-type  env))
(def db-name (:db-name  env))
(def db-user (:db-user  env))
(def db-password (:db-password  env))

(def db {:dbtype db-type
         :dbname db-name
         :user db-user
         :password db-password})

(def ds (jdbc/get-datasource db))
