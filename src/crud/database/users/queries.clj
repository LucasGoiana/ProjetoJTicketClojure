(ns crud.database.users.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query]))

; inserir usuario
(defn inserir [id-profile name email password slug] (query/insert! ds :usuario
              {:idPerfil id-profile :Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.)}))

;atualizar usuario pelo id
(defn atualizar [id name email password slug] (query/update! ds :usuario
              {:Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.)}    {:idUsuario id } ))

(defn ler [request] (read-as-local) (query/query ds ["select * from usuario"]))