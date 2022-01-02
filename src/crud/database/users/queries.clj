(ns crud.database.users.queries
  (:use
    [crud.database.connection]),
  (:require
    [next.jdbc.sql :as query]))


(defn inserir [id-profile name email password slug] (query/insert! ds :usuario
              {:idPerfil id-profile :Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.) }) )