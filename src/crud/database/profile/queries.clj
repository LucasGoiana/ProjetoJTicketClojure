(ns crud.database.profile.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]
    ))

; Inserir um Profile
(defn make [name] (query/insert! ds :profile {:name name}))

; Buscar Lista de Profiles
(defn readAll [request] (read-as-local) (query/query ds ["select idProfile, name, DATE_FORMAT(modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate from profile"]))

; Buscar um Profile pelo id
(defn readById [id]  (jdbc/execute-one! ds ["select idProfile, name, DATE_FORMAT(modifiedDate, \"%Y-%m-%d %h:%s:%i\") modifiedDate from profile where idProfile = ?" id]))
