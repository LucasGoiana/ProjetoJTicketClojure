(ns crud.database.status.queries
  (:use
   [crud.database.connection]
   [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]
    ))

; Inserir um Perfil
(defn make [name] (query/insert! ds :status {:name name}))

; Buscar Lista de Perfis
(defn readAll [request] (read-as-local) (query/query ds ["select idStatus, name, DATE_FORMAT(modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate from status"]))

; Buscar um perfil pelo id
(defn readById [id]  (jdbc/execute-one! ds ["select idStatus, name, DATE_FORMAT(modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate from status where idStatus = ?" id]))
