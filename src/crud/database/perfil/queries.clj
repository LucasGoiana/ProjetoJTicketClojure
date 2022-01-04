(ns crud.database.perfil.queries
  (:use
    [crud.database.connection] [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query]
    ))

; Inserir um Perfil
(defn inserir [nome] (query/insert! ds :perfil {:Nome nome :dataModifcacao (java.util.Date.)}))

; Buscar Lista de Perfis
(defn ler [request] (read-as-local) (query/query ds ["select * from perfil"]))

; Buscar um perfil pelo id
(defn lerPorId [id]  (query/get-by-id ds :perfil  id :idPerfil {}))
