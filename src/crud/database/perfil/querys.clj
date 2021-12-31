(ns crud.database.perfil.querys
  (:use
    [crud.database.connection]),
  (:require
    [next.jdbc.sql :as query]))

; Inserir um Perfil
(defn inserir [nome] (query/insert! ds :perfil {:Nome nome}) )
;(query/query ds [])

; Buscar Lista de Perfis
(defn ler [request] (query/query ds ["select * from perfil"]))
;
;
;(query/update! ds :fruit {:name "Apple" :appearance "rosy" :cost 24}  { :name "Orange"})
;
;(query/delete! ds :fruit { :cost 24})