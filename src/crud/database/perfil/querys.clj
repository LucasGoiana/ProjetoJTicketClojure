(ns crud.database.perfil.querys
  (:use
    [crud.database.connection] [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query]
    ))
(defn now [] )
; Inserir um Perfil
(defn inserir [nome] (query/insert! ds :perfil {:Nome nome :dataModifcacao (java.util.Date.) }) )
;(query/query ds [])

; Buscar Lista de Perfis
(defn ler [request] (read-as-local) (query/query ds ["select * from perfil"]))
;
;
;(query/update! ds :fruit {:name "Apple" :appearance "rosy" :cost 24}  { :name "Orange"})
;
;(query/delete! ds :fruit { :cost 24})