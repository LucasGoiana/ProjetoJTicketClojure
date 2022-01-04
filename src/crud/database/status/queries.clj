(ns crud.database.status.queries
  (:use
   [crud.database.connection]
   [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query]
    ))

; Inserir um Perfil
(defn make [nome] (query/insert! ds :status {:Nome nome :dataModificacao (java.util.Date.)}))

; Buscar Lista de Perfis
(defn readAll [request] (read-as-local) (query/query ds ["select * from status"]))

; Buscar um perfil pelo id
(defn readById [id]  (query/get-by-id ds :status id :idStatus {}))
